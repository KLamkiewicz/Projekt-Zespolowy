package pl.studia.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 velocity;			//Object current speed
	public Vector2 terminalVelocity;	//Object maximum speed
	public Vector2 friction;			//Object friction
	public Vector2 acceleration;		//Object acceleration
	public Rectangle bounds;			//Object area used to determine collision
	public TextureRegion platform;
	

	public GameObject(){
		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		velocity = new Vector2();
		terminalVelocity = new Vector2(1, 1);
		friction = new Vector2();
		acceleration = new Vector2();
		bounds = new Rectangle();
	}
	
	public void update (float deltaTime) {
		updateMotionX(deltaTime);
		updateMotionY(deltaTime);
		// Move the object to the new position after all the "physics" are applied to its motion
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;
	}
	
	public abstract void render (SpriteBatch batch);
	
	/**Check for both negative and positive velocity, if it's not equal to 0 object is moving
	 * Apply friction
	 * If velocity is positive, apply friction max out of (velocity - friction, 0)
	 * 0 so the velocity doesn't change its sign (-/+)
	 * Next acceleration is applied to the velocity
	 * Finally use the clamp function (value, min, max) so the velocity doesn't go beyond the limits
	 */
	protected void updateMotionX (float deltaTime) {
		//Check if object is moving and apply friction
		if (velocity.x != 0) {
			if (velocity.x > 0) {
				velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
			} else {
				velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
			}
		}
		// Acceleration is applied to the velocity
		velocity.x += acceleration.x * deltaTime;
		// Velocity is clamped - preventing it from exceeding both positive and negative speed
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
	}
	
	protected void updateMotionY (float deltaTime) {
		//Check if object is moving and apply friction
		if (velocity.y != 0) {
			if (velocity.y > 0) {
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
			} else {
				velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
			}
		}
		// Acceleration is applied to the velocity
		velocity.y += acceleration.y * deltaTime;
		// Velocity is clamped - preventing it from exceeding both positive and negative speed
		velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
	}
	
}
