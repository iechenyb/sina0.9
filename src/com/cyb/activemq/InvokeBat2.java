package com.cyb.activemq;

import java.io.IOException;
import java.io.InputStream;

public class InvokeBat2 {
    public void runbat(String batName) {
        try {
            Process ps = Runtime.getRuntime().exec(batName);
            InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// ����㲻��Ҫ����������п���ע����
            }
            in.close();
            ps.waitFor();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child thread done");
    }

    public static void main(String[] args) {
    	System.out.println("startup activemq...");
        InvokeBat2 test1 = new InvokeBat2();
        String batName = "D:\\Ѹ������\\apache-activemq-5.13.3-bin\\apache-activemq-5.13.3\\bin\\win64\\activemq.bat";
        test1.runbat(batName);
        System.out.println("startup activemq success!");
    }
}