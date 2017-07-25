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
    
    private Integer residentBookletId; // 所属户口本ID

    private String name; // 农户姓名
    
    private String relation; // 与户主关系

    private Integer sex; // 性别（1男，2女）
    
    private String nativePlace; // 籍贯

    private String nation; // 民族

    private String idCard; // 身份证号

    private String contact; // 联系方式

    private Integer marriage; // 婚姻状态（1已婚，2未婚，默认2）

    private Integer education; // 教育程度（1无文凭，2小学，3初中，4高中，5大专/高职，6本科，7研究生及以上）

    private Integer politics; // 政治面貌（1群众，2共青团员，3预备党员，4党员）
    
    private String religion; // 宗教信仰

    private String speciality; // 专业特长

    private String certificate; // 持证情况

    private String slackSeason; // 农闲时间节点

    private Integer disadvantaged; // 是否弱势群体（1是，2不是，默认2）

    private Integer special; // 是否特殊人群（1是，2不是，默认2）

    private String jobCondition; // 就业状况（在何地，干什么）

    private String jobIntention; // 创业就业意向

    private String populationProperty; // 人口属性（按常住人口统计）

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

    public Integer getResidentBookletId() {
		return residentBookletId;
	}

	public void setResidentBookletId(Integer residentBookletId) {
		this.residentBookletId = residentBookletId;
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

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getSlackSeason() {
		return slackSeason;
	}

	public void setSlackSeason(String slackSeason) {
		this.slackSeason = slackSeason;
	}

	public Integer getDisadvantaged() {
		return disadvantaged;
	}

	public void setDisadvantaged(Integer disadvantaged) {
		this.disadvantaged = disadvantaged;
	}

	public Integer getSpecial() {
		return special;
	}

	public void setSpecial(Integer special) {
		this.special = special;
	}

	public String getJobCondition() {
		return jobCondition;
	}

	public void setJobCondition(String jobCondition) {
		this.jobCondition = jobCondition;
	}

	public String getJobIntention() {
		return jobIntention;
	}

	public void setJobIntention(String jobIntention) {
		this.jobIntention = jobIntention;
	}

	public String getPopulationProperty() {
		return populationProperty;
	}

	public void setPopulationProperty(String populationProperty) {
		this.populationProperty = populationProperty;
	}
}