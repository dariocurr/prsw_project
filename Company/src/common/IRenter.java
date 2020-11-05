package common;

import java.rmi.RemoteException;

public interface IRenter extends IObserver {
	
	public boolean isTrusted() throws RemoteException;
	
}
