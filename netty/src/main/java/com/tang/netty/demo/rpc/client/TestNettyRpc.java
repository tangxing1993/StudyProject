package com.tang.netty.demo.rpc.client;

import com.tang.netty.demo.rpc.clientStub.NettyProxy;

public class TestNettyRpc {

	public static void main(String[] args) {
		// 测试第一个远程调用
		HelloNetty helloNetty = (HelloNetty) NettyProxy.createProxy(HelloNetty.class);
		helloNetty.hello();
	}
	
}
