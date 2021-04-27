	/**
 * 
 */
package tests.primitives;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.Point3D;
import static org.junit.Assert.*;
import static primitives.Util.*;
import org.junit.Test;

import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 * @author Michael Garusi & Haim Miletzky
 *
 */
/**
 * 
 */
public class VectorTests {

	
	
	/**
	 * Test method for {@link primitives.Vector#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
	
		// ============ Equivalence Partitions Tests ==============
		
		// not similar vectors
		Vector v1 = new Vector(1.1, 2, 3);
		Vector v2 = new Vector(1.0, 2.0, 3.0);
		assertFalse("v1 isnt equals to v2" , v1.equals(v2));
		
		
		//similar vectors, with and without point
		Vector v3 = new Vector(1, 2, 3);
		Vector v4 = new Vector(1.0, 2.0, 3.0);
		assertEquals("The Vectors are not equal", v3, v4);
		
			
	}
	
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		
		//subtract positive numbers

		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 4, 6);
        Vector v3 = new Vector(-1,-2,-3); // the ans
      
        assertTrue(v1.subtract(v2).equals(v3));
        
        //subtract negative numbers
        
        Vector v7 = new Vector(-1, -2, -3);
        Vector v8 = new Vector(-2, -4, -6);
        Vector v9 = new Vector(1,2,3);

        assertTrue(v7.subtract(v8).equals(v9));
        
        // =============== Boundary Values Tests ==================

        try {
          v3 = (v1.subtract(v1));	//ERROR excepted. v1 minus v1 should give us 0 - and throw exception
      	fail("Didn't throw, Problem - zero vector");


        } catch (IllegalArgumentException e) {
        }

      	}
	

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		
		// ============ Equivalence Partitions Tests ==============

		//Add Positive to negative numbers

		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(-1,-2,-3);

        assertTrue(v1.add(v2).equals(v3));
        
        //Add Positive numbers
        
        Vector v4 = new Vector(1, 2, 3);
        Vector v5 = new Vector(2, 4, 6);
        Vector v6 = new Vector(3,6,9);

        assertTrue(v4.add(v5).equals(v6));
        
        //Add negative numbers
        
        Vector v7 = new Vector(-1, -2, -3);
        Vector v8 = new Vector(-2, -4, -6);
        Vector v9 = new Vector(-3,-6,-9);

        assertTrue(v7.add(v8).equals(v9));
        
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		
		// ============ Equivalence Partitions Tests ==============
		
		    Vector v1 = new Vector(1,1,1);

	        Vector v1test = v1.scale(1);
	        assertTrue(v1.equals(v1test));


	        Vector v2test = v1test.scale(2); 
	      	      
	        
	        assertTrue(new Vector(2,2,2).equals(v2test));

	        Vector v3test = v2test.scale(-2);
	        assertEquals(new Vector(-4,-4,-4),v3test);
	

	        // =============== Boundary Values Tests ==================


	        try {
	        	Vector v4test = v1.scale(0.0);	//ERROR excepted. v1 minus v1 should give us 0 - and throw exception
				fail("Didn't throw, Problem - zero vector");

	        } catch (IllegalArgumentException e) {
	        }

	      	}
	        

	      
	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		
		// ============ Equivalence Partitions Tests ==============
		
		//positive numbers tests
		Vector v1 = new Vector(1, 1, 1);
		Vector v2 = new Vector(2, 8, 1);
		
		double num_dot = v1.dotProduct(v2);
		double ans = 11;
		assertEquals(" dotProduct() wrong result ", num_dot, ans,0.00000000000001);
		
		//zero test
		Vector v3 = new Vector(1, 0, 0);
		Vector v4 = new Vector(0, 1, 0);
		
		double num_dot1 = v3.dotProduct(v4);
		double ans1 = 0;
		assertEquals(" dotProduct() wrong result ", num_dot1, ans1,0.0000000001);
	
		//negative and positive numbers test

		Vector v5 = new Vector(-1, 3, 3);
		Vector v6 = new Vector(3, 1, 0);
		
		double num_dot2 = v5.dotProduct(v6);
		double ans2 = 0;
		assertEquals(" dotProduct() wrong result ", num_dot2, ans2,0.0000000001);

		
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        System.out.println(vr.dotProduct(v1));
        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of parallal vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {

		// ============ Equivalence Partitions Tests ==============

		
        // Test Length Squared result with positive and negative values and zero

		 Vector v1 = new Vector(1, 1, 1);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector (0,0,1);
	     
	     double vd1 = 3;
	     double vd2 = 56;
	     double vd3 = 1;
	     
	     assertTrue(v1.lengthSquared()==vd1);
	     assertTrue(v2.lengthSquared()== vd2);
	     assertTrue(v3.lengthSquared()== vd3);


	
	
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		
		// ============ Equivalence Partitions Tests ==============
		
        // Test Length Squared result with positive and negative values and zero


		 Vector v1 = new Vector(1, 1, 1);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector (0,0,1);
	     
	     double vd1 = Math.sqrt(3);
	     double vd2 = Math.sqrt(56);
	     double vd3 = Math.sqrt(1);
	     
	     assertTrue(v1.length()==vd1);
	     assertTrue(v2.length()== vd2);
	     assertTrue(v3.length()== vd3);


	
		
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		
		// basic check
		Vector v = new Vector(3.5,-5,10);
		v.normalize();
		assertEquals("", 1, v.length(),1e-10);
		
		
		// ============ Equivalence Partitions Tests ==============
		
		//check for different vectors if after normalize we get 
		//for each coordinate number bigger than 1
		 Vector v1 = new Vector(1, 1, 1);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector (0,0,1);
	     
	     v1.normalize();
	     v2.normalize();
	     v3.normalize();
	     
	     assertFalse(v1.getX() >1||v1.getY()>1||v1.getZ()>1);
	     assertFalse(v2.getX()>1||v2.getY()>1||v2.getZ()>1);
	     assertFalse(v3.getX()>1||v3.getY()>1||v3.getZ()>1);
	     
	     
	  // =============== Boundary Values Tests ==================
	  // test zero vector need throw exception
	     try {
	    	 v = new Vector(0,0,0);
	    	 v.normalize();
	    	 fail("Didn't throw divide by zero exception!");
	    	 } catch (IllegalArgumentException e) {
	    	 assertTrue(true);
	    	 }
	     

	
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		
		// ============ Equivalence Partitions Tests ==============

		// 3 test cases: 1  - positive, 2- negative, 3- includes 0's
		 Vector v1 = new Vector(1, 1, 1);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector (0,0,1);
	     
	     Vector vv1 = v1.normalized();
	     Vector vv2 = v2.normalized();
	     Vector vv3 = v3.normalized();
	     
	     v1.getHead().toString();
	     assertFalse(vv1.getX()>1||vv1.getY()>1||vv1.getZ()>1);
	     assertFalse(vv2.getX()>1||vv2.getY()>1||vv2.getZ()>1);
	     assertFalse(vv3.getX()>1||vv3.getY()>1||vv3.getZ()>1);	
	     
	}

}


