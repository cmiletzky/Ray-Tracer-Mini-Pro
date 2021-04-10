/**
 * 
 */
package unittests;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import geometries.Triangle;
import primitives.Point3D;
//import primitives.Vector;

/**
 * @author חייקי
 *
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// one equivalent partition, no boundary cases
		Triangle t= new Triangle(new Point3D(0,0,0),new Point3D(1,2,0) , new Point3D(2,0,0));
		primitives.Vector ans1=new primitives.Vector(0.0, 0.0, -1.0);
		primitives.Vector ans2=new primitives.Vector(0.0, 0.0, 1.0);
		assertTrue("Got wrong normal",(t.getNormal(new Point3D(1, 1, 1)).equals(ans1) ||t.getNormal(new Point3D(1, 1, 1)).equals(ans2)));
	}

}
