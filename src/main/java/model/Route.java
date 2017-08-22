package model;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Luke Derry
 * @created 7/4/17
 * 
 * A collection of StopPoints
 */
public class Route implements iValidatable {
	
	private Set<StopPoint> stopPoints;
	private final SimpleStringProperty mID;
	private final String defaultID = "Route";
	
	// Validity Criteria
	private final int isValidMinSize = 2;
	
	
	/**
	 * Default Constructor
	 *  - mID set to default String 'Route'
	 */
	public Route() {
		
		stopPoints = new HashSet<StopPoint>();
		this.mID = new SimpleStringProperty( defaultID );
		
	}
	
	/**
	 * Constructor
	 * @param id 
	 */
	public Route(String id) {
		
		stopPoints = new HashSet<StopPoint>();
		
		if (id.trim().isEmpty() ) {
			this.mID = new SimpleStringProperty( defaultID );
		} else {
			this.mID = new SimpleStringProperty(id);
		}
		
	}
	
	
	/**
	 * Check if ROUTE is valid
	 * @return true if a valid ROUTE can be made from selectedStopPoints HashSet
	 */
	@Override
	public boolean isValid() {
		System.out.println("isValidSize = "+isValidMinSize);
		System.out.println("stopPoints.size() = "+this.stopPoints.size());
		//return true if size > 1, false otherwise
		if ( this.stopPoints.size() < isValidMinSize ) {
			return false;
		}
		return true;
			
	}
	
	@Override
	public String toString() {
		//Simply return mID as string.
		return this.mID.get().toString();
	}
	
	// Public Accessors //
	
	
	public HashSet<StopPoint> getStopPoints() {
		
		return ( HashSet<StopPoint> ) stopPoints;
		
	}
	
	public void setID(String s) {
		this.mID.set(s);
	}
	public String getID() {
		return this.mID.get();
	}
	

	// String Properties //
	
	
	public StringProperty mIDProperty() {
		return mID;
	}


}
