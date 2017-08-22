package controller;

import java.net.URL;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import model.Car;
import model.Route;
import model.Trip;

public class DriverCreateTripsController implements Initializable, iControlledScreen {

	@FXML ComboBox<Route> cb_route;
	@FXML TextField tf_tripID;
	
	@FXML ToggleButton tb_direction;
	@FXML ToggleButton tb_fromUni;
	@FXML ComboBox<Car> cb_car;
	
	@FXML Pane pane_recurring;
	@FXML CheckBox chkb_recurring;
	@FXML DatePicker dp_expiryDate;
	
	@FXML CheckBox chkb_monday;
	@FXML CheckBox chkb_tuesday;
	@FXML CheckBox chkb_wednesday;
	@FXML CheckBox chkb_thursday;
	@FXML CheckBox chkb_friday;
	@FXML CheckBox chkb_saturday;
	@FXML CheckBox chkb_sunday;
	
	private ScreensController myController;
	public static Route ROUTE;	//Dangerous+stupid but working. TODO::FIXME
	
	@Override
	public void initialize( URL location, ResourceBundle resources ) {
		
		//Populate comboBoxes
		cb_route.getItems().addAll( Main.driver.getRoutes() );
		cb_car.getItems().addAll( Main.driver.getCars() );
		
		checkRecurring();
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}
	
	
	/**
	 * Attempt to create a trip from UI values. 
	 * Goes to DriverViewTrips on success.
	 * Shows Error Popup On Error.
	 */
	@FXML
	public void createTrip() {
	
		// Cache UI Vals //////////////////////////////////////////////////
		
		// Expiry Date : Requires [java.time.format.DateTimeFormatter] & [java.time.LocalDate;]
		LocalDate expiry = null;
		
		// Check Null
		if ( dp_expiryDate.getValue() != null ) {
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			expiry = dp_expiryDate.getValue();
			//expiry = LocalDate.parse( dp_expiryDate.getValue().format(dtf) );
		} else {
			// If Null : Display Error and exit.
			Main.Alert("Error",  "Please select an expiry date.",  AlertType.WARNING);
			return;
			// A non-null Trip cannot be instantiated with variables as null.
		}
		
		// Direction
		Trip.Direction dir = tb_direction.isSelected() ? Trip.Direction.toUni : Trip.Direction.fromUni;
		
		// Recurring
		boolean recur = tb_direction.isSelected();
		
		// Car
		Car car = cb_car.getSelectionModel().getSelectedItem();
		
		// Trip ID
		String id = tf_tripID.getText();
		
		// Read [days] check boxes as ArrayLists of Booleans :     ( mon -> sun  = index [0 -> 6] )
		// ( First ensure they are all deselected if recurring check box is false. )
		if ( !chkb_recurring.isSelected() ) { deselectDays(); }
		
		ArrayList<Boolean> days = new ArrayList<Boolean>();
		days.add( chkb_monday.isSelected() );
		days.add( chkb_tuesday.isSelected() );
		days.add( chkb_wednesday.isSelected() );
		days.add( chkb_thursday.isSelected() );
		days.add( chkb_friday.isSelected() );
		days.add( chkb_saturday.isSelected() );
		days.add( chkb_sunday.isSelected() );
	
		//////////////////////////////////////////////////////////////
		
		// Set Route
		ROUTE = cb_route.getSelectionModel().getSelectedItem();
		
		// Create Trip from cached vals
		Trip trip = new Trip(ROUTE, days, dir, recur, expiry, car, id);
		
		// Check validity
		 if ( trip.isValid() ) { // Valid:
			
			 // Add to Database
			 Main.fauxDatabase.addTrip(trip);
			 // And proceed to view trips [Calls local method]
			 goToDriverViewTrips(null);
			 
		 } else { // Invalid:
			 Main.Alert("Error", "The Trip Is Not Valid.",  AlertType.ERROR);
		 }
		
	}

	
	@FXML
	private void deselectDays() {
		chkb_monday.setSelected(false);
		chkb_tuesday.setSelected(false);
		chkb_wednesday.setSelected(false);
		chkb_thursday.setSelected(false);
		chkb_friday.setSelected(false);
		chkb_saturday.setSelected(false);
		chkb_sunday.setSelected(false);
	}
	
	@FXML
	private void checkRecurring() {
		if ( chkb_recurring.isSelected() ) {
			//Show expirydate, tripdates and listview
			pane_recurring.setVisible(true);
		} else {
			pane_recurring.setVisible(false);
		}
	}
	
	@FXML
	private void toggleDirection() {
		if ( tb_direction.isSelected() ) {
			tb_direction.setText("To University");
		} else {
			tb_direction.setText("From University");
		}
	}
	
	
	@FXML
	public void goToDriverEditTripStopTimes( ActionEvent event ) {
		
		// Check that a Route has been selected first.
		if ( cb_route.getSelectionModel().getSelectedItem() != null ) {
			
			//Set ROUTE
			ROUTE = cb_route.getSelectionModel().getSelectedItem();
			
			//Load
			Main.mainContainer.loadScreen(Main.driverEditTripStopTimesID, Main.driverEditTripStopTimesFile);
			myController.setScreen( Main.driverEditTripStopTimesID );
		
		} else {
		
			Main.Alert("Error", "Please Select A Route First.", AlertType.ERROR);
		
		}
	
	}

	@FXML
	public void goToDriverHome( ActionEvent event ) {
		Main.mainContainer.loadScreen(Main.driverHomeID, Main.driverHomeFile);
		myController.setScreen( Main.driverHomeID );
	}
	
	@FXML
	public void goToDriverViewTrips( ActionEvent event ) {
		Main.mainContainer.loadScreen(Main.driverViewTripsID, Main.driverViewTripsFile);
		myController.setScreen( Main.driverViewTripsID );
	}

}

//OLD CODE -- When I used actual dates instead of mon-sunday booleans - as per instructions.
//@FXML
//public void addDate() {
//	
//	// Get selected Date
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	LocalDate date = dp_select.getValue();
//	if ( date != null && date.isAfter(LocalDate.now() ) ) {
//		
//		//Add date to list
//		lv_validDates.getItems().add( dp_select.getValue().format(formatter) );
//	
//	} else {
//		
//		Main.Alert( "Invalid Date", "Please select a valid date.", AlertType.INFORMATION );
//	
//	}
//	
//}
//
//
//
//@FXML
//public void removeDate() {
//	
//	//Remove selected index
//	if ( lv_validDates.getSelectionModel().getSelectedItem() != null ) {
//		lv_validDates.getItems().remove( lv_validDates.getSelectionModel().getSelectedIndex() );
//	}
//	
//}
