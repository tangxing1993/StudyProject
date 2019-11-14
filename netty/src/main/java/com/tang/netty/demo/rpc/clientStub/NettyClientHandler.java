package com.tang.netty.demo.rpc.clientStub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;
/**
 * @author tangxing
 * a 客户端的业务处理
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	@Getter
	private Object response;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		response = msg;
	}


	
	
}
