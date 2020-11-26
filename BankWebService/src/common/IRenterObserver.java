package common;

import java.io.Serializable;

/** Interface to manage all the informations about the renter */
public interface IRenterObserver extends Serializable {
	/** Not used*/
	public boolean update();
	
	/** Verify if the renter is trusted
	 * @return true if the renter is trusted, false otherwise */
	public boolean isTrusted();
	
	/** Get the renter's email
	 * @return the renter's email */
	public String getEmail();
	
	/** Get the renter's password
	 * @return the renter's password */
	public String getPassword();
}
