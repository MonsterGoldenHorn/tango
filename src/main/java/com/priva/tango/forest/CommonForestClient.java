package com.priva.tango.forest;

import java.io.File;
import java.util.Map;

import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Header;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.extensions.DownloadFile;

/**
 * 
 * CommonForestClient.java
 * 动态代理会读取本类的配置，在注入时将本类代理，需要在启动类上加上@ForestScan
 * @author tangjc
 * @date 2021年1月6日
 */
public interface CommonForestClient {
	
	/**
	 * Post请求
	 * @param headerMap	需要添加的请求头信息
	 * @param base	url中的host部分，eg: http://127.0.0.1:8080
	 * @param param	url中参数部分，eg: username=aaa&passwd=bbb
	 * @return
	 */
	@Post(url = "${base}?${param}")
	public String send(
		@Header Map<String, Object> headerMap,
	    @DataVariable("base") String base,
	    @DataVariable("param") String param
	);
	
	/**
	 * Get请求
	 * @param headerMap 需要添加的请求头信息
	 * @param base url全部
	 * @return
	 */
	@Get(url = "${url}")
	public String get(
		@Header Map<String, Object> headerMap,
	    @DataVariable("url") String base
	);
	
	/**
	 * 下载文件请求
	 * @param url 下载地址
	 * @param tempDir 本地文件夹
	 * @param tempDir 
	 * @return
	 */
	@Get(url = "${0}?${1}")
	@DownloadFile(dir = "${2}")
	public File downloadFile(String url, String queryString, String tempDir);
	
	@Post(url = "${url}")
	public String  postJsonString(@DataVariable("url") String url, @JSONBody String body);
}
