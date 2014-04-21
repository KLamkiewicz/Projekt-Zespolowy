package pl.studia.gra;

import pl.studia.objects.GameObject;
import pl.studia.objects.Level;
import pl.studia.objects.ingame.Character;
import pl.studia.objects.ingame.Character.JUMP_STATE;
import pl.studia.objects.ingame.Platform;
import pl.studia.util.CameraHelper;
import pl.studia.util.Constants;
import pl.studia.objects.ingame.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WorldController extends InputAdapter{

	private static final String TAG = WorldController.class.getName();
	public CameraHelper 		cameraHelper;
	public Sprite[] 			testSprites;
	public int 					selectedSprite;
	public Texture 				texture;
	public Level				level;
	
	
	public WorldController(){
		init();
	}
	
	/*Initialization in separate method instead of in constructor,
	* this approach can greatly reduce 
	* the interruptions by the Garbage Collector
	* */
	private void init(){
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		//initTestObjects();
		initLevel();
		//cameraHelper.setCharacter(testSprites[0]);
		cameraHelper.setCharacter(level.character);
	}
	
	private void initLevel() {
		level = new Level(Constants.LEVEL_01);
	}
	
	/*This method contain the game logic and will 
	* be called several hundred times per second. 
	* It requires delta time so that it can apply 
	* updates to the game world according to the 
	* fraction of time */
	public void update (float deltaTime){
		//updateTestObjects(deltaTime);	
		handleInput(deltaTime);
		level.update(deltaTime);
		checkForCollision();
		cameraHelper.shakeCam(deltaTime);
		
	}
	
	@Override
	public boolean keyUp(int keycode) {
		// Reset game world
		if (keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		// Select next sprite
//		else if (keycode == Keys.SPACE) {
//			selectedSprite = (selectedSprite + 1) % testSprites.length;
//			Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
//			//cameraHelper.setCharacter(testSprites[selectedSprite]);
//		}
		else if (keycode==Keys.ESCAPE){
	    		Gdx.app.log("Exit", Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
	    		Gdx.app.exit();
	    }
		else if (keycode==Keys.ENTER){
    		cameraHelper.setCharacter(cameraHelper.hasCharacter() ? null : level.character);
    		Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasCharacter());
		}
		else if(keycode==Keys.Q){
			cameraHelper.evokeShakeCam(10);
			Gdx.app.debug(TAG, "Shake enabled");
		}
		return false;
	}
	
//	private void initTestObjects(){
//		
//	//Create new array for 5 sprites
//	testSprites = new Sprite[5];
//	texture = new Texture(Gdx.files.internal("data/test.png"));
//	texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
//	// Create new sprites using a random texture region
//		for (int i = 0; i < testSprites.length; i++) {
//			Sprite spr = new Sprite(texture);
//			// Define sprite size to be 1m x 1m in game world
//			spr.setSize(1.52f, 1.52f);
//			// Set origin to spriteÕs center
//			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
//			// Calculate random position for sprite
//			float randomX = MathUtils.random(0f, 1.0f);
//			float randomY = MathUtils.random(0f, 1.0f);
//			spr.setPosition(randomX, randomY);
//			// Put new sprite into array
//			testSprites[i] = spr;
//		}
//		// Set first sprite as selected one
//		selectedSprite = 0;
//	}
//
//	private void updateTestObjects(float deltaTime){
//		
//		// Get current rotation from selected sprite
//		float rotation = testSprites[selectedSprite].getRotation();
//		//Rotate sprite by 90 degrees per second
//		rotation+=90*deltaTime;
//		rotation%=360;
//		testSprites[selectedSprite].setRotation(rotation);
//	}
//		
//	private void moveSelectedSprite(float x, float y){
//		testSprites[selectedSprite].translate(x, y);
//		
//	}
	
	private void moveCamera(float x, float y){
		x+=cameraHelper.getPosition().x;
		y+=cameraHelper.getPosition().y;
		cameraHelper.setPosition(x,y);
	}
	
	//test method ONLY
	private void handleInput(float deltaTime){
	    
		// Selected Sprite Controls - TEMPORARY
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			level.character.velocity.x = -level.character.terminalVelocity.x;
	    }
	    if (Gdx.input.isKeyPressed(Keys.RIGHT))
	    	level.character.velocity.x = level.character.terminalVelocity.x;
	    if(Gdx.input.isKeyPressed(Keys.SPACE))
	    	level.character.setJumping(true);
//	    if (Gdx.input.isKeyPressed(Keys.UP))
//	    	moveSelectedSprite(0f, 0.2f);
//	    if (Gdx.input.isKeyPressed(Keys.DOWN))
//	    	moveSelectedSprite(0f, -0.2f);
	    
	    // Camera Controls Move
	    if (Gdx.input.isKeyPressed(Keys.A)){
	    	moveCamera(-0.2f, 0f);
	    }
	    if (Gdx.input.isKeyPressed(Keys.D))
	    	moveCamera(0.2f, 0f);
	    if (Gdx.input.isKeyPressed(Keys.W))
	    	moveCamera(0f, 0.2f);
	    if (Gdx.input.isKeyPressed(Keys.S))
	    	moveCamera(0f, -0.2f);
	    
	}
	
	/*
	 * Creating rectangle which will be used for collisions
	 * First rectangle will contain the character
	 * Second rectangle will contain whichever object our character might come in contact with
	 * We will check if the boundaries of r1(character) overlap with the boundaries of r2(object)
	 */
	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();
	
	/*
	 * Our method which will check whether there is an overlap or not
	 */
	public void checkForCollision(){
		/*
		 * Because our first rectangle will always be our character we can just set it now
		 * To define a rectangle we need the its bottom left corner position and its width and height
		 */
		r1.set(level.character.position.x, level.character.position.y, level.character.bounds.width, level.character.bounds.height);
	
		/*
		 * In here we will loop through all of our objects, setting the r2
		 */
		for(GameObject platform : level.platforms){
			r2.set(platform.position.x, platform.position.y, platform.bounds.width, platform.bounds.height);
			/*
			 * Here we check the overlapping, if there is none we loop to the other object
			 * if we come across an rectangle overlap we execute the characterCollisionWithPlatform method
			 * which will make the character react to the collision
			 */
			if(!r1.overlaps(r2)) 
				continue;
			characterCollisionWithPlatform(platform);
		}
	}
	
	/*
	 * This method is called where we detect the character collision with Platform
	 */
	public void characterCollisionWithPlatform(GameObject platform){
		//Create reference to character so we can call character instead of level.character
		Character character = level.character;
		
		/*
		 * Here we check how big is the vertical overlap
		 * We get an absolute value from the character vertical position minus the sum of platform vertical position
		 * and platform bounds height
		 * This gives us a value how much has the character vertically overlapped with the platform
		 * If the overlap is greater than certain value we check the side collisions, otherwise we check the jumpState
		 */
		
		float overlap = Math.abs(character.position.y - (platform.position.y + platform.bounds.height));

		if (overlap > 0.25f) {
			/*
			 * Here we check which edge has been hit, checking if character horizontal position is greater than
			 * platform horizontal position plus half of platform width, meaning we can state which side has been hit
			 * if character horizontal position is greater that means left edge has been hit and the leftEdge equals true,
			 * otherwise leftEdge is false and the right edge of the platform has been hit
			 */
			boolean leftEdge = character.position.x > (platform.position.x + platform.bounds.width / 2.0f);
			if (leftEdge) {
				character.position.x = platform.position.x + platform.bounds.width;
			} else {
				character.position.x = platform.position.x - character.bounds.width;
			}
			return;
		}
		
		/*
		 * Here we check our character jumpState, only when the overlap is lesser than given value
		 * If the character is in the JUMP_FALLING state its vertical position will equal to platform vertical position
		 * plus its height, positioning our character right on top of it, then we change character state to GROUNDED
		 * If the character is in the JUMP_RISING state (meaning it can hit a platform from the bottom), we change its 
		 * vertical position to platform vertical position plus character bounds height and its vertical origin, then we change
		 * the character state to JUMP_FALLING, because it can neither rise or be grounded
		 */
		switch (character.jumpState) {
		case GROUNDED:
			break;
		case FALLING:
		case JUMP_FALLING:
			//character.position.y = platform.position.y + character.bounds.height + character.origin.y;
			character.position.y = platform.position.y + platform.bounds.height;
			character.jumpState = JUMP_STATE.GROUNDED;
			break;
		case JUMP_RISING:
			character.position.y = platform.position.y + character.bounds.height;
			//This removes the ping-pong effect
			character.jumpState = JUMP_STATE.JUMP_FALLING;
			break;
		}
	}
	

}
