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
public class ArticleAppControllerTest {
	private final String HOST = "http://127.0.0.1:8080/api/app/rest/article";
	private final Integer userId = 19;
	private final String token = "bdab5f708afae71b07335d6300162e91";
	private final long tmp = 2441947860229L;
	
	@Test
	public void queryByIdTest() {
		long btime = System.currentTimeMillis();
		
		Map<String, Object> params = new HashMap<String, Object>();
		Integer id = 7;
		
		Map<String, Object> signData = new HashMap<String, Object>();
		signData.put("id", id);
		signData.put("UID", userId);
		signData.put("tmp", tmp);
		PrivateKey privateKey;
		try {
			privateKey = RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
			String oriSignData = RSAUtil.signData(token, signData);
			byte[] sign = RSAUtil.signature(privateKey, oriSignData.getBytes(), RSAUtil.ALGORITHM);
			signData.put("sign", Base64Util.byte2Base64(sign));
			params = signData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = HOST + "/queryById";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
	@Test
	public void searchPageTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", "黑");
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
		
		String url = HOST + "/searchPage";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
}
