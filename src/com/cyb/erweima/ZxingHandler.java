package com.cyb.erweima;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.cyb.date.DateUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingHandler {
	public static void main(String[] args) {
		String imgPath = System.getProperty("user.dir")+"/"+DateUtil.date2long14(new Date())+".png"; 
        System.out.println(imgPath);
        // 益达无糖口香糖的条形码  
        String contents = "6923450657713";
        int width = 200, height = 200;  
        ZxingHandler handler = new ZxingHandler();  
        handler.encode(contents, width, height, imgPath);  
        String code = handler.decode(System.getProperty("user.dir")+"/20160810110636258.png");//解析方法通用
        System.out.println("解析结果："+code);
	}
	/**
     * 生成二维码
     * 
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public void encode(String contents, int width, int height, String imgPath) {
        Map<EncodeHintType, Object> hints = new Hashtable<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);
 
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 解析二维码
     * 
     * @param imgPath
     * @return
     */
    public static String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
 
            Map<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");
 
            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
