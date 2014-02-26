package pl.studia.gra;

import pl.studia.objects.Assets;
import pl.studia.objects.Background;
import pl.studia.util.CharacterCamera;
import pl.studia.util.Constants;
import pl.studia.util.ResolutionManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable{

	private OrthographicCamera 	debugCamera;
	private OrthographicCamera 	cam;
	private OrthographicCamera 	cameraGUI;
	private CharacterCamera 	characterCamera;
	
	private SpriteBatch 		batch;
	private WorldController		worldController;
	
	/**
	 * Testing debugmode variable
	 */
	private boolean debugMode = false;
	
	
	/*Temporary ! It shouldn't be here 
	 * just for testing !
	 * */
	
	private Sprite 				sprite2;
	private Texture 			texture2;
	private Background			background;
	
	public WorldRenderer(WorldController worldController){
		this.worldController=worldController;
		init();
	}
	
	private void init(){
		batch =new SpriteBatch();
		
		//texture = new Texture(Gdx.files.internal("data/kk.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/libgdx.png"));
		// Enabling Filtering for Scaling up and down, we can apply different filters
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		//sprite = new Sprite(texture);
		sprite2=new Sprite(texture2);
		
		debugCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		debugCamera.position.set(Constants.VIEWPORT_WIDTH/2f,Constants.VIEWPORT_HEIGHT/2f, 0);
		debugCamera.update();
		
		cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);	
		cam.position.set(Constants.VIEWPORT_WIDTH/2f,Constants.VIEWPORT_HEIGHT/2f, 0); // 3 parametr nie istotny przy 2d
		cam.update();
	
		cameraGUI =new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);	
		cameraGUI.position.set(0,0, 0);
		cameraGUI.setToOrtho(false); 
		
		characterCamera = new CharacterCamera();
		characterCamera.setCharacter(worldController.testSprites[0]);
		
		Assets.instance.init(new AssetManager());
		background = new Background();
	}
	
	public void render(){
 		cameraMode();
      	//Change to character
 		characterCamera.updateCharacterCamera(cam);
 		debugCamera.update();
        cameraGUI.update();

        // clear previous frame
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
     
		batch.begin();
		background.render(batch);
		batch.end();

		renderTestObjects(debugMode);
     	renderGui(batch);
	
	}
	
	private void renderTestObjects(boolean debugMode){
		if(debugMode){
			batch.setProjectionMatrix(debugCamera.combined);
		}
		else{
			batch.setProjectionMatrix(cam.combined);
		}
		batch.begin();
		for(Sprite sprite: worldController.testSprites){
			sprite.draw(batch);
		}
		batch.end();
	}
	
	private void renderGui (SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		sprite2.draw(batch);
		batch.end();
	}
	
	public void resize(int width, int height){

		cam.viewportWidth=(Constants.VIEWPORT_HEIGHT/height)*width;
		cam.position.x=cam.viewportWidth/2f;
		cam.update();

		cameraGUI.viewportHeight=Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();

		// Reposition of the GUI element - change only first two parameters
		float x =ResolutionManager.calculateX(ResolutionManager.HorizontalAlignment.LEFT, 0, sprite2.getHeight(), cameraGUI.viewportWidth);
		float y=ResolutionManager.calculateY(ResolutionManager.VerticalAlignment.TOP, 0, sprite2.getHeight(), cameraGUI.viewportHeight);
	    sprite2.setPosition(x,y);
		
	}
	
	@Override 
	public void dispose(){
		batch.dispose();
		//texture.dispose();
	}
	
	//test method ONLY
	public void handleInput(float deltaTime){
	    if (Gdx.input.isKeyPressed(Keys.LEFT)){
	    		characterCamera.moveCharacterCamera(-0.2f, 0f);
	    }
	    if (Gdx.input.isKeyPressed(Keys.RIGHT))
	    		characterCamera.moveCharacterCamera(0.2f, 0f);
	    if (Gdx.input.isKeyPressed(Keys.UP))
	    		characterCamera.moveCharacterCamera(0f, 0.2f);
	    if (Gdx.input.isKeyPressed(Keys.DOWN))
	    		characterCamera.moveCharacterCamera(0f, -0.2f);
	    if(Gdx.input.isKeyPressed(Keys.Q)){
	    	debugMode = (debugMode == true) ? false : true;
	    	System.out.println(debugMode);
	    }
	    if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
	    		Gdx.app.log("Exit", Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
	    		Gdx.app.exit();
	    }
	}
	
	//test method ONLY
	public void debugInput(float deltaTime){
	    if (Gdx.input.isKeyPressed(Keys.A)){
	    		debugCamera.position.x-=0.2;
	    }
	    if (Gdx.input.isKeyPressed(Keys.D))
	    		debugCamera.position.x+=0.2;
	    if (Gdx.input.isKeyPressed(Keys.W))
	    		debugCamera.position.y+=0.2;
	    if (Gdx.input.isKeyPressed(Keys.S))
	    		debugCamera.position.y-=0.2;
	    if(Gdx.input.isKeyPressed(Keys.Q)){
	    	debugMode = (debugMode == true) ? false : true;
	    	System.out.println(debugMode);
	    }
	    if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
	    		Gdx.app.log("Exit", Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
	    		Gdx.app.exit();
	    }
	}

	/*
	 * Changes the mode of the camera from regular game mode to debug mode with free camera
	 */
	public void cameraMode(){
		if(!debugMode){
			handleInput(Gdx.graphics.getDeltaTime());
			batch.setProjectionMatrix(cam.combined);
		}
		else{
	 		debugInput(Gdx.graphics.getDeltaTime());
	 		batch.setProjectionMatrix(debugCamera.combined);
		}
	}
}
