package com.cyb.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

	public class ConverterEJB implements SessionBean {
		 
		   private static final long serialVersionUID = 1L;
		  
		   public double dollarToYen(double dollars) {
		       return dollars * 121.6000;
		   }
		 
		   public double yenToEuro(double yen) {
		       return yen * 0.0077;
		   }
		  
		   public String helloEcho(String word) {
		       System.out.println("Someone called 'Hello Echo Successed!'");
		       return "*********" + word + "*********";
		   }
		  
		   public void ejbCreate() {
		       System.out.println("Ejb 4 is creating!...");
		   }
		  
		   public void ejbPostCreate() {
		   }
		  
		   public void ejbActivate() throws EJBException, RemoteException {
		   }
		 
		   public void ejbPassivate() throws EJBException, RemoteException {      
		   }
		 
		   public void ejbRemove() throws EJBException, RemoteException {
		   }
		 
		   public void setSessionContext(SessionContext arg0) throws EJBException,
		   RemoteException {      
		   }
		 
}
