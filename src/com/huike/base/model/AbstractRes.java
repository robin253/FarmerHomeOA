package com.huike.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源信息实体
 * 
 * @author shadow
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractRes implements Serializable {

	private Integer id; // 编号

	private String name; // 资源名称
	
	private String symbol; // 资源标识

	private String url; // 菜单(资源)路径
	
	private String icon; // 菜单小图标地址
	
	private Integer parentId; // 父菜单编号
	
	private Integer menuOrder; // 菜单顺序
	
	private String isUsable; // 是否可用,1：可用，0不可用
	
	private String remark; // 资源备注
	
	private Date createTime; // 创建时间

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}