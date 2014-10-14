package djohnson.invaders.entities;


import java.util.LinkedList;


import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.drawContent.DrawMan;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.sprites.SpriteMan;

final public class EntityMan {

	public Entity addEntity(Entity _entity) {
	
		Entity e = _entity;
		
		list.add(e);
		numberOfEntities++;
		
		return e;
	}
	
	public void remove(Entity _entity, int removalCode, int index) {
		
		switch(removalCode) {
			case REMOVE_RESOURCES:
				//remove _entity image
				MyImageMan.Instance().Remove(_entity.getBoundingBox()[index].sprite.getMyImage());
					
				//remove _entity sprite
				SpriteMan.Instance().Remove(_entity.getBoundingBox()[index].sprite);
					
				//remove _entity bounding box
				BoundingBoxMan.Instance().RemoveBoundingBoxObj(_entity.getBoundingBox()[index]);
			
				if(_entity.getEntityName() == EntityName.Missile) {
					removalCode = REMOVE_ENTITY;
				}
				else 
					break;
			
			case REMOVE_ENTITY:
				//remove the entity from the draw manager list
				DrawMan.Instance().remove(_entity);
			
				//now remove the entity the missile collided with
				list.remove(_entity);
			
			default:
				return;
		
	}
		
		//decrement number of entities
		numberOfEntities--;
		
	}
	
	public Entity get(EntityName _name) {
		
		Entity found = null;
		
		for(Entity e : list)
			if(e.getEntityName() == _name)
				found = e;
		
		return found;
	}
	
	public void moveAll() {
		
		//change this list to array to avoid concurrent modification
		Entity[] _e = this.list.toArray(new Entity[0]);
		
		for(int i = 0; i < _e.length; i++)
			_e[i].move();
	}
	
	public void acceptAll(LinkedList<CollisionVisitor> visitor) {
		
		//change both lists to arrays to avoid concurrent modification
		Entity[] _e = list.toArray(new Entity[0]);
		CollisionVisitor[] c = visitor.toArray(new CollisionVisitor[0]);
		
		for(int i = 0; i < _e.length; i++) 
			for(int j = 0; j < c.length; j++)
				_e[i].accept(c[j]);
	}
	
	public void animate() {
		
		Entity[] _e = this.list.toArray(new Entity[0]);
		
		for(int i = 0; i < _e.length; i++)
			_e[i].animate();
		
	}
	
	public int size() { 
		return numberOfEntities;
	}
	
	public static EntityMan Instance() {
		
		if(instance == null)
			instance = new EntityMan();
		return instance;
		
	}
	
	private EntityMan() {
		
		list = new LinkedList<Entity>();
		numberOfEntities = 0;
		instance = null;
	}
	
	public final int REMOVE_RESOURCES = 0;
	public final int REMOVE_ENTITY = 1;

	private LinkedList<Entity> list;
	private int numberOfEntities;
	private static EntityMan instance;
}
