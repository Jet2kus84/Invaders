package djohnson.invaders.collisions;

import java.util.LinkedList;


public class CollisionMan {

	public void attach(CollisionVisitor visitor) {
		
		list.add(visitor);
		numberOfObjects++;
	}
	
	public void dettach(CollisionVisitor visitor) {
		
		list.remove(visitor);
		numberOfObjects--;
	}
	
	//if getList works out get hid of getVisitor
	public CollisionVisitor getVisitor(CollisionType type) {
		
		CollisionVisitor found = null;
		
		for(CollisionVisitor c : list)
			if(c.getType() == type)
				found = c;
		return found;
	}

	public LinkedList<CollisionVisitor> getVisitorList() { return list; }
	
	public int size() {
		return numberOfObjects;
	}
	
	public static CollisionMan Instance() {
		
		if(instance == null)
			instance = new CollisionMan();
		return instance;
		
	}
	
	private CollisionMan() {
		
		list = new LinkedList<CollisionVisitor>();
		numberOfObjects = 0;
		instance = null;
	}
	
	private int numberOfObjects;
	private static CollisionMan instance;
	private LinkedList<CollisionVisitor> list;
}
