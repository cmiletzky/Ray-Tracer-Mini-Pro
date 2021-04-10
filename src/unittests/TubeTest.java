/**
 * 
 */
package unittests;
import geometries.*;
import primitives.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author חייקי
 *
 */
public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		Point3D point = new Point3D(0, 0, 0);
		Vector vector= new Vector(0,1,0);
		Ray ray= new Ray(vector, point);
		Tube tube=new Tube(1.0,ray);
		 // ============ Equivalence Partitions Tests ==============
		
		// need check only one case
		assertEquals("wrong normal in Equivalence Partitions ", new Vector(1,0,0), tube.getNormal(new Point3D(1,1,0)));
		
		
		// =============== Boundary Values Tests ==================
		
		// need check only one case
		assertEquals("wrong normal in Boundary Values ", new Vector(1,0,0), tube.getNormal(new Point3D(1,0,0)));

		
	}

}
