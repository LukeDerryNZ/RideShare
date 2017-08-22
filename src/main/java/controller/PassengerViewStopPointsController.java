package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StopPoint;

public class PassengerViewStopPointsController implements Initializable, iControlledScreen {
	

	private ScreensController myController;
	
	@FXML TableView<StopPoint> tableID;
	@FXML TableColumn<StopPoint, String> iNumber;
	@FXML TableColumn<StopPoint, String> iStreet;	
	@FXML TableColumn<StopPoint, String> iSuburb;

	@FXML TextField tf_Number;
	@FXML TextField tf_Street;
	@FXML TextField tf_Suburb;
	
	public static StopPoint SELECTEDSTOPPOINT;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Init table cells
		iNumber.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mNumber") );
		iStreet.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mStreet") );
		iSuburb.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mSuburb") );
		
		// Retrieve all StopPoints from Database
		tableID.getItems().addAll( Main.fauxDatabase.getStopPoints() );
		// Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
		
		SELECTEDSTOPPOINT = tableID.getSelectionModel().getSelectedItem();
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}
	
	
	@FXML
	public void search() {
		
		// Read search params
		String num = tf_Number.getText().trim().toLowerCase();
		String str  = tf_Street.getText().trim().toLowerCase();
		String sub = tf_Suburb.getText().trim().toLowerCase();
		
		if ( num.isEmpty() && str.isEmpty() && sub.isEmpty() ) {
			// clearSearch();
			return;
		}
		
		// Create new list
		List<StopPoint> temp = new ArrayList<StopPoint>();
		
		// Create sublist from params 		 [ a.contains(b) or b.contains(a) ]
		for ( StopPoint sp : tableID.getItems() ) {
			String spNum = sp.getNumber().toLowerCase();
			String spStr = sp.getStreet().toLowerCase();
			String spSub = sp.getSuburb().toLowerCase();
			
			if ( 	  !num.isEmpty() && (spNum.contains(num) || num.contains(spNum)) ) {
				temp.add(sp);
			}
			
			else if ( !str.isEmpty() && (spStr.contains(str) || str.contains(spStr)) ) {
				temp.add(sp);
			}
			
			else if ( !sub.isEmpty() && (spSub.contains(sub) || sub.contains(spSub)) ) {
				temp.add(sp);
			}
		}
		
		// Update tableview items
		tableID.getItems().clear();
		tableID.getItems().addAll(temp);
	
	}
	
	
	@FXML
	public void clearSearch() {
		
		// Clear Search Params
		tf_Number.clear();
		tf_Street.clear();
		tf_Suburb.clear();
		
		// And Reload Table
		tableID.getItems().clear();
		tableID.getItems().addAll( Main.fauxDatabase.getStopPoints() );
		
	}
	
	
	@FXML
	public void goToPassengerHome(ActionEvent event) {	
		SELECTEDSTOPPOINT = null;
		myController.setScreen(Main.passengerHomeID);
	}
	
	@FXML
	public void goToPassengerViewRides(ActionEvent event) {
		// Get selected Stop Point
		SELECTEDSTOPPOINT = tableID.getSelectionModel().getSelectedItem();
		Main.mainContainer.loadScreen(Main.passengerViewRidesID,  Main.passengerViewRidesFile);
		myController.setScreen(Main.passengerViewRidesID);
	}
	
}
