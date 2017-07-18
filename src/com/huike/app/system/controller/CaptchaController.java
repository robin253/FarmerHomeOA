package com.huike.app.system.controller;
  
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.base.tools.CacheHelp;

@RestController  
@RequestMapping("/api/web/rest/kaptcha")  
public class CaptchaController {  
    
    @RequestMapping("/imgCode")
    public void getKaptchaImage1(HttpServletRequest request, HttpServletResponse response) throws IOException{

 		int width=60, height=20;  
 		BufferedImage img = new BufferedImage(width, height,
 				BufferedImage.TYPE_INT_RGB);

 		Graphics g = img.getGraphics();

 		Random r = new Random();
 		g.setColor(getRandColor(200,250));  

 	    g.fillRect(0, 0, width, height);  
//       设定字体  
         g.setFont(new Font("Times New Roman",Font.PLAIN,18));  
//       随机产生155条干扰线，使图象中的认证码不易被其它程序探测到  
         g.setColor(getRandColor(160,200));  
         for (int i=0;i<155;i++)  
         {  
          int x = r.nextInt(width);  
          int y = r.nextInt(height);  
                 int xl = r.nextInt(12);  
                 int yl = r.nextInt(12);  
          g.drawLine(x,y,x+xl,y+yl);  
         } 

 		StringBuffer sb = new StringBuffer();

 		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

 		int index, len = ch.length;

 		for (int i = 0; i < 4; i++) {

 			index = r.nextInt(len);

 			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
 			g.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));  
 			g.drawString("" + ch[index], 13*i+6,16);

 			sb.append(ch[index]);
 		}
 		HttpSession session = request.getSession();
 		session.setAttribute(CacheHelp.KAPTCHA_SESSION_KEY, sb.toString());
 		CacheHelp.SESSION_USER_MAP.put(request.getParameter("sessionId"), sb.toString());
 		ImageIO.write(img, "JPG", response.getOutputStream());
 	}

 	/*
 	 * 给定范围获得随机颜色
 	 */
 	private Color getRandColor(int fc, int bc) {
 		Random random = new Random();
 		if (fc > 255)
 			fc = 255;
 		if (bc > 255)
 			bc = 255;
 		int r = fc + random.nextInt(bc - fc);
 		int g = fc + random.nextInt(bc - fc);
 		int b = fc + random.nextInt(bc - fc);
 		return new Color(r, g, b);
 	}

 	/**
 	 * 随机生成字符，含大写、小写、数字
 	 * 
 	 * @author dxf
 	 * @return
 	 */
 	public static String getRandomChar() {
 		int index = (int) Math.round(Math.random() * 2);
 		String randChar = "";
 		switch (index) {
 		case 0:// 大写字符
 			randChar = String.valueOf((char) Math
 					.round(Math.random() * 25 + 65));
 			break;
 		case 1:// 小写字符
 			randChar = String.valueOf((char) Math
 					.round(Math.random() * 25 + 97));
 			break;
 		default:// 数字
 			randChar = String.valueOf(Math.round(Math.random() * 9));
 			break;
 		}
 		return randChar;
 	}

 	/**
 	 * 随机生成字体、文字大小
 	 * 
 	 * @author dxf
 	 * @return
 	 */
 	public static Font getRandomFont() {
 		String[] fonts = { "Georgia", "Verdana", "Arial", "Tahoma",
 				"Time News Roman", "Courier New", "Arial Black", "Quantzite" };
 		int fontIndex = (int) Math.round(Math.random() * (fonts.length - 1));
 		int fontSize = (int) Math.round(Math.random() * 4 + 16);
 		return new Font(fonts[fontIndex], Font.PLAIN, fontSize);
 	}
    
	/**
	 * <p class="detail">
	 * 功能：登录页验证码失去焦点时判断验证码是否正确
	 * </p>
	 * @author tangy
	 * @date 2015-7-1
	 * @param code
	 * @param request
	 * @param response
	 * @return 1：验证码错误，0：验证码正确
	 */
	@RequestMapping("/verifyCode")
	public Map<String, Object> vefifyCode(String code, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultCode", 1);
		Object sessionCode = request.getSession().getAttribute(CacheHelp.KAPTCHA_SESSION_KEY);
		String piccode = sessionCode == null ? "" : sessionCode.toString();
		if (code == null || !(code.toLowerCase().equals(piccode.toLowerCase()))) {
			resultMap.put("resultMsg", "验证码错误！");
		}else{
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		}
		return resultMap;
	}
}