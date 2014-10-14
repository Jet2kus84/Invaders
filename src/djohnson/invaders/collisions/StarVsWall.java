package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.StarEntity;

public class StarVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Star) {
			star = (StarEntity)_entity;
		}
		else 
			return;
		
		//did the star collide with the right side of the wall?
		if(star.getBoundingBox()[0].pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()-42) {
			star.setMove(false);
		}
		
		//did the star collide with the left side of the wall?
		if((star.getBoundingBox()[0].pos_x) < BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinX())  {
			star.setMove(true);
		}
	}
	
	@Override
	public CollisionType getType() { return CollisionType.StarVsWall; }

	private StarEntity star;
}
