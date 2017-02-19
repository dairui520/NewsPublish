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
	
	// Ĭ�ϵ���֤��
	private static String VERIFY_CODE="1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
	
	
	/**
	 *  ÿ����һ��HttpServlet    �ͻ����������  ������GET ������POST
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		 resp.setHeader("Pragma","No-cache");     
		 resp.setHeader("Cache-Control","no-cache");     
		 resp.setDateHeader("Expires", 0);     
		 resp.setContentType("image/jpeg");
		 
		 String code=defaultVerifyCode(4); // ʹ��Ĭ�ϵ��ַ�Դ  4λ��֤��
		 
		 req.getSession().setAttribute("code", code); // ����session��
		 OutputVerifyCodeImage(150, 40, resp.getOutputStream(), code);
		 	
	}
	
	
	/**
	 * ��ȡĬ�ϵ��ַ�Դ��֤��
	 * @param size ��֤��ĸ���
	 * @return 
	 */
	public String defaultVerifyCode(int size) {
		return defaultVerifyCode(VERIFY_CODE,size);		
	}
	
	/**
	 * ʹ��ָ����Դ��������֤�룬���Ϊ�վ�ʹ��Ĭ�ϵ��ַ�Դ
	 * @param code �ַ�Դ
	 * @param size
	 * @return ��֤����ַ�
	 */
	public String defaultVerifyCode(String source ,int size) {
		if (source.isEmpty()) {
			source=VERIFY_CODE;
		}
		// �趨��ʼ����
		StringBuilder verifyBuilder=new StringBuilder(size);
		
		for (int i = 0; i < size; i++) {
			verifyBuilder.append(source.charAt(RandomUtils.nextInt(source.length())));
		}
		
		return verifyBuilder.toString();
		
	}
	
	
	/**
	 * ʹ��Ĭ�ϵ��ַ�Դ��������֤�루4 λ��ͼƬ��
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
		g.drawRect(0, 0, width, height); // �����εı߿�( ��ʵ���ûʲô�� ���Һܳ�)
		
		g.setColor(randomColor(200,250)); // ���û��ʵ���ɫ
		g.fillRect(0, 0, width, height); // ���ָ���ľ���
		
		 //�������     
        for (int i=0;i<155;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(6) + 1;     
            int yl = random.nextInt(12) + 1;     
//            g.drawLine(x,y,x + xl,y + yl);   
            g.drawLine(x, y, x + xl + 40, y + yl + 20);
        }     
    
        //����һ���������     
        for (int i = 0;i < 70;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(12) + 1;     
            int yl = random.nextInt(6) + 1;     
            g.drawLine(x,y,x - xl,y - yl);     
        }
        
        // ������
        float yawpRate = 0.05f;// ������
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
        // ���������С������
        int fontSize = height-4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g.setFont(font);
        for (int i = 0; i < 4; i++) {
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(code, (width-10)/4+5, height/2 + fontSize/2 - 10);
		}
        g.dispose(); // ���� �ͷ��ڴ���Դ
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
	 * ����֤��ͼƬ�ļ�
	 * @param code 
	 * @param outputFile ·��
	 * @param width
	 * @param height
	 * @return
	 */
	public void OutputImageFile(String code, File outputFile,int width,int height) {
		File file= outputFile.getParentFile();
		if (!file.exists()) {			
			/**
			 * mkdir()    ֻ�����Ѿ����ڵ�Ŀ¼�д��������ļ��С�
			 * mkdirs()  �����ڲ����ڵ�Ŀ¼�д����ļ��С�  
			 */
			file.mkdirs();  // ��������Ŀ¼ ����·���� Ҳ�����������ļ�����
		}
		try {
			outputFile.createNewFile();  // �ڳ���·���ϴ����ļ� �����ļ���ǰ��ĸ����ļ�����
			FileOutputStream fos=new FileOutputStream(outputFile);			
			OutputVerifyCodeImage(width, height, fos, code);
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * ��ȡ�����ɫ ��ɫ
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
