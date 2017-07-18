package test.com.huike.app.cotroller.app;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smartframework.common.utils.ExHttpClientUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huike.base.tools.Base64Util;
import com.huike.base.tools.RSAUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
public class FeedbackAppControllerTest {
	
	private final String HOST = "http://127.0.0.1:8080/api/app/rest/feedback";
	private final Integer userId = 18;
	private final String token = "b445d5276059fe85966bdde69742d801";
	private final long tmp = 2441947860229L;
	
	@Test
	public void addFeedbackTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneType", "IOS");
		params.put("deviceName", "iPhone 6s");
		params.put("osVersion", "IOS_9.0");
		params.put("appVersion", "Version_1.0");
		params.put("ip", "127.0.0.1");
		params.put("content", "黑屏。。。");
		params.put("UID", userId);
		params.put("tmp", tmp);
		PrivateKey privateKey;
		try {
			privateKey = RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
			String oriSignData = RSAUtil.signData(token, params);
			byte[] sign = RSAUtil.signature(privateKey, oriSignData.getBytes(), RSAUtil.ALGORITHM);
			params.put("sign", Base64Util.byte2Base64(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = HOST + "/add";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
}
