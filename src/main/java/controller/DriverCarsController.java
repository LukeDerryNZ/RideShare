package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Car;

public class DriverCarsController implements Initializable, iControlledScreen{

	private ScreensController myController;
	
	@FXML TableView<Car> tableID;
	@FXML TableColumn<Car, String> typeCol;
	@FXML TableColumn<Car, String> modelCol;
	@FXML TableColumn<Car, String> colourCol;
	@FXML TableColumn<Car, String> regNoCol;
	@FXML TableColumn<Car, String> yearCol;
	@FXML TableColumn<Car, String> seatsNoCol;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		//Init left table
		typeCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mType") );
		modelCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mModel") );
		colourCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mColour") );
		regNoCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mRegistrationNumber") );
		yearCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mYear") );
		seatsNoCol.setCellValueFactory( new PropertyValueFactory<Car, String>("mSeatsNum") );
		//Populate Table
		tableID.getItems().addAll( Main.driver.getCars() );
		//Default Selection to first item.
		tableID.getSelectionModel().selectFirst();
	}

	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		
		myController = screenParent;
		
	}


	@FXML
	public void unregisterCar() {

		//Remove selected car
		Main.driver.getCars().remove( tableID.getSelectionModel().getSelectedItem() );
		tableID.getItems().remove( tableID.getSelectionModel().getSelectedIndex() );
	
	}
	
	@FXML
	public void goToDriverRegisterCar(ActionEvent event) {
		Main.mainContainer.loadScreen(Main.driverRegisterCarID, Main.driverRegisterCarFile);
		myController.setScreen(Main.driverRegisterCarID);
	}
	
	@FXML
	public void goToDriverHome( ActionEvent event ) {	
		Main.mainContainer.loadScreen(Main.driverHomeID, Main.driverHomeFile);
		myController.setScreen( Main.driverHomeID );
	}


}
