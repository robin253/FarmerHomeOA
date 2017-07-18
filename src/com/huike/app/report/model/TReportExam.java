package com.huike.app.report.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：报表审核实体信息类
 * </p>
 * @ClassName: TReportEaxm 
 * @version V1.0  
 * @date 2017年6月6日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TReportExam {
    private Integer id; // 报表上传ID

    private Integer reportId; // 报表ID
    
    private Integer reportType; // 报表ID

    private String reportName; // 报表名称

    private Integer status; // 状态（0未提交，1审核中（乡），2审核中（县），3成功，4未成功）

    private String xiangName; // 乡名称

    private Integer xiangId; // 乡ID

    private String cunName; // 村名称

    private Integer cunId; // 村ID

    private Integer userId; // 上传用户ID

    private String username; // 上传用户姓名

    private String updateRequest; // 修改要求

    private Date createTime;  // 上传时间

    private Date updateTime; // 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getXiangName() {
        return xiangName;
    }

    public void setXiangName(String xiangName) {
        this.xiangName = xiangName;
    }

    public Integer getXiangId() {
        return xiangId;
    }

    public void setXiangId(Integer xiangId) {
        this.xiangId = xiangId;
    }

    public String getCunName() {
        return cunName;
    }

    public void setCunName(String cunName) {
        this.cunName = cunName;
    }

    public Integer getCunId() {
        return cunId;
    }

    public void setCunId(Integer cunId) {
        this.cunId = cunId;
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

    public String getUpdateRequest() {
        return updateRequest;
    }

    public void setUpdateRequest(String updateRequest) {
        this.updateRequest = updateRequest;
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
}