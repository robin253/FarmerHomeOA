package com.huike.base.tools.sms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huike.app.schedule.ScheduledTasks;
import com.huike.app.system.model.SmsSendOrder;
import com.huike.base.tools.CacheHelp;

@Service("sendMessageService")
public class SendMessageService{
	
	@Autowired
    private PhoneHelper    phoneHelper;
	
//	private SmsSendOrder sso;
	
	Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
/*	public SendMessageService(SmsSendOrder smsSendOrder) {
		this.sso = fill(smsSendOrder);
	}*/
	
	public void run(SmsSendOrder sso) {
		try {
			long transStart = System.currentTimeMillis();
			sendMessage(sso);
			long anlyEnd = System.currentTimeMillis();
			log.info("发送提醒短信:" + Thread.currentThread().getName() + "-分的时间:"
					+ (anlyEnd - transStart) +"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(SmsSendOrder sso){
		if("true".equals(GlobalConfig.getConfigValue("debug_mode"))){
			// 公司短信发送
			//flag = phoneHelper.sendRegCodeByXYT(sso.getReceiverPhone(), verifyCode);
		}else{
			// 上线后的短信发送
			phoneHelper.sendRegCodeByYTX(fill(sso));
		}
	}
	
	/** 生成短信模板 */
	private SmsSendOrder fill(SmsSendOrder smsSendOrder) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> smsSendParams = new HashMap<String, Object>();
		smsSendOrder.setSmsUrl(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_url").toString());
		smsSendOrder.setSmsPort(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_port").toString());
		smsSendOrder.setAccountName(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_sid").toString());
		smsSendOrder.setAccountPsd(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_token").toString());
		smsSendOrder.setAppId(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_appid").toString());
//		smsSendOrder.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid").toString());
		return smsSendOrder;
	}
	
}
