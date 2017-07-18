package com.huike.base.model;

import java.io.Serializable;

/**
 * 分组实体
 * 
 * @author shadow
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractGroup implements Serializable {

	private Integer id; // 编号

	private String name; // 名称

	private String expound; // 描述信息

	private Integer status; // 状态 1.正常 2.冻结 3.删除 4.异常

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpound() {
		return expound;
	}

	public void setExpound(String expound) {
		this.expound = expound;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}