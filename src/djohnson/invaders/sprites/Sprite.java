package djohnson.invaders.sprites;

import djohnson.invaders.drawContent.Draw;
import djohnson.invaders.game.GameNode;

abstract class Sprite extends GameNode implements Draw
{
	public SpriteName name;

    public Sprite()
    {
    	this.SetNext(null);
        this.SetPrev(null);
    }
    
    public abstract SpriteType GetSpriteType();
    
    }
