package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Route;

public class DriverRoutesController implements Initializable, iControlledScreen {

	private ScreensController myController;
	
	@FXML TableView<Route> tableID;
	@FXML TableColumn<Route, String> iRouteID;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Init table cells
		iRouteID.setCellValueFactory( new PropertyValueFactory<Route, String>("mID") );
		//Populate Table
		tableID.getItems().addAll( Main.driver.getRoutes() );
		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
	}

	
	@FXML
	public void deleteSelectedRoute(ActionEvent event) {
		
		//Remove from driver.routes first!
		Main.driver.getRoutes().remove( tableID.getSelectionModel().getSelectedItem() );
		tableID.getItems().remove( tableID.getSelectionModel().getSelectedIndex() );
		
		
	}
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		
	}
	
	
	@FXML
	public void goToDriverRoutes(ActionEvent event) {		
		Main.mainContainer.loadScreen(Main.driverViewRoutesID, Main.driverViewRoutesFile);
		myController.setScreen(Main.driverViewRoutesID);
	}
	
	
	@FXML
	public void goToDriverHome( ActionEvent event ) {
		Main.mainContainer.loadScreen(Main.driverHomeID, Main.driverHomeFile);
		myController.setScreen( Main.driverHomeID );
	}
	
	@FXML
	public void goToDriverCreateRoute(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverCreateRoutesID, Main.driverCreateRoutesFile);
		myController.setScreen(Main.driverCreateRoutesID);
	}
	

}
