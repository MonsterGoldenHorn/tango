package com.priva.tango.test.serialize.java;

import java.io.IOException;
import java.io.Serializable;

import org.msgpack.MessagePack;

import com.priva.tango.test.serialize.proto.SampleProbuf;

public class SerMain {
	/**
	 * protobuf
	 * ./protoc -I=D:\workspace1\tango\src\main\resources\proto --java_out=D:\tjc\sitech\proto sample.proto
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Sample s = new Sample("aaaaaaaamlllllllllllllllllllllllllll", 19);
//		byte[] serialize = SerializationUtils.serialize(s);
//		System.out.println(serialize.length);//200
		
		MessagePack msg = new MessagePack();
		msg.register(Sample.class);
//		msg.createBufferPacker(1024);
		byte[] pack = msg.write(s);
		System.out.println(pack.length);//5
		
		SampleProbuf.RequestMsg.Builder builder = SampleProbuf.RequestMsg.newBuilder();
		builder.setId(19);
		builder.setName("aaaaaaaamlllllllllllllllllllllllllll");
		
		SampleProbuf.RequestMsg msg1 = builder.build();
		System.out.println(msg1.toByteArray().length);
	}
}

class Sample implements Serializable{
	private String name;
	private Integer id;
	
	//MessagePack必须要的
	public Sample() {
	}
	public Sample(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}