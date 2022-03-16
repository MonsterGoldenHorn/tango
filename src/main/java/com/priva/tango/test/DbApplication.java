package com.priva.tango.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

//@EnableDubbo
//@ImportResource("classpath:dubbo-provider.xml")
@SpringBootApplication
public class DbApplication implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		
	}
	
	
	/**
	 * 
	 @Reference
	 consumer
	 @Reference(url = "")
	 消费者直连，跳过注册中心
	 @Service
	 provider
	 
	 ServiceConfig<T>替代细化service配置
	 MethodConfig
	 ...
	 
	 注册中心宕机回有本地缓存进行通讯
	 * 
	 */
	
	
	
}
