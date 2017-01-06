package com.cyb.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

 interface Converter extends EJBObject {
	   public double dollarToYen(double dollars) throws RemoteException;
	   public double yenToEuro(double yen) throws RemoteException;
	 
	   // Simple example.
	   public String helloEcho(String word) throws RemoteException;
}