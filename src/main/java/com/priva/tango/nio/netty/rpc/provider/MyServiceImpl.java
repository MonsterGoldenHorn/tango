package com.priva.tango.nio.netty.rpc.provider;

import com.priva.tango.nio.netty.rpc.MyService;

public class MyServiceImpl implements MyService {

	@Override
	public void sayHello(String msg) {
		System.out.println("hello " + msg);
	}

}
