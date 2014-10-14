package djohnson.invaders.mediators;

public interface User {

	//send signal to move left/right
	public void send(boolean signal);
	
	//send signal to move down
	public void send(float value);
	
	//send signal to fire
	//public void sendFire(boolean signal);
	
	//receive move left/right notification
	public void receive(boolean signal);
	
	//receive move down notification
	public void receive(float value);
	
	//receive fire notification
	//public void receiveFire();
}
