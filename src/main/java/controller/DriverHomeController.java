package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DriverHomeController implements Initializable, iControlledScreen {

	@FXML Label lbl_driverHome;
	
	private ScreensController myController;


	
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		
		setHeader();
		
	}

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		
	}
	
	
	public void setHeader() {
		
		if (Main.username.length() > 0 ) {
			lbl_driverHome.textProperty().set("[ Welcome "+Main.username+" ]");
		} else {
			lbl_driverHome.textProperty().set("Driver Home");
		}
	}
	
	
	
	
	@FXML
	public void goToLogin(ActionEvent event) {
		myController.setScreen(Main.loginID);
	}
	
	@FXML
	public void goToDriverCreateStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateStopPointsID, Main.driverCreateStopPointsFile);
		myController.setScreen(Main.driverCreateStopPointsID);
	}
	
	@FXML
	public void goToDriverViewRoutes(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverViewRoutesID, Main.driverViewRoutesFile);
		myController.setScreen(Main.driverViewRoutesID);
	}
	
	@FXML
	public void goToDriverCreateRoute(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateRoutesID, Main.driverCreateRoutesFile);
		myController.setScreen(Main.driverCreateRoutesID);
	}

	@FXML
	public void goToDriverRegisterCar(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverRegisterCarID, Main.driverRegisterCarFile);
		myController.setScreen(Main.driverRegisterCarID);
	}
	
	@FXML
	public void goToDriverViewCars(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverViewCarsID, Main.driverViewCarsFile);
		myController.setScreen(Main.driverViewCarsID);
	}
	
	@FXML
	public void goToDriverViewStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverViewStopPointsID, Main.driverViewStopPointsFile);
		myController.setScreen(Main.driverViewStopPointsID);
	}
	
	@FXML
	public void goToDriverCreateTrip(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateTripsID, Main.driverCreateTripsFile);
		myController.setScreen(Main.driverCreateTripsID);
	}
	
	@FXML
	public void goToDriverViewTrips(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverViewTripsID, Main.driverViewTripsFile);
		myController.setScreen(Main.driverViewTripsID);
	}

}
