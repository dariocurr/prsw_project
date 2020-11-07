package common;

import java.io.Serializable;

public interface IRenterObserver extends Serializable {
	
	public void update();
	
	public boolean isTrusted();
	
	public String getEmail();
	
	public String getPassword();
}
