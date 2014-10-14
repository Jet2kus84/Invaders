package djohnson.invaders.screens;

import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class Screen extends JPanel {


	public abstract ScreenType getScreenType();
	
	abstract void draw(Graphics g);
	
	abstract void init();
	
	private static final long serialVersionUID = 2L;
}
