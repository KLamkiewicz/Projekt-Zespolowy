package pl.studia.gra;

import pl.studia.objects.Assets;
import pl.studia.objects.Background;
import pl.studia.util.Constants;
import pl.studia.util.ResolutionManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable{

	private OrthographicCamera 	cam;
	private OrthographicCamera 	cameraGUI;
	private SpriteBatch 		batch;
	private WorldController		worldController;
	
	/**
	 * Testing debugmode variable
	 */
	
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
		batch = new SpriteBatch();
		
		//texture = new Texture(Gdx.files.internal("data/kk.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/klepsydraUI.png"));
		// Enabling Filtering for Scaling up and down, we can apply different filters
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		//sprite = new Sprite(texture);
		sprite2 = new Sprite(texture2);
		cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);	
		cam.position.set(Constants.VIEWPORT_WIDTH/2f,Constants.VIEWPORT_HEIGHT/2f, 0); // 3 parametr nie istotny przy 2d
		cam.update();
	
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);	
		cameraGUI.position.set(0,0, 0);
		cameraGUI.setToOrtho(false); 
		background = new Background();

	}
	
	public void render() {
 		
      	//Apply settings of helper to main camera
 		worldController.cameraHelper.applyTo(cam);
        cameraGUI.update();

        // clear previous frame
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
		batch.begin();
		background.render(batch);
		worldController.level.render(batch);
		batch.end();

		//renderTestObjects();
     	renderGui(batch);
	
	}
	
//	private void renderTestObjects() {
//		
//		batch.begin();
//		for(Sprite sprite: worldController.testSprites){
//			sprite.draw(batch);
//		}
//		batch.end();
//	}
	
	private void renderGui (SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		//sprite2.draw(batch);
		renderGuiTime(batch); 
		renderGuiScore(batch);
		batch.end();
	}
	
	
	private void renderGuiTime (SpriteBatch batch) {
		float timeLeft = worldController.level.character.timeLeft;
		
		/*	if (timeLeft < 4) {
				if (((int)(timeLeft * 5) % 2) != 0) {
					batch.setColor(1, 1, 1, 0.5f);
				}
			}*/
			batch.draw(Assets.instance.timeAsset.time, 50,900, 74/2f, 60, 74, 120, 1, 1, 0);
			batch.setColor(1, 1, 1, 1);
			Assets.instance.fonts.defaultNormal.draw(batch, "" + (int)timeLeft,  150,980);
		}
	
	private void renderGuiScore(SpriteBatch batch){
		float x;
		float y;
		x= ResolutionManager.calculateX(ResolutionManager.HorizontalAlignment.RIGHT, 0, 200, cameraGUI.viewportWidth);
		y = ResolutionManager.calculateY(ResolutionManager.VerticalAlignment.TOP, 0, 50, cameraGUI.viewportHeight);
		Assets.instance.fonts.defaultNormal.draw(batch, "" + (int)worldController.level.character.score, x  , y );
		batch.draw(Assets.instance.goldCoin.goldCoinStatic, x -70, y -60);
	}
	
	
	public void resize(int width, int height) {

		cam.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
		cam.position.x = cam.viewportWidth/2f;
		cam.update();

		cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();

		// Reposition of the GUI element - change only first two parameters
		float x = ResolutionManager.calculateX(ResolutionManager.HorizontalAlignment.LEFT, 0, sprite2.getHeight(), cameraGUI.viewportWidth);
		float y = ResolutionManager.calculateY(ResolutionManager.VerticalAlignment.TOP, 0, sprite2.getHeight(), cameraGUI.viewportHeight);
	    sprite2.setPosition(x+50,y-50);
		
	}
	
	@Override 
	public void dispose() {
		batch.dispose();
		//texture.dispose();
	}

}
