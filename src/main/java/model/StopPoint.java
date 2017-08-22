package model;

import controller.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert.AlertType;

public class StopPoint implements Comparable<StopPoint>, iValidatable {

	private final SimpleStringProperty mNumber;
	private final SimpleStringProperty mStreet;
	private final SimpleStringProperty mSuburb;
	private final SimpleStringProperty mAddress;
	private final SimpleStringProperty mTime;
	
	
	/**
	 * Default Constructor
	 */
	public StopPoint() {
		this.mNumber = new SimpleStringProperty();
		this.mStreet = new SimpleStringProperty();
		this.mSuburb = new SimpleStringProperty();
		this.mAddress = new SimpleStringProperty();
		
		this.mTime = new SimpleStringProperty();
		
		setAddress();
	}
	
	/**
	 * Constructor
	 * @param num
	 * @param st
	 * @param sub
	 */
	public StopPoint(String num, String st, String sub) {
		this.mNumber = new SimpleStringProperty(num);
		this.mStreet = new SimpleStringProperty(st);
		this.mSuburb = new SimpleStringProperty(sub);
		this.mAddress = new SimpleStringProperty();
		
		this.mTime = new SimpleStringProperty();
		
		setAddress();
	}
	
	/**
	 * Constructor
	 * @param adr - Full address given as parameter.
	 * 				Using whitespace as delimeter
	 * 				Extraction of strings to:
	 * 				 - mNumber
	 * 				 - mStreet
	 * 				 - mSuburb
	 */
	public StopPoint(String adr) {

		String num = "", st = "", sub = "";
		String s[] = adr.trim().split("\\s+");	//Split using whitespace as delimeter
		if ( s.length == 3 ) { 		// eg: '12 parnwellSt burwood'
				
			num = s[0];
			st  = s[1];
			sub = s[2];
		}
		
		if ( s.length == 4 ) 
		{	// eg: '12 parnwell st burwood'
			num = s[0];
			st = s[1]+" "+s[2];
			sub = s[3];
		}
		else if ( s.length == 5 ) {	// eg: '32 horton Rd St Martins'
			num = s[0];
			st  = s[1]+" "+s[2];
			sub = s[3]+" "+s[4];	
		}
		
		else {	
			Main.Alert("Error", "StopPoint Construction Error. Invalid Address.\n [ Example Address = '12 Parnwell St Burwood' ]", AlertType.ERROR);
		}
		
		this.mNumber = new SimpleStringProperty(num);
		this.mStreet = new SimpleStringProperty(st);
		this.mSuburb = new SimpleStringProperty(sub);
		this.mAddress = new SimpleStringProperty(adr);
		
		this.mTime = new SimpleStringProperty(); //NULL
		
		setAddress(); //Restore address String
	}
	
	
	/**
	 * Returns true iff:
	 *  - mAddress contains < 3 OR > 5 'words'.
	 *  - mNumber max length > 5.
	 *  - mStreet is not null.
	 *  - mSuburb is not null.
	 */
	@Override
	public boolean isValid() {
		
		// Check Address
		int minLen = 3, maxLen = 5;
		String s[] = this.getAddress().trim().split("\\s+");
		if ( s.length < minLen || s.length > maxLen ) {
			return false;
		}
		
		//Check mNumber
		String num = mNumber.get();
		int numMaxLen = 5;
		if ( num.isEmpty() || num.length() > numMaxLen ) {
			return false;
		}
		
		// Check Street
		if ( mStreet.get().isEmpty() ) {
			return false;
		}
		
		// Check Suburb
		if ( mSuburb.get().isEmpty() ) {
			return false;
		}
		
		return true;
		
	}


	@Override
	public boolean equals(Object other) {
		boolean returnVal = false;
		if ( other instanceof StopPoint ) {
			StopPoint sp = (StopPoint) other;
			//Compare hashcodes of addresses for equality
			int spAdrLwrCse = sp.getAddress().toLowerCase().hashCode();
			int thisAdrLwrCse = this.getAddress().toLowerCase().hashCode();
			returnVal = spAdrLwrCse == thisAdrLwrCse;
		}
		
		return returnVal;
	}
	
	
	@Override
	public int hashCode() {
		return getAddress().hashCode();
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((mAddress == null) ? 0 : mAddress.hashCode());
//		result = prime * result + ((mNumber == null) ? 0 : mNumber.hashCode());
//		result = prime * result + ((mStreet == null) ? 0 : mStreet.hashCode());
//		result = prime * result + ((mSuburb == null) ? 0 : mSuburb.hashCode());
//		return result;
		
	}
	
	
	@Override
	public int compareTo(StopPoint o) {
		
		return this.getAddress().compareToIgnoreCase(o.getAddress());
	
	}
	
	
	// Public accessors //
	
	
	private void setAddress() {
		mAddress.set( getNumber()+" "+getStreet()+" "+getSuburb() );
	}
	public String getAddress() {
		return mAddress.get();
	}
	
	public String getNumber() {
		return mNumber.get();
	}
	public void setNumber(String s) {
		mNumber.set(s);
		setAddress();
	}
	
	public String getStreet() {
		return mStreet.get();
	}
	public void setStreet(String s) {
		mStreet.set(s);
		setAddress();
	}

	public String getSuburb() {
		return mSuburb.get();
	}
	public void setSuburb(String s) {
		mSuburb.set(s);
		setAddress();
	}
	
	public String getTime() {
		return mTime.get();
	}
	public void setTime(String s) {
		mTime.set(s);
	}
	
	// String Properties //
	public StringProperty mNumberProperty() {
		return mNumber;
	}
	public StringProperty mStreetProperty() {
		return mStreet;
	}
	public StringProperty mSuburbProperty() {
		return mSuburb;
	}
	public StringProperty mAddressProperty() {
		return mAddress;
	}
	public StringProperty mTimeProperty() {
		return mTime;
	}


	
}
