package model;

import java.util.Calendar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car implements Comparable<Car>, iValidatable {

	public enum Type {
		Sedan, Hatchback, Stationwagon, Van, Motorbike
	}
	
	public enum Colour {
		Black, White, Grey, Blue, Red, Green, Yellow, Purple, Silver, Gold, Brown, Indiscernible
	}
	
	private SimpleObjectProperty<Car.Type> mType;
	private SimpleIntegerProperty mNumSeats;
	private SimpleStringProperty mModel;
	private SimpleStringProperty mColour;
	private SimpleStringProperty mRegistrationNumber;
	private SimpleIntegerProperty  mYear;
	
	// Validity Criteria
	private final int ValidRegMaxLen = 6;
	private final int ValidRegMinLen = 4;
	private final int ValidMinNumSeats = 0;
	private final int ValidMaxNumSeats = 10;
	private final int ValidMinYear = 1890;
	private final int ValidMaxYear = Calendar.getInstance().get(Calendar.YEAR);

	/* 
	 * Two fields are incorrectly set to null after creating a car within application. TODO
	 * 
	 */
	
	
	
	/**
	 * Default Constructor
	 * Null Values, Int values = 0
	 */
	public Car() {
		
		this.mType 				 = new SimpleObjectProperty<Type>();
		this.mNumSeats 			 = new SimpleIntegerProperty(0);
		this.mModel 			 = new SimpleStringProperty();
		this.mRegistrationNumber = new SimpleStringProperty();
		this.mColour			 = new SimpleStringProperty();
		this.mYear 				 = new SimpleIntegerProperty(0);
		
	}
	
	
	/**
	 * Constructor
	 * @param mType
	 * @param _model
	 * @param _reg
	 * @param _col
	 * @param _numSeats
	 * @param _yr
	 */
	public Car(Car.Type type, String _model, String _reg, String _col, int _numSeats, int _yr ) {
		
		this.mType 				 = new SimpleObjectProperty<Type>( (Car.Type)type);
		this.mNumSeats			 = new SimpleIntegerProperty(_numSeats);
		this.mModel 			 = new SimpleStringProperty(_model);
		this.mRegistrationNumber = new SimpleStringProperty(_reg);
		this.mColour 			 = new SimpleStringProperty(_col);
		this.mYear 				 = new SimpleIntegerProperty(_yr);
		
	}
	
	
	/**
	 * Constructor
	 * @param c
	 */
	public Car( Car c) {
		
		this.mType 					= c.mType;
		this.mNumSeats 				= c.mNumSeats;
		this.mModel					= c.mModel;
		this.mRegistrationNumber 	= c.mRegistrationNumber;
		this.mColour 				= c.mColour;
		this.mYear					= c.mYear;
	
	}
	
	
	// Comparators //
	
	
	@Override
	public int compareTo(Car o) {
		
		return this.toString().toLowerCase().compareToIgnoreCase(o.toString().toLowerCase());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mColour == null) ? 0 : mColour.hashCode());
		result = prime * result + ((mModel == null) ? 0 : mModel.hashCode());
		result = prime * result + ((mNumSeats == null) ? 0 : mNumSeats.hashCode());
		result = prime * result + ((mRegistrationNumber == null) ? 0 : mRegistrationNumber.hashCode());
		result = prime * result + ((mType == null) ? 0 : mType.hashCode());
		result = prime * result + ((mYear == null) ? 0 : mYear.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {

		boolean returnVal = false;
		if ( other instanceof Car ) {
			Car c = (Car) other;
			//Compare hashcodes of addresses for equality
			int carToStrLwrCse = c.toString().toLowerCase().hashCode();
			int thisToStrLwrCse = this.toString().toLowerCase().hashCode();
			returnVal = carToStrLwrCse == thisToStrLwrCse;
		}		
		return returnVal;
		
	}

	@Override
	public String toString() {
		return getType() + " " + getModel() + " " + getRegistrationNumber();
	}

	/**
	 * Returns true iff:
	 *  - mType, mModel, mRegistrationNumber, mColour is not null.
	 * 	- mRegistrationNumber length is between 4-6 characters inclusive.
	 *  - mNumSeats is between 0-10 inclusive.
	 *  - mYear is between 1890-[CURRENTYEAR] inclusive.
	 */
	@Override
	public boolean isValid() {

		// mType
		if ( this.mType == null ) return false; 
		
		// mModel 
		if ( this.mModel == null || this.mModel.get().isEmpty() ) return false;
		
		// mRegistrationNumber
		String reg = this.mRegistrationNumber.get();
		int regLen = reg.length();
		if ( reg.isEmpty() || regLen > ValidRegMaxLen || regLen < ValidRegMinLen ) return false;
		
		// mColour
		if ( this.mColour == null ) return false;
		
		// mNumSeats
		int n = this.mNumSeats.get();
		if ( n < ValidMinNumSeats || n > ValidMaxNumSeats ) return false;
		
		//mYear
		int yr = this.mYear.get();
		if ( yr < ValidMinYear || yr > ValidMaxYear ) return false;

		return true;
	}
	

	
	// Public Accessors //
	
	public Car.Type getType() {
		return (Car.Type) mType.get();
	}
	public void setType(Car.Type type) {
		this.mType.set( (Car.Type) type );
	}

	public int getNumSeats() {
		return mNumSeats.get();
	}
	public void setNumSeats(int numSeats) {
		this.mNumSeats.set(numSeats);
	}

	public String getModel() {
		return mModel.get();
	}
	public void setModel(String s) {
		this.mModel.set(s);
	}

	public String getColour() {
		return mColour.get();
	}
	public void setColour(String s) {
		this.mColour.set(s);
	}

	public String getRegistrationNumber() {
		return mRegistrationNumber.get();
	}
	public void setRegistrationNumber(String s) {
		this.mRegistrationNumber.set(s);
	}

	public int getYear() {
		return mYear.get();
	}
	public void setYear(int year) {
		this.mYear.set(year);
	}

	
	// String Properties //
	
	
	public ObjectProperty<Type> mTypeProperty() {
		return mType;
	}
	public IntegerProperty mNumSeatsProperty() {
		return mNumSeats;
	}
	public StringProperty mModelProperty() {
		return mModel;
	}
	public StringProperty mRegistrationNumberProperty() {
		return mRegistrationNumber;
	}
	public StringProperty mColourProperty() {
		return mColour;
	}
	public IntegerProperty mYearProperty() {
		return mYear;
	}

}