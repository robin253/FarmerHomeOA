package com.huike.app.system.model;

/**
 * 短信验证码实体类
 * @author LiCheng
 *
 */
public class SmsRegcode {

	private String phone; // 电话号码
	
	private String smsCode; // 短信验证码
	
	private String sendTime; // 发送时间

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
