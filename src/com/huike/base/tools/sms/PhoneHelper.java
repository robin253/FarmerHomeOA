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
package com.huike.base.tools.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.google.gson.reflect.TypeToken;
import com.huike.app.system.model.SmsSendOrder;
import com.huike.base.tools.JsonUtil;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: PhoneHelper 
 * @version V1.0  
 * @date 2016年4月18日 
 * @author panwuhai
 * Copyright 2016 tsou.com, Inc. All rights reserved
 */
@Service("phoneHelper")
public class PhoneHelper {

    /** 日志. */
    private static final Log logger = LogFactory.getLog(PhoneHelper.class);
    @Autowired
    private CCPRestSmsSDK    restSmsAPI;

   // private CCPRestSmsSDK   restSmsAPI = new CCPRestSmsSDK();

    public boolean sendRegCodeByXYT(String tel, String verifyCode) {

        String result = null;
        HttpURLConnection httpCon = null;
        OutputStream os = null;
        try {
            URL url = new URL("http://sms.1035.mobi/store_sendSms");
            httpCon = (HttpURLConnection) url.openConnection();
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpCon.setDoInput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpCon.setDoOutput(true);
            // 设定请求的方法为"POST"，默认是GET
            httpCon.setRequestMethod("POST");
            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            StringBuilder builder = new StringBuilder();
            builder.append("username=");
            builder.append("store");
            builder.append("&password=");
            builder.append("store123");
            builder.append("&tel=");
            builder.append(tel);
            builder.append("&content=");
            builder.append("验证码：" + verifyCode);
            os = httpCon.getOutputStream();
            os.write(builder.toString().getBytes("utf-8"));
            os.flush();
            if (httpCon.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    httpCon.getInputStream()));
                httpCon.connect();
                result = br.readLine();
                br.close();
            }

            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
          
            Map<String, String> resultMap =   JsonUtil.toObject(result, Map.class);
            if (resultMap != null && resultMap.get("result") != null
                && resultMap.get("result").toString().equals("0")) {
                return true;
            }
            logger.error("发送短信校验码失败：" + result);
            return false;
        } catch (Exception e) {
            logger.error(tel + "发送校验码失败：" + verifyCode + "; 发送短信结果：" + result, e);
            return false;
        } finally {
            try {
                if (os != null)
                    os.close();
                if (httpCon != null) {
                    httpCon.disconnect();
                    httpCon = null;
                }
            } catch (IOException e) {
                logger.error("关闭流异常", e);
                os = null;
                httpCon = null;
            }
        }
    }

    /**
     * <p class="detail">
     * 功能：容联云通讯-发送短信
     * </p>
     * @author liuwh
     * @date 2016-1-22 
     * @param tel   手机号码，多个用，分隔
     * @param verifyCode  验证码
     * @param content  短信内容 按短信模板对应占位符变量：
     *   Map<Integer, String> content = new HashMap<Integer, String>();
     *   content.put(2, verifyCode);
     *   content.put(3, "失效时间");
     *   content.put(1, "注册");
     * @return
     */
    public boolean sendRegCodeByYTX(SmsSendOrder smsSendOrder) {

        HashMap<String, Object> result = null;

        try {
            // 请求地址 端口
            restSmsAPI.init(smsSendOrder.getSmsUrl(), smsSendOrder.getSmsPort());
            // 接口账号密码
            restSmsAPI.setAccount(smsSendOrder.getAccountName(), smsSendOrder.getAccountPsd());
            // 接口注册应用ID
            restSmsAPI.setAppId(smsSendOrder.getAppId());

            // 接口短信模板id
            result = restSmsAPI.sendTemplateSMS(smsSendOrder.getReceiverPhone(),
                smsSendOrder.getSmsTempid(), smsSendOrder.getContents());
        } catch (Exception e) {
            logger.error(smsSendOrder.getReceiverPhone() + ":容联云通讯-发送短信接口异常", e);
            return false;
        }

        if (StringUtils.equals("000000", result.get("statusCode").toString())) {
            //正常返回输出data包体信息（map）
                        @SuppressWarnings("unchecked")
                        Map<String, Object> data = (HashMap<String, Object>) result.get("data");

                        Iterator<Map.Entry<String, Object>> entries = data.entrySet().iterator();

                        while (entries.hasNext()) {
                            Map.Entry<String, Object> entry = entries.next();
                            if (logger.isDebugEnabled()) {
                                logger.debug(entry.getKey() + " = " + entry.getValue());
                            }
                        }
            return true;
        }
        //异常返回输出错误码和错误信息      
        logger.error("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));

        return false;
    }
}
