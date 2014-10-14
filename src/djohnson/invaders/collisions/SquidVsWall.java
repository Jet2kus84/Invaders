package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.SquidEntity;
import djohnson.invaders.game.GameFields;

public class SquidVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Squid) {
			squid = (SquidEntity)_entity;
		}
		else
			return;
		
		//squids hit right-edge of the screen
		for(BoundingBox b : squid.getBoundingBox()) {
			if(b.pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()-54) {
			
				//send message to all aliens to move left
				squid.send(false);
				
				//send message to all aliens to move down
				squid.send(GameFields.dy);
			}
		}
		
		//squids hit left-edge of the screen
		for(BoundingBox b : squid.getBoundingBox()) {
			if(b.pos_x < BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinX()-15)  {
			
				//send message to all aliens to move right
				squid.send(true);
				
				//send message to all aliens to move down
				squid.send(GameFields.dy);
			}
		}
		return;
	}

	@Override
	public CollisionType getType() {
		return CollisionType.SquidVsWall;
	}

	SquidEntity squid;
}
