package pl.studia.objects;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	private TextureRegion backgroundRegion;
	
	public Background(){
		backgroundRegion = Assets.instance.backgroundAsset.background;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(backgroundRegion.getTexture(), 0, 0,12.8f, 12.8f,backgroundRegion.getRegionX(),backgroundRegion.getRegionY(),backgroundRegion.getRegionWidth(), backgroundRegion.getRegionHeight(), false, false);
	}
}

