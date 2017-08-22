package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Trip;

public class DriverViewTripsController implements Initializable, iControlledScreen {

	private ScreensController myController;
	
	@FXML TableView<Trip> tableID;
	@FXML TableColumn<Trip, String> iTripID;
	@FXML TableColumn<Trip, String> iRouteID;
	@FXML TableColumn<Trip, String> iCar;
	@FXML TableColumn<Trip, String> iDirection;
	
	
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		
		//Init table cells
		iTripID.setCellValueFactory( new PropertyValueFactory<Trip, String>("mTripID") );
		iRouteID.setCellValueFactory( new PropertyValueFactory<Trip,String>("mRouteID") );
		iCar.setCellValueFactory( new PropertyValueFactory<Trip, String>("mCarID") );
		iDirection.setCellValueFactory( new PropertyValueFactory<Trip, String>("mDirection") );
		
		//Populate Table
		tableID.getItems().addAll( Main.fauxDatabase.getTrips() );
		
		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
		
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}
	
	
	@FXML
	public void goToDriverHome( ActionEvent event ) {
		Main.mainContainer.loadScreen(Main.driverHomeID, Main.driverHomeFile);
		myController.setScreen( Main.driverHomeID );
	}
	
	
	@FXML
	public void goToDriverCreateTrip(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateTripsID, Main.driverCreateTripsFile);
		myController.setScreen(Main.driverCreateTripsID);
	}
	
	
}
