package controller;

import java.util.HashMap;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyValue;
import javafx.animation.KeyFrame;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreensController extends StackPane {

	public HashMap<String, Node> screens = new HashMap<>();
	
	private boolean FADE = false;
	
	public void addScreen(String id, Node screen) {
		screens.put(id, screen);
	}
	
	public boolean setScreen(final String id) {
		if (FADE) {	//Use silly fade animation
		    if (screens.get(id) != null) {   //screen loaded
		        final DoubleProperty opacity = opacityProperty();
		
		        if (!getChildren().isEmpty()) {
		            Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(250), new EventHandler<ActionEvent>() {
		               
                    	@Override
		                public void handle(ActionEvent t) {
		                    getChildren().remove(0);
		                    getChildren().add(0, screens.get(id));
		                    Timeline fadeIn = new Timeline(
		                            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
		                            new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
		                    fadeIn.play();
		                }
		            }, new KeyValue(opacity, 0.0)));
		            fade.play();
	
		        } else {

		        	setOpacity(0.0);
		            getChildren().add(screens.get(id));
		            Timeline fadeIn = new Timeline(
		                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
		                    new KeyFrame(new Duration(625), new KeyValue(opacity, 1.0)));
		            fadeIn.play();
		        }
		        return true;
		    } else {
		        System.out.println("screen hasn't been loaded!!! \n");
		        return false;
		    }
		} else {	// No silly fade animation //////////////////////////////////////////////////////////
			if(screens.get(id) != null) { 
				
				// If there is one or more screens
				if ( !getChildren().isEmpty() ) { 
					// Remove displayed screen
					getChildren().remove(0);
					// Add new screen
					getChildren().add(0, screens.get(id));
		    	
				} else { 
					// No one else been displayed, then just show 
					getChildren().add(screens.get(id)); 
		        }
		        return true;
		    
			} else {
				
				System.out.println("Screen [ "+id+" ] hasn't been loaded!\n"); 
		        return false; 
			
			}
		}
		
	}
	
	
	/**
	 * Unloads specified screen from screens.
	 * @param id
	 * @return true on success
	 */
	public boolean unloadScreen(String id) {
		if ( screens.remove(id) == null) {
			//TODO : Report error.
			return false;
		}
		return true;
	}
	
	
	/**
	 * Loads the resource(FXML) 
	 * @param id
	 * @param resource
	 * @return
	 */
	public boolean loadScreen(String id, String resource) {
		try {
		
			if ( id.isEmpty() || resource.isEmpty() )
				System.out.println("[ERROR] ScreensController.LoadScreen Method Contains Null Parameter.");
			
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
			Parent loadScreen = (Parent) myLoader.load();
			iControlledScreen myScreenController = ( (iControlledScreen) myLoader.getController());	
			myScreenController.setScreenParent(this);
			addScreen(id, loadScreen);
			return true;
		
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return false;
		
		}
	}
}
