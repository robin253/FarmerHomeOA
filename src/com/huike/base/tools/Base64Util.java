package com.huike.base.tools;

import java.io.IOException;

import sun.misc.BASE64Decoder;//转byet[]换为base64
import sun.misc.BASE64Encoder;//转byet[]换为base64

public class Base64Util {

	/**
	 * // 将byte[]转换为base64
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2Base64(byte[] bytes) {
		// 定义一个BASE64Encoder
		BASE64Encoder encode = new BASE64Encoder();
		// 将byte[]转换为base64
		String base64 = encode.encode(bytes);
		// 返回base64
		return base64;
	}

	/**
	 * 将base64转换为byte[]
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] base642Byte(String base64) {
		// 新建一个BASE64Decoder
		BASE64Decoder decode = new BASE64Decoder();
		// 将base64转换为byte[]
		byte[] bytes = null;
		try {
			bytes = decode.decodeBuffer(base64);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 输出转换后的byte[]
		return bytes;
	}

	public static void main(String args[]) throws IOException {
/*		// 定义一个BASE64Encoder
		BASE64Encoder encode = new BASE64Encoder();
		// 将byte[]转换为base64
		String base64 = encode.encode("1".getBytes());
		// 输出base64
		System.out.println(base64);
		// 新建一个BASE64Decoder
		BASE64Decoder decode = new BASE64Decoder();
		// 将base64转换为byte[]
		byte[] b = decode.decodeBuffer(base64);
		// 输出转换后的byte[]
		System.out.println(new String(b));*/
		BASE64Encoder encode = new BASE64Encoder();
		// 将byte[]转换为base64
		//String base64 = encode.encode("1".getBytes());
		String tokenbase64 = "iVlV4G85ddLwDqEMzxFtv3jHnCl5NkejR9JLeRwCMbFnTDoMZr2foJusgxuTmuvXmB4gdw/xPMLe\r\ncT3hGAObdmRaGAHiCkbY9pI9O1xPvRXoSa/oHoJdOwr1MzA/2eHga3rrV/xiuEA9xS2hOJQmuzRD\r\n4KsgYo/rl1Uq//amIin1khJCybCOpeeLbzhd5YtK8XbV4eYNlWtrF5abhXiUsiwX/wwY60OURK48\r\n61qjRAW5ZUJDA20WSFr4OmtHyRvZe7DuwqZgF1p0zFGuCEfJ6locJddoYhG6EpHEO5b2dGKCFiS/\r\nmhEHO5ccruLqO+4LlTqEjUXNakODJtn5M0Uz5A==";
		byte[] bytes = Base64Util.base642Byte(tokenbase64);
		System.out.println(bytes);
	}
	
}