package pl.studia.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;

	public GameObject(){
		position = new Vector2();
		dimension = new Vector2();
		origin = new Vector2();
	}
	
	public void update (float deltaTime) {}
	public abstract void render (SpriteBatch batch);
}
