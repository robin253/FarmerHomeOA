package com.huike.base.tools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。
 * 
 */
public class RSAUtil {

	public static final String SERVER_KEYPAIR_PATH = System.getProperty("user.dir") + "\\src\\ServerRSAKey.txt";
	public static final String CLIENT_KEYPAIR_PATH = System.getProperty("user.dir") + "\\src\\ClientRSAKey.txt";
	public static final String ALGORITHM = "RSA";
	public static final String ALGORITHM_MD5 = "MD5withRSA";

	/**
	 * * 生成密钥对 *
	 * 
	 * @return KeyPair *
	 * @throws EncryptException
	 */
	public static KeyPair generateKeyPair(String file) throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSAUtil.ALGORITHM, new BouncyCastleProvider());
			final int KEY_SIZE = 2048;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			System.out.println("publicKey:" + keyPair.getPublic().toString());
			System.out.println("privateKey:" + keyPair.getPrivate().toString());
			saveKeyPair(keyPair, file);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static KeyPair getKeyPair(String filePath) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream oos = new ObjectInputStream(fis);
		KeyPair kp = (KeyPair) oos.readObject();
		oos.close();
		fis.close();
		return kp;
	}

	public static void saveKeyPair(KeyPair kp, String filePath) throws Exception {
		FileOutputStream fos = new FileOutputStream(filePath);

		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 生成密钥
		oos.writeObject(kp);
		oos.close();
		fos.close();
	}

	/**
	 * * 使用modulus和publicExponent字节数组生成公钥 *
	 * 
	 * @param modulus
	 *            *
	 * @param publicExponent
	 *            *
	 * @return RSAPublicKey *
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance(RSAUtil.ALGORITHM, new BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * * 使用modulus和publicExponent字节数组生成公钥 *
	 * 
	 * @param modulus
	 *            *
	 * @param publicExponent
	 *            *
	 * @return RSAPublicKey *
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance(RSAUtil.ALGORITHM, new BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPrivateKeySpec pubKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 使用json格式的，以Base64形式存储的modulus和publicExponent字符生成RSAPublicKey
	 * 
	 * @param publickeystr
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(String publickeystrBase64) throws Exception {
		Map<String, Object> serverPublicKey = MapUtil.string2Map(publickeystrBase64);
		byte[] module = Base64Util.base642Byte(serverPublicKey.get("m").toString());
		byte[] exponent = Base64Util.base642Byte(serverPublicKey.get("e").toString());
		return RSAUtil.generateRSAPublicKey(module, exponent);
	}

	/**
	 * 使用json格式的，以Base64形式存储的modulus和publicExponent字符生成RSAPublicKey
	 * 
	 * @param publickeystr
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(String privatestrBase64) throws Exception {
		Map<String, Object> serverPublicKey = MapUtil.string2Map(privatestrBase64);
		byte[] module = Base64Util.base642Byte(serverPublicKey.get("m").toString());
		byte[] exponent = Base64Util.base642Byte(serverPublicKey.get("e").toString());
		return RSAUtil.generateRSAPrivateKey(module, exponent);
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Util.base642Byte(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.ALGORITHM);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Util.base642Byte(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 得到公钥modulus和privateExponent的json格式字符串
	 */
	public static String getRSAPublicKeyME(String filePath) throws Exception {
		String jsonME = "";
		if (filePath.equals(SERVER_KEYPAIR_PATH) || filePath.equals(CLIENT_KEYPAIR_PATH)) {
			// 客户端生成密钥对，并将公钥的模和指数以base64形式传到服务端
			RSAPublicKey publicKey = (RSAPublicKey) RSAUtil.getKeyPair(filePath).getPublic();
			String module = Base64Util.byte2Base64(publicKey.getModulus().toByteArray());
			String empoent = Base64Util.byte2Base64(publicKey.getPublicExponent().toByteArray());
			Map<String, Object> meMap = new HashMap<String, Object>();
			meMap.put("m", module);
			meMap.put("e", empoent);
			jsonME = MapUtil.map2JsonString(meMap);
		}
		return jsonME;
	}

	/**
	 * 得到公钥modulus和privateExponent的json格式字符串
	 */
	public static String getRSAPrivateKeyME(String filePath) throws Exception {
		String jsonME = "";
		if (filePath.equals(SERVER_KEYPAIR_PATH) || filePath.equals(CLIENT_KEYPAIR_PATH)) {
			// 客户端生成密钥对，并将公钥的模和指数以base64形式传到服务端
			RSAPrivateKey publicKey = (RSAPrivateKey) RSAUtil.getKeyPair(filePath).getPrivate();
			String module = Base64Util.byte2Base64(publicKey.getModulus().toByteArray());
			String empoent = Base64Util.byte2Base64(publicKey.getPrivateExponent().toByteArray());
			Map<String, Object> meMap = new HashMap<String, Object>();
			meMap.put("m", module);
			meMap.put("e", empoent);
			jsonME = MapUtil.map2JsonString(meMap);
		}
		return jsonME;
	}

	/**
	 * * 加密 *
	 * 
	 * @param key
	 *            加密的密钥 *
	 * @param data
	 *            待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(RSAUtil.ALGORITHM, new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。

				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encryptIOS(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}

	/**
	 * * 解密 *
	 * 
	 * @param key
	 *            解密的密钥 *
	 * @param raw
	 *            已经加密的数据 *
	 * @return 解密后的明文 *
	 * @throws Exception
	 */
	public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(RSAUtil.ALGORITHM, new BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decryptIOS(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}

	/**
	 * 组装签名前的数据 sign＝token+key1value1key2velue2key3value3
	 * 
	 * @param token
	 * @param parameters
	 * @return
	 */
	public static String signData(String token, Map<String, Object> parameters) {
		String signData = token;
		// 对parameters排序
		Map<String, Object> sortedMap = MapUtil.sortMapByKey(parameters);

		for (String key : sortedMap.keySet()) {
			signData += key + sortedMap.get(key);
		}
		return signData;
	}

	/**
	 * 私钥对原始数据进行签名
	 */
	public static byte[] signature(PrivateKey privateKey, byte[] data, String keyType) throws Exception {
		// Signature signature = Signature.getInstance(keyType, new
		// BouncyCastleProvider());
		Signature signature = Signature.getInstance(keyType);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * 公钥、原始数据、原始签名数据进行验证
	 */
	public static boolean verify(PublicKey publicKey, byte[] data, byte[] sign) throws Exception {
		// Signature signature = Signature.getInstance(RSAUtil.ALGORITHM_MD5,
		// new BouncyCastleProvider());
		Signature signature = Signature.getInstance(RSAUtil.ALGORITHM_MD5);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(sign);
	}

	public static byte[] singnatureData(PrivateKey privateKey, byte[] data) {
		try {
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(privateKey);
			signature.update(data);
			return signature.sign();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 从字符串中加载私钥
	 *
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PrivateKey loadPrivateKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Util.base642Byte(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSAUtil.ALGORITHM);
			// X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * * *
	 * 
	 * @param args
	 *            *
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		PrivateKey priKey = RSAUtil.loadPrivateKey(
				"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKAFgXUPaxhZ0IqKn55UsrGaOT4W6GtdjDeiZDUr9sENc5uuxeofkHi/MGtrqHxCaGnPos//PAbwNopWBwMHzAjYbWzTzftP2Ax4ZjR1zZNCyTfHrbaLeDouoNjDXsRC3J37UY2mhPbtzQyRQbG8KQsRFT26ZNzzNeVJ7aGdWuuXAgMBAAECgYEAmqb3iJTQIgabX2m9/h1JXg/5BqLJGQqd+x68zg6hOc6wTmPI7w52Z9wPBaJUgVME6k47wd2gu+voKiYePLWKX/Kbg5mDasGj4QwgxOCcQqfJBZk8QvKz9CodAURuQJ34IXp9QINuPv5wdVAm8xik50k0ghzWtx8jlXymFO8cPFECQQDSJ+YX6bf7gYueQOu0LCgBmFrbIdNGqAUVyYorK64PEAyTeOsySykWgwpVsjlR7Le/evDZWDhQPgdg1yAb9th7AkEAwu3d4yhh7gNP47/tgtMH9JFNkXHVYqVzHT/+CRbUi9vlkdqUJKv/5FaYjZeGUgiGpCU2P2PRJUPh84zDRNcElQJBAKkrCvJG5h4jytLqKZJQVSfrj3+D/AGNcQJ3yKw1rhhb7/OqS5GhpQKFG4WeQLvs1IDjowsiE7qsI95VbXh1PQ8CQCuhbFHk/w50RVR36CSpMZWnVFJixV1gJQT+BtFn/8kSPi9IWnJe4VbtnxXKPbFs4R4qihrkL7MEn7WQ40AsVIUCQBmrvrfb2bGzmSFItmLjpUL5fXeG6/DiwlLJyZ0+QAk3e7fC7tVsHx9kssZSdWPQ8fREQuK51VFHztqUM94pTbY=");
		PublicKey pubKey = RSAUtil.loadPublicKey(
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgBYF1D2sYWdCKip+eVLKxmjk+FuhrXYw3omQ1K/bBDXObrsXqH5B4vzBra6h8Qmhpz6LP/zwG8DaKVgcDB8wI2G1s0837T9gMeGY0dc2TQsk3x622i3g6LqDYw17EQtyd+1GNpoT27c0MkUGxvCkLERU9umTc8zXlSe2hnVrrlwIDAQAB");
		// byte[] sign = signature(privateKey, "123".getBytes(),
		// RSAUtil.ALGORITHM_MD5);

		//sign=VnRa5vFBgTwx3ADQs132mb0IWjx0ltsOPykj5IHKjPZmsQDxAe4WBfwGHuiZgMhug6WcdqEtXLq88%2BnraUOOS7XkjSQuwAdFDsSQGj42%2BAa30RbLZ1J1QFX%2FhOejdGfg%2Fb2mIvys0vncaLybboFaPgAgM4%2BqpKxcjvdq7vvWkqY%3D&UID=47&userId=47&tmp=1494471412433&deviceType=0

				
		byte[] sign = singnatureData(priKey, "94de6aa8e4dac857882ce86fed7f8402UID47id47name姐tmp1494834346538".getBytes());
		String signBase64 = Base64Util.byte2Base64(sign);
		System.out.println(signBase64);
		boolean verify = verify(pubKey, "94de6aa8e4dac857882ce86fed7f8402UID47id47name姐tmp1494834346538".getBytes(), Base64Util.base642Byte(signBase64));
		System.out.println(verify);

		/*
		 * String str = "张" ;
		 * 
		 * byte[] jiema= str.getBytes("gb2312") ; //解码 byte[] jiema2=
		 * str.getBytes("UTF-8") ; //解码 byte[] jiema3= str.getBytes() ; //解码
		 * 
		 * //编码 String code = "12fds王震f3"; byte[] code1 = code.getBytes() ; //解码
		 * byte[] code2 = code.getBytes("UTF-8") ; //解码 byte[] code3 =
		 * code.getBytes("gb2312") ; //解码
		 * 
		 * 
		 * String urlEnCode = URLEncoder.encode(code,"UTF-8");
		 * System.out.println(urlEnCode); //step2 //解码 String urlDeCode =
		 * URLDecoder.decode(urlEnCode,"UTF-8"); System.out.println(urlDeCode);
		 * 
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("UID", "36"); map.put("userId", "36"); map.put("articleId",
		 * "11"); map.put("content", "啦啦啦"); map.put("tmp", "1494213860478");
		 * String signStr = RSAUtil.signData("9eb35ef4c39da4d1d9c4c44da17b358c",
		 * MapUtil.sortMapByKey(map)); String sign =
		 * "lre+ipFA9ddbC0T3k2UPpZZiWFe3uQ3dajUQSh357iuGNFZatcr6gdFqLyxupTm28M/81gWP14xWNs3h55NBRvxGSzcreyhTlZIZYswYbILybJnKsxJdUijBM5pjt+4sLthR4flJ7wRpwmUbtzubZrsPk+QXAxy1fdUGjcMh2dDq/VK+iG9LwdCC7p4o5o4DGWrONcFqXpegRWfvZS/J9quGH3s7cboVHzxU/D/sr///3qdPGkrop6qbp7eAQ+lW0+7AmjkTrZYvK3wjX5mKpAQiJVlqrFwn8hP60m+xmxqoUYz9sMAsiN4SFSNPZxDV15XAvsfePQSM9Diaqwazlw==";
		 * 
		 * String publickeystr =
		 * "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwfdKTVzFIY16wkEQEW3MjYde7c4/IntWw1UoVFlwWegl0ZV6CYPDahgVxKpXKrTEYgfQDc2H9FAzqyGeL/PeloP0b1fhK9YBPe8fikqTg6qzLP8VbfDTkkf9pfK9b2kIoccCkRHCaisQrJ2p3jYGXWcOy2IKhS2Er1buIlJPwdcUfGqnPBmfUSy1vi4J9SEw7FLnm7h8YJUt7xIz8mLZeBYTAlRi6gG3fUNrwF9qRG9UkEKXpk1UxCTf/K+bFuwu/QLfFLAp2+CtfkN1knkmF/0p5+ugPWr2qM/yoLmHgD+MEo0Qqpg80Zv2VDPgvP3o2HbkYZQNFdqwDCPsnwr8RQIDAQAB";
		 * RSAPublicKey rsaPublicKey = (RSAPublicKey)
		 * RSAUtil.loadPublicKey(publickeystr); boolean flag =
		 * RSAUtil.verify(rsaPublicKey, signStr.getBytes(),
		 * Base64Util.base642Byte(sign)); System.out.println(flag);
		 * 
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("userId", "36位"); params.put("articleId", "6");
		 * params.put("content",
		 * "you're 6666放大范德萨范德萨发生大范甘迪公司的非官方的功夫大使馆的身范德萨范德萨范德萨范德萨范德萨范德萨范德萨发大水范德萨发的说法第三个份而飞哥哥爱人跟人啊v不v"
		 * ); params.put("UID", 36); params.put("tmp", "1494213860478");
		 * PrivateKey privateKey; try { privateKey =
		 * RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate(); String
		 * oriSignData = RSAUtil.signData("9eb35ef4c39da4d1d9c4c44da17b358c",
		 * MapUtil.sortMapByKey(map)); byte[] signt =
		 * RSAUtil.signature(privateKey, oriSignData.getBytes(),
		 * RSAUtil.ALGORITHM_MD5); String signclient =
		 * Base64Util.byte2Base64(signt); boolean flag1 =
		 * RSAUtil.verify(RSAUtil.getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).
		 * getPublic(), oriSignData.getBytes(),
		 * Base64Util.base642Byte(signclient)); System.out.println(flag1);
		 * params.put("sign", Base64Util.byte2Base64(signt)); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("UID", "36"); map.put("id", "36"); map.put("name", "王震");
		 * map.put("tmp", "1493350442986"); String signStr =
		 * RSAUtil.signData("63e9f7ea531a654782f43b3fe4bb4d38",
		 * MapUtil.sortMapByKey(map));
		 * 
		 * String privatekeystrWZ =
		 * "MIIEpAIBAAKCAQEAoP2jrSy7bi+jlzz35xiIrO3vo6l4EAKsFHf9ewtfk5VI43z8bBk384cirHdrncMJ5QOGaigh+7j/zhJOFm4KwfAvXTBSnbGq8msF4IbZ8HFOE8YwntS/scuwwB+/ue2LC91UR9blahhhF2dMmaxKzYpVh13JnfNC47Pg+asN8OAKaevH+OSOzrGbDrLk5WqshlIEQuTGO8ZotLEnfLobmzE8TPz9Am7LvgKjHopIQb+zzR8GUlHfNfwufxthnUftjUvpUBRJAPinZHA9LDCWu347eUyAG8qJB7XHrm5x9qEdoys8PJSe/Ju4Hdc6okhodFjr6AKIjTJV/0rZGrPNXwIDAQABAoIBAEZMu86BPA9vihYer3oggn5RIFXtSNpk8FO32d6DJudBRyTfjTJ/CKm1FvUM6NdpNcbldSqh/8T2Ee4gnCy7PMNXaLdexJTL4oQdK+HRwRV3xMppvw18hYDUG4ip1IUSL6esrIL6Wg+sOamImDB6xedfQt3rnID2mvznrZQYl+vYmB4boJohG89HrY/X6KFgjZHjLiqaulXeKE6St7jW26my1D6pvmqcTCBVwrZ/w+W/ve2Eb7YEEdO4jTfCmKNpUSlLuFwojlQIsONgbjM8rgCypWmP3UyC7mJCd35NHp9MqBXqPz+Y2yRPYhAPHCX8qggEkyQVClTaEmaJINBMShECgYEAzvwpKm8ntMsOnZTuc6yT/JV1ryvKk8wBCs6BcNbqmjLmy7B9QEH5YDB3wL8UuVdyZzGE4YNbGf4l89Ndnfc+l34oGVWoNaaDUNF31xsij1DI32CkqvJIPTPwYWxnS34tK4gGHxdduOQ8/ZVukw1s9eODYGUooo5A0qtqBbGwj7UCgYEAxx03y0TqC18d+2woO65e73egKj5dXmOZzRD424ZRR/kKiMlQzMv0wrGKNoLcZCkViVFsZ2BPorVkL0Dz2D2pZn7OfnyNS2ZrD4LWv/QClexEoOumElhvQJlZktXD1jDJ5hiekHiaSy+Wb/gE7eH1nyTEoLS2GFwLMWr/VeSqDUMCgYEAhDC5Hbe1tRpF8FAQZeHe9V6CtWgTTYbpV3yBYI5xcB94FLhXlbVMzajGolumoaP+lXcjtV7NzPDqTik8Tcl8uQXCGEQDVJhOn0vX7JIQ5VSjpT20jNSFXEkSiTXRVn5z3KxXtH2Yxp9FcHKCFvppDzTJT8PrSr3tGl6NLKvJ5k0CgYEAiJjizfBZH7QBPZrVfxwAxQesOU7PfxGVUblzACcFW/rd3zk81UKWh3qE9yCPUaddvva9B65q85S9Ri2pG5Axxr73qo6H4FJC4EFCUi10GKYd7se7Bprkt8x8Rt+fmjGeUNxS64nRUAM1kVr1Br0dZDA1NIrE4bFDfIOK58IXZ0ECgYBviiIWqBJLd1KOdEP6pMF2SwzILtDaLfA8YkMwHyJicn4hh0RmFQxIANp9GyzcpbGQwuVqfYWXoNx2gupsk7StR6o0D8NBfpHKql+ja1/biZguW8AZFuSWP05xzwUCtg3qT/+U7bpc9PnF2qDhQ1J0hLQnI7jR246BFlr5xIKbTQ==";
		 * 
		 * PrivateKey privateKey = RSAUtil.loadPrivateKeyByStr(privatekeystrWZ);
		 * byte[] sign = RSAUtil.signature(privateKey, signStr.getBytes(),
		 * RSAUtil.ALGORITHM); System.out.println(Base64Util.byte2Base64(sign));
		 */

		/*
		 * String privateMeMap =
		 * RSAUtil.getRSAPrivateKeyME(RSAUtil.SERVER_KEYPAIR_PATH);
		 * RSAUtil.generateRSAPrivateKey(privateMeMap);
		 * System.out.println("ok"); RSAPrivateKey rsap1 = (RSAPrivateKey)
		 * RSAUtil.getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPrivate(); String
		 * privateKeyStr2 = Base64Util.byte2Base64(rsap1.getEncoded());
		 * System.out.println("privateKeyString base64:"+privateKeyStr2);
		 * System.out.println("===========");
		 */

		/*
		 * RSAPublicKey rsap222 = (RSAPublicKey)
		 * RSAUtil.getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPublic(); String
		 * publicKeyStr2 = Base64Util.byte2Base64(rsap222.getEncoded());
		 * System.out.println("publicKeyString base64:"+StringTools.replaceBlank
		 * (publicKeyStr2));
		 * 
		 * System.out.println("===========");
		 */

		/*
		 * KeyPairGenerator keyPairGen =
		 * KeyPairGenerator.getInstance(RSAUtil.ALGORITHM, new
		 * BouncyCastleProvider()); final int KEY_SIZE = 2048;//
		 * 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
		 * keyPairGen.initialize(KEY_SIZE, new SecureRandom()); KeyPair keyPair
		 * = keyPairGen.generateKeyPair();
		 * System.out.println("publicKey:"+keyPair.getPublic().toString());
		 * System.out.println("privateKey:"+keyPair.getPrivate().toString());
		 * 
		 * System.out.println("==============="); //RSAPublicKey rsap =
		 * (RSAPublicKey)
		 * RSAUtil.generateKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPublic();
		 * //RSAPublicKey rsap2 = (RSAPublicKey)
		 * RSAUtil.generateKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPublic();
		 * String test = "123456a"; PublicKey publicKey =
		 * getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPublic(); PrivateKey
		 * privateKey = getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPrivate();
		 * 
		 * // PublicKey clientpublicKey =
		 * getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPublic(); // PrivateKey
		 * clientprivateKey =
		 * getKeyPair(RSAUtil.CLIENT_KEYPAIR_PATH).getPrivate();
		 * 
		 * byte[] en_test = encrypt(publicKey, test.getBytes());
		 * System.out.println(Base64Util.byte2Base64(en_test)); // String
		 * en_test =
		 * "ViP2uSddwIWuIbmexenPamiS/00pPzxLvpMQ+1sncdmAnhyor0bXs1G6ZhDrTur13939aH6O+Gtx651HZWEWVK7xHpS1RR4IQV7G5dF0dKrPb0bXo4ILFT72HitnDwJPW8YNvDtXhxXLTiVQWOkFKf9PrAnqCxG263hcDycPsDaJs6MdOx3LWalLfTooj36T84BAJk3tfvLnRSc5Zjw8MmUMVLM21XdO0+oiN16ry3oMMkl93LdW3yhzSPgN3w/YCWhcQhP7qp7plEamtGtg08sjK19POm+PPHeLK1E+g5hynndbm41ipzRrCAORqYduAQdztd74L2d0wKABpBjhjQ==";
		 * byte[] de_test = decrypt(privateKey, en_test); System.out.println(new
		 * String(de_test));
		 * 
		 * byte[] sign = signature(privateKey,
		 * "a002ddc460ae7e5ddff75698fc8757fbothersothersothers2others2".getBytes
		 * (), RSAUtil.ALGORITHM); boolean verify = verify(publicKey,
		 * "a002ddc460ae7e5ddff75698fc8757fbothersothersothers2others2".getBytes
		 * (), sign); System.out.println(verify);
		 * 
		 * 
		 * String password2 =
		 * "qMZ8a1GWeaN5XMrSvxar4qDwS3kXoOvcAX6r2Ne03Usq88yIs8I7ckSO4qboR6JbWdmt+lalAxdYvmP+MF7oegXQ7NVmWSHnl2q4P+jEbiO1/WMvXMqvuTlu20qHj6wch++cFL0I4VUXQQbvNLnKwyvmjaWWwq1cXqW+UuHYE7WgaVQ9lT3IhAwVr6l6rcaNIUERAG8dTnwO5FpUrdt8p3VO1SkeMloa2l/vwsYU7mTTYwpP13uKf+6UvoY2jIcVuDJ4u9ri1Nr6zjgnjw0vIUZz7vTKTEDn6ptZHRVmBAHx5Nz6qBpHJb0OztCGbShiOIZlfFf5QCsxjaqTsopztA==";
		 * String password =
		 * "rNJqAq5iGwtq5AeWkHr5XTW7MIDYQjZHi8iV+fPA8bJU3/BZi2L4ps653zBUqn6KOWDodlHESU9r6Q0EqoFPK9AE2fe6u2Fc6G4ZRdVD+I0EtTwnpEUlFXRCetZ28xquCB8oLxw+0Hb7z7N3Vo3t8zmc37fcvfhz9G7KXQ8XbzVAaENCjXMSvewXD6mqo5SlN/9YvQj2NhpySDgSTKfjN+lAo8mc6hDxlrBehlXDkR8sEJQpsszVyqrrKBa8w4MC3quY5yk+9j9IaScLtzkvyQhYf7NddK6nox5WOtw1AFMZiQACYl7U26iPJ0ReEyqpwzy+RTzV5+PNXSgew9N3Lw==";
		 * byte[] out = Base64Util.base642Byte(password2); byte[] WZ =
		 * decryptIOS((RSAPrivateKey)privateKey, out);
		 * System.out.println("wz:"+new String(WZ)+"---wz");
		 * 
		 * 
		 * 
		 * System.out.println("-------------"); RSAPublicKey rsap =
		 * (RSAPublicKey)
		 * RSAUtil.getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPublic(); String
		 * publicKey1 = Base64Util.byte2Base64(rsap.getEncoded());
		 * System.out.println("传到app的公钥string:"+publicKey1);
		 * 
		 * RSAPrivateKey rsap2 = (RSAPrivateKey)
		 * RSAUtil.getKeyPair(RSAUtil.SERVER_KEYPAIR_PATH).getPrivate(); String
		 * privateKey1 = Base64Util.byte2Base64(rsap2.getEncoded());
		 * System.out.println("传到app的私钥string:"+privateKey1);
		 * 
		 * PublicKey publickey = RSAUtil.loadPublicKey(publicKey1);
		 * System.out.println("客户端通过string得到的公钥："+publickey);
		 */

	}

}