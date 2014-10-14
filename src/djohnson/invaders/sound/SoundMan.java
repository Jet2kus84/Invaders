package djohnson.invaders.sound;

import java.util.LinkedList;

public class SoundMan {

	public void addSound(Sound _sound) {
		list.add(_sound);
	}
	
	public void remove(Sound _sound) {
		list.remove(_sound);
	}
	
	public Sound get(SoundName name) {
		
		Sound found = null;
		for(Sound s : list)
			if(s.getSoundName() == name)
				found = s;
		return found;
	}
	
	public int size() {
		return list.size();
	}
	
	public static SoundMan Instance() {
		
		if(instance == null)
			instance = new SoundMan();
		return instance;
	}
	
	private SoundMan() {
		
		instance = null;
		list = new LinkedList<Sound>();
	}
	
	private static SoundMan instance;
	private LinkedList<Sound> list;
}
