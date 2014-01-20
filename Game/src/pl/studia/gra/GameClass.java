package pl.studia.gra;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;



public class GameClass implements ApplicationListener {
		
	private WorldController		worldController;
	private WorldRenderer		worldRenderer;
	private boolean				paused;


	@Override
	public void create() {	

		//Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		//Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		
		//Game world is active on start - Android Optymalization
		paused = false;
		
	}


	@Override
	public void render() {	
		
		//Update game world by the time that has passed
		//since last rendered frame
		
		//Do not update game world when paused
		if(!paused){
		worldController.update(Gdx.graphics.getDeltaTime());
		}
		
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		 worldRenderer.resize(width, height);
	}

	@Override
	public void dispose(){
		worldRenderer.dispose();
	}


	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}
	
}
