package com.huike.app.archive.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：农户档案信息实体类
 * </p>
 * @ClassName: TResidentBooklet 
 * @version V1.0  
 * @date 2017年7月24日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TResidentBooklet {
    private Integer id; // 农户档案ID
    
    private Integer xiangId; // 乡ID
    
    private Integer cunId; // 村ID

    private String houseHolder; // 户主姓名

    private String villageGroup; // 村民小组组别

    private String contactAddress; // 联系地址

    private String breedCategory; // 家庭主要经营种养植类别

    private String breedScale; // 家庭主要经营种养规模（亩，羽，只，群）

    private String bankLoan; // 银行贷款总额（单位万元）

    private String privateLending; // 民间借贷总额（单位万元）

    private String loanCondition; // 贷款需求情况

    private String houseNumber; // 房产证编号

    private String houseSpace; // 房屋住房面积

    private Integer landOut; // 土地、林地农业产业经营流出流入意向（1有，2无）默认2

    private Integer houseOut; // 空闲房屋流出意向（1有，2无）默认2

    private String landOutSpace; // 愿意流出地块和面积（亩）

    private String location; // 所在位置

    private String landInSpace; // 需要流入面积（亩）

    private String houseFloorSpace; // 建筑层数和面积（平方米）

    private Integer nature; // 性质（1耕地，2林地）

    private Integer houseOutYear; // 愿意流出使用年限意向   默认0

    private Integer houseRun; // 空闲房屋租赁经营意向（1有，2无） 默认2

    private String bestArea; // 最佳地域

    private String buildSpace; // 建筑面积（平方米） 默认0

    private Integer rentYear; // 租赁经营年限意向（年）默认0

    private Integer trainHope; // 希望何种创业培训（1有，2无） 默认2

    private String agriculturalTechnique; // 农业生产技术

    private String homeManage; // 民宿经营管理

    private String eCommerce; // 电子商务

    private String employability; // 就业技能

    private String others; // 其他
    
    private Date createTime; // 创建时间

	private Date updateTime; // 最后更新时间
 
	private Integer isDelete; // 是否删除（0正常，1删除）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getXiangId() {
		return xiangId;
	}

	public void setXiangId(Integer xiangId) {
		this.xiangId = xiangId;
	}

	public Integer getCunId() {
		return cunId;
	}

	public void setCunId(Integer cunId) {
		this.cunId = cunId;
	}

	public String getHouseHolder() {
        return houseHolder;
    }

    public void setHouseHolder(String houseHolder) {
        this.houseHolder = houseHolder;
    }

    public String getVillageGroup() {
        return villageGroup;
    }

    public void setVillageGroup(String villageGroup) {
        this.villageGroup = villageGroup;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getBreedCategory() {
        return breedCategory;
    }

    public void setBreedCategory(String breedCategory) {
        this.breedCategory = breedCategory;
    }

    public String getBreedScale() {
        return breedScale;
    }

    public void setBreedScale(String breedScale) {
        this.breedScale = breedScale;
    }

    public String getBankLoan() {
        return bankLoan;
    }

    public void setBankLoan(String bankLoan) {
        this.bankLoan = bankLoan;
    }

    public String getPrivateLending() {
        return privateLending;
    }

    public void setPrivateLending(String privateLending) {
        this.privateLending = privateLending;
    }

    public String getLoanCondition() {
        return loanCondition;
    }

    public void setLoanCondition(String loanCondition) {
        this.loanCondition = loanCondition;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseSpace() {
        return houseSpace;
    }

    public void setHouseSpace(String houseSpace) {
        this.houseSpace = houseSpace;
    }

    public Integer getLandOut() {
        return landOut;
    }

    public void setLandOut(Integer landOut) {
        this.landOut = landOut;
    }

    public Integer getHouseOut() {
        return houseOut;
    }

    public void setHouseOut(Integer houseOut) {
        this.houseOut = houseOut;
    }

    public String getLandOutSpace() {
        return landOutSpace;
    }

    public void setLandOutSpace(String landOutSpace) {
        this.landOutSpace = landOutSpace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandInSpace() {
        return landInSpace;
    }

    public void setLandInSpace(String landInSpace) {
        this.landInSpace = landInSpace;
    }

    public String getHouseFloorSpace() {
        return houseFloorSpace;
    }

    public void setHouseFloorSpace(String houseFloorSpace) {
        this.houseFloorSpace = houseFloorSpace;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getHouseOutYear() {
        return houseOutYear;
    }

    public void setHouseOutYear(Integer houseOutYear) {
        this.houseOutYear = houseOutYear;
    }

    public Integer getHouseRun() {
        return houseRun;
    }

    public void setHouseRun(Integer houseRun) {
        this.houseRun = houseRun;
    }

    public String getBestArea() {
        return bestArea;
    }

    public void setBestArea(String bestArea) {
        this.bestArea = bestArea;
    }

    public String getBuildSpace() {
        return buildSpace;
    }

    public void setBuildSpace(String buildSpace) {
        this.buildSpace = buildSpace;
    }

    public Integer getRentYear() {
        return rentYear;
    }

    public void setRentYear(Integer rentYear) {
        this.rentYear = rentYear;
    }

    public Integer getTrainHope() {
        return trainHope;
    }

    public void setTrainHope(Integer trainHope) {
        this.trainHope = trainHope;
    }

    public String getAgriculturalTechnique() {
        return agriculturalTechnique;
    }

    public void setAgriculturalTechnique(String agriculturalTechnique) {
        this.agriculturalTechnique = agriculturalTechnique;
    }

    public String getHomeManage() {
        return homeManage;
    }

    public void setHomeManage(String homeManage) {
        this.homeManage = homeManage;
    }

    public String geteCommerce() {
        return eCommerce;
    }

    public void seteCommerce(String eCommerce) {
        this.eCommerce = eCommerce;
    }

    public String getEmployability() {
        return employability;
    }

    public void setEmployability(String employability) {
        this.employability = employability;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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