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

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: SysConfigKeyValue 
 * @version V1.0  
 * @date 2016年4月19日 
 * @author panwuhai
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
public class SysConfigKeyValue {

    private String ckey;

    private String cvalue;

    /**
     * @return ckey
     */

    public String getCkey() {
        return ckey;
    }

    /**
     * @param ckey
     */
    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    /**
     * @return cvalue
     */

    public String getCvalue() {
        return cvalue;
    }

    /**
     * @param cvalue
     */
    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

}
