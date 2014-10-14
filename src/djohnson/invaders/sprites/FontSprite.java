package djohnson.invaders.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import djohnson.invaders.textures.GameFont;
import djohnson.invaders.textures.Texture;


public class FontSprite extends Sprite {
	
	@Override
	public SpriteType GetSpriteType() {
		return SpriteType.FontSprite;
	}

	@Override
	public void drawContent(Graphics2D _sprite, ImageObserver io) {
		
		_sprite.setFont(gfont);
		_sprite.setColor(color);
		_sprite.drawString(dataString, xLoc, yLoc);
		
	}
	
	public GameFont getGameFont() { 
		return gfont; 
	}
	
	public void setDataString(String message) {
		dataString = message;
	}
	
	public void setColor(Color _color) {
		this.color = _color;
	}
	
	public FontSprite(SpriteName _name, Texture text, String message, float _xLoc, float _yLoc, Color _color) {
    	
    	this.color = _color;
        this.name = _name;
        this.dataString = message;
        this.gfont = (GameFont)text.getGameTexture().gText();
        this.xLoc = _xLoc;
        this.yLoc = _yLoc;
    }

	private String dataString;
    private GameFont gfont;
    private Color color;
    private float xLoc;
    private float yLoc;

}
