package common;

import java.io.Serializable;

public interface IRenterObserver extends Serializable {
	
	public boolean update();
	
	public boolean isTrusted();
	
	public String getEmail();
	
	public String getPassword();
}
