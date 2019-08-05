/**
 * 
 */
package com.chuangqi.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 验证码
 * @author wmk
 *
 */
public class RandomCodeUtil {
	//生成验证码key
	public static final String RD_CODE="rdCode";
	
    private byte[] imageBytes;
    
    private String str;//验证码  
    
    //验证码序列。
    private static final char[] randomSequence = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    private  Random random = new Random();
    
    private static volatile RandomCodeUtil randomCodeUtil;
    
    private RandomCodeUtil() {
        //init();//初始化属性  
    }
    
    public static RandomCodeUtil getInstance() {
    	if(randomCodeUtil==null){
    		synchronized (RandomCodeUtil.class) {
				randomCodeUtil=new RandomCodeUtil();
			}
    	}
        return randomCodeUtil;
    }
    
    public ByteArrayInputStream getImage() {
        return new ByteArrayInputStream(this.imageBytes);
    }
    
    public byte[] getImageBytes() {
        return this.imageBytes;
    }
    
    public String getString() {
        return this.str;
    }

    /**
     * 生成验证码数据
     */
    public RandomCodeUtil createData() {
        //       在内存中创建图象  
        int width = 85, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //       获取图形上下文  
        Graphics g = image.getGraphics();
        //       设定背景色  
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Times New Roman", Font.PLAIN, height - 2);
        //设置字体。
        g.setFont(font);
        
        //随机产生155条干扰线，使图象中的认证码不易被其它程序探测到  
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //       取随机产生的认证码(6位数字)  
        StringBuffer sRand = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(35);
            String rand = String.valueOf(randomSequence[index]);
            sRand.append(rand);
            // 将认证码显示到图象中  
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //      调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成  
            g.drawString(rand, 13 * i + 6, 16);
        }
        this.str = sRand.toString();
        //       图象生效  
        g.dispose();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageOutputStream imageOut = null;
        try {
            imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);
            this.imageBytes = output.toByteArray();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("验证码图片产生出现错误", e);
        }
        finally {
            if (null != output) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("生成验证码关闭输出流异常ByteArrayOutputStream", e);
                }
            }
            if (imageOut != null) {
                try {
                    imageOut.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("生成验证码关闭输出流异常ImageOutputStream", e);
                }
            }
        }
        return randomCodeUtil;
    }
    
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    public static void main(String[] args) {
    	for (int i = 0; i < 10; i++) {
    		//System.out.println(random.nextInt(1000));
		}
    	
	}
}
