package com.priva.tango.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class ChannelMain {
	public static void main(String[] args) throws IOException {
//		tcp
//		ServerSocketChannel
//		SocketChannel
//		FileChannel		file
		FileInputStream fileInputStream = new FileInputStream("");
		FileChannel fc = fileInputStream.getChannel();
		FileOutputStream fileOutputStream = new FileOutputStream("");
		FileChannel fco = fileOutputStream.getChannel();
		
		fco.transferFrom(fc, 0, fc.size());
		
		fileInputStream.close();
		fc.close();
//		ByteBuffer bf = ByteBuffer.allocate(1024);
//		while(true) {
//			int read = fc.read(bf, 1024);
//			if(read == -1) {
//				break;
//			}
//			bf.flip();
//			fco.write(bf);
//			
//			bf.clear();
//		}
//		
//		org.apache.tomcat.util.http.fileupload.IOUtils.closeQuietly(fc);
//		org.apache.tomcat.util.http.fileupload.IOUtils.closeQuietly(fco);
		
//		DatagramChannel	UDP
	}
}
