package pl.studia.gra;

import java.awt.Dimension;
import java.awt.Toolkit;

import pl.studia.GameMain;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;
	public static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args) {
		
		/*
		 * Packs all the images from assets-raw/images into a Power Of Two image
		 * Allows Non-Power Of Two images to be used as Texture Regions
		 */
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 4096;
			settings.maxHeight = 4096;
			settings.debug = false;
			TexturePacker2.process(settings, "assets-raw/images",
					"../Game-android/assets/images", "game.pack");
			TexturePacker2.process(settings, "assets-raw/images-ui", "../Game-android/assets/images", "game-ui.pack");
			System.out.println(settings.debug);
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "Game";
		cfg.useGL20 = true;
		cfg.fullscreen = false;
		cfg.width = screenDimension.width;
		cfg.height= screenDimension.height;
	
		new LwjglApplication(new GameMain(), cfg);
	}
}
