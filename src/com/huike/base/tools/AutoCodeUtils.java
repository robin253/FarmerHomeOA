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
package com.huike.base.tools;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

/** <p class="detail">
 * 功能：生成code工具类
 * </p>
 * @ClassName: AutoCodeUtils 
 * @version V1.0  
 * @date 2016年4月17日 
 * @author panwuhai
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
public class AutoCodeUtils {

    /** <p class="detail">
    * 功能： 取charCount位随机数
    * </p>
    * @author panwuhai
    * @date 2016年4月17日 
    * @param charCount   随机数位数
    * @return    
    */
    public static String getRandNum(int charCount) {
        StringBuilder charValue = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < charCount; i++) {
            // 取大于等于0小于10的整数
            charValue.append(r.nextInt(10));
        }
        return charValue.toString();
    }

    /** <p class="detail">
    * 功能：生成邀请码  6位=头字母大写+5位数字
    * </p>
    * @author panwuhai
    * @date 2016年5月5日 
    * @return    
    */
    public static String getInvitationCode() {
        StringBuilder builder = new StringBuilder();
        // 随机头大写字母
        char c = (char) (int) (Math.random() * 26 + 97);
        builder.append(StringUtils.upperCase(String.valueOf(c)));
        // 5位随机数字
        builder.append(getRandNum(5));
        return builder.toString();
    }

}
