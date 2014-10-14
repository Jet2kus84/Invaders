package djohnson.invaders.sprites;

public class SpriteAnimator {

     //allow frame cycles/pause/resume & start at a specific frame
     /*public void animate()
     {
         
    	 if(GameTime.currentGameTime() - timeLapse > 200) {

			 entity.getEntity().sprite = this.sprite[currentFrame];
	            
	            currentFrame++;
	            if (currentFrame == 2)
	                currentFrame = 0;
		
			timeLapse = GameTime.currentGameTime();
    	 }
     }
     
	public void set(CrabEntity _entity, Sprite _first, Sprite _second) {
		
		
		this.entity = _entity;
		
		if( (_first instanceof GameSprite) && (_second instanceof GameSprite) ) {
			this.firstImage = _first;
			this.secondImage = _second;
		}
		else
			throw new IllegalArgumentException();
	}

	public static SpriteAnimator Instance() {
		if(instance == null) 
			instance = new SpriteAnimator();
		return instance;
		
	}
	private SpriteAnimator() {
		
        timeLapse = 0;
        currentFrame = 0;
        millisecondPerFrame = -1;
	}
	
	private static SpriteAnimator instance;
    public int millisecondPerFrame;
   
    private int currentFrame;
    private long timeLapse;
	private Sprite firstImage;
	private Sprite secondImage;
	private CrabEntity entity;
	private GameSprite[] sprite = { 
			
			(GameSprite) firstImage, 
			(GameSprite) secondImage 
			
			};
	*/
}
