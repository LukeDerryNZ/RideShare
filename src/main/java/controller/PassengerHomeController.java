package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PassengerHomeController implements Initializable, iControlledScreen {

	private ScreensController myController;
	
	@FXML Label lbl_passengerHome;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setHeader();
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}

	@FXML
	public void goToLogin(ActionEvent event) {
		myController.setScreen(Main.loginID);
	}
	
	@FXML
	public void goToPassengerBrowseStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.passengerViewStopPointsID,  Main.passengerViewStopPointsFile);
		myController.setScreen(Main.passengerViewStopPointsID);
	}

	@FXML
	public void goToPassengerFindRide(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.passengerViewRidesID,  Main.passengerViewRidesFile);
		myController.setScreen(Main.passengerViewRidesID);
	}

	public void setHeader() {
		
		if (Main.username.length() > 0 ) {
			lbl_passengerHome.textProperty().set("[ Welcome "+Main.username+" ]");
		} else {
			lbl_passengerHome.textProperty().set("Passenger Home");
		}
		
	}
	
}
