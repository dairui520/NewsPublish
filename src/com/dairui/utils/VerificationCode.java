package com.dairui.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.math.RandomUtils;
import org.apache.struts2.ServletActionContext;


public class VerificationCode extends HttpServlet {
	
	// 默认的验证码
	private static String VERIFY_CODE="1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
	
	
	/**
	 *  每请求一次HttpServlet    就会走这个方法  不管是GET 或者是POST
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		 resp.setHeader("Pragma","No-cache");     
		 resp.setHeader("Cache-Control","no-cache");     
		 resp.setDateHeader("Expires", 0);     
		 resp.setContentType("image/jpeg");
		 
		 String code=defaultVerifyCode(4); // 使用默认的字符源  4位验证码
		 
		 req.getSession().setAttribute("code", code); // 存入session中
		 OutputVerifyCodeImage(150, 40, resp.getOutputStream(), code);
		 	
	}
	
	
	/**
	 * 获取默认的字符源验证码
	 * @param size 验证码的个数
	 * @return 
	 */
	public String defaultVerifyCode(int size) {
		return defaultVerifyCode(VERIFY_CODE,size);		
	}
	
	/**
	 * 使用指定的源来生成验证码，如果为空就使用默认的字符源
	 * @param code 字符源
	 * @param size
	 * @return 验证码的字符
	 */
	public String defaultVerifyCode(String source ,int size) {
		if (source.isEmpty()) {
			source=VERIFY_CODE;
		}
		// 设定初始容量
		StringBuilder verifyBuilder=new StringBuilder(size);
		
		for (int i = 0; i < size; i++) {
			verifyBuilder.append(source.charAt(RandomUtils.nextInt(source.length())));
		}
		
		return verifyBuilder.toString();
		
	}
	
	
	/**
	 * 使用默认的字符源来生成验证码（4 位）图片流
	 * @param width
	 * @param height
	 * @param os
	 * @return
	 * @throws IOException 
	 */
	public String OutputVerifyCodeImage(int width,int height,OutputStream os,String code) throws IOException
	{
		Random random=new Random();
		
		
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		
		
		g.setColor(randomColor(200,250));
		g.drawRect(0, 0, width, height); // 画矩形的边框( 其实这个没什么用 而且很丑)
		
		g.setColor(randomColor(200,250)); // 设置画笔的颜色
		g.fillRect(0, 0, width, height); // 填充指定的矩形
		
		 //画随机线     
        for (int i=0;i<155;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(6) + 1;     
            int yl = random.nextInt(12) + 1;     
//            g.drawLine(x,y,x + xl,y + yl);   
            g.drawLine(x, y, x + xl + 40, y + yl + 20);
        }     
    
        //从另一方向画随机线     
        for (int i = 0;i < 70;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(12) + 1;     
            int yl = random.nextInt(6) + 1;     
            g.drawLine(x,y,x - xl,y - yl);     
        }
        
        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
        // 设置字体大小和子型
        int fontSize = height-4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g.setFont(font);
        for (int i = 0; i < 4; i++) {
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(code, (width-10)/4+5, height/2 + fontSize/2 - 10);
		}
        g.dispose(); // 画完 释放内存资源
        ImageIO.write(image, "jpg", os);
		return code;
		
	}
	
	private int getRandomIntColor() {
		Random random=new Random();
		
		int r=random.nextInt(256);
		int g=random.nextInt(256);
		int b=random.nextInt(256);
		return new Color(r, g, b).getRGB();
		
	}


	/**
	 * 生验证码图片文件
	 * @param code 
	 * @param outputFile 路径
	 * @param width
	 * @param height
	 * @return
	 */
	public void OutputImageFile(String code, File outputFile,int width,int height) {
		File file= outputFile.getParentFile();
		if (!file.exists()) {			
			/**
			 * mkdir()    只能在已经存在的目录中创建创建文件夹。
			 * mkdirs()  可以在不存在的目录中创建文件夹。  
			 */
			file.mkdirs();  // 创建父级目录 就是路径了 也叫做创建了文件夹了
		}
		try {
			outputFile.createNewFile();  // 在抽象路径上创建文件 就是文件的前面的各种文件夹了
			FileOutputStream fos=new FileOutputStream(outputFile);			
			OutputVerifyCodeImage(width, height, fos, code);
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 获取随机颜色 深色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color randomColor(int fc,int bc)
	{
		Random random=new Random();
		if (fc>255) fc=255;
		if (bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r, g, b);
			
	}
}
