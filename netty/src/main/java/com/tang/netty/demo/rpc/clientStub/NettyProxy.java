package com.tang.netty.demo.rpc.clientStub;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.tang.netty.demo.rpc.serverStub.ClassInfo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *    动态代理
 * @author tangxing
 *
 */
public class NettyProxy {

	public static Object createProxy(Class<?> targetClass) {
		return Proxy.newProxyInstance(targetClass.getClassLoader(),
                new Class<?>[] { targetClass },
                new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// 封装类的信息
						ClassInfo info = new ClassInfo();
						info.setClassName(targetClass.getName());
						info.setMethodName(method.getName());
						info.setObjects(args);
						info.setTypes(method.getParameterTypes());
						// 发送信息到服务端
						Bootstrap bootstrap = new Bootstrap();
						NioEventLoopGroup group = new NioEventLoopGroup();
						bootstrap.group(group)
								 .channel(NioSocketChannel.class)
								 .handler(new ChannelInitializer<Channel>() {
									@Override
									protected void initChannel(Channel ch) throws Exception {
										// TODO 添加自定义的处理器
										ch.pipeline().addLast("encoder", new ObjectEncoder());
										ch.pipeline().addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
										ch.pipeline().addLast(new NettyClientHandler());
									}
								});
						ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
						future.channel().writeAndFlush(info).sync();
						NettyClientHandler clientHandler = future.channel().pipeline().get(NettyClientHandler.class);
						future.channel().closeFuture().sync();
						return clientHandler.getResponse();
					}
				});
	}
	
}
