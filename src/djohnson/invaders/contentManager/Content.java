package djohnson.invaders.contentManager;

import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.AlienMissileVsWall;
import djohnson.invaders.collisions.CollisionMan;
import djohnson.invaders.collisions.CrabVsMissile;
import djohnson.invaders.collisions.CrabVsWall;
import djohnson.invaders.collisions.MissileVsWall;
import djohnson.invaders.collisions.OctopusVsMissile;
import djohnson.invaders.collisions.OctopusVsWall;
import djohnson.invaders.collisions.ShipVsWall;
import djohnson.invaders.collisions.SquidVsMissile;
import djohnson.invaders.collisions.SquidVsWall;
import djohnson.invaders.collisions.StarVsMissile;
import djohnson.invaders.collisions.StarVsWall;
import djohnson.invaders.collisions.UFOVsMissile;
import djohnson.invaders.drawContent.DrawMan;
import djohnson.invaders.entities.CrabEntity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.OctopusEntity;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.entities.SquidEntity;
import djohnson.invaders.entities.StarEntity;
import djohnson.invaders.entities.UFOEntity;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.mediators.AlienMediator;
import djohnson.invaders.scoringSystem.ScoreMan;
import djohnson.invaders.sound.AlienHitSound;
import djohnson.invaders.sound.AlienWalkSound;
import djohnson.invaders.sound.ShipHitSound;
import djohnson.invaders.sound.ShipShootSound;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.UFOHitSound;
import djohnson.invaders.sound.UFOSound;
import djohnson.invaders.sprites.FontSprite;
import djohnson.invaders.sprites.GameSprite;
import djohnson.invaders.sprites.SpriteMan;
import djohnson.invaders.sprites.SpriteName;
import djohnson.invaders.sprites.SpriteType;
import djohnson.invaders.textures.GameFont;
import djohnson.invaders.textures.GameText2D;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.TextType;
import djohnson.invaders.textures.TextureMan;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class Content {

	public void loadTextures() {
		
		//load in a textures
		TextureMan.Instance().Load(TextType.Text2D, TextName.Star, new GameText2D("gameImages/star.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.White, new GameText2D("gameImages/1x1White.png"));
		TextureMan.Instance().Load(TextType.Text2D,TextName.Ship, new GameText2D("gameImages/ship2.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Missiles, new GameText2D("gameImages/missile.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Crab, new GameText2D("gameImages/crab.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Crab2, new GameText2D("gameImages/crab2.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Octopus, new GameText2D("gameImages/octopus.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Octopus2, new GameText2D("gameImages/octopus2.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Squid, new GameText2D("gameImages/squid.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.Squid2, new GameText2D("gameImages/squid2.png"));
		TextureMan.Instance().Load(TextType.Text2D, TextName.UFO, new GameText2D("gameImages/ufo.png"));
	}
	
	public void loadFonts() {
		
		//load in fonts
		TextureMan.Instance().Load(TextType.Font, TextName.TimeNewRoman, 
				new GameFont("TimesRoman", Font.TRUETYPE_FONT, 28));
		
		TextureMan.Instance().Load(TextType.Font, TextName.Stencil, 
				new GameFont("Stencil", Font.TRUETYPE_FONT, 28));
		
		TextureMan.Instance().Load(TextType.Font, TextName.Courier, 
				new GameFont("Courier", Font.TRUETYPE_FONT, 28));
		
		TextureMan.Instance().Load(TextType.Font, TextName.Jokerman, 
				new GameFont("Jokerman", Font.TRUETYPE_FONT, 28));
	}
	
	public void loadImage() {
		
		//load images
		MyImageMan.Instance().AddImages(TextureMan.Instance().Get(TextName.Star), 50, 50, 42, 42);
		MyImageMan.Instance().AddImages(TextureMan.Instance().Get(TextName.Ship), 100, 560, 50, 31);
		MyImageMan.Instance().AddImages(TextureMan.Instance().Get(TextName.UFO), -100, 10, 60, 20);
		
	}
	
	public void loadSprite() {
		
										/**load game sprites**/
		
		//load star sprite
		SpriteMan.Instance().AddGameSprite(SpriteName.Star_Test, TextName.Star, MyImageMan.Instance());
		
		//load ship sprite
		SpriteMan.Instance().AddGameSprite(SpriteName.NonAnimation_Ship, TextName.Ship, MyImageMan.Instance());
		
		//load ufo sprite
		SpriteMan.Instance().AddGameSprite(SpriteName.NonAnimation_UFO, TextName.UFO, MyImageMan.Instance());
	}
	
	public void loadFontSprites() {
		
		//load Font sprites
		SpriteMan.Instance().AddFontSprite(SpriteName.PlayerScore1, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				"Score<1>", numStart, 15, Color.WHITE);
		
		SpriteMan.Instance().AddFontSprite(SpriteName.ActualScore, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				Integer.toString(ScoreMan.getInstance().getScore()), numStart, 30, Color.WHITE);
				
		SpriteMan.Instance().AddFontSprite(SpriteName.HighScore, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				"Hi-Score", numStart+150, 15, Color.WHITE);
		
		SpriteMan.Instance().AddFontSprite(SpriteName.ActualHighScore, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				Integer.toString(ScoreMan.getInstance().getHighScore()), numStart+150, 30, Color.WHITE);
				
		SpriteMan.Instance().AddFontSprite(SpriteName.PlayerScore2, TextureMan.Instance().Get(TextName.TimeNewRoman), 
				"Score<2>", numStart+(220 + 90), 15, Color.WHITE);
	}
	
	public void loadBoundingBox() { 
		
									/** load BoundingBoxs **/
		
		//create wall
		BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.WallBox, null, 
				new Rectangle(0, 0, SCREEN_SIZE_X, SCREEN_SIZE_Y), Color.RED);
		
		//load star BoundingBox
		BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.StarBox, 
				(GameSprite)SpriteMan.Instance().Get(SpriteName.Star_Test, 
				SpriteType.GameSprite), new Rectangle(
				MyImageMan.Instance().Get(TextName.Star).getSource().x, 
				MyImageMan.Instance().Get(TextName.Star).getSource().y, 
				MyImageMan.Instance().Get(TextName.Star).getWidth(), 
				MyImageMan.Instance().Get(TextName.Star).getHeight()), Color.RED);
		
		//load ship BoundingBox
		BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.ShipBox, 
				(GameSprite)SpriteMan.Instance().Get(SpriteName.NonAnimation_Ship,
				SpriteType.GameSprite), new Rectangle( 
				MyImageMan.Instance().Get(TextName.Ship).getSource().x,
				MyImageMan.Instance().Get(TextName.Ship).getSource().y,
				MyImageMan.Instance().Get(TextName.Ship).getWidth(),
				MyImageMan.Instance().Get(TextName.Ship).getHeight()), Color.RED);
		
		//load UFO BoundingBox
		BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.UFOBox, 
				(GameSprite)SpriteMan.Instance().Get(SpriteName.NonAnimation_UFO, SpriteType.GameSprite), 
				new Rectangle(
				MyImageMan.Instance().Get(TextName.UFO).getSource().x+9,
				MyImageMan.Instance().Get(TextName.UFO).getSource().y+20,
				MyImageMan.Instance().Get(TextName.UFO).getWidth(),
				MyImageMan.Instance().Get(TextName.UFO).getHeight()), Color.RED);
		
	}
	
	public void loadEntities() {
		
		//load Entities
		EntityMan.Instance().addEntity(new ShipEntity());
		EntityMan.Instance().addEntity(new StarEntity());
		EntityMan.Instance().addEntity(new UFOEntity());
		EntityMan.Instance().addEntity(new CrabEntity());
		EntityMan.Instance().addEntity(new SquidEntity());
		EntityMan.Instance().addEntity(new OctopusEntity());
		
	}
	
	public void loadCollisionReactions() {
		
		//load Collision Reactions
		CollisionMan.Instance().attach(new StarVsWall());
		CollisionMan.Instance().attach(new ShipVsWall());
		CollisionMan.Instance().attach(new MissileVsWall());
		CollisionMan.Instance().attach(new StarVsMissile());
		CollisionMan.Instance().attach(new UFOVsMissile());
		CollisionMan.Instance().attach(new CrabVsWall());
		CollisionMan.Instance().attach(new CrabVsMissile());
		CollisionMan.Instance().attach(new SquidVsWall());
		CollisionMan.Instance().attach(new SquidVsMissile());
		CollisionMan.Instance().attach(new OctopusVsMissile());
		CollisionMan.Instance().attach(new OctopusVsWall());
		CollisionMan.Instance().attach(new AlienMissileVsWall());
	}
	
	public void loadDrawComponents() {
		
		/**load draw components **/
		//draw ship
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.Ship));
		
		//draw UFO
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.UFO));
		
		//draw wall 
		DrawMan.Instance().addDrawComponent(BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox);
		
		//draw test star box & image
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.Star));
		
		//draw alien crab
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.Crab));
		
		//draw alien squid
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.Squid));
		
		//draw alien octopus
		DrawMan.Instance().addDrawComponent(EntityMan.Instance().get(EntityName.Octopus));
		
		//draw score 1
		FontSprite f = (FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore1, SpriteType.FontSprite);
		f.getGameFont().setSize(wordSize);
		
		DrawMan.Instance().addDrawComponent((FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore1, 
				SpriteType.FontSprite));
		
		//draw actual score
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.ActualScore, SpriteType.FontSprite);
		f.getGameFont().setSize(wordSize);
		
		DrawMan.Instance().addDrawComponent((FontSprite)SpriteMan.Instance().Get(SpriteName.ActualScore, 
				SpriteType.FontSprite));
		
		//draw high score
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.HighScore, SpriteType.FontSprite);
		f.getGameFont().setSize(wordSize);
		
		DrawMan.Instance().addDrawComponent((FontSprite)SpriteMan.Instance().Get(SpriteName.HighScore, 
				SpriteType.FontSprite));
		
		//draw actual high score
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.ActualHighScore, SpriteType.FontSprite);
		f.getGameFont().setSize(wordSize);
		
		DrawMan.Instance().addDrawComponent((FontSprite)SpriteMan.Instance().Get(SpriteName.ActualHighScore, 
				SpriteType.FontSprite));
		
		//draw score 2
		f = (FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore2, SpriteType.FontSprite);
		f.getGameFont().setSize(wordSize);
		DrawMan.Instance().addDrawComponent((FontSprite)SpriteMan.Instance().Get(SpriteName.PlayerScore2, 
				SpriteType.FontSprite));
		
	}
	
	public void loadSound() {
		
		SoundMan.Instance().addSound(new AlienHitSound());
		SoundMan.Instance().addSound(new AlienWalkSound());
		SoundMan.Instance().addSound(new ShipHitSound());
		SoundMan.Instance().addSound(new ShipShootSound());
		SoundMan.Instance().addSound(new UFOHitSound());
		SoundMan.Instance().addSound(new UFOSound());
		
	}
	
	public void loadMediators() {
		
		AlienMediator.getInstance().addUsers((SquidEntity)EntityMan.Instance().get(EntityName.Squid));
		AlienMediator.getInstance().addUsers((CrabEntity)EntityMan.Instance().get(EntityName.Crab));
		AlienMediator.getInstance().addUsers((OctopusEntity)EntityMan.Instance().get(EntityName.Octopus));
	}
	public static Content Instance() {

		if(instance == null) 
			instance = new Content();
		return instance;
	}
	
	private Content() {
		
		instance = null;
		numStart = 140;
		SCREEN_SIZE_X = 700;
		SCREEN_SIZE_Y = 600;
		wordSize = 19;
	}
	
	private static Content instance;
	private int numStart;
	private int wordSize;
	private final int SCREEN_SIZE_X;
	private final int SCREEN_SIZE_Y;
	
}
