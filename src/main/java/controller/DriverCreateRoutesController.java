package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Route;
import model.StopPoint;

public class DriverCreateRoutesController implements Initializable, iControlledScreen {

	@FXML TableView<StopPoint> leftTable;
	@FXML TableColumn<StopPoint, String> iNumber;
	@FXML TableColumn<StopPoint, String> iStreet;	
	@FXML TableColumn<StopPoint, String> iSuburb;
	
	@FXML TableView<StopPoint> rightTable;
	@FXML TableColumn<StopPoint, String> iAddress;
	@FXML TextField tf_routeID;
	
	private ScreensController myController;
	
	private ObservableList<StopPoint> selectedStopPoints = FXCollections.observableArrayList();
	private boolean hasWarned = false;
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Init Left Table
		iNumber.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mNumber") );
		iStreet.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mStreet") );
		iSuburb.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mSuburb") );
		//Populate Left Table
		leftTable.getItems().addAll( Main.driver.getStopPoints() );
		//Default Selection to first item.
		leftTable.getSelectionModel().selectFirst();
		//Init Right Table
		iAddress.setCellValueFactory( new PropertyValueFactory<StopPoint, String>("mAddress") );
		
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
	public void addToSelectionTable() {
		
		//Get selected elements from leftTable.
		StopPoint sp = new StopPoint();
		
		// Fix for the rare occasion that you drag columns in table around
		// Resulting in no row being selected after drag.
		if ( leftTable.getSelectionModel().getSelectedItem() == null ) {
			leftTable.getSelectionModel().selectFirst(); // So simply select the first.
			return; // And exit
		}
		
		sp = leftTable.getSelectionModel().getSelectedItem();
		
		if ( !selectedStopPoints.contains(sp) ) {
			
			selectedStopPoints.add(sp);
			rightTable.getItems().add(sp);

			System.out.println("Adding to selection: ["+sp.getNumber() + ", "+sp.getStreet()+", "+sp.getSuburb()+" ]");
			
		} else {
			Main.Alert("Error", "This StopPoint has already been added.",  AlertType.WARNING);
		}
		
	}
	
	
	@FXML
	public void removeFromSelectionTable() {
		
		//Remove from both HashSet and Table
		selectedStopPoints.remove( rightTable.getSelectionModel().getSelectedItem() );
		rightTable.getItems().remove( rightTable.getSelectionModel().getSelectedItem() );
	
	}
	
	
	@FXML
	public void createRoute() {		
	
		//Warn user if no ROUTE ID is provided.
		if ( tf_routeID.getText().isEmpty() && !hasWarned ) {
			Main.Alert("Warning", "You are about to create an unnamed Route. If you contintue, the ROUTE will be named 'Route'.",  AlertType.WARNING);
			hasWarned = true;
			return;
		}
		
		//Create ROUTE object and add stopPoints selected.
		Route r = new Route( tf_routeID.getText() );
		
		//Add points from UI selectedStopPoints tableView
		r.getStopPoints().addAll(selectedStopPoints);

		// Check if ROUTE is valid
		if ( !r.isValid() ) {
			Main.Alert("Error",  "Route Is Not Valid.",  AlertType.ERROR);
			return;
		}
		
		r.getStopPoints().addAll(selectedStopPoints);
		
		//Add this ROUTE to the driver
		Main.driver.getRoutes().add(r);
		
		//Preload DriverRoutesController TableView
		Main.mainContainer.loadScreen(Main.driverViewRoutesID, Main.driverViewRoutesFile);
	
		//Then switch to routes page.
		myController.setScreen( Main.driverViewRoutesID );
		
	}


}
