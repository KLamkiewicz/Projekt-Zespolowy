package pl.studia.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {
	/*
	 * Character position is defined by the character.getX() and character.getY()
	 */
	private Vector2 position;
	private Sprite character;

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
	
	/**
	 * Updates the camera focused on the character
	 * 
	 * @param camera	OrthographicCamera that is going to be used for the character
	 */
	public void applyTo(OrthographicCamera camera){
		//This method is called at least several times per second
		// Be carefull !! If camera has no character to follow, you can't establish position without null pointer exception
		if (!hasCharacter()){
			camera.position.x=this.position.x;
			camera.position.y=this.position.y;
			camera.update();
		}
		else {
			camera.position.x = character.getX();
			camera.position.y = character.getY();
			camera.update();
		}
	}
}
