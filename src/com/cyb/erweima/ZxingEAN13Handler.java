package com.cyb.erweima;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class ZxingEAN13Handler {
	 /**
     * 条形码编码
     * 
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public void encodeEAN_13(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, height, null);
 
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void encode(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_8, codeWidth, height, null);
 
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 解析条形码
     * 
     * @param imgPath
     * @return
     */
    public String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public static void main(String[] args) {  
        String imgPath = System.getProperty("user.dir")+"/zxing_EAN13.png"; 
        System.out.println(imgPath);
        // 益达无糖口香糖的条形码  
        String contents = "9787111508243"; // 9787111508243 6923450657713
        int width = 105, height = 50;  
        ZxingEAN13Handler handler = new ZxingEAN13Handler();  
        handler.encodeEAN_13(contents, width, height, imgPath);  
        String code = handler.decode(imgPath);
        System.out.println("解析结果："+code);
    }  
}
