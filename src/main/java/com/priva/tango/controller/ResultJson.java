package com.priva.tango.controller;

import java.io.Serializable;

/**
 * Description: 用json为返回格式时的封装
 * Copyright: 川大智胜系统集成公司 
 * Date: 2015年9月29日
 * Time: 下午4:50:45
 * @author zhang.yuwei
 * @version 1.0
 */
public class ResultJson implements Serializable{
	public static final int ERROR = -1;
	private static final long serialVersionUID = -2771137844516796010L;
	private boolean success = true;
	private int code;
	private String msg = "";
	private Object obj = null;
	public static final ResultJson OK = new ResultJson(true, 200);
	public static final ResultJson ERROR_PARAM = new ResultJson(false, "未传请求参数", null);
	public static final ResultJson ERROR_SAVE = new ResultJson(false, "保存失败", null);
	public static final ResultJson ERROR_UPDATE = new ResultJson(false, "更新失败", null);
	public static final String s = "";
	public ResultJson() {
		super();
	}
	
	public ResultJson(String errorMsg) {
		super();
		this.success = false;
		this.msg = errorMsg;
	}
	
	public ResultJson(boolean success) {
		super();
		this.success = success;
	}
	
	public ResultJson(boolean success, int code) {
		super();
		this.success = success;
		this.code = code;
	}

	public ResultJson(boolean success, Object obj) {
		super();
		this.success = success;
		this.obj = obj;
	}
	
	public ResultJson(boolean success, String msg, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}
	
	public ResultJson(boolean success, String msg, int code, Object obj) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
		this.code = code;
	}
	public static ResultJson error(String errorMsg) {
		return new ResultJson(false, errorMsg, null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	
}
