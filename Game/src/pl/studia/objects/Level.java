package pl.studia.objects;

import java.util.ArrayList;
import java.util.List;

import pl.studia.objects.ingame.Plat1;
import pl.studia.objects.ingame.Platform;
import pl.studia.objects.ingame.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	public static final String TAG = Level.class.getName();
	// Game objects
	Plat1 p;
	public List<Platform> platforms;
	public Character character;
	
	/*
	 *	Enum - kazdy obiekt ma przydzielony konkretny kolor (R, G, B)
	 *  W pliku level znajduja sie pixele o konkretnych kolora odpowiadajace obiektom,
	 *  co pozwala na szybsza generacje leveli
	 * 
	 */
	
	public enum BLOCK_TYPE {
		EMPTY(0, 0, 0), // black
		PLATFORM(0, 0, 255),
		SPAWN(255, 255, 255); //white spawn point
		
		private int color;
		/* Each channel is represented by 8 bits
		 * Color is represented by int value
		 * For example :
		 * (0, 0, 255) value is 255
		 * (0, 255, 0) value is 65280
		 * (255, 0, 0) value is 16711680
		 */
		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor (int color) {
			return this.color == color;
		}
		
		public int getColor () {
			return color;
		}
	}
	
	public Level (String filename) {
		init(filename);
	}
	
	private void init (String filename) {
		p = new Plat1();
		platforms = new ArrayList<Platform>();
		character = null;
		//Load the level image
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		//int lastPixel = -1;
		
		//Scan the image pixel by pixel from top-left to bottom-right
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				GameObject obj = null;
				float offsetHeight = 0;
				// height grows from bottom to top
				float baseHeight = pixmap.getHeight() - pixelY;
				//Get the current pixel color
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				
				/**
				 * Check if current pixel color is the same 
				 * as the color specified in the ENUM
				 */
				if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {

				}				
				else if (BLOCK_TYPE.PLATFORM.sameColor(currentPixel)) {
					obj = new Platform();
					offsetHeight = -2.5f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					//obj.bounds.set(0, 0, obj.dimension.x, obj.dimension.y);
					platforms.add((Platform)obj);
				}	
				else if(BLOCK_TYPE.SPAWN.sameColor(currentPixel)) {
					obj = new Character();
					offsetHeight = -3.0f;
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					character = (Character) obj;
				}
			}
		}
		//Free memory
		pixmap.dispose();	
	}
	
	//Render the generated items
	public void render (SpriteBatch batch) {
		character.render(batch);
		for(Platform p : platforms){
			p.render(batch);
		}
	}
	
	public void update(float deltaTime){
		character.update(deltaTime);
		for(Platform platform : platforms){
			platform.update(deltaTime);
		}
	}
	
}
