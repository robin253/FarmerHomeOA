/*
 * 版权所有(C) 浙江天搜科技股份有限公司2016-2020
 * Copyright 2016-2020 Zhejiang Tsou Technology Co., Ltd.
 *  
 * This software is the confidential and proprietary information of
 * Zhejiang Tsou Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Zhejiang Tsou
 */
package com.huike.app.system.model;

import java.io.Serializable;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: SmsSendOrder 
 * @version V1.0  
 * @date 2016年5月13日 
 * @author panwuhai
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
public class SmsSendOrder implements Serializable {

    /** 
     * <p class="detail">
     * 功能：
     * </p>
     * @Fields serialVersionUID 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private static final long serialVersionUID = 285752858169472445L;
    /** 
     * <p class="detail">
     * 功能：接收短信者
     * </p>
     * @Fields receiverPhone 
     * @author panwuhai
     * @date 2016年5月13日
     */
    String                    receiverPhone;
    /** 
     * <p class="detail">
     * 功能：地址
     * </p>
     * @Fields smsUrl 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            smsUrl;

    /** 
     * <p class="detail">
     * 功能：端口
     * </p>
     * @Fields smsPort 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            smsPort;

    /** 
     * <p class="detail">
     * 功能：账号
     * </p>
     * @Fields accountName 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            accountName;

    /** 
     * <p class="detail">
     * 功能：密码
     * </p>
     * @Fields accountPsd 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            accountPsd;

    /** 
     * <p class="detail">
     * 功能：对接应用id
     * </p>
     * @Fields appId 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            appId;

    /** 
     * <p class="detail">
     * 功能：对方接口的模板id
     * </p>
     * @Fields smsTempid 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            smsTempid;

    /** 
     * <p class="detail">
     * 功能：短信内容 按占位符排序
     * </p>
     * @Fields content 
     * @author panwuhai
     * @date 2016年5月13日
     */
    private String            contents[];

    /**
     * @return receiverPhone
     */

    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * @param receiverPhone
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * @return smsUrl
     */

    public String getSmsUrl() {
        return smsUrl;
    }

    /**
     * @param smsUrl
     */
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    /**
     * @return smsPort
     */

    public String getSmsPort() {
        return smsPort;
    }

    /**
     * @param smsPort
     */
    public void setSmsPort(String smsPort) {
        this.smsPort = smsPort;
    }

    /**
     * @return accountName
     */

    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return accountPsd
     */

    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * @param accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * @return appId
     */

    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return smsTempid
     */

    public String getSmsTempid() {
        return smsTempid;
    }

    /**
     * @param smsTempid
     */
    public void setSmsTempid(String smsTempid) {
        this.smsTempid = smsTempid;
    }

    /**
     * @return contents
     */

    public String[] getContents() {
        return contents;
    }

    /**
     * @param contents
     */
    public void setContents(String[] contents) {
        this.contents = contents;
    }

}
