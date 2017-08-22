package steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.Assert;

import model.Car;
import model.Driver;
import model.Route;
import model.StopPoint;
import model.Trip;
import model.Passenger;

/**
 * @Author: Luke Derry
 */
public class PassengerSteps {

    private Passenger passenger;
    private Car testCar;
    private Route route;
    private StopPoint stopPoint_Empty;
    private StopPoint stopPoint_Full;
    private Trip trip;
    
    
    @Before()
    public void beforeScenario() {
    	passenger = new Passenger();
        testCar = new Car( Car.Type.Sedan, "Honda", "BUM007", "Yellow", 4, 1982 );
        route = new Route();
        //driver.getRoutes().add( route );
        stopPoint_Empty = new StopPoint("","","");
        stopPoint_Full = new StopPoint("123a", "Brookhaven St", "Hempsworth");
        trip = new Trip();

    }
    
    //SCENARIO: View Available Rides //////////////////////////////////////////////////
    @Given("^I am a passenger$")
    public void i_am_a_passenger() throws Throwable {
        //Create a new passenger.
        this.passenger = new Passenger();
    }
    /*
     *   Given I am a passenger
         When I select a stop point
         Then The available rides are displayed ordered by day and time
         Then The available rides may be filtered based on direction
         Then The user may book a ride or see its details

     */
    
    @When("^I select a stop point$")
    public void i_select_a_stop_point() throws Throwable {
        //Select a stop point
    	StopPoint selected = stopPoint_Full;
    }
    
    @Then("^The available rides are displayed ordered by day and time$")
    public void the_available_rides_are_displayed_ordered_by_day_and_time() throws Throwable {
        //Assert that the available rides are on screen?
        //TODO ->  Assert.assertTrue(driver.getCars().contains(testCar));
    }
    /////////////////////////////////////////////////////////////////////////////
    
}
