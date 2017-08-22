package model;

import java.util.HashSet;
import java.util.Set;

public class Driver extends User {
	
	private Set<Route> routes;
	private Set<Car> cars;
	private Set<Trip> trips;
	
	/**
	 * Default constructor
	 */
	public Driver() {

		super(); //Call to super constructor
		routes = new HashSet<Route>();
		cars = new HashSet<Car>();
		trips = new HashSet<Trip>();
	}
	
	/**
	 * Constructor
	 * @param user - Create a driver from an existing user.
	 */
	public Driver(User user) {
		
		super();
		routes = new HashSet<Route>();
		cars = new HashSet<Car>();
		//Add user stopPoints to this stopPoints HashSet.
		getStopPoints().clear();
		getStopPoints().addAll( user.getStopPoints() );
		
	}

	

	
	
	// Public Accessors //
	
	
	public HashSet<StopPoint> getStopPoints() {
		
		return super.getStopPoints();
		
	}
	
	public HashSet<Route> getRoutes() {
		
		return (HashSet<Route>) this.routes;
		
	}
	
	public HashSet<Car> getCars() {
		
		return (HashSet<Car>) this.cars;
	
	}

	public HashSet<Trip> getTrips() {
		
		return (HashSet<Trip>) this.trips;
		
	}
	
	
	
	

}
