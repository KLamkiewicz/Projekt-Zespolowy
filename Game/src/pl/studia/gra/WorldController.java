package pl.studia.gra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class WorldController {

	@SuppressWarnings("unused")
	private static final String TAG = WorldController.class.getName();
	
	public Sprite[] testSprites;
	public int 		selectedSprite;
	public Texture 	texture;
	
	public WorldController(){
		init();
	}
	
	
	/*Initialization in separate methon instead of in constructor,
	* this approach can greatly reduce 
	* the interruptions by the Garbage Collector*/
	private void init(){
		initTestObjects();
	}
	
	
	/*This method contain the game logic and will 
	* be called several hundred times per second. 
	* It requiers delta time so that it can apply 
	* updates to the game world according to the 
	* fraction of time */
	public void update (float deltaTime){
		updateTestObjects(deltaTime);
	}
	
	
	private void initTestObjects(){
		
	//Create new array for 5 sprites
	testSprites = new Sprite[5];
	texture = new Texture(Gdx.files.internal("data/test.jpg"));
	texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		
		//Create new sprites using just created texture
		for(int i=0;i<testSprites.length;i++){
			Sprite spr = new Sprite (texture);
			spr.setSize(100,100);
			spr.setOrigin(spr.getWidth()/2f, spr.getHeight()/2f);
			float randomX = MathUtils.random(100f,500f);
			float randomY = MathUtils.random(100f,300f);
			spr.setPosition(randomX, randomY);
			testSprites[i]=spr;
			
		}
	
	//Set first sprite as selected one
		selectedSprite = 0;
		
	}
	
	
	private void updateTestObjects(float deltaTime){
		
		// Get current rotation from selected sprite
		float rotation = testSprites[selectedSprite].getRotation();
		//Rotate sprite by 90 degrees per second
		rotation+=90*deltaTime;
		rotation%=360;
		testSprites[selectedSprite].setRotation(rotation);
		
	}

}
