package pl.studia.util;
public class ResolutionManager {

    
	public enum HorizontalAlignment {
	    LEFT, MIDDLE, RIGHT
	}
	 
	public enum VerticalAlignment {
	    TOP, MIDDLE, BOTTOM
	}
	 
	public static float calculateX(HorizontalAlignment horizontal, int deltax,float sizex,float ViewportWidth) {
	        float width = ViewportWidth;
	 
	        switch (horizontal) {
	            case LEFT:
	                return deltax;
	            case MIDDLE:
	                return (width / 2) + deltax - sizex / 2;
	            case RIGHT:
	                return width + deltax - sizex;
	        }
	        return -1;
	    }
	 
	    public static float calculateY(VerticalAlignment vertical, int deltay, float sizey,float ViewportHeight) {
	        float height = ViewportHeight;
	 
	        switch (vertical) {
	            case TOP:
	            	return height + deltay - sizey;     
	            case MIDDLE:
	                return (height / 2) + deltay - sizey / 2;
	            case BOTTOM:
	            	return deltay;
	        }
	        return -1;

	    }
    
    
    
}
