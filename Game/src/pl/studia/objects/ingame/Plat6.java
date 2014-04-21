package pl.studia.objects.ingame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.studia.objects.Assets;
import pl.studia.objects.GameObject;

public class Plat6 extends GameObject {

	
	private TextureRegion platform;

	public Plat6(){
		init();
	}
	
	private void init(){
		platform = Assets.instance.plat6.platform;
		//define origin and position here
		position = new Vector2(2f, 2f);
		dimension = new Vector2(1f, 1f);
		bounds = new Rectangle();
		bounds.set(0, 0, dimension.x, dimension.y);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = platform;
		//draw batch
		batch.draw(region.getTexture(), position.x, position.y, dimension.x, dimension.y, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
		
	}
	
	
	
	
	
}
