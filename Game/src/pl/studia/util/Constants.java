package pl.studia.util;

import java.awt.Dimension;
import java.awt.Toolkit;


public class Constants {
	
	public static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	/////////////////////////////////// Sta�e Ekran ///////////////////////////////////
	public static final int VIRTUAL_WIDTH = 1920;
	public static final int VIRTUAL_HEIGHT = 1080;
	public static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
	public static final String TEXTURE_ATLAS_OBJECTS = "images/game.pack";
	public static final int SCREEN_WIDTH = screenDimension.width;
	public static final int SCREEN_HEIGHT = screenDimension.height;
	
	public static final float PIXELS_TO_METER = 100;

}
