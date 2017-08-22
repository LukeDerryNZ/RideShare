import static org.junit.Assert.*;
import org.junit.Test;
import model.Car;

/**
 * @author Luke Derry
 */
public class CarTest {

	//		FORMAT:			Car(	enum		 string  string   string int int  )
	Car globalTestCar = new Car( Car.Type.Sedan, "BMW", "DDN884", "Blue", 2, 2001 );
	
	@Test
	public void testConstructor() {
		
		//Test instantiation
		Car testCar1 = new Car( Car.Type.Sedan, "Honda", "PEO903", "5", 4, 1996 );
		Car testCar2 = new Car( testCar1 );
		
		//Assert that they are both equal
		assertEquals( testCar1.getModel(),  testCar2.getModel() );
		assertEquals( testCar1.getNumSeats(),  testCar2.getNumSeats() );
		assertEquals( testCar1.getRegistrationNumber(),  testCar2.getRegistrationNumber() );
		assertEquals( testCar1.getColour(), testCar2.getColour() );
		assertEquals( testCar1.getType(), testCar2.getType() );
		assertEquals( testCar1.getYear(), testCar2.getYear() );
		
	}
	
	@Test
	public void test_setNumSeats() {
		//setNumSeat tests
		Car localTestCar = new Car( globalTestCar );
		
		// Add unreasonable value and test validity
		localTestCar.setNumSeats(9999);
		assertFalse( localTestCar.isValid() );
		
		//Add reasonable value and test validity
		localTestCar.setNumSeats(3);
		assertTrue( localTestCar.isValid() );
		
		// Add unreasonable value and test validity
		localTestCar.setNumSeats(-987654321);
		assertFalse( localTestCar.isValid() );
		
		//Add reasonable value and test validity
		localTestCar.setNumSeats(0);
		assertTrue( localTestCar.isValid() );
	}
	
	@Test
	public void test_setRegNo() {
		//setRegNo tests
		Car localTestCar = new Car( globalTestCar );
		
		String testStr = "a";
		localTestCar.setRegistrationNumber( testStr );
		
		assertEquals( localTestCar.getRegistrationNumber(), testStr);
		testStr = "abcefghijklmnopqrstuvwxyz";
		localTestCar.setRegistrationNumber( testStr );
		assertEquals( localTestCar.getRegistrationNumber(), testStr );
		testStr = "\n\t''9234920345)(SF( )W#RK)CMWEFASa\n     ";
		localTestCar.setRegistrationNumber( testStr );
		assertEquals( localTestCar.getRegistrationNumber(), testStr );
	}

}
