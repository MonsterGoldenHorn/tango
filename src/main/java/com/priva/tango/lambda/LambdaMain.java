package com.priva.tango.lambda;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.net.NioEndpoint;

import ch.qos.logback.core.util.FileUtil;

public class LambdaMain {
	public static void main(String[] args) {
		File tofile = new File("D:\\workspace1\\soapServer\\src.txt");
		File file = new File("D:\\workspace1\\sc_ChannelMavenWebapp\\src\\main\\java");
		File[] listFiles = file.listFiles();
		ForkJoinPool fjp = new ForkJoinPool(10);
		fjp.execute(new ForkJoinTask<File>() {

			@Override
			public File getRawResult() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void setRawResult(File value) {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected boolean exec() {
				// TODO Auto-generated method stub
				return false;
			}
		});
//		Arrays.asList(listFiles).stream().reduce(identity, accumulator);
		
		//1.实现内部类
//		Play p = new Play() {
//			@Override
//			public void print(String s) {
//				System.out.println(s);
//			}
//		};
//		p.print("999");
		
//		Play p = (String val) -> {
//			System.out.println(val);
//		};
		
//		Play p = val -> {
//			System.out.println(val);
//		};
		
//		Play p = val -> System.out.println(val);
		
		
		/**
		 * 线程
		 */
//		new Thread(() -> System.out.println("start")).start();
		
	}
}

interface Play {
	void print(String s);
}