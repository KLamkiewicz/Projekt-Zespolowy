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
        
        public class AssetPlatform{
        		public final AtlasRegion platform;
        		
        		public AssetPlatform(TextureAtlas atlas) {
        			platform = atlas.findRegion("platform");
        		}
        }
        
        public class AssetCharacter{
        		public final AtlasRegion character;
        		
        		public AssetCharacter(TextureAtlas atlas) {
        			character = atlas.findRegion("morda");
        		}
        }
        
}