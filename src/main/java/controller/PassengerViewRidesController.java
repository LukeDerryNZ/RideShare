package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.StopPoint;


public class PassengerViewRidesController implements Initializable, iControlledScreen{

	private ScreensController myController;
	
	private StopPoint selectedStopPoint;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		handleStopPointSelection();
		
		// Init Javafx
		
		
	}
	
	
	/**
	 * Simply sets the selected StopPoint and handles null error
	 */
	private void handleStopPointSelection() {
		
		// Obtain previously selected Stop Point.
		selectedStopPoint = PassengerViewStopPointsController.SELECTEDSTOPPOINT;
		if ( selectedStopPoint == null ) {
			System.out.println("[System Error : Selected Stop Point Was Null.]");
			//Return to previous screen if somehow we have no stoppoint selected.
			goToPassengerBrowseStopPoints(null);
		}
		System.out.println(selectedStopPoint.getAddress() + " Was Selected." );
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
	
	@FXML
	public void goToBookARide(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.bookARideID,  Main.bookARideFile);
		myController.setScreen(Main.bookARideID);
	}
	
}
