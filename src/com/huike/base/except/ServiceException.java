package com.huike.base.except;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -1152116589449795404L;

	private String code;

	public ServiceException(){
		super();
	}
	
	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(Throwable e) {
		super(e);
	}
	
	public ServiceException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
}
