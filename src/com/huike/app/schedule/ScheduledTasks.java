package com.huike.app.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huike.app.consult.service.impl.ConsultService;
import com.huike.app.report.service.impl.ReportManageService;
import com.huike.app.system.model.SmsSendOrder;
import com.huike.app.system.service.impl.UserService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.DateRange;
import com.huike.base.tools.DateUtils;
import com.huike.base.tools.sms.SendMessageService;

/**
 * 定时器 1.每季度第一天上午9点给所有上传报表人员发送提醒短信，并归档到t_report_manage表中
 * 2.每季度第五天上午9点给尚未上传报表人员发送提醒短信。
 * 3.每季度第一天凌晨归档上传报表人员
 * 4.办理完成期限为7个工作日，如最后1天还未办结，短信提醒(每天早上九点)
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	@Qualifier("reportManageService")
	ReportManageService reportManageService;
	
	@Autowired
	@Qualifier("consultService")
	ConsultService consultService;
	
	@Autowired
	SendMessageService sendMessageService;
	
	//@Scheduled(fixedRate = 1000 * 30)
	public void reportCurrentTime() {
		log.info("Scheduling Tasks Prepared.");
	}

	// 每季度第一天执行一次
	@Scheduled(cron = "0 0 9 1 1/3 ? ")
//	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentByCron1() {
		log.info("每季度第一天发送报表上传提醒短信定时器开始...");
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询出所有需要发送短信的人员；(用户名，手机号)
		List<Map<String, Object>> userList = userService.selectAllReportUserListForSMS(params);
		if(userList!=null &&userList.size()>0){
			for(int i=0; i<userList.size(); i++){
				Map<String, Object> user = userList.get(i);
				// 发送短信
				SmsSendOrder sso = new SmsSendOrder();
				sso.setReceiverPhone(user.get("user_phone").toString());
				sso.setContents(new String[]{user.get("username").toString()});
				sso.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid_2").toString());
				//new SendMessageService(sso);
//				new Thread(sendMessageService).start();
				sendMessageService.run(sso);
			}
		}
		log.info("每季度第一天发送报表上传提醒短信定时器结束...");
	}

	// 每季度第五天执行一次
	@Scheduled(cron = "0 0 9 5 1/3 ? ")
//	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentByCron2() {
		log.info("每季度第五天上午9点给尚未上传报表人员发送提醒短信定时器开始...");
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询出本季度所有需要发送短信的人员；(用户名，手机号)
		DateRange currentQuarter = DateUtils.getThisQuarter();
		params.put("createTime", ExDateUtils.formatDate(currentQuarter.getStart()));
		List<Map<String, Object>> userList = reportManageService.selectUnuploadList(params);
		if(userList!=null &&userList.size()>0){
			for(int i=0; i<userList.size(); i++){
				Map<String, Object> user = userList.get(i);
				// 发送短信
				SmsSendOrder sso = new SmsSendOrder();
				sso.setReceiverPhone(user.get("phone").toString());
				sso.setContents(new String[]{user.get("username").toString()});
				sso.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid_3").toString());
//				sendMessageService.sendMessage(sso);
//				new Thread(sendMessageService).start();
				sendMessageService.run(sso);
			}
		}
		log.info("每季度第五天上午9点给尚未上传报表人员发送提醒短信定时器结束...");
	}
	
	// 每季度第一天凌晨一点执行一次
	@Scheduled(cron = "0 0 1 1 1/3 ? ")
//	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentByCron3() {
		log.info("每季度第一天凌晨归档上传报表人员定时器开始...");
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询出所有需要发送短信的人员；
		List<Map<String, Object>> userList = userService.selectAllReportUserListForManage(params);
		if(userList!=null &&userList.size()>0){
			for(int i=0; i<userList.size(); i++){
				Map<String, Object> user = userList.get(i);
				DateRange currentQuarter = DateUtils.getThisQuarter();
				user.put("createTime", ExDateUtils.formatDate(currentQuarter.getStart()));
				for(int j = 1; j < 3; j++){
					user.put("reportType", j);
					// 插入到归档表中
					reportManageService.save(user);
				}
			}
		}
		log.info("每季度第一天凌晨归档上传报表人员定时器结束...");
	}
	
	// 每个工作日上午9点执行一次(办理完成期限为7个工作日，如最后1天还未办结，短信提醒)
	@Scheduled(cron = "0 0 9 ? * MON-FRI")
//	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentByCron4() {
		log.info("每个工作日早上九点定时器开始...");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("handleStatus", "2");
		params.put("updateTime", DateUtils.getWorkDay(ExDateUtils.getTodayDate(), 7));
		// 查询出所有需要发送短信的人员；
		List<Map<String, Object>> userList = consultService.unHandleMessage(params);
		if(userList!=null &&userList.size()>0){
			for(int i=0; i<userList.size(); i++){
				Long userId = Long.valueOf(userList.get(i).get("handleUserId").toString());
				// 获取交办对象手机号
				String phone = userService.queryById(userId).get("phone").toString();
				// 发送短信
				SmsSendOrder sso = new SmsSendOrder();
				sso.setReceiverPhone(phone);
				sso.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid_5").toString());
				sendMessageService.run(sso);
			}
						
		}
		log.info("每季度第一天凌晨归档上传报表人员定时器结束...");
	}
}
