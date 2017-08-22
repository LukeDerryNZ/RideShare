package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.Assert;

import model.Car;
import model.Route;
import model.StopPoint;
import model.Trip;
import model.Driver;


/**
 * @Author: Luke Derry
 */
public class DriverSteps {
    
    private Driver driver;
    private Car testCar;
    private Route route;
    private StopPoint stopPoint_Empty;
    private StopPoint stopPoint_Full;
    private Trip trip;
    
    @Before()
    public void beforeScenario() {
    	driver = new Driver();
        testCar = new Car( Car.Type.Sedan, "Honda", "BUM007", "Yellow", 4, 1982 );
        route = new Route();
        driver.getRoutes().add( route );
        stopPoint_Empty = new StopPoint("","","");
        stopPoint_Full = new StopPoint("123a", "Brookhaven St", "Hempsworth");
        trip = new Trip();

    }
    
    //SCENARIO: Register a car //////////////////////////////////////////////////
    @Given("^I am a driver$")
    public void i_am_a_driver() throws Throwable {
        //Create a new driver.
        this.driver = new Driver();
    }
    
    @When("^I register a car$")
    public void i_register_a_car() throws Throwable {
        //Attempt to register a car.
        driver.getCars().add(testCar);
    }
    
    @Then("^The car is added to the drivers account$")
    public void the_car_is_added_to_the_drivers_account() throws Throwable {
        //Assert that the driver's registered vehicles contains the testCar.
        Assert.assertTrue(driver.getCars().contains(testCar));
    }
    /////////////////////////////////////////////////////////////////////////////
    
    
    
    //SCENARIO: Create a stop point /////////////////////////////////////////////
    @Given("^I create a stop point$")
    public void I_create_a_stop_point() throws Throwable {
    	//Note that the ROUTE has alread been added to the driver above.
        //Create empty stopPoint.
        route.getStopPoints();
    }
    @Then("^I can specify the address$")
    public void I_can_specify_the_address() throws Throwable {
        //Set an address for the stopPoint created above.
        stopPoint_Empty.setNumber("1");
        stopPoint_Empty.setStreet("Burwood Rd");
        stopPoint_Empty.setSuburb("Burwood");
    }
    @Then("^The stop point is added to the route$")
    public void the_stop_point_is_added_to_the_route() throws Throwable {
        route.getStopPoints().add(stopPoint_Empty);
    }
    @Then("^The stop point cannot be added more than once$")
    public void the_stop_point_cannot_be_added_more_than_once() throws Throwable {
    	//Assert that we cannot add the same stopPoint > 1 times
    	
    	//Clear
    	route.getStopPoints().clear();
    	//Add 3
    	route.getStopPoints().add(new StopPoint("a", "a", "a"));  //Add unique stopPoint.
    	route.getStopPoints().add(new StopPoint("a", "a", "a"));  //Try to add again.
    	route.getStopPoints().add(stopPoint_Full);			//Add another.
    	//Test for 2
    	Assert.assertTrue(route.getStopPoints().size() == 2);
    }
    /////////////////////////////////////////////////////////////////////////////
    
    
    
    //SCENARIO: Add Trips ///////////////////////////////////////////////////////
    @When("^I add a trip$")
    public void i_add_a_trip() throws Throwable {
        
    	Trip testTrip = new Trip();
    	driver.getTrips().add(testTrip);
    }
    @Then("^I want to specify the direction$")
    public void i_want_to_specify_the_direction() throws Throwable {
        
    	trip.setDirection(Trip.Direction.toUni);
        trip.setDirection(Trip.Direction.fromUni);
    }
    @Then("^I want to specify whether the trip is recurring$")
    public void i_want_to_specify_whether_the_trip_is_recurring() throws Throwable {
        
    	trip.setRecurring(true);
        trip.setRecurring(false);
    }
    @Then("^I want to specify the days$")
    public void i_want_to_specify_the_days() throws Throwable {
    	
    	trip.getDays().clear();
    	
    	boolean mon = true;
    	boolean tue = false;
    	boolean wed = true;
    	boolean thur = false;
    	boolean fri = true;
    	boolean sat = false;
    	boolean sun = true;
    	
    	trip.getDays().add(mon);
    	trip.getDays().add(mon);
    	trip.getDays().add(tue);
    	trip.getDays().add(wed);
    	trip.getDays().add(thur);
    	trip.getDays().add(fri);
    	trip.getDays().add(sat);
    	trip.getDays().add(sun);
    }
    @Then("^I want to specify the expiry date$")
    public void i_want_to_specify_the_expiry_date() throws Throwable {
    	LocalDate exDate = LocalDate.now().plus(1, ChronoUnit.WEEKS);
    	trip.setExpiryDate(exDate);
    }
    @Then("^I want to specify which car is used$")
    public void i_want_to_specify_which_car_is_used() throws Throwable {
        trip.setCar(testCar);
    }
    /////////////////////////////////////////////////////////////////////////////
    
    
    
}
