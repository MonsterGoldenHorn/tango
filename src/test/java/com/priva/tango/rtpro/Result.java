package com.priva.tango.rtpro;

public class Result {
	Father f ;

	public Result(Father f) {
		this.f = f;
		System.out.println("hit on "+f.getClass().getName());
	}
	
}
