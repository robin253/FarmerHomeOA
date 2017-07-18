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

import org.apache.commons.lang.StringUtils;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: UserTypeEnums 
 * @version V1.0  
 * @date 2016年4月14日 
 * @author panwuhai
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
public enum UserNameType {

    EMAIL("EMAIL", "邮箱"), PHONE("PHONE", "手机 ");

    private String code;

    private String detail;

    UserNameType(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static UserNameType getEnumByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (UserNameType activitie : UserNameType.values()) {
                if (StringUtils.equals(code, activitie.getCode())) {
                    return activitie;
                }
            }
        }
        return null;
    }

    /**
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
