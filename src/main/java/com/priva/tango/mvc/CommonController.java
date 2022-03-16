package com.priva.tango.mvc;

import com.priva.tango.mvc.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;



@RestController
@RequestMapping("/test")
public class CommonController {
	
	@Autowired
	TransactionService transactionService;
	@Value("${spring.datasource.username}")
	private String TEMP_FILE_PATH;
	/**
	 * 根据数据字典id返回数据字典
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/read/header", method = RequestMethod.GET)
	public Object read(HttpServletRequest req) {
		//params
		Enumeration<String> attributeNames = req.getParameterNames();
		System.out.println("params body:");
		while(attributeNames.hasMoreElements()) {
			String key = attributeNames.nextElement();
			System.out.println(key + ":" + req.getParameter(key));
		}
		//headers
		System.out.println("headers:");
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			System.out.println(key + ":" + req.getHeader(key));
		}
		System.out.println(TEMP_FILE_PATH);
		return 1000;
	}

	@RequestMapping(value="/test1", method = RequestMethod.GET)
	public Object test1(HttpServletRequest req) {
		transactionService.test1();
		return 1000;
	}

	@RequestMapping(value="/test2", method = RequestMethod.GET)
	public Object test2(HttpServletRequest req) {
		transactionService.test2();
		return 1000;
	}
}
