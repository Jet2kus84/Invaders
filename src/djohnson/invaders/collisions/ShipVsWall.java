package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.ShipEntity;

public class ShipVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Ship) {
			this.ship = (ShipEntity) _entity;
		}
		else
			return;
		
		if(ship.getBoundingBox()[0].pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()-49) {
			ship.getBoundingBox()[0].pos_x -= offset;
		}
		
		if(ship.getBoundingBox()[0].pos_x < BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinX()) {
			ship.getBoundingBox()[0].pos_x += offset;
		}
	}

	@Override
	public CollisionType getType() {
		return CollisionType.ShipVsWall;
	}

	public ShipVsWall() {
		
		offset = 7;
	}
	
	private ShipEntity ship;
	private int offset;
}
