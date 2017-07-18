package com.huike.app.system.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：系統用戶信息实体类
 * </p>
 * @ClassName: TUser 
 * @version V1.0  
 * @date 2017年5月25日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TUser {
    private Long id; // 用户ID

    private String username; // 姓名
 
    private String password; // 密码

    private String userPhone; // 手机号

    private Long xiangId; // 乡ID
    
    private Long cunId; // 村ID

    private Long departId; // 部门ID
    
    private Integer level; // 0县级，1乡级，2村级

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isDelete; // 是否删除（0未删除，1已删除）
    
    private Integer isReport; // 是否上传报表用户（1不是，0是）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getXiangId() {
		return xiangId;
	}

	public void setXiangId(Long xiangId) {
		this.xiangId = xiangId;
	}

	public Long getCunId() {
		return cunId;
	}

	public void setCunId(Long cunId) {
		this.cunId = cunId;
	}

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}
}