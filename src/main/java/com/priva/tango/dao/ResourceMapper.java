package com.priva.tango.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//@Mapper
public interface ResourceMapper {
	void loadResource();
	void writeResource();
}
