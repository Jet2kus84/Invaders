package djohnson.invaders.textures;

public class GameText2D implements GameTextures {

	@Override
	public String gText() { return text2D; }
	
	public GameText2D(String fileName) {
		text2D = fileName;
	}
	
	//this is actually the file where the text live
	private String text2D;

}
