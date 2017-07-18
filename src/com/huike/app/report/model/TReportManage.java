package com.huike.app.report.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：报表上传备案
 * </p>
 * @ClassName: TReportManage 
 * @version V1.0  
 * @date 2017年6月19日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TReportManage {
    private Integer id; // 记录ID

    private Integer userId; // 用户ID
    
    private String username; // 用户名

    private Integer xiangId; // 乡ID

    private Integer cunId; // 村ID

    private String xiangName; // 乡名

    private String cunName; // 村名

    private String phone; // 手机号

    private Integer reportType; // 报表类型（1财务报表，2服务报表）

    private Date createTime; // 创建时间

    private Integer isUpload; // 是否上传（0已上传，1未上传）

    private Date uploadTime;  // 上传时间 

    private String by1; // 补充字段1

    private String by2; // 补充字段2

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

    public String getXiangName() {
        return xiangName;
    }

    public void setXiangName(String xiangName) {
        this.xiangName = xiangName;
    }

    public String getCunName() {
        return cunName;
    }

    public void setCunName(String cunName) {
        this.cunName = cunName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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