package pl.studia.objects.ingame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.studia.objects.Assets;
import pl.studia.objects.GameObject;

public class Character extends GameObject{
	
	private TextureRegion character;
	
	/**
	 * Jumping variables
	 * JUMP_TIME_MAX defines the maximum time in which our character can be in a jump state
	 * JUMP_TIME_MIN defines the minimum time in which our character can be in a jump state
	 */
	public float timeJumping;
	private final float JUMP_TIME_MAX = 0.4f;
	private final float JUMP_TIME_MIN = 0.1f;
	
	/**
	 * This is the variable of the JUMP_STATE ENUM 
	 * which will hold the current state of the character
	 */
	public JUMP_STATE jumpState;
	/**
	 * ENUM is used in here because there is a limited
	 * number of states of which our character can be in
	 */
	public enum JUMP_STATE {
		GROUNDED, FALLING, JUMP_RISING, JUMP_FALLING
	}
	
	public Character () {
		init();
	}
	
	public void init () {
		character = Assets.instance.characterAsset.character; //Initializing the character from the assets
		dimension.set(1, 1);
		origin.set(dimension.x / 2, dimension.y / 2); //set the origin in the middle of the object
		/**
		 * Setting the boundaries of the character (character is a rectangle)
		 * x,y are the bottom left coordinates
		 * dimension.x is the width
		 * dimension.y is the height
		 */
		bounds.set(0, 0, dimension.x, dimension.y);
		/**
		 * Setting the terminal velocity (the maximum speed at which
		 * our character can travel at most both horizontally and vertically
		 */
		terminalVelocity.set(3.0f, 4.0f); 
		/**
		 * Setting the friction for the character, there is no vertical friction
		 * only horizontal
		 */
		friction.set(12.0f, 0.0f);
		/**
		 * Setting the acceleration for the character
		 * Acceleration in here is applied only vertically, as it mimics the gravity
		 * GameObject method updateMotionY, increases the acceleration of the character
		 * until it reaches its terminal velocity
		 */
		acceleration.set(0.0f, -0.5f);
		
		/**
		 * Set the character inital state as falling, set time jumping to 0
		 */
		jumpState = JUMP_STATE.JUMP_FALLING;
		timeJumping = 0;
	};
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = null;
		region = character;
		batch.draw(region.getTexture(), position.x, position.y, dimension.x, dimension.y, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
	}
	
	@Override
	public void update (float deltaTime) {
		super.update(deltaTime);
	}
	
	/**
	 * Override the updateMotionY method from the extended class GameObject
	 */
	@Override
	public void updateMotionY (float deltaTime){
		//Check what the current jumpState is
		switch (jumpState) {
		case GROUNDED:
			jumpState = JUMP_STATE.FALLING;
			break;
		case JUMP_RISING:
			timeJumping += deltaTime;
			if(timeJumping <= JUMP_TIME_MAX){
				velocity.y = terminalVelocity.y;
			}
			break;
		case FALLING:
			break;
		case JUMP_FALLING:
			timeJumping += deltaTime;
			if(timeJumping > 0 && timeJumping <= JUMP_TIME_MIN){
				velocity.y = terminalVelocity.y;
			}
		}
		//Call the the "upper" updateMotionY method only when our character is NOT grounded
		if(jumpState != JUMP_STATE.GROUNDED){
			super.updateMotionY(deltaTime);
		}
	}
	
	/**
	 * Character jumping method, first we check the jumpState with switch
	 * GROUNDED - if we press the jump key, we change the state of character to RISING
	 * JUMP_RISING - check whether the jump key is still pressed, if not we change the state to FALLING,
	 * there is a time limit in which the character can be in the RISING state, defined by the JUMP_TIME_MAX,
	 * this condition is checked in the overriden method updateMotionY
	 */
	public void setJumping (boolean keyPressed){
		switch (jumpState){
		case GROUNDED:
			if(keyPressed){
				timeJumping = 0;
				jumpState = JUMP_STATE.JUMP_RISING;
			}
			break;
		case JUMP_RISING:
			if(!keyPressed){
				jumpState = JUMP_STATE.JUMP_FALLING;
			}
			break;
		case FALLING:
		case JUMP_FALLING:
			break;
		}
	}
	
	
}
