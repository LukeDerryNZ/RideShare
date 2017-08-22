package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Car;

public class DriverRegisterCarController implements Initializable, iControlledScreen {

	ScreensController myController;
	
	@FXML TextField tf_Model;
	@FXML TextField tf_RegistrationNumber;
	@FXML TextField tf_Year;

	ObservableList<String> Colours = (ObservableList<String>) FXCollections.observableArrayList(
			"Black", "White", "Grey", "Red", "Green", "Blue", "Yellow", "Brown", "Silver", "Gold", "Indiscernible" );
	@FXML private ComboBox<String> cb_Colour = new ComboBox<String>();
	
	@FXML private ComboBox<Car.Type> cb_CarType = new ComboBox<Car.Type>();
	
	ObservableList<String> SeatNums; //Filled in initialize()
	
	@FXML private ComboBox<String> cb_SeatNum = new ComboBox<String>();
	
	private final int minSeats = 0;
	private final int maxSeats = 10;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Populate ComboBoxes
		cb_Colour.getItems().addAll(Colours);		
		cb_CarType.setItems( FXCollections.observableArrayList( Car.Type.values() ) );
		
		for (int i=minSeats; i<=maxSeats;i++) {
			cb_SeatNum.getItems().add( Integer.toString(i) );
		}
		//Set first set
		cb_SeatNum.getSelectionModel().selectFirst();
		
	}
	
	
	/**
	 * Register Car. Handle UI Input Errors.
	 * On Success, Set Screen To View Cars Screen.
	 */
	@FXML
	public void registerCar() {
		
		// Cache car vals
		Car.Type type = cb_CarType.getSelectionModel().getSelectedItem();
		String reg = tf_RegistrationNumber.getText();
		String model = tf_Model.getText();
		String col = cb_Colour.getSelectionModel().getSelectedItem();
		int numsts = Integer.parseInt( cb_SeatNum.getSelectionModel().getSelectedItem() ); //Default set to firstItem
		
		//Read year [ CRITERIA: 4 Integers Only ]
		int yr = 0;
		if ( !tf_Year.getText().isEmpty() && tf_Year.getText().matches("^\\d{4}") ) {
			yr = Integer.parseInt( tf_Year.getText() );
		}

		// Create car object
		Car car = new Car(type, model, reg, col, numsts, yr);
		
		// Check car object validity
		if ( !car.isValid() ) {

			Main.Alert("Error",  "Invalid Car. Ruleset:\n "
					+ "[ Valid Years = 1890-Current]\n "
					+ "[ Valid Reg Length = 4  -  6]\n "
					+ "[ Null Variables Not Allowed]\n ", AlertType.ERROR);
			return;
		
		} 
		
		// Add to driver cars list.
		Main.driver.getCars().add(car);
		// And goto View Driver Cars page.
		Main.mainContainer.loadScreen(Main.driverViewCarsID, Main.driverViewCarsFile);
		myController.setScreen(Main.driverViewCarsID);	
		
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
	
	

}
