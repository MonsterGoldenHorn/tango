package com.priva.tango.rtpro;

public class TestMain {
	public static void main(String[] args) {
		try {
			Class	c = (Class<Father>)Class.forName("com.priva.tango.rtpro.Son");
			Father newInstance = (Father)c.newInstance();
			newInstance.hit();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
