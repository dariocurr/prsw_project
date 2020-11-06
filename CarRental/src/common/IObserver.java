package common;

import java.io.Serializable;

public interface IObserver extends Serializable {
	
	public void update();
	
}
