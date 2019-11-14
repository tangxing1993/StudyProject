package com.tang.netty.demo.rpc.server;

public class HelloNettyImpl implements HelloNetty {

	@Override
	public void hello() {
		System.out.println("Hello Netty!");
	}

}
