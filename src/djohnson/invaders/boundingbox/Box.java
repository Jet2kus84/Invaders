package djohnson.invaders.boundingbox;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import djohnson.invaders.drawContent.Draw;
import djohnson.invaders.game.GameFields;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.Texture;
import djohnson.invaders.textures.TextureMan;


/*
 * 
 *  this class contains collision boxes
 */

public class Box implements Draw {

	//Set the fields for the collision box
    public void Set(BoundingBoxName _name, Rectangle _rect, Color _color)
    {
        this.name = _name;
        this.Rec = _rect;
        this.color = _color;
    }

    //create the collision box
    public void drawContent(Graphics2D spriteBatch, ImageObserver _observer)
    {
    	//must load image into TextureMan first in GameScreen class
        Texture collText = TextureMan.Instance().Get(TextName.White);
        
        //Use the white image as a base
        Image image = Toolkit.getDefaultToolkit().getImage((String) collText.getGameTexture().gText());
        
        if(GameFields.showBoundingBoxes) {
        	this.privDrawBox(spriteBatch, image, this.Rec, 0, this.color);
        }
        
    }
    
    //allow color of box to change
    public void setColor(Color _color)     { color = _color; }
    
    //allow access to game name
    public BoundingBoxName getGameName()   { return name;    }
    
    //allow access to main rec
    public Rectangle getRec()     		   { return Rec;     }
    
    public Box()
    {
        this.name = BoundingBoxName.Not_Initialized;
        Rec = null;
        this.color = Color.WHITE;
    }
    
    private void privDrawBox(Graphics2D _batch, Image _text, Rectangle _rec, int thickness, Color _color)
    {
        // Draw top line
    	_batch.setColor(_color);
        _batch.drawRect((int)_rec.getX(), (int)_rec.getY(), (int)_rec.getWidth(), thickness);

        // Draw left line
        _batch.setColor(_color);
        _batch.drawRect((int)_rec.getX(), (int)_rec.getY(), thickness, (int)_rec.getHeight());

        // Draw right line
        _batch.setColor(_color);
        _batch.drawRect((int)_rec.getX() + ((int)_rec.getWidth() - thickness), (int)_rec.getY(), thickness, (int)_rec.getHeight());
        
        // Draw bottom line
        _batch.setColor(_color);
        _batch.drawRect((int)_rec.getX(), (int)_rec.getY() + (int)_rec.getHeight() - thickness, (int)_rec.getWidth(), thickness);
       
    }
    
    private BoundingBoxName name;
    private Rectangle Rec;
    private Color color;
  
}
