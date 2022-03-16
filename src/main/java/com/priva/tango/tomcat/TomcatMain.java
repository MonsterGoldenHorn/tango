package com.priva.tango.tomcat;

import org.apache.catalina.connector.CoyoteAdapter;
import org.apache.catalina.startup.Bootstrap;
import org.apache.tomcat.util.net.NioEndpoint;

public class TomcatMain {
	public static void main(String[] args) {
		//tomcat包去除去了，这个还是内嵌的boot
		Bootstrap.main(args);
	}
}
