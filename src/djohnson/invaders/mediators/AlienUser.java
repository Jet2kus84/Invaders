package djohnson.invaders.mediators;

import djohnson.invaders.entities.EntityName;
import djohnson.invaders.mediators.User;

/*
 *  interface for the objects using this mediator
 */

public abstract class AlienUser implements User {

	public AlienUser(AlienMediator _mediator, EntityName _name) {
		this.mediator = _mediator;
		this.name = _name;
	}
	
	protected AlienMediator mediator;
	protected EntityName name;
}
