package com.huike.app.transmit.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：发文接收者阅件实体类
 * </p>
 * @ClassName: TTransmitReceiver 
 * @version V1.0  
 * @date 2017年6月1日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TTransmitReceiver {
	
    private Long id; // 阅件ID

    private Long transmitId; // 文件传达ID

    private Long receiverId; // 收件人ID

    private String receiverName; // 收件人姓名

    private String receiverPhone; // 收件人手机号

    private Integer isRead; // 是否已阅读（0未读，1已读）

    private Date readTime; // 阅读时间

    private Date receiveTime; // 收件时间

    private String by1; // 补充字段1

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public Long getTransmitId() {
        return transmitId;
    }

    public void setTransmitId(Long transmitId) {
        this.transmitId = transmitId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1;
    }
}