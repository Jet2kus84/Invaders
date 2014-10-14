package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.CrabEntity;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.game.GameFields;

public class CrabVsWall implements CollisionVisitor {
	
	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Crab) {
			crab = (CrabEntity)_entity;
		}
		else
			return;
		
		//Crabs hit right-edge of the screen
		for(BoundingBox b : crab.getBoundingBox()) {
			if(b.pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()-54) {
			
				//send message to all aliens to move left
				crab.send(false);
				
				//send message to all aliens to move down
				crab.send(GameFields.dy);
				
			}
		}
		
		//Crabs hit left-edge of the screen
		for(BoundingBox b : crab.getBoundingBox()) {
			if(b.pos_x < BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinX()-15)  {
			
				//send message to all aliens to move right
				crab.send(true);
				
				//send message to all aliens to move down
				crab.send(GameFields.dy);
			}
		}
		return;
	}

	@Override
	public CollisionType getType() {
		return CollisionType.CrabVsWall;
	}

	CrabEntity crab;
}
