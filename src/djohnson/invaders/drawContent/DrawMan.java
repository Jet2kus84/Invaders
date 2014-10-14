package djohnson.invaders.drawContent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

/*
 * 	This class will only add, remove, and draw all game content
 */
final public class DrawMan {
	
	public void addDrawComponent(Draw _drawComponent) {
		
		list.add(_drawComponent);
		
		numberOfDrawComponents++;
		
	}
	
	public void remove(Draw _drawComponent) {
		
		list.remove(_drawComponent);
		
		numberOfDrawComponents--;
		
	}
	
	public void DrawGameComponents(Graphics2D g2d, ImageObserver _observer) {
		
		//give each component a broader stroke
		g2d.setStroke(new BasicStroke(1.5f));
		
		//create a new Rendering hint
		RenderingHints rh = 
				new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
		
		//increase rendering speed/quality
		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_SPEED);
		
		//set rendering hints for all game components drawn
		g2d.setRenderingHints(rh); 

		//change list into an array to avoid concurrent modification
		Draw[] d = list.toArray(new Draw[0]);
		
		//draw all components inheriting drawContent
		for(Draw a : d)
			a.drawContent(g2d, _observer); 
		
		/*for(Draw d : list)
			d.drawContent(g2d, _observer);*/
	}
	
	public int size() { return numberOfDrawComponents; }
	
	public static DrawMan Instance() {
		
		if(instance == null) 
			instance = new DrawMan();
		return instance;
		
	}
	
	private DrawMan() {
		
		list = new LinkedList<Draw>();
		instance = null;
		numberOfDrawComponents = 0;
	}
	
	private LinkedList<Draw> list;
	private static DrawMan instance;
	private int numberOfDrawComponents;
	
}
