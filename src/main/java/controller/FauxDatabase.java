package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.control.Alert.AlertType;
import model.StopPoint;
import model.Trip;


/**
 * @author Luke Derry
 * @Created 7/4/17
 * 
 * This class represents a dummy online database in which our users, both drivers and passengers
 * may view the info stored.
 *
 */
public class FauxDatabase {
	
	private Set<StopPoint> stopPoints;
	private List<Trip> trips;
	
	//private Set<User> users  -- contains username / passwords -- TODO
	
	
	/**
	 * Default Constructor
	 */
	public FauxDatabase() {
		
		stopPoints = new HashSet<StopPoint>();
		trips = new ArrayList<Trip>();
		
	}
	
	
	/**
	 * Add a trip to the Database. Only Complete and Valid Trips may be added.
	 * [ +Validity Check / +Add StopPoints To DB ]
	 * @param trip
	 * @return true on success.
	 */
	public boolean addTrip(Trip trip) {
		
		// Validity Check.
		if ( !trip.isValid() ) {
			Main.Alert("ERROR",  "DATABASE ERROR : ATTEMPTING TO ADD INVALID TRIP TO DATABASE.",  AlertType.ERROR);
			return false;
		}
		
		// Add StopPoints From Trip To Database.
		for ( StopPoint sp : trip.getRoute().getStopPoints() ) {
			if ( !getStopPoints().contains(sp) ) {
				System.out.println("[ StopPoint("+sp.getAddress()+") Added To Database.]");		//DEBUG
				getStopPoints().add(sp);
			}
		}
		
		// Add Trip To Database.
		getTrips().add(trip);
		System.out.println(" [ Trip("+trip.getID()+") Added To Database. ]");					//DEBUG
		return true;
	}
	
	
	/**
	 * Removes the given matching trip from Database.
	 * Also removes all StopPoints that are NOT present elsewhere in the Database.
	 * @param trip
	 * @return
	 */
	public boolean removeTrip(Trip trip) {
		
		// If Trip Exists
		if ( getTrips().contains(trip) ) {
			
			// Convert because java is aids
			List<StopPoint> sps = new ArrayList<StopPoint>(trip.getRoute().getStopPoints() );
			boolean[] foundIndex = new boolean[ sps.size() ];
			Arrays.fill(foundIndex, false); // Set all to false
			
			// If SP exists elsewhere in Database, set index to true
			for ( int i = 0; i < sps.size(); i++ ) {
				for ( Trip t : getTrips() ) {
					if ( t.getID() != trip.getID() && t.getRoute().getStopPoints().contains( sps.get(i) ) ) {
						
						foundIndex[i] = true;
						
					}
				}
			}
			
			// For All StopPoints Not Found, Remove From Database.
			for ( int i = 0; i < foundIndex.length; i++ ) {
				if ( foundIndex[i] == true ) {
					this.getStopPoints().remove( sps.get(i) );
				}
			}
			
			// Finally Remove Trip Itself.
			getTrips().remove(trip);
			return true;
		}
		
		return false;
	
	}
	
	
	// Public Accessors //
	
	
	public ArrayList<Trip> getTrips() {
		
		return (ArrayList<Trip>) this.trips; 
	
	}
	
	/**
	 * 
	 * @return all unique StopPoints as a HashSet, from all Routes.
	 */
	public HashSet<StopPoint> getStopPoints() {
		
		return (HashSet<StopPoint>) this.stopPoints;
		
	}
	
	
	
}
