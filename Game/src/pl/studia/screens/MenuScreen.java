package pl.studia.screens;

import pl.studia.screens.transitions.ScreenTransition;
import pl.studia.screens.transitions.ScreenTransitionSlice;
import pl.studia.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;





public class MenuScreen extends AbstractGameScreen{
	private Stage stage;
	private Skin skinGame;
	// menu
	private Image imgBackground;
	private Button btnMenuPlay;
	private Music music;
	
	// debug
		private final float DEBUG_REBUILD_INTERVAL = 5.0f;
		private boolean debugEnabled = false;
		private float debugRebuildStage;
	
	public MenuScreen (DirectedGame game) {
		super(game);
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/menu.mp3"));
		music.setLooping(true);
		music.play();
	}
	
	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (debugEnabled) {
			debugRebuildStage -= deltaTime;
			if (debugRebuildStage <= 0) {
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
		stage.act(deltaTime);
		stage.draw();
		Table.drawDebug(stage);
	}
	
	
	
	@Override
	public void resize (int width, int height) {
		stage.setViewport((Constants.VIEWPORT_UI_HEIGHT / (float)height) * (float)width, Constants.VIEWPORT_UI_HEIGHT, false);
	}
	
	
	@Override
	public void show () {
		stage = new Stage();
		rebuildStage();
	}

	@Override
	public void hide () {
		stage.dispose();
		skinGame.dispose();
	}

	@Override
	public void pause () {
	}
	
	private void rebuildStage () {
		skinGame = new Skin(Gdx.files.internal(Constants.SKIN_GAME_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));

		// build all layers
		Table layerBackground = buildBackgroundLayer();
		Table layerControls = buildControlsLayer();

		// assemble stage for menu screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_UI_WIDTH, Constants.VIEWPORT_UI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerControls);

	}
	
	
	
	private Table buildBackgroundLayer () {
		Table layer = new Table();
		// + Background
		imgBackground = new Image(skinGame, "menubackground");
		layer.add(imgBackground);
		
		return layer;
	}
	
	
	private Table buildControlsLayer () {
		Table layer = new Table();
		layer.center();
		// + Play Button
		btnMenuPlay = new Button(skinGame, "play");
		layer.add(btnMenuPlay);
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				onPlayClicked();
			}
		});
		
	return layer;	
	}
	
	private void onPlayClicked () {
		ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);
		game.setScreen(new GameScreen(game), transition);
		music.dispose();
	}

	
	@Override
	public InputProcessor getInputProcessor () {
		return stage;
	}
	
	
}
