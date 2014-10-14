package djohnson.invaders.collisions;

import djohnson.invaders.entities.Entity;

public interface CollisionVisitor {

	public void visit(Entity _entity);
	
	public CollisionType getType();
}
