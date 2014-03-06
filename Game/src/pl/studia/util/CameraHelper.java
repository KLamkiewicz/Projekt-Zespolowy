package pl.studia.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {
	/*
	 * Character position is defined by the character.getX() and character.getY()
	 */
	private Vector2 position;
	private Sprite	character;
	private float 	currentShakeTime=1;
	private float 	shakeTime=0;
	private float 	shakeX;
	private float 	shakeY;
	private boolean pom=false;
	public CameraHelper(){
		position = new Vector2();
	}
	
	
	public Sprite getCharacter() {
		return character;
	}

	public void setCharacter(Sprite character) {
		this.character = character;
	}
	
	/*
	 * Check if camera has a target ( character )
	 */
	public boolean hasCharacter(){
		
		return this.character!=null;
		}

	/*
	 * Overload method Check if camera has a specified target in a parameter
	 */
	public boolean hasCharacter(Sprite character){
		return this.character!=null && this.character.equals(character);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y) {
		this.position.x=x;
		this.position.y=y;
	}
	
	public void setPosition(Vector2 characterPosition) {
		this.position = characterPosition;
	}
	
	
	//This method is called (only if it were evoked before) at least severl times per second in WorldController update
	//DebugMode(FreeMode) has no shakeCam - null pointer exception prevention
	public void shakeCam(float deltaTime){
		if (currentShakeTime <= shakeTime && hasCharacter()) {
			
			shakeX = MathUtils.random(character.getX()-0.1f,character.getX()+0.1f);
			shakeY = MathUtils.random(character.getY()-0.1f,character.getY()+ 0.1f);
		
		/*	if(this.position.x<shakeX)
				this.position.x += 0.05;
			if(this.position.x>shakeX)
				this.position.x -= 0.05;
			
			if(this.position.y<shakeY)
				this.position.y += 0.05;
			if(this.position.y>shakeY)
				this.position.y -= 0.05;*/
			
			
			
			this.position.y = shakeY;
			this.position.x = shakeX;
			
			this.currentShakeTime+=deltaTime;		
		}
	}
	
	// This method evokes shakeCam on main camera.
	public void evokeShakeCam(float time){
		this.shakeTime=time;
		this.currentShakeTime=0;
	}
	
	/**
	 * Updates the camera focused on the character
	 * 
	 * @param camera	OrthographicCamera that is going to be used for the character
	 */
	public void applyTo(OrthographicCamera camera) {
		// This method is called at least several times per second
		// Be carefull !! If camera has no character to follow, you can't
		// establish position without null pointer exception
		if (!hasCharacter()) {
			camera.position.x = this.position.x;
			camera.position.y = this.position.y;
			camera.update();
		} else {
			camera.position.x = character.getX();
			camera.position.y = character.getY();

			if (currentShakeTime <= shakeTime) {
				camera.position.x = this.position.x;
				camera.position.y = this.position.y;
			}
			camera.update();
		}
	}
}
