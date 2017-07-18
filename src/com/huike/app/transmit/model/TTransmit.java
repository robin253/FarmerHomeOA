package com.huike.app.transmit.model;

import java.util.Date;

/**
 * <p class="detail">
 * 功能：文件传达实体类
 * </p>
 * @ClassName: TTransmit 
 * @version V1.0  
 * @date 2017年6月1日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class TTransmit {
	
    private Long id; // 记录ID 

    private String receiver; // 收件人ID集合，以;分隔
    
    private String receiverName; // 收件人姓名集合，以;分隔

    private String title; // 标题

    private String content; // 内容

    private Long sender; // 收件人ID

    private Date sendTime; // 发送时间

    private String by1; // 附件路径

    private String by2; // 附件名称

    private String by3; // 预留字段3

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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

    public String getBy3() {
        return by3;
    }

    public void setBy3(String by3) {
        this.by3 = by3;
    }
}