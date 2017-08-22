package controller;

import java.net.URL;
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

public class DriverCreateStopPointsController implements Initializable, iControlledScreen {

	private ScreensController myController;
	
	@FXML TableView<StopPoint> tableID;
	@FXML TableColumn<StopPoint, String> iNumber;
	@FXML TableColumn<StopPoint, String> iStreet;
	@FXML TableColumn<StopPoint, String> iSuburb;
	@FXML TextField mNumber;
	@FXML TextField mStreet;
	@FXML TextField mSuburb;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Init table cells
		iNumber.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mNumber") );
		iStreet.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mStreet") );
		iSuburb.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mSuburb") );
		//Populate Table
		tableID.getItems().addAll( Main.driver.getStopPoints() );
		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
	}


	@Override
	public void setScreenParent(ScreensController screenParent) {

		myController = screenParent;
		
	}
	
	
	@FXML
	public void goToDriverHome( ActionEvent event ) {
		
		myController.setScreen( Main.driverHomeID );
		
	}
	
	
	@FXML
	public void addStopPoint() {
		
		String num = mNumber.getText().trim();
		String st =  mStreet.getText().trim();
		String sub = mSuburb.getText().trim();
		
		
		//Check not null
		if ( num.isEmpty() || st.isEmpty() || sub.isEmpty()) {
			
			Main.Alert("Notification",  "All fields must contain text.",  AlertType.INFORMATION);
			return;
		}
		
		//Create StopPoint
		StopPoint sp = new StopPoint( mNumber.getText(), mStreet.getText(), mSuburb.getText() );
		
		// Check StopPoint Validity
		if ( !sp.isValid() ) {

			Main.Alert("Error",  "StopPoint is not valid. Null Values not accepted.", AlertType.ERROR);
			return;
		}
		
	
		//Custom search if exists TODO : Get compareTo override in StopPoint working!!!
		boolean exists = false;
		for (StopPoint s : Main.driver.getStopPoints() ) {

			if ( sp.compareTo(s) == 0 )
				exists = true;
		
		}
		
		// If it exists already. end method.
		if ( exists ) { 		

			Main.Alert("Notification",  "Duplicate StopPoint Found.",  AlertType.INFORMATION);
			clearTextFields();
			return;
		
		}
	
		//Add StopPoint and clean up.
		System.out.println( "StopPoint[ "+num+", "+st+", "+sub+" ] created." );
		Main.driver.getStopPoints().add(sp);
		
		// Add to FauxDatabase
		System.out.println("[Added Stop Point to Database. ]");
		Main.fauxDatabase.getStopPoints().add(sp);
		
		clearTextFields(); 				// Clear fields
		tableID.getItems().clear();		// And refresh the Table
		tableID.getItems().addAll( Main.driver.getStopPoints() );
		
	}
	
	
	private void clearTextFields() {
		
		mNumber.clear();
		mStreet.clear();
		mSuburb.clear();
	
	}

	
	@FXML
	public void goToDriverViewStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverViewStopPointsID, Main.driverViewStopPointsFile);
		myController.setScreen(Main.driverViewStopPointsID);
	}
	
}
