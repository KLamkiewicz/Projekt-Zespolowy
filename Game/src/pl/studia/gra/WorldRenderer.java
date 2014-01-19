package pl.studia.gra;

import pl.studia.objects.Assets;
import pl.studia.objects.Background;
import pl.studia.util.Constants;
import pl.studia.util.ResolutionManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable{

	private OrthographicCamera 	cam;
	private OrthographicCamera 	cameraGUI;
	private Rectangle 			viewport;
	private Rectangle 			viewport2;
	private SpriteBatch 		batch;
	private WorldController		worldController;
	
	
	/*Temporary ! It shouldn't be here 
	 * just for testing !
	 * */
	private Sprite 				sprite;
	private Sprite 				sprite2;
	private Texture 			texture;
	private Texture 			texture2;
	private Background			background;
	
	public WorldRenderer(WorldController worldController){
		this.worldController=worldController;
		init();
	}
	
	private void init(){
		batch =new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/kk.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); // ustawienie Filtrowania tex, dla skalowania w dol i gore ( mozna ustawiæ rozne filtry )
		texture2.setFilter(TextureFilter.Linear, TextureFilter.Nearest);

		sprite = new Sprite(texture);
		sprite2=new Sprite(texture2);
		
		cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);	
		cam.position.set(Constants.VIRTUAL_WIDTH/2,Constants.VIRTUAL_HEIGHT/2, 0); // 3 parametr nie istotny przy 2d
		
		cameraGUI =new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	
		cameraGUI.position.set(Constants.VIRTUAL_WIDTH/2,Constants.VIRTUAL_HEIGHT/2, 0);
		
		Assets.instance.init(new AssetManager());
		background = new Background();
	}
	
	public void render(){
		
		GL20 gl = Gdx.graphics.getGL20(); // OpenGL 2.0 Temporary
		
		// update camera
        cam.update();
        
        cameraGUI.update();
        //cam.apply(Gdx.gl10); only in GL1.0
       
        
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
        
        // clear previous frame
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		sprite.draw(batch);
		//Texture Region test 
		//background.render(batch);
		batch.end();

		renderTestObjects();
		
        // set viewport
        Gdx.gl.glViewport((int) viewport2.x, (int) viewport2.y,
                          (int) viewport2.width, (int) viewport2.height);
     	renderGui(batch);
		
		handleInput(Gdx.graphics.getDeltaTime());
		
	}
	
	
	private void renderTestObjects(){
		batch.setProjectionMatrix(cam.combined);
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
		
		 ResolutionManager.scaleDisplayResolution(width,height);

		 float w = (float)1920*ResolutionManager.scale;
	     float h = (float)1080*ResolutionManager.scale;
 
	     viewport = new Rectangle(ResolutionManager.crop.x,ResolutionManager.crop.y,w,h); // Start at 0,0 ;
	     viewport2 = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 

	     cameraGUI.setToOrtho(false, width,height); 
	     cameraGUI.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0); 
	     

		/*Resize and replace HUD ELEMENTS - !*/
		sprite2.setSize(512*ResolutionManager.scale, 512*ResolutionManager.scale);
		sprite2.setPosition(ResolutionManager.calculateX(ResolutionManager.HorizontalAlignment.RIGHT,0,512*ResolutionManager.scale ),ResolutionManager.calculateY(ResolutionManager.VerticalAlignment.TOP,0,512*ResolutionManager.scale ));

		
	}
	
	@Override 
	public void dispose(){
		batch.dispose();
		texture.dispose();
	}
	
	//test method ONLY
	public void handleInput(float deltaTime){
	    if (Gdx.input.isKeyPressed(Keys.LEFT))
	    		cam.position.x-=10;
	    if (Gdx.input.isKeyPressed(Keys.RIGHT))
	            cam.position.x+=10;
	    if (Gdx.input.isKeyPressed(Keys.UP))
	            cam.position.y+=10;
	    if (Gdx.input.isKeyPressed(Keys.DOWN))
	            cam.position.y-=10;
	    if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
	    		Gdx.app.log("Exit", Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
	    		Gdx.app.exit();
	    }
	}
	
}
