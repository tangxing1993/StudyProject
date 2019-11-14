package com.tang.netty.demo.rpc.serverStub;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 *     服务端处理数据
 * @author tangxing
 */
public class InvokerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress().toString().substring(1) + "接入！");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress().toString().substring(1) + "退出！");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 解析类对象信息  	 
		ClassInfo classInfo = (ClassInfo)msg;
		String className = classInfo.getClassName().substring(classInfo.getClassName().lastIndexOf("."));
		// 扫描指定包下的文件，并根据类名查找类的实现
		System.out.println(classInfo + ":" + className);  
		String packageInterface = "com.tang.netty.demo.rpc.server" + className;
		Class<?> clazz = ReflectionUtils.forName(packageInterface, this.getClass().getClassLoader());
		@SuppressWarnings("rawtypes")
		Optional<Class> clazzOpt = Optional.ofNullable(clazz);
		// 调用方法
		if(clazzOpt.isPresent()) {
			String pack = clazz.getPackage().getName();
			Reflections reflections = new Reflections(pack);
			@SuppressWarnings("unchecked")
			Set<Class<?>> sets = reflections.getSubTypesOf(clazzOpt.get());
			if(sets.size() == 0) {
				System.out.println("未找到接口的实现...");
			}else if(sets.size() > 1) {
				System.out.println("找到多个接口的实现 ");
			}else {
				sets.forEach(curClazz -> {
					try {
						Object newInstance = curClazz.newInstance();
						@SuppressWarnings("unchecked")
						Method method =  clazzOpt.get().getMethod(classInfo.getMethodName(), classInfo.getTypes());
						Object invoke = method.invoke(newInstance, classInfo.getObjects());
						// 向通道内写入返回值
						ctx.writeAndFlush(invoke);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				});
			}
		}else {
			throw new Exception("未找到指定的接口类...");
		}
	}
	

}
