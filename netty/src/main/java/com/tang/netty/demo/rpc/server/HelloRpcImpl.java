package com.tang.netty.demo.rpc.server;

public class HelloRpcImpl implements HelloRpc {

	@Override
	public void hello(String name) {
		System.out.println("Hello Rpc," + name);
	}

}
