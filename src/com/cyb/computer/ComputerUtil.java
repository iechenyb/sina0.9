package com.cyb.computer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ComputerUtil {
	private static InetAddress addr;
	static {
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public static String getRealIP(){
		String ip=addr.getHostAddress().toString();//获得本机IP
		return ip;
	}
	public static String getDefaultIP(){
		return "127.0.0.1";
	}
	public static String getDefaultIPDomain(){
		return "localhost";
	}
	public static String getName(){
		String computerName=addr.getHostName().toString();//获得本机IP
		return computerName;
	}
	public static String getMacAddress(String host) {
		String mac ="";
		StringBuffer sb = new StringBuffer();
		try {
		NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress
		.getByName(host));
		byte[] macs = ni.getHardwareAddress();
		for (int i = 0; i < macs.length; i++) {
		mac = Integer.toHexString(macs[i] & 0xFF);
		if (mac.length() == 1) {
		mac = '0' + mac;
		}
		sb.append(mac +"-");
		}
		} catch (SocketException e) {
		e.printStackTrace();
		} catch (UnknownHostException e) {
		e.printStackTrace();
		}
		mac = sb.toString();
		mac = mac.substring(0, mac.length() - 1);
		return mac;

		}

	public static void main(String[] args) {
		System.out.println(ComputerUtil.getRealIP());
		System.out.println(ComputerUtil.getName());
		System.out.println(getMacAddress(ComputerUtil.getRealIP()));
	}
}
