package pl.studia.util;


public class Constants {
	
	/*Screen Constants*/
	// Visible game world is 16 meters wide
	public static final float VIEWPORT_WIDTH = 16.0f;
	
	// Visible game world is 10 meters tall
	public static final float VIEWPORT_HEIGHT = 10.0f;
	
	/*Pixels for Pixel Perfect Positioning
	GUI is independent from Physics etc. calculations
	pixels are good here*/
	public static final int VIEWPORT_GUI_HEIGHT = 1040;
	public static final int VIEWPORT_GUI_WIDTH = 650;
	
	// UI Width
	public static final float VIEWPORT_UI_WIDTH = 1920.0f;

	// UI Height
	public static final float VIEWPORT_UI_HEIGHT = 1200.0f;
	
	public static final String TEXTURE_ATLAS_OBJECTS = "images/game.pack";
	public static final String TEXTURE_ATLAS_UI = "images/game-ui.pack";
	public static final String LEVEL_01 = "levels/level_01.png";
	
	// Location of description file for skins
	public static final String SKIN_GAME_UI = "images/game-ui.json";
	
	//Game Time
	public static final int LEVEL_TIME = 120;
	
}
