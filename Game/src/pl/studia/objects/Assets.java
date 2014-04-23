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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;





public class Assets implements Disposable, AssetErrorListener {
        public static final String TAG = Assets.class.getName();
        public static final Assets instance = new Assets();
        private AssetManager assetManager;
    	public AssetFonts fonts;
        public AssetBackground backgroundAsset;
        public AssetPlatform platformAsset;
        public AssetPlat1 plat1;
        public AssetPlat2 plat2;
        public AssetPlat3 plat3;
        public AssetPlat4 plat4;
        public AssetPlat5 plat5;
        public AssetPlat6 plat6;
        public AssetPlat7 plat7;
        public AssetTime timeAsset;
        public AssetCharacter characterAsset;
        public AssetGoldCoin goldCoin;
    	
        

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
                timeAsset = new AssetTime(atlas);
                goldCoin = new AssetGoldCoin(atlas);
            	fonts = new AssetFonts();
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

        public class AssetFonts {
    		public final BitmapFont defaultSmall;
    		public final BitmapFont defaultNormal;
    		public final BitmapFont defaultBig;

    		public AssetFonts () {
    			// create three fonts using Libgdx's built-in 15px bitmap font
    			defaultSmall = new BitmapFont(Gdx.files.internal("images/myFont.fnt"), false);
    			defaultNormal = new BitmapFont(Gdx.files.internal("images/myFont.fnt"), false);
    			defaultBig = new BitmapFont(Gdx.files.internal("images/myFont.fnt"), false);
    			// set font sizes
    			defaultSmall.setScale(0.5f);
    			defaultNormal.setScale(0.75f);
    			defaultBig.setScale(1.0f);
    			// enable linear texture filtering for smooth fonts
    			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    		}
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
        
        public class AssetTime {
    		public final AtlasRegion time;

    		public AssetTime(TextureAtlas atlas) {
    			time = atlas.findRegion("klepsydraUI");
    		}
    	}
        
        
        public class AssetPlatform{
        		public final AtlasRegion platform;
        		
        		public AssetPlatform(TextureAtlas atlas) {
        			platform = atlas.findRegion("plat1");
        		}
        }
        
        
        public class AssetGoldCoin {
        	public final AtlasRegion goldCoinStatic;
    		public final Animation animGoldCoin;
    		
    		public AssetGoldCoin (TextureAtlas atlas) {
    			goldCoinStatic = atlas.findRegion("Static_gold");
    			// Animation: Gold Coin
    			Array<AtlasRegion> regions = atlas.findRegions("gold");
    			animGoldCoin = new Animation(0.01f, regions, Animation.LOOP);
    		}
    	}
    
        
        public class AssetCharacter{
        		public final AtlasRegion character;
        		public final Animation animWalk;
        		public final Animation animJump;
        		
        		public AssetCharacter(TextureAtlas atlas) {
        			character = atlas.findRegion("static");
        			
        			Array<AtlasRegion> regions = null;
        			AtlasRegion region = null;

        			// Animation: Walk
        			regions = atlas.findRegions("walk");
        			animWalk = new Animation(0.2f, regions, Animation.LOOP_PINGPONG);
        			
        			// Animation: Jump
        			regions = atlas.findRegions("jump");
        			animJump = new Animation(0.2f, regions);
        			
        			
        		}
        }
        
}