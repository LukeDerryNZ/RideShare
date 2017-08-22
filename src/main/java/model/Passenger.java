package model;

import java.util.HashSet;

public class Passenger extends User {
	
	/**
	 * Default Constructor
	 */
	public Passenger() {
		
		super(); //Call to super constructor
		
	}
	
	/**
	 * Constructor
	 * @param user - Create a driver from an existing user.
	 */
	public Passenger(User user) {
		
		super();
		
		//Add user stopPoints to this stopPoints HashSet.
		getStopPoints().clear();
		getStopPoints().addAll( user.getStopPoints() );
		
	}
	
	
	// Public Accessors //
	
	
	public HashSet<StopPoint> getStopPoints() {
		
		return super.getStopPoints();
		
	}

}
