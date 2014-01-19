package pl.studia.objects;

import pl.studia.util.Constants;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	private TextureRegion backgroundRegion;
	
	public Background(){
		backgroundRegion = Assets.instance.backgroundAsset.background;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(backgroundRegion.getTexture(), 0, 0-(Constants.SCREEN_HEIGHT/2), backgroundRegion.getRegionWidth(), backgroundRegion.getRegionHeight());
	}
}

