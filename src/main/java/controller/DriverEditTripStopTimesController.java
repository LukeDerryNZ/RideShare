package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StopPoint;

public class DriverEditTripStopTimesController implements Initializable, iControlledScreen{

	private ScreensController myController;

	@FXML public TableView<StopPoint> tableID;
	@FXML public TableColumn<StopPoint, String> iNumber;
	@FXML public TableColumn<StopPoint, String> iStreet;	
	@FXML public TableColumn<StopPoint, String> iSuburb;
	@FXML public TableColumn<StopPoint, String> iTime;
	
	@FXML TextField tf_EditTime;
	
	@Override
	public void initialize( URL location, ResourceBundle resources ) {

		//Init table cells
		iNumber.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mNumber") );
		iStreet.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mStreet") );
		iSuburb.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mSuburb") );
		iTime.setCellValueFactory(   new PropertyValueFactory<StopPoint, String>("mTime") );
		
		//Retrieve all StopPoints PREVIOUSLY selected route.
		tableID.getItems().addAll( DriverCreateTripsController.ROUTE.getStopPoints() );

		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
	}

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		
	}
	
	@FXML
	public void setEditTime() {
		
		// Check if it is in format [HH:mm]
		try {
			
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm");
			date = format.parse( tf_EditTime.getText() );
			
			// On Success, Add As Normal String.
			tableID.getSelectionModel().getSelectedItem().setTime( format.format(date) );
			
			clearEditTime();
			
		} catch ( Exception e ) {
		
			Main.Alert("Error", "Invalid Time Format. Please use [HH:mm]", AlertType.ERROR);
		
		}
		
	}
	
	
	@FXML
	public void clearEditTime() {
		
		tf_EditTime.clear();
	
	}
	
	
	@FXML
	public void goToDriverCreateTrip(ActionEvent event) {
		
		//Do not load - use existing values to return to previous state.
		
		myController.setScreen(Main.driverCreateTripsID);
		
	}


}
