package djohnson.invaders.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import djohnson.invaders.sprites.FontSprite;
import djohnson.invaders.sprites.SpriteMan;
import djohnson.invaders.sprites.SpriteName;
import djohnson.invaders.sprites.SpriteType;
import djohnson.invaders.textures.GameFont;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.TextType;
import djohnson.invaders.textures.TextureMan;


public class MenuScreen extends Screen {
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw content to the screen
		draw(g);
	}
	
	@Override
	public ScreenType getScreenType() {
		return ScreenType.MenuScreen;
	}
	

	@Override
	void init() {
		
		//set the background color of the panel
		//figure out how to set pic as background
		setBackground(Color.BLACK);
				
		//preferred size for this particular panel
		setPreferredSize(new Dimension(SCREEN_SIZE_X, SCREEN_SIZE_Y));
	}

	@Override
	void draw(Graphics g) {
		
		//create a  2d graphic
		Graphics2D g2d = (Graphics2D) g;
		
		//draw text to screen
		//draw score
		FontSprite f = (FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore1, SpriteType.FontSprite);
		f.getGameFont().setSize(20);
		f.drawContent(g2d, this);
				
		//draw high score
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.HighScore, SpriteType.FontSprite);
		f.getGameFont().setSize(20);
		f.drawContent(g2d, this);
				
		//draw score 2
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore2, SpriteType.FontSprite);
		f.getGameFont().setSize(20);
		f.drawContent(g2d, this);
		
		//draw title
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.GameTitle, SpriteType.FontSprite);
		f.getGameFont().setSize(50);
		f.drawContent(g2d, this);
		
		
		//ensure that all graphics are up to date
		Toolkit.getDefaultToolkit().sync();
						
		//dispose of any resources
		g2d.dispose();	
				
	}
	
	public MenuScreen() {
		
		SCREEN_SIZE_X = 600;
		SCREEN_SIZE_Y = 600;
		
		//initialize any content
		init();
		
		//load content
		LoadContent();
		
	}
	
	private void LoadContent() {
		
		//load textures
		TextureMan.Instance().Load(TextType.Font, TextName.Jokerman, new GameFont("Jokerman", Font.TRUETYPE_FONT, 28));
		TextureMan.Instance().Load(TextType.Font, TextName.TimeNewRoman, new GameFont("TimesRoman", Font.TRUETYPE_FONT, 28));
		
		//load Font sprites
		SpriteMan.Instance().AddFontSprite(SpriteName.PlayerScore1, TextureMan.Instance().Get(TextName.TimeNewRoman), 	
				"Score<1>", 80, 30, Color.WHITE);
				
		SpriteMan.Instance().AddFontSprite(SpriteName.HighScore, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				"Hi-Score", 230, 30, Color.WHITE);
				
		SpriteMan.Instance().AddFontSprite(SpriteName.PlayerScore2, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				"Score<2>", 370, 30, Color.WHITE);
		
		SpriteMan.Instance().AddFontSprite(SpriteName.GameTitle, TextureMan.Instance().Get(TextName.Jokerman), 
				"Space Invaders", 150, 130, Color.GREEN);
	}
	
	private final int SCREEN_SIZE_X;
	private final int SCREEN_SIZE_Y;
	private static final long serialVersionUID = 3L;
	
}
