package com.cyb;

import com.cyb.computer.ComputerUtil;

public class Contanst {
	public static int port = 8082;
	public static String pushUrl  ;
	public String name ="chenyb";
	static{
		pushUrl = "http://"+ComputerUtil.getRealIP()+":"+port;
	}
}
