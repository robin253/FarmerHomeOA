package com.huike.app.consult.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：咨询信息实体类
 * </p>
 * @ClassName: TConsult 
 * @version V1.0  
 * @date 2017年5月27日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TConsult {
    private String id; // 查询号（生成规则yyyymmdd+4位有序数）

    private String consultantName; // 咨询人姓名

    private String consultantPhone; // 咨询人手机号

    private String consultantAddress; // 咨询人地址

    private String consultantMail; // 咨询人邮箱

    private String consultQuestion; // 咨询问题

    private Long handleUserId; // 交办对象ID

    private Integer handleStatus; // 办理状态（1已受理，2正在办理，3已办理）

    private String handleResult; // 处理结果

    private Integer handleFeedback; // 咨询者反馈（1满意，2基本满意，3不满意）

    private Integer isPublic; // 是否公开（0公开，1不公开）

    private Long currentUserId; // 当前处理人ID

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isDelete; // 是否删除（0未删除，1已删除）

    private String by1; // 补充字段1

    private String by2; // 补充字段2

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantAddress() {
        return consultantAddress;
    }

    public void setConsultantAddress(String consultantAddress) {
        this.consultantAddress = consultantAddress;
    }

    public String getConsultantMail() {
        return consultantMail;
    }

    public void setConsultantMail(String consultantMail) {
        this.consultantMail = consultantMail;
    }

    public String getConsultQuestion() {
        return consultQuestion;
    }

    public void setConsultQuestion(String consultQuestion) {
        this.consultQuestion = consultQuestion;
    }

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public Integer getHandleFeedback() {
        return handleFeedback;
    }

    public void setHandleFeedback(Integer handleFeedback) {
        this.handleFeedback = handleFeedback;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
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