// Klasa odpowiedzalna za skalowanie obrazu wzglêdem Aspect Ratio
// Dostosowuje automatycznie rozdzielczoœæ i zmienia zakres widzenia poprzez przesuwanie VIEWPORTU
// poszczególne wartoœci w if,else statemant mog¹ ulec zmianie aby lepiej dopasowaæ skalowanie
package pl.studia.util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import pl.studia.util.Constants;

public class ResolutionManager {

    
    public static float scale = 1f;
    public static Vector2 crop = new Vector2(0f, 0f);
	
	
    
    public static void scaleDisplayResolution(int width,int height){
    	float aspectRatio = (float)width/(float)height;
    	scale=1;
    	
    	if(aspectRatio > Constants.ASPECT_RATIO) // jesli szerokosc ekranu wieksza (za duza) lub wys za ma³a wzgledem szerokosci
        {
        	if(width>1920){
	        	scale = (float)height/((float)Constants.VIRTUAL_HEIGHT);
	        	System.out.println("Scale1: "+scale);
	            crop.x = (width - Constants.VIRTUAL_WIDTH*scale)/2f;
        	}
        	else if(height<550){
        		scale = (float)height/((float)Constants.VIRTUAL_HEIGHT);
        		scale+=0.3;
            	System.out.println("Scale2: "+scale);
                crop.x = (width - Constants.VIRTUAL_WIDTH*scale)/2f;
                
        	}
        	else if(width<1920&&width>550){
        		scale = (float)height/((float)Constants.VIRTUAL_HEIGHT);
        		scale+=0.25;
            	System.out.println("Scale7: "+scale);
                crop.x = (width - Constants.VIRTUAL_WIDTH*scale)/2f;
        	}
        	else{
        		System.out.println("Scale8: "+scale);
        		crop.x =0;
        	}
           
        }
        else if(aspectRatio < Constants.ASPECT_RATIO) //jesli szerokosc ekranu mniejsza (za ma³a) 
        {
        	if(width<1024&&width>1000){
        	scale = (float)width/((float)Constants.VIRTUAL_WIDTH);
        	scale+=0.25;
            crop.y = (height - Constants.VIRTUAL_HEIGHT*scale)/2f;
            System.out.println("Scale4: "+scale);
        	}
        	else if(width<1000&&width>500){
        		scale=(float)1000/(float)1920;
        		scale+=0.35;
        		if(height<=600)
        			crop.y = (height - Constants.VIRTUAL_HEIGHT*scale +340.5f)/2f;
        		else
        		if(height<=500)
        			crop.y = (height - Constants.VIRTUAL_HEIGHT*scale +380f)/2f;
        		else
        			crop.y = (height - Constants.VIRTUAL_HEIGHT*scale)/2f;
        		
        		System.out.println("Scale5: "+scale);
        	}
        	else if(width<=500){
        		scale=(float)1000/(float)1920;
        		scale+=0.15;
        		if(height<=500)
        			crop.y = (height - Constants.VIRTUAL_HEIGHT*scale +220)/2f;
        		else
        			crop.y = (height - Constants.VIRTUAL_HEIGHT*scale)/2f;
        		
        		System.out.println("Scale6: "+scale);     
        	}
        	else
        	{
        		crop.y=0;
        	}
        }
        else
        {
        	if(width<1440){
            scale = (float)width/(float)Constants.VIRTUAL_WIDTH;
            scale+=0.25;
            System.out.println("first");
        	}
        	
        	else{
        		scale=1;
        		System.out.println("second");
        		}
        }

    }
    
    
    public enum HorizontalAlignment {
	    LEFT, MIDDLE, RIGHT
	}
	 
	public enum VerticalAlignment {
	    TOP, MIDDLE, BOTTOM
	}
	 
	public static float calculateX(HorizontalAlignment horizontal, float deltax, float sizex) {
	        int width = Gdx.graphics.getWidth();
	 
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
	 
	    public static float calculateY(VerticalAlignment vertical, float deltay, float sizey) {
	        int height = Gdx.graphics.getHeight();
	 
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
