package djohnson.invaders.mediators;

public interface Mediator {

	public void sendMessage(boolean signal, AlienUser _user);
	public void sendMessage(float signal, AlienUser _user);
	public void addUsers(AlienUser _entity);
}
