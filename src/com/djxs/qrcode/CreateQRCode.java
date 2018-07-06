package com.djxs.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode{

	public static void main(String[] args) throws IOException {
		int ver =20;
		//创建对象
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeVersion(ver);
		qrcdoe.setQrcodeEncodeMode('B');
		int  imageSize = 67+12*(ver-1);
		//图片缓存对象
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
		//创建画板
		Graphics2D gs = bufferedImage.createGraphics();
		//设置背景色
		gs.setBackground(Color.WHITE);
		//设置前景色
		gs.setColor(Color.BLACK);
		//清空画板
		gs.clearRect(0, 0, imageSize, imageSize);
		
		
		String str = "BEGIN:VCARD\r\n" +
		   "PHOTO;VALUE=uri:http://p1.so.qhimgs1.com/bdr/_240_/t0164879031a42312dd.jpg\n "+ 
		   "FN:姓名:宋佳育\r\n"+
		   "ORG:学校：河北科技师范学院	院系:数学与信息科技学院\r\n"+
		   "TITLE:学生\r\n" + 
		   "TEL;WORK;VOICE:2715123\r\n"+
		   "TEL;HOME;VOICE:15033508895\r\n"+
		   "TEL;CELL;VOICE:18333956285\r\n"+
		   "ADR;WORK:河北科技师范学院\r\n"+
		   "ADR;HOME:河北邢台\r\n"+
		   "URL:http://www.baidu.com\r\n "+
		   	"EMAIL;HOME:2292641531@qq.com\r\n" + 
		   "END:VCARD";
		
		int startR = 255;
		int startG = 0;
		int startB = 0;
		

		int endR = 0;
		int endG = 0;
		int endB = 255;
		//得到二位数组  可以确定二维码那个点有颜色
		boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes("UTF-8"));
		
		System.out.println(calQrcode.length);
		int x = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int num1 = startR + (endR - startR) * (i+1)/calQrcode.length;
					int num2 = startG + (endG - startG) * (i+1)/calQrcode.length;
					int num3 = startB + (endB - startB) * (i+1)/calQrcode.length;
					
					Color color = new Color(num1, num2, num3);
					
					gs.setColor(color);
					//填充颜色
					gs.fillRect(i*3+x, j*3+x, 3, 3);
				}
			}
		}
		Image logo =ImageIO.read(new File("d:/logo.png"));
		int logoSize=60;
		int logoX= (imageSize-logoSize)/2;
		gs.drawImage(logo, logoX, logoX, logoSize, logoSize,null);
		
		gs.dispose();
		bufferedImage.flush();
		//输出二维码图片
		ImageIO.write(bufferedImage, "png", new File("d:/qrcode1.png"));
		System.out.println("ok");
	}
}
