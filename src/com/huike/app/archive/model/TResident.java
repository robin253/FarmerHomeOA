package com.huike.app.archive.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：农户信息实体类
 * </p>
 * @ClassName: TResident 
 * @version V1.0  
 * @date 2017年5月26日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TResident {
	
    private Long id; // 农户ID

    private String name; // 农户姓名

    private Integer sex; // 性别（1男，2女）

    private Integer education; // 教育程度（1无文凭，2小学，3初中，4高中，5大专/高职，6本科，7研究生及以上）

    private Integer politics; // 政治面貌（1群众，2共青团员，3预备党员，4党员）

    private Long xiangId; // 乡ID
    
    private Long cunId; // 村ID

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isDelete; // 是否删除（0未删除，1已删除）

    private String by1; // 补充字段1

    private String by2; // 补充字段2

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
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

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1;
    }

    public String getBy2() {
        return by2;
    }

    public void setBy2(String by2) {
        this.by2 = by2;
    }
}