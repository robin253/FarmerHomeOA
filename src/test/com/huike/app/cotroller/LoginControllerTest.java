package test.com.huike.app.cotroller;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smartframework.common.utils.ExHttpClientUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huike.base.tools.Base64Util;
import com.huike.base.tools.MapUtil;
import com.huike.base.tools.RSAUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
public class LoginControllerTest {

	private final String HOST = "http://127.0.0.1:8080";

	/**
	 * 测试 ：第一次登录，返回公钥的 Modulus 与PublicExponent的hex编码形式。
	 */
	@Test
	public void firstLoginTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		String url = HOST + "/api/app/rest/login/first";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}

	@Test
	public void secondLoginTest() {
		// 得到公钥
		String url = HOST + "/api/app/rest/login/first";
		String sendPostResult = ExHttpClientUtils.doGet(url);
		Map<String, Object> serverPublicKey = MapUtil.string2Map(sendPostResult);
		String publicKeyStr = serverPublicKey.get("publicKey").toString();
		//byte[] module = Base64Util.base642Byte(serverPublicKey.get("m").toString());
		//byte[] exponent = Base64Util.base642Byte(serverPublicKey.get("e").toString());
		// 构造用户ID和密码
		String username = "18271624120";
		String password = "123456";
		// 使用服务端的公钥加密密码
		byte[] en_password;
		try {
			//RSAPublicKey pubKey = RSAUtil.generateRSAPublicKey(module, exponent);
			RSAPublicKey pubKey = (RSAPublicKey) RSAUtil.loadPublicKey(publicKeyStr);
			en_password = RSAUtil.encrypt(pubKey, password.getBytes());
			// password = new String(en_password);
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("username", username);
			userMap.put("password", Base64Util.byte2Base64(en_password));
			// 客户端生成密钥对，并将公钥的模和指数以base64形式传到服务端
			/*String clientPublickeyem = RSAUtil.getRSAPublicKeyME(RSAUtil.CLIENT_KEYPAIR_PATH);*/
			RSAPublicKey rsap = (RSAPublicKey) RSAUtil.getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPublic();
			String publicKey = Base64Util.byte2Base64(rsap.getEncoded());
			userMap.put("publicKey", publicKey);
			long btime = System.currentTimeMillis();
			Map<String, Object> params = new HashMap<String, Object>();
			String url2 = HOST + "/api/app/rest/login/second";
			String sendPostResult2 = ExHttpClientUtils.doGet(url2, userMap);
			long etime = System.currentTimeMillis();
			long ttime = (etime - btime);
			System.out.println("查询时间：" + ttime + "--- sendPostResult2" + sendPostResult2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void doSomeRequestTest() {
		// token+key1value1key2value2使用客户端私钥生成签名按key升序排列
		long btime = System.currentTimeMillis();
		String token = "100cc6adc552d56611a0cf9efe713d31"; // 使用客户端公钥加密的base64形式的token
		String others = "others";
		String others2 = "others2";
		String userId = "18";

		// 使用客户端私钥对token解密,并转为base64形式
		//byte[] en_token = Base64Util.base642Byte(token);
		try {
			PrivateKey privateKey = RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
			/*byte[] de_token = RSAUtil.decrypt(privateKey, en_token);
			token = Base64Util.byte2Base64(de_token);*/

			// 生成sign
			Map<String, Object> signparams = new HashMap<String, Object>();
			signparams.put("others", others);
			signparams.put("others2", "others2");
			signparams.put("userId", userId);
			signparams.put("tmp", 2491547860229L);
			String oriSignData = RSAUtil.signData(token, signparams);
			byte[] sign = RSAUtil.signature(privateKey, oriSignData.getBytes(), RSAUtil.ALGORITHM);
			
			// 构建请求参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("tmp", 2490599898695L);
			params.put("others", others);
			params.put("others2", others2);
			params.put("sign", Base64Util.byte2Base64(sign));
			String url = HOST + "/api/app/rest/login/dosomething";
			String sendPostResult = ExHttpClientUtils.doGet(url, params);
			long etime = System.currentTimeMillis();
			long ttime = (etime - btime);
			System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void logoutTest() {
		long btime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", "21");
		String url = HOST + "/api/app/rest/login/logout";
		String sendPostResult = ExHttpClientUtils.doGet(url, params);
		long etime = System.currentTimeMillis();
		long ttime = (etime - btime);
		System.out.println("查询时间：" + ttime + "--- sendPostResult" + sendPostResult);
	}
	
/*	public static void main(String args []) {
		System.out.println(System.currentTimeMillis());
	}*/
}
