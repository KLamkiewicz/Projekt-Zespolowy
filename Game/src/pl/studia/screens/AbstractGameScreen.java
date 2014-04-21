package pl.studia.screens;

import pl.studia.objects.Assets;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;


public abstract class AbstractGameScreen {

	protected DirectedGame game;

	public AbstractGameScreen (DirectedGame game) {
		this.game = game;
	}

	public abstract void render (float deltaTime);

	public abstract void resize (int width, int height);

	public abstract void show ();

	public abstract void hide ();

	public abstract void pause ();

	public abstract InputProcessor getInputProcessor ();

	public void resume () {
		Assets.instance.init(new AssetManager());
	}

	public void dispose () {
		Assets.instance.dispose();
	}
	
	
	
}
