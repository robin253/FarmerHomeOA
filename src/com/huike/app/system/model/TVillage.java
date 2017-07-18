package com.huike.app.system.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：乡村实体类
 * </p>
 * @ClassName: TVillage 
 * @version V1.0  
 * @date 2017年5月25日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TVillage {
    private Long id; // 乡村ID

    private Integer level; // 级别（0为乡级，1为村级）

    private Integer parentId; // 父级ID

    private String villageName; // 乡村名称

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isDelete; // 是否删除（0未删除，1已删除）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
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