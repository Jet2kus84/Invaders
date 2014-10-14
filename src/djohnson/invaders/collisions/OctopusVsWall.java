package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.OctopusEntity;
import djohnson.invaders.game.GameFields;

public class OctopusVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Octopus) {
			octopus = (OctopusEntity)_entity;
		}
		else
			return;
		
		//Octopus hit right-edge of the screen
		for(BoundingBox b : octopus.getBoundingBox()) {
			if(b.pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()-54) {
			
				//send message to all aliens to move left
				octopus.send(false);
				
				//send message to all aliens to move down
				octopus.send(GameFields.dy);
				
			}
		}
		
		//octopus hit left-edge of the screen
		for(BoundingBox b : octopus.getBoundingBox()) {
			if(b.pos_x < BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinX()-15)  {
			
				//send message to all aliens to move right
				octopus.send(true);
				
				//send message to all aliens to move down
				octopus.send(GameFields.dy);
			}
		}
		
		//clean up, if there are no more octopus entities remove them from the collision list
		if(GameFields.numberOfOctopus == 0) {
			CollisionMan.Instance().dettach(this);
		}
		else
			return;
	}

	@Override
	public CollisionType getType() {
		return CollisionType.OctopusVsWall;
	}

	OctopusEntity octopus;

}
