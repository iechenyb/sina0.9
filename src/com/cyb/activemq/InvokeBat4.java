package com.cyb.activemq;

import java.io.IOException;

public class InvokeBat4 {
    public void runbat(String batName) {
        String cmd = "cmd /c start F:\\database_backup\\ngx_backup\\"+ batName + ".bat";// pass
        try {
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("child thread donn");
    }

    public static void main(String[] args) {
        InvokeBat4 test1 = new InvokeBat4();
        test1.runbat("backup_ngx");
        System.out.println("main thread");
    }
}
