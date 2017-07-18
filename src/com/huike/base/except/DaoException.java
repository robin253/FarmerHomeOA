package com.huike.base.except;

public class DaoException extends Exception{
	
	private static final long serialVersionUID = -3319524281271140597L;
	private String code;
	
	public DaoException(){
		super();
	}
	
	public DaoException(String msg){
		super(msg);
	}

	public DaoException(String code, String msg){
		super(msg);
		this.code = code ;
	}
}
