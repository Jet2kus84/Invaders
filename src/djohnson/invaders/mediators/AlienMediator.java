package djohnson.invaders.mediators;

import java.util.LinkedList;

public class AlienMediator implements Mediator {

	@Override
	public void sendMessage(boolean signal, AlienUser _user) {
		
		for(AlienUser a : this.users) {
			a.receive(signal); 
		}
	}
	
	@Override
	public void sendMessage(float value, AlienUser _user) {
		
		//all alien users must receive messages including the sender
		for(AlienUser a : this.users) {
			a.receive(value);
		}
	}
	
	@Override
	public void addUsers(AlienUser _user) {
		this.users.add(_user);
	}
	
	public static AlienMediator getInstance() {
		if(instance == null)
			instance = new AlienMediator();
		return instance;
	}
	
	private AlienMediator() {
		this.users = new LinkedList<AlienUser>();
	}
	
	private LinkedList<AlienUser> users;
	private static AlienMediator instance;
}
