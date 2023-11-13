package edu.pitt.cs;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	@Before
	public void setUp() throws Exception {
		//Config.setBuggyRentACat(true); <-- to check if the test cases are correct. If they are correct, 
		//									 11 of the tests should fail (see README for details)
		// Turn on automatic bug injection in the Cat class, to emulate a buggy Cat.
		// Your unit tests should work regardless of these bugs.
		Cat.bugInjectionOn = true;

		// INITIALIZE THE TEST FIXTURE
		// 1. Create a new RentACat object and assign to r
		r = RentACat.createInstance();

		// 2. Create an unrented Cat with ID 1 and name "Jennyanydots", assign to c1
		// TODO: Fill in DONE
		c1 = Mockito.mock(Cat.class);
		Mockito.when(c1.getName()).thenReturn("Jennyanydots");
		Mockito.when(c1.getId()).thenReturn(1); // NOTE, INSTEAD OF getId() IT COULD BE setId(), nevermind I was right somehow

		// 3. Create an unrented Cat with ID 2 and name "Old Deuteronomy", assign to c2 
		//c2 = new Cat(2, "Old Deuteronomy");
		c2 = Mockito.mock(Cat.class);
		Mockito.when(c2.getName()).thenReturn("Old Deuteronomy");
		Mockito.when(c2.getId()).thenReturn(2);
		
		// TODO: Fill in DONE

		// 4. Create an unrented Cat with ID 3 and name "Mistoffelees", assign to c3
		// TODO: Fill in DONE
		c3 = Mockito.mock(Cat.class);
		Mockito.when(c3.getName()).thenReturn("Mistoffelees");
		Mockito.when(c3.getId()).thenReturn(3);
	}

	@After
	public void tearDown() throws Exception {
		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 * </pre>
	 */

	@Test
	public void testGetCatNullNumCats0() {
		Assert.assertEquals(null, r.getCat(2)); //:D
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2. 
	 * </pre> 
	 */

	@Test
	public void testGetCatNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		/*
		 *  EXECUTION STEPS
		 */
		Cat cat = r.getCat(2);
		/*
		 *  POSTCONDITIONS
		 */
		Assert.assertNotNull(cat);
		Assert.assertEquals(2, cat.getId());
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats0() {
		Assert.assertFalse(r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c3 is rented.
	 *                c1 and c2 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatAvailableTrueNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(false);
		Mockito.when(c3.getRented()).thenReturn(true);
		/*
		 *  EXECUTION STEPS and POSTCONDITION
		 */
		Assert.assertTrue(r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 *                c1 and c3 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		Mockito.when(c1.getRented()).thenReturn(false);
		Mockito.when(c2.getRented()).thenReturn(true);
		Mockito.when(c3.getRented()).thenReturn(false);
		/*
		 *  EXECUTION STEPS and POSTCONDITION
		 */
		Assert.assertEquals(false, r.catAvailable(2));

	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatExistsFalseNumCats0() {
		assertFalse(r.catExists(2));
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatExistsTrueNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		Mockito.when(c1.getId()).thenReturn(1);
		r.addCat(c1);
		Mockito.when(c2.getId()).thenReturn(2);
		r.addCat(c2);
		Mockito.when(c3.getId()).thenReturn(3);
		r.addCat(c3);
		/*
		 *  EXECUTION STEPS and POSTCONDITION
		 */
		assertTrue(r.catExists(2));
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats0() {
		assertEquals("", r.listCats());
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		Mockito.when(c1.toString()).thenReturn("ID 1. Jennyanydots\n");
		r.addCat(c1);
		Mockito.when(c2.toString()).thenReturn("ID 2. Old Deuteronomy\n");
		r.addCat(c2);
		Mockito.when(c3.toString()).thenReturn("ID 3. Mistoffelees\n");
		r.addCat(c3);
		/*
		 *  EXECUTION STEPS
		 */
		String line = r.listCats();
		assertEquals(line, "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n");
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testRentCatFailureNumCats0() {
		boolean val = r.rentCat(2);
		assertFalse(val);
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c1.rentCat(), c2.rentCat(), c3.rentCat() are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testRentCatFailureNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		r.addCat(c1);
		Mockito.when(c2.getRented()).thenReturn(true); //c2 is rented
		r.addCat(c2);
		r.addCat(c3);
		/*
		 *  EXECUTION STEPS
		 */
		//Mockito.doNothing().when(c2).rentCat();
		boolean val = r.rentCat(2);

		//______________________
		Mockito.verify(c1, Mockito.never()).rentCat();
		Mockito.verify(c2, Mockito.never()).rentCat();
		Mockito.verify(c3, Mockito.never()).rentCat();

		assertFalse(val);
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testReturnCatFailureNumCats0() {
		//exec steps
		boolean val = r.returnCat(2);
		//
		assertFalse(val);
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2.returnCat() is called exactly once.
	 *                 c1.returnCat() and c3.returnCat are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testReturnCatNumCats3() {
		/*
		 *  PRECONDITIONS
		 */
		r.addCat(c1);
		Mockito.when(c2.getRented()).thenReturn(true); //c2 is rented
		r.addCat(c2);
		r.addCat(c3);
		/*
		 *  EXECUTION STEPS
		 */
		Mockito.doNothing().when(c2).rentCat(); //added this even tho it doesn't fail when cat.rentCat() is called 
												//since it should throw the exception, but I'll leave this here anyway  -- A bit confusing, may need to get rid of this
		boolean val = r.returnCat(2);

		//______________________
		Mockito.verify(c2, Mockito.times(1)).returnCat();
		//Mockito.never() is equivalent to Mockito.times(0) i think -- Then we should stick with one method of doing it
		Mockito.verify(c1, Mockito.never()).returnCat();
		Mockito.verify(c3, Mockito.times(0)).returnCat();

		assertTrue(val);
	}
}
