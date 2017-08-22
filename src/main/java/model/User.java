package model;

import java.util.HashSet;
import java.util.Set;

public class User {
	
	private Set<StopPoint> stopPoints;
	
	/**
	 * Default Constructor
	 */
	public User() {
		
		stopPoints = new HashSet<StopPoint>();
		
	}
	
	
	// Public Accessors //
	
	
	public HashSet<StopPoint> getStopPoints() {
		
		return (HashSet<StopPoint>) stopPoints;
		
	}

}
