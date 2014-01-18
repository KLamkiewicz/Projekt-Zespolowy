package pl.studia.gra;

import java.awt.Dimension;
import java.awt.Toolkit;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		cfg.title = "Game";
		cfg.useGL20 = true;
		cfg.fullscreen=false;
		cfg.width = screenDimension.width;
		cfg.height= screenDimension.height;
	
		
		new LwjglApplication(new GameClass(), cfg);
	}
}
