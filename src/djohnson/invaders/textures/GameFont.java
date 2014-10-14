package djohnson.invaders.textures;

import java.awt.Font;

public class GameFont extends Font implements GameTextures {

	@Override
	public GameFont gText() 		 	 { return this; 		   } //Doesn't need to be called
	
	public void setSize(int _size) 		 { this.pointSize = _size; }
	
	public void setFont(String fontType) { this.name = fontType;   }
	
	public void setStyle(int fontStyle)  { this.style = fontStyle; }
	
	public GameFont(String fontType, int style, int size) {
		super(fontType, style, size);
	}
	
	private static final long serialVersionUID = 1L;
	
}
