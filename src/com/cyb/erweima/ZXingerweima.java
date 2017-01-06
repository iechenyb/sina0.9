package com.cyb.erweima;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class ZXingerweima {
	private static final int BLACK = 0xFF000000;  
	   private static final int WHITE = 0xFFFFFFFF;  
	    
	   private ZXingerweima() {}  
	    
	      
	   public static BufferedImage toBufferedImage(BitMatrix matrix) {  
	     int width = matrix.getWidth();  
	     int height = matrix.getHeight();  
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	     for (int x = 0; x < width; x++) {  
	       for (int y = 0; y < height; y++) {  
	         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
	       }  
	     }  
	     return image;  
	   }  
	    
	   public static void writeToFile(BitMatrix matrix, String format, File file)  
	       throws IOException {  
	     BufferedImage image = toBufferedImage(matrix);  
	     if (!ImageIO.write(image, format, file)) {  
	       throw new IOException("Could not write an image of format " + format + " to " + file);  
	     }  
	   }  
	    
	      
	   public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)  
	       throws IOException {  
	     BufferedImage image = toBufferedImage(matrix);  
	     if (!ImageIO.write(image, format, stream)) {  
	       throw new IOException("Could not write an image of format " + format);  
	     }  
	   }  
	   public static void main(String[] args) {
		   try {
		        String content = "http://www.baidu.com"; 		        
		     	SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmssSSS" );
			   	Date d=new Date();
			   	String str=sdf.format(d);
			    String path = System.getProperty("user.dir")+"/"; 
		        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		        Map hints = new HashMap();  
		        //内容所使用编码  
		        hints.put(EncodeHintType.CHARACTER_SET, "gb2312");  
		        BitMatrix bitMatrix = multiFormatWriter.encode(content,BarcodeFormat.QR_CODE, 200, 200, hints);  
		        //生成二维码  
		        File outputFile = new File(path,str+".jpg"); 
		        ZXingerweima.writeToFile(bitMatrix, "jpg", outputFile);  
		        System.out.println(path);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
