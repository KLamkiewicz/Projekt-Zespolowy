package pl.studia.objects;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	private TextureRegion backgroundRegion;
	private List<TextureRegion> backgroundRegions;
	
	public Background(){
		backgroundRegions = new ArrayList<TextureRegion>();
		for(AtlasRegion region : Assets.instance.backgroundAsset.backgrounds){
			backgroundRegions.add(region);
		}
		
		//backgroundRegion = Assets.instance.backgroundAsset.background;
	}
	
	   public void render(SpriteBatch batch){
           float width = 19.504761f;
           float height = 16.6095f;
           float x = 0f;
           float y = 0f;
           int it = 1;
          
           for(TextureRegion backgroundRegion : backgroundRegions){
                   batch.draw(backgroundRegion.getTexture(), x, y, 19.504761f, 16.6095f,backgroundRegion.getRegionX(),backgroundRegion.getRegionY(),backgroundRegion.getRegionWidth(), backgroundRegion.getRegionHeight(), false, false);
                   //To remove space between the rendered backgrounds
                   if(it%2==0){
                           x+=width - (0.08f);
                           y = 0f;
                   }else{
                           y+=height - (0.08f);
                   }
                   it++;
           }
   }
}

