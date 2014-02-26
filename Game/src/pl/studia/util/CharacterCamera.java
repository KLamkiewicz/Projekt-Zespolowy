package pl.studia.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CharacterCamera {
	/*
	 * Character position is defined by the character.getX() and character.getY()
	 */
	private Vector2 characterPosition;
	/*
	 * Character is the focus of the camera
	 */
	private Sprite character;

	public CharacterCamera(){
		characterPosition = new Vector2();
	}
	
	public Sprite getCharacter() {
		return character;
	}

	public void setCharacter(Sprite character) {
		this.character = character;
	}
	
	public Vector2 getCharacterPosition() {
		return characterPosition;
	}

	public void setCharacterPosition(Vector2 characterPosition) {
		this.characterPosition = characterPosition;
	}

	/**
	 * Moves the camera with the character centered
	 * 
	 * @param x defines the x-axis coordinate
	 * @param y defines the y-axis coordinate
	 */
	public void moveCharacterCamera(float x, float y){
		character.setX(character.getX()+x);
		character.setY(character.getY()+y);
	}
	
	/**
	 * Updates the camera focused on the character
	 * 
	 * @param camera	OrthographicCamera that is going to be used for the character
	 */
	public void updateCharacterCamera(OrthographicCamera camera){
		camera.position.x = character.getX();
		camera.position.y = character.getY();
		camera.update();
	}
}
