package pl.studia.gra;

import pl.studia.util.Constants;
import pl.studia.util.ResolutionManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

	public class GameClass implements ApplicationListener {
	private SpriteBatch 		batch;
	private Texture 			texture;
	private Sprite 				sprite;
	private Rectangle 			viewport;
	private OrthographicCamera 	cam;
	
	@Override
	public void create() {		
		texture = new Texture(Gdx.files.internal("data/kk.jpg"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); // ustawienie Filtrowania tex, dla skalowania w dol i gore ( mozna ustawiæ rozne filtry )
		batch = new SpriteBatch();
		sprite = new Sprite(texture);
		cam = new OrthographicCamera(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);	
		cam.position.set(Constants.VIRTUAL_WIDTH/2,Constants.VIRTUAL_HEIGHT/2, 0); // 3 parametr nie istotny przy 2d
	
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		GL20 gl = Gdx.graphics.getGL20(); // OpenGL 2.0 umo¿liwia ³adowanie dowolnego rozmiaru textur TYMCZASOWO
		
		// update camera
        cam.update();
        //cam.apply(Gdx.gl10); only in GL1.0
       
        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        // clear previous frame
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        
		// Texturing --------------------- /
		gl.glActiveTexture(GL10.GL_TEXTURE0);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		//texture.bind();
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	
	
		 ResolutionManager.scaleDisplayResolution(width,height);
		 
		 float w = (float)Constants.VIRTUAL_WIDTH*ResolutionManager.scale;
	     float h = (float)Constants.VIRTUAL_HEIGHT*ResolutionManager.scale;
	     viewport = new Rectangle(ResolutionManager.crop.x, ResolutionManager.crop.y, w, h); // pocz¹tkowo 0,0 
	     System.out.println(ResolutionManager.crop.x+" "+ResolutionManager.crop.y);
	
	
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
