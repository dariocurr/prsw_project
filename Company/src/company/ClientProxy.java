package company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.ICarRentalObservable;

public class ClientProxy {
	
	public static void main(String[] args) {
		
		try {
			ICarRentalObservable carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
			/*
			System.setProperty("java.security.policy", "/Company/src/company/sec.policy");
			System.setProperty("java.rmi.server.codebase", "/Company/bin");
			System.setSecurityManager(new RMISecurityManager());
			*/
			System.out.println(carRental.test());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
}
