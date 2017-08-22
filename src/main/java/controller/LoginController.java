package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable, iControlledScreen {

	ScreensController myController;
	
	@FXML TextField tf_username;
	@FXML Label lb_VERSIONID;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Set VERSIONID
		lb_VERSIONID.textProperty().set(Main.CREATORID+" : "+Main.VERSIONID);
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		
	}
	
	
	@FXML
	public void goToDriverHome(ActionEvent event) {
		
			setUsername();
			Main.mainContainer.loadScreen(Main.driverHomeID, Main.driverHomeFile);
			myController.setScreen(Main.driverHomeID);
		
	}

	
	@FXML
	public void goToPassengerHome(ActionEvent event) {
		
			setUsername();
			Main.mainContainer.loadScreen(Main.passengerHomeID, Main.passengerHomeFile);
			myController.setScreen(Main.passengerHomeID);			
		
	}

	
	
	/**
	 * Check whether the username and password are correct.
	 * FORNOW: Just accept all.
	 * @return
	 */
	private boolean checkCredentials() {
		
		if ( tf_username.getText().trim().length() >= Main.USERNAME_SIZE ) {
			return true;
		}
		return false;
		
	}

	
	public void setUsername() {
		
		if ( checkCredentials() ) {
			
			Main.username = tf_username.getText().trim();
		
		} else {
			Main.username = "DEBUG";
		}
		
	}


}
