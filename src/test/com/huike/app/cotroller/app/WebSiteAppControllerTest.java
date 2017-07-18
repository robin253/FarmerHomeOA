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
public class WebSiteAppControllerTest {

	private final String HOST = "http://127.0.0.1:8080";
	private final Integer userId = 21;
	private final String token = "31ccefe04bef94b40384c8990ba43c5d";
	private final long tmp = 3492395640079L;
	
	@Test
	public void queryByIdTest() {
		long btime = System.currentTimeMillis();
		
		Map<String, Object> params = new HashMap<String, Object>();
		Integer websiteId = 7;
		
		Map<String, Object> signData = new HashMap<String, Object>();
		signData.put("id", websiteId);
		signData.put("UID", userId);
		signData.put("tmp", tmp);
		PrivateKey privateKey;
		try {
			privateKey = RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
			String oriSignData = RSAUtil.signData(token, signData);
			byte[] sign = RSAUtil.signature(privateKey, oriSignData.getBytes(), RSAUtil.ALGORITHM_MD5);
			signData.put("sign", Base64Util.byte2Base64(sign));
			params = signData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = HOST + "/api/app/rest/website/queryById";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	
	@Test
	public void searchPageTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "孔");
		params.put("pageSize", 2);
		params.put("pageIndex", 1);
		
		params.put("UID", userId);
		params.put("tmp", tmp);
		
		try {
			PrivateKey privateKey = RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
			String oriSignData = RSAUtil.signData(token, params);
			byte[] sign = RSAUtil.signature(privateKey, oriSignData.getBytes(), RSAUtil.ALGORITHM);
			params.put("sign", Base64Util.byte2Base64(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String url = HOST + "/api/app/rest/website/searchPage";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
}
