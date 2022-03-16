package com.priva.tango.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.priva.tango.entry.ChTSoapqueue;
//@Mapper
public interface ChTSoapqueueMapper {

    ChTSoapqueue selectByUniqueKey(@Param("seq")String seq);
}