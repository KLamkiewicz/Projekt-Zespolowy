package pl.studia.objects.ingame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.studia.objects.Assets;
import pl.studia.objects.GameObject;

public class Character extends GameObject{
	
	private TextureRegion character;
	public float timeJumping;

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
	};
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = null;
		region = character;
		batch.draw(region.getTexture(), position.x, position.y, 1f, 1f, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
		
	}
	
	@Override
	public void update (float deltaTime) {
		super.update(deltaTime);
	}
	
}
