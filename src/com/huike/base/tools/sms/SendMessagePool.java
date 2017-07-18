package com.huike.base.tools.sms;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class SendMessagePool{
	
	private static Logger log = Logger.getLogger(SendMessagePool.class);
	
	private static final long serialVersionUID = -8527440803121243741L;
	
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 2000, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>(50),
					new RejectedExecutionHandler() {
						// 拒绝处理策略
						public void rejectedExecution(Runnable r,
								ThreadPoolExecutor executor) {
							log.info("处理不完拒绝任务!");
						}
	});
	
    /**
     * 多线程处理发送短信
     * @param req
     * @param resp
     * @param inlist
     */
    private void sendMessage() {
    	//pool.submit(new SendMessageService());
		initPoolManager();
    }
    
    private void initPoolManager(){
    	int activeCount = pool.getActiveCount();
    	long completedTaskCount = pool.getCompletedTaskCount();
    	int largestPoolSize = pool.getLargestPoolSize();
    	int poolSize = pool.getPoolSize();
    	int maximumPoolSize = pool.getMaximumPoolSize();
    	long taskCount = pool.getTaskCount();
    	int queueSize = pool.getQueue().size();
    	ThreadPoolManager.getTPM().init(activeCount, completedTaskCount, largestPoolSize, poolSize, maximumPoolSize, taskCount, queueSize);
    }
}

