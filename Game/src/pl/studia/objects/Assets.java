package pl.studia.objects;

import java.util.ArrayList;
import java.util.List;

import pl.studia.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;



public class Assets implements Disposable, AssetErrorListener {
        public static final String TAG = Assets.class.getName();
        public static final Assets instance = new Assets();
        private AssetManager assetManager;
        public AssetBackground backgroundAsset;
        public AssetPlatform platformAsset;
        public AssetPlat1 plat1;
        public AssetPlat2 plat2;
        public AssetPlat3 plat3;
        public AssetPlat4 plat4;
        public AssetPlat5 plat5;
        public AssetPlat6 plat6;
        public AssetPlat7 plat7;
        public AssetCharacter characterAsset;

        // singleton: prevent instantiation from other classes
        private Assets() {
        	
        }

        public void init(AssetManager assetManager) {
                this.assetManager = assetManager;
                // set asset manager error handler
                assetManager.setErrorListener(this);
                // load texture atlas
                assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
                // start loading assets and wait until finished
                assetManager.finishLoading();
                Gdx.app.debug(TAG,
                                "# of assets loaded: " + assetManager.getAssetNames().size);
                for (String a : assetManager.getAssetNames())
                        Gdx.app.debug(TAG, "asset: " + a);

                TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

                // enable texture filtering for pixel smoothing
                for (Texture t : atlas.getTextures())
                        t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
                // create game resource objects
                backgroundAsset = new AssetBackground(atlas);
                platformAsset = new AssetPlatform(atlas);
                plat1 = new AssetPlat1(atlas);
                plat2 = new AssetPlat2(atlas);
                plat3 = new AssetPlat3(atlas);
                plat4 = new AssetPlat4(atlas);
                plat5 = new AssetPlat5(atlas);
                plat6 = new AssetPlat6(atlas);
                plat7 = new AssetPlat7(atlas);
                characterAsset = new AssetCharacter(atlas);
        }

        
        @Override
        public void dispose() {
                assetManager.dispose();
        }

        
        @Override
        public void error(AssetDescriptor asset, Throwable throwable) {
                Gdx.app.error(TAG, asset.fileName, (Exception) throwable);
        }

       
        public class AssetBackground{
                //public final AtlasRegion background;
                public final List<AtlasRegion> backgrounds;

                public AssetBackground(TextureAtlas atlas) {
                	backgrounds = new ArrayList<AtlasRegion>();
                	backgrounds.add(atlas.findRegion("background1"));
                	backgrounds.add(atlas.findRegion("background2"));
                	backgrounds.add(atlas.findRegion("background3"));
                	backgrounds.add(atlas.findRegion("background4"));
                	
                	//background = atlas.findRegion("background3");
                }
        }
        
        
        //Generaly the same code , but needed to distinct platforms 
        public class AssetPlat1{
    		public final AtlasRegion platform;
    		
    		public AssetPlat1(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat1");
    		}
        }
        
        public class AssetPlat2{
    		public final AtlasRegion platform;
    		
    		public AssetPlat2(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat2");
    		}
        }
        
        public class AssetPlat3{
    		public final AtlasRegion platform;
    		
    		public AssetPlat3(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat3");
    		}
        }
        
        public class AssetPlat4{
    		public final AtlasRegion platform;
    		
    		public AssetPlat4(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat4");
    		}
        }
        
        public class AssetPlat5{
    		public final AtlasRegion platform;
    		
    		public AssetPlat5(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat5");
    		}
        }
        
        public class AssetPlat6{
    		public final AtlasRegion platform;
    		
    		public AssetPlat6(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat6");
    		}
        }
        
        public class AssetPlat7{
    		public final AtlasRegion platform;
    		
    		public AssetPlat7(TextureAtlas atlas) {
    			platform = atlas.findRegion("plat7");
    		}
        }
        
        
        
        
        public class AssetPlatform{
        		public final AtlasRegion platform;
        		
        		public AssetPlatform(TextureAtlas atlas) {
        			platform = atlas.findRegion("plat1");
        		}
        }
        
    
        
        public class AssetCharacter{
        		public final AtlasRegion character;
        		
        		public AssetCharacter(TextureAtlas atlas) {
        			character = atlas.findRegion("morda");
        		}
        }
        
}