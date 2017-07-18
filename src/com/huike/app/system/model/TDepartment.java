package com.huike.app.system.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：部门实体类
 * </p>
 * @ClassName: TDepartment 
 * @version V1.0  
 * @date 2017年5月24日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TDepartment {
	
    private Long id; // 部门ID

    private String departName; // 部门名称

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isDelete; // 更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
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
}