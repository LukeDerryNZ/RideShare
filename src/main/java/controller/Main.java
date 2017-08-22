package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.*;
import javafx.stage.Stage;
import model.*;
import model.Trip.Direction;

public class Main extends Application {
	
	// FXML Page Data
    public static String loginID = "loginView";
    public static String loginViewFile = "/Login.fxml";
    
    
    // Driver Page Links
    public static String driverHomeID = "driverHome";
    public static String driverHomeFile = "/DriverHome.fxml";
    //
    public static String driverCreateRoutesID = "driverCreateRoutes";
    public static String driverCreateRoutesFile = "/DriverCreateRoutes.fxml";
    //
    public static String driverViewRoutesID = "driverRoutes";
    public static String driverViewRoutesFile = "/DriverRoutes.fxml";
    //
    public static String driverViewCarsID = "driverCars";
    public static String driverViewCarsFile = "/DriverCars.fxml";
    //
    public static String driverCreateStopPointsID = "driverCreateStopPoints";
    public static String driverCreateStopPointsFile = "/DriverCreateStopPoints.fxml";
    //
    public static String driverViewStopPointsID = "driverViewStopPoints";
    public static String driverViewStopPointsFile = "/DriverViewStopPoints.fxml";
    //
    public static String driverRegisterCarID = "driverRegisterCar";
    public static String driverRegisterCarFile = "/DriverRegisterCar.fxml";
    //
    public static String driverCreateTripsID = "driverCreateTrips";
    public static String driverCreateTripsFile = "/DriverCreateTrips.fxml";
    //
    public static String driverViewTripsID = "driverViewTrips";
    public static String driverViewTripsFile = "/DriverViewTrips.fxml";
    //
    public static String driverEditTripStopTimesID = "driverEditTripStopTimes";
    public static String driverEditTripStopTimesFile = "/DriverEditTripStopTimes.fxml";
    
    // Passenger Page Links
    public static String passengerHomeID = "passengerHome";
    public static String passengerHomeFile = "/PassengerHome.fxml";
    //
    public static String passengerViewStopPointsID = "passengerViewStopPoints";
    public static String passengerViewStopPointsFile = "/PassengerViewStopPoints.fxml";
    //
    public static String passengerViewRidesID = "passengerViewRides";
    public static String passengerViewRidesFile = "/PassengerViewRides.fxml";
    
    // User
    public static String bookARideID = "bookARide";
    public static String bookARideFile = "/BookARide.fxml";
    
    public static final int USERNAME_SIZE = 4;
    public static final int PASSWORD_SIZE = 4;
    //
    
    
    // Test purpose Variabless
    public static Driver driver;
    public static Passenger passenger;
    public static FauxDatabase fauxDatabase;
    public static String username;
    
    // Version ID
    public static String VERSIONID = "V0.14";
    public static String CREATORID = "Luke Derry : ljd77";
    
    // Our main Screen Container
    public static ScreensController mainContainer;
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    	//Begin JavaFX
    	launch(args);
    
    }
    
    
    /**
     * Initialise Our Database
     */
    public void InitialiseDatabase() {
    	
    	//For now use a mock DB
    	fauxDatabase = new FauxDatabase();

    }
    
    
    /**
     * Creates profiles and test data to our database for testing purposes.
     */
    private void CreateProfiles() {
    	
    	// Create temp user to store stop points
    	User user = new User();
    	user.getStopPoints().add( new StopPoint("12", "Parnwell St", "Burwood") );
    	user.getStopPoints().add( new StopPoint("33", "Barbadoes St", "Edgeware") );
    	user.getStopPoints().add( new StopPoint("125", "Bealey Ave", "St Albans") );
    	
    	// Update Database with TEST DATA
    	fauxDatabase.getStopPoints().addAll( user.getStopPoints() );

    	// Instantiate TEST Driver / Passenger
    	// NOTE: Later these should be instantiated as needed after the user logs in.
    	driver = new Driver(user);
    	passenger = new Passenger(user);
    	
    	// Add Test Car (to driver)
    	Car car = new Car(Car.Type.Hatchback, "Nissan", "DNB448", Car.Colour.Black.toString(), 2, 1997 );
    	driver.getCars().add(car);
    	
    	// Add Route (to driver)
    	Route r = new Route();
    	r.getStopPoints().addAll(driver.getStopPoints() );
    	r.setID("Route007");
    	driver.getRoutes().add(r);
    	
    	// Add Trip1 (to database)
    	Trip t1 = new Trip();
    	t1.setCar(car);
    	t1.setDirection(Direction.fromUni);
    	t1.setExpiryDate(LocalDate.now().plusWeeks(1)); // 1 week from now
    	t1.setID("TestTrip");
    	t1.setRecurring(true);
    	t1.setRoute(r);	//										mon   tue    wed    thur   fri    sat    sun
    	ArrayList<Boolean> days = new ArrayList<>(Arrays.asList(true, false, false, false, false, false, true) );
    	t1.setDays(days);
    	//
    	fauxDatabase.addTrip(t1);
    	
    	// Add Trip2 (to database)
    	Trip t2 = new Trip(t1);
    	t2.setID("TestTrip2");
    	t2.setDirection(Direction.toUni);
    	//
    	fauxDatabase.addTrip(t2);
    	
    }
    
    
    
	@Override
	public void start(Stage primaryStage) {
		
		InitialiseDatabase();
		
		CreateProfiles();
		
		mainContainer  = new ScreensController();
		
		// Load our first fxml file into our container | Others are loaded in sub controllers with a call to switch content.
		mainContainer.loadScreen(Main.loginID, Main.loginViewFile);
		
		//Set our current (First) Screen
		mainContainer.setScreen(loginID);
		
		// Init and show screen
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	/**
	 * An alert dialog box wrapper.
	 * @param title - Used to display the title.
	 * @param text  - Used to display the message.
	 * @param type  - Type of alert to be used.
	 */
	public static void Alert(String title, String text, AlertType type ) {
		javafx.scene.control.Alert a = new javafx.scene.control.Alert(type);
		a.setTitle(title);
		a.setHeaderText("");
		a.setContentText(text);
		a.show();
		
		// DEBUG - also log alert (title and text)
		System.out.println("Alert Logged: ["+title+"] ("+text+")");
	
	}

	
}





