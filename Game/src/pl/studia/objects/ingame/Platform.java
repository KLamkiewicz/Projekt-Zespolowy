package pl.studia.objects.ingame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pl.studia.objects.Assets;
import pl.studia.objects.GameObject;

public class Platform extends GameObject {
	private TextureRegion platform;

	public Platform(){
		init();
	}
	
	private void init(){
		platform = Assets.instance.platformAsset.platform;
		//define origin and position here
	}
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = platform;
		//draw batch
		//batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		
	}
	
}
