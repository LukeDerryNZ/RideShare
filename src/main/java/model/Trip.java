package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Luke Derry
 *
 */
public class Trip implements iValidatable {

	public static enum Direction { toUni, fromUni, notSet }
	
	//private List<LocalDate> dates; OBSOLETE
	private Route route;
	private Direction direction;
	private boolean recurring;
	private LocalDate expiryDate;
	private Car car;
	
	private SimpleStringProperty mTripID;
	private SimpleStringProperty mCarID;
	private SimpleStringProperty mRouteID;
	private SimpleStringProperty mDirectionID;
	
	private List<Boolean> days; // [0-6]
	
	/**
	 * Default Constructor
	 */
	public Trip() {
		
		route = new Route();
		//dates = new ArrayList<LocalDate>();	//Replaced with 'days'
		setDays(new ArrayList<Boolean>());		//Initialised to false
		direction = Direction.toUni;
		recurring = false;
		setDefaultExpiryDate();
		car = new Car();
		mCarID = new SimpleStringProperty("CarID");
		mTripID = new SimpleStringProperty("TripID");
		mRouteID = new SimpleStringProperty("RouteID");
		mDirectionID = new SimpleStringProperty("DirID");
	}
	
	
	/**
	 * Constructor
	 * @param _route
	 */
	public Trip(Route _route) {
	
		setRoute( _route );
		//dates = new ArrayList<LocalDate>();	//Replaced with 'days'
		setDays(new ArrayList<Boolean>());		//Initialised to false
		direction = Direction.toUni;
		recurring = false;
		setDefaultExpiryDate();
		car = new Car();
		mCarID = new SimpleStringProperty("CarID");
		mTripID = new SimpleStringProperty("TripID");
		mRouteID = new SimpleStringProperty( _route.getID() );
		mDirectionID = new SimpleStringProperty("DirID");
	}
	
	/**
	 * Constructor
	 * @param _trip
	 */
	public Trip(Trip t) {

		// Ensure that all variables are set
		route = t.getRoute();
		days =  t.getDays();
		direction = t.getDirection();
		recurring = t.getRecurring();
		expiryDate = t.getExpiryDate();
		car = t.getCar();
		
		mCarID = new SimpleStringProperty( t.getCar().getModel() );
		mTripID = new SimpleStringProperty( t.getID() );
		mRouteID = new SimpleStringProperty( t.getRoute().getID() );
		mDirectionID = new SimpleStringProperty( t.getDirection().toString() );
		
	}
	
	
	/**
	 * Constructor
	 * @param _route
	 * @param _dates
	 * @param _dir
	 * @param _recur
	 * @param _expiry
	 * @param _car
	 * @param id
	 */
	public Trip(Route _route, List<Boolean> _days, Trip.Direction _dir, boolean _recur, LocalDate _expiry, Car _car, String _tripId) {
		
		route = _route;
		days = _days;
		direction = _dir;
		recurring = _recur;
		expiryDate = _expiry;
		car = _car;
		
		// Set javafx strings
		setMCarID( _car );
		mTripID = new SimpleStringProperty( _tripId );
		setMRouteID( _route );
		setMDirectionID( _dir );
	}
	
	
	
	/**
	 * Returns true iff:
	 *  - stopPoints, mID, direction, recurring is not null.
	 * 	- ExpiryDate is not BEFORE day of instantiation
	 *  - Car is not null
	 */
	@Override
	public boolean isValid() {
		
		// ROUTE
		if ( route == null || route.getStopPoints().isEmpty() ) return false; // Check null vals first!
		if ( route.mIDProperty().toString().isEmpty() ) return false;
		
		// Direction
		if ( direction == Trip.Direction.notSet || direction == null ) return false;
		
		// Recurring
		// Note: - A boolean, when not true or false is set to false.
		//       - Newly instantiated Booleans are set by default to false.
		
		// Expiry Date
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		if ( getExpiryDate() == null ) return false;
		if ( getExpiryDate().isBefore(today) ) return false;
		
		// ID
		if ( getID().isEmpty() ) return false;
		
		// Car
		if ( !getCar().isValid() || getCar() == null ) return false;
		
		return true;
	}
	
	
	/**
	 * Sets the expiryDate to 7 days from instantiation date.
	 * [ Called from within constructors only. ]
	 */
	private void setDefaultExpiryDate() {

		// Set current date + 1 week
		this.expiryDate = LocalDate.now().plusWeeks(1);
		
	}
	

	
	//----------------// Public Accessors //----------------------------------------//
	
	// Route
	public Route getRoute() { 
		return this.route; 
	}
	
	public void setRoute(Route r) { 
		this.route = r;
		this.mRouteID.set( r.getID() ); // Also set simplestringprop
	}
	
	// Direction
	public Direction getDirection() { 
		return this.direction; 
	}
	
	public void setDirection(Trip.Direction d) {
		this.direction = d;
		this.mDirectionID.set( d.toString() ); // Also set simplestringprop
	}
	
	// Recurring
	public void setRecurring(boolean r) {
		this.recurring = r; 
	}
	
	public boolean getRecurring() { 
		return this.recurring; 
	}
	
	// Expiry Date
	public void setExpiryDate( LocalDate ld ) {
		this.expiryDate = ld;
	}
	
	public LocalDate getExpiryDate() { 
		return this.expiryDate; 
	}
	
	// Car
	public void setCar( Car c) { 
		car = c;
		this.mCarID.set( c.getModel() ); // Also set simplestringprop
	}
	
	public Car getCar() { 
		return car; 
	}
	
	// ID
	public String getID() { 
		return mTripID.get(); 
	}
	
	public void setID(String s) { 
		mTripID.set(s); 
	}
	
	// Days
	public List<Boolean> getDays() { 
		return days;
	}
	
	public void setDays(List<Boolean> days) { 
		this.days = days; 
	}
	
	
	
	// Simple # Properties //
	
	
	private void setMCarID( Car c ) {
		if (c != null) {
			mCarID = new SimpleStringProperty( c.getModel() );
		}
	}
	
	private void setMRouteID( Route r) {
		if (r != null) {
			mRouteID = new SimpleStringProperty( r.getID() );
		}
	}
	
	private void setMDirectionID( Direction d ) {
		if (d != null) {
			mDirectionID = new SimpleStringProperty( d.toString() );
		}
	}
	
	public StringProperty mTripIDProperty() {
		return mTripID;
	}
	
	public StringProperty mCarIDProperty() {
		return mCarID;
	}
	
	public StringProperty mRouteIDProperty() {
		return mRouteID;
	}
	
	public StringProperty mDirectionProperty() {
		return mDirectionID;
	}
	
	
//  --  OBSOLETE  --
//	public List<LocalDate> getDates() {	return this.dates; }
//	public boolean setDates(List<LocalDate> d) {
//		try {
//			this.dates = new ArrayList<LocalDate>(d);
//		} 
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
//		return true;
//	}
	
	
}