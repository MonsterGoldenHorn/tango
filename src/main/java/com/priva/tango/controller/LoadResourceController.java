package com.priva.tango.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.priva.tango.mq.MqService;

@Controller
@ResponseBody
public class LoadResourceController {
	
//	@Autowired
//	ResourceMapper resourceMapper;
	@Autowired
	MqService mqService;
	
	@RequestMapping(value="/deliver",method = RequestMethod.GET)
	public String deliver() {
		// TODO Auto-generated method stub
//		resourceMapper.writeResource();
		for (int i = 0; i < 3; i++) {
		}
		mqService.send();
		return "done";
	}

	@RequestMapping(value="/startcsm",method = RequestMethod.GET)
	public String startcsm() {
		// TODO Auto-generated method stub
//		resourceMapper.writeResource();
		return "done";
	}
}
