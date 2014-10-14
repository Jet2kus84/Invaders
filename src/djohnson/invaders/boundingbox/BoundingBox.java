package djohnson.invaders.boundingbox;

import java.awt.Color;
import java.awt.Rectangle;

import djohnson.invaders.game.GameNode;
import djohnson.invaders.sprites.GameSprite;

public class BoundingBox extends GameNode {

	private float col_off_x;
    private float col_off_y;
    private Rectangle colRect;
    public GameSprite sprite;

    public BoundingBoxName name;
    public Box colBox;
    public float pos_x;
    public float pos_y;
    public float dir = 1.0f;

    private void UpdateValues(float _x, float _y) {
    	
        this.colRect.x = (int)_x;
        this.colRect.y = (int)_y;

        this.colBox.getRec().x = (int)_x;
        this.colBox.getRec().y = (int)_y;
    }

    public BoundingBox() {
    	
        name = BoundingBoxName.Not_Initialized;
        colBox = new Box();
    }

    public void Set(BoundingBoxName _name, GameSprite _sprite, Rectangle _colRect, Color colBoxColor) {
    	
        this.name = _name;
        this.sprite = _sprite;
        this.colRect = _colRect;

        this.colBox.Set(_name, colRect, colBoxColor);

        if (sprite != null) {
        	
            this.pos_x = sprite.getMyImage().getSource().x;
            this.pos_y = sprite.getMyImage().getSource().y;
        }

        this.col_off_x = this.colRect.x - this.pos_x;
        this.col_off_y = this.colRect.y - this.pos_y;
    }

    public void Update() {
    	
        if (sprite != null) {

            this.sprite.getMyImage().getSource().x = (int)this.pos_x;
            this.sprite.getMyImage().getSource().y = (int)this.pos_y;
        }

        UpdateValues(this.pos_x + this.col_off_x, this.pos_y + this.col_off_y);
    }
}
