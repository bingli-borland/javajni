package com.leancloud.api.web.resp;

import java.io.File;

public enum ClientStateCode {
	SUCCESS("00000","success"),
    SYSTEM_ERR("00001","system err"),
    file_empty("10001","file empty"),
    uploadfile_type_err("10002", "Content-Type not 'application/octet-stream' "),
    file_not_exists("10003","file not exists"),
	;
	
	
	private String code;
	private String msg;
	
	
	private ClientStateCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
