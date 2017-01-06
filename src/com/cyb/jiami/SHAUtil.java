package com.cyb.jiami;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
//http://encode.chahuo.com/
public class SHAUtil {
	 /*** 
     * SHA加密 生成40位SHA码
     * @param 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr,String type) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance(type);//SHA SHA1
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("14714875392291471487535490");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + shaEncode(str,"SHA").equals("a42acdda36ae54864603d2e465b95412f46dedd9"));
        System.out.println("SHA后：" + shaEncode(str,"SHA"));
        System.out.println("SHA1后：" + shaEncode(str,"SHA-1"));
        System.out.println("SHA后：" + shaEncode(str,"SHA-1"));
        System.out.println("SHA224后：" + shaEncode(str,"SHA-224"));
        System.out.println("SHA后：" + shaEncode(str,"SHA-224"));
        System.out.println("SHA256后：" + shaEncode(str,"SHA-256"));
        System.out.println("SHA后：" + shaEncode(str,"SHA-256"));
        System.out.println("SHA384后：" + shaEncode(str,"SHA-384"));
        System.out.println("SHA后：" + shaEncode(str,"SHA-384"));
        System.out.println("SHA512后：" + shaEncode(str,"SHA-512"));
        System.out.println("SHA后：" + shaEncode(str,"SHA-512"));
        Mac mac = Mac.getInstance("HmacSHA1"); 
        System.out.println(mac.getProvider());
    }
    @SuppressWarnings("unused")
	private static byte[] getHmacSHA1(String src)  
            throws NoSuchAlgorithmException, UnsupportedEncodingException,  
            InvalidKeyException {  
        Mac mac = Mac.getInstance("HmacSHA1");  
        SecretKeySpec secret = new SecretKeySpec(  
                "chenyb".getBytes("UTF-8"), mac.getAlgorithm());  
        mac.init(secret);  
        return mac.doFinal(src.getBytes());  
    }  
    public static String sha1Digest(String filename) {  
        InputStream fis = null;  
        byte[] buffer = new byte[1024];  
        int numRead = 0;  
        MessageDigest sha1;  
        try {  
            fis = new FileInputStream(filename);  
            sha1 = MessageDigest.getInstance("SHA-1");  
            while ((numRead = fis.read(buffer)) > 0) {  
                sha1.update(buffer, 0, numRead);  
            }  
            return String.valueOf(sha1.digest());  
        } catch (Exception e) {  
            System.out.println("error");  
            return null;  
        } finally {  
            try {  
                if (fis != null) {  
                    fis.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
