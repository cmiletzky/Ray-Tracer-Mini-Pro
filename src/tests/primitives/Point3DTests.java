/**
 * 
 */
package tests.primitives;

import static java.lang.System.out;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author Michael Garusi & Haim Miletzky
 *
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3); 
		Point3D p2 = new Point3D(2, 3, 4); 
		Vector p3 = new Vector(1, 1, 1);
		assertTrue(p3.equals(p2.subtract(p1)));
		
		
		
		// =============== Boundary Values Tests ==================	
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		
		Point3D p1 = new Point3D(1, 2, 3); 
		Vector v1 = new Vector(-1, -2, -3);
		
		
		
		
		// =============== Boundary Values Tests ==================	
		
		assertEquals("ERROR: Point + Vector does not work correctly", Point3D.ZERO,p1.add(v1));
//      if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
//          out.println("ERROR: Point + Vector does not work correctly");
	}
	
	
	@Test
	public void testdistance() {
		Point3D p1 = new  Point3D(1,2,3);
		Point3D p2 = new Point3D(7,8,9);

		assertEquals("distance isnt corect", 10.392304845413264, p1.distance(p2),0.000000000001);
		
	}

}
