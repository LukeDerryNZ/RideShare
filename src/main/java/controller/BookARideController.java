package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class BookARideController implements Initializable, iControlledScreen {

	private ScreensController myController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Init
		
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}

	@FXML
	public void goToPassengerBrowseStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.passengerViewStopPointsID, Main.passengerViewStopPointsFile);
		myController.setScreen(Main.passengerViewStopPointsID);
	}
	
}
