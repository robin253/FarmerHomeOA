package com.huike.app.system.model;

/**
 * 系统配置信息实体类
 * @author LiCheng
 *
 */
public class SysConfig {

	private Integer id; // 配置ID
	
	private String configName; // 配置名称
	
	private String groupCode; // 分组CODE
	
	private String ckey; // 配置英文标识
	
	private String cvalue; // 配置的值
	
	private String remark; // 备注说明

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
