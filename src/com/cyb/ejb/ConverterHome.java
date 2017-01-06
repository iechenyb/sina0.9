package com.cyb.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ConverterHome extends EJBHome {
	   Converter create() throws CreateException, RemoteException;
}
