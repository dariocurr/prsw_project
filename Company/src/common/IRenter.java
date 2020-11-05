package common;

import java.rmi.RemoteException;

public interface IRenter extends Observer {
	
	public boolean isTrusted() throws RemoteException;
	
}
