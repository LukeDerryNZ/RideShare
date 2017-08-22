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

public class DriverViewStopPointsController implements Initializable, iControlledScreen{
	

	private ScreensController myController;
	
	@FXML TableView<StopPoint> tableID;
	@FXML TableColumn<StopPoint, String> iNumber;
	@FXML TableColumn<StopPoint, String> iStreet;
	@FXML TableColumn<StopPoint, String> iSuburb;

	@FXML TextField tf_Number;
	@FXML TextField tf_Street;
	@FXML TextField tf_Suburb;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Init table cells
		iNumber.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mNumber") );
		iStreet.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mStreet") );
		iSuburb.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mSuburb") );
		
		//Retrieve all StopPoints from Database
		tableID.getItems().addAll( Main.fauxDatabase.getStopPoints() );
		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
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
			return;
		}
		
		// Create new temp list
		List<StopPoint> temp = new ArrayList<StopPoint>();
		
		// Create sublist from params 		 [ a.contains(b) or b.contains(a) ]
		for ( StopPoint sp : tableID.getItems() ) {
			String spNum = sp.getNumber().toLowerCase();
			String spStr = sp.getStreet().toLowerCase();
			String spSub = sp.getSuburb().toLowerCase();
			
			if ( !num.isEmpty() && (spNum.contains(num) || num.contains(spNum)) ) {
				temp.add(sp);
			}
			
			else if ( !str.isEmpty() && (spStr.contains(str) || str.contains(spStr)) ) {
				temp.add(sp);
			}
			
			else if ( !sub.isEmpty() && (spSub.contains(sub) || sub.contains(spSub)) ) {
				temp.add(sp);
			}
			
			//NOTE: As this is, consecutive attempts to search without clearing does NOT work.
			// So force clearing of search query after each search. Until this is resolved.
			tf_Number.clear();
			tf_Street.clear();
			tf_Suburb.clear();
		}
		
		// Update tableview items
		tableID.getItems().clear();
		tableID.getItems().addAll(temp);
	
	}
	
	
	@FXML
	public void clearSearch() {
		
		//Clear Search Params
		tf_Number.clear();
		tf_Street.clear();
		tf_Suburb.clear();
		
		//And Reload Table
		tableID.getItems().clear();
		tableID.getItems().addAll( Main.fauxDatabase.getStopPoints() );
		
	}
	
	
	@FXML
	public void goToDriverHome(ActionEvent event) {	
		myController.setScreen(Main.driverHomeID);
	}
	
	@FXML
	public void goToDriverCreateStopPoints(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateStopPointsID, Main.driverCreateStopPointsFile);
		myController.setScreen(Main.driverCreateStopPointsID);
	}
	
	
}
