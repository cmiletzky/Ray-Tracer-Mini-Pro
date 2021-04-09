/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import geometries.Triangle;
import primitives.*;

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
		Triangle t= new Triangle(new Point3D(0,0,0),new Point3D(1,2,0) , new Point3D(2,0,0));
		assertEquals(new primitives.Vector(0, 0, -4), t.getNormal(new Point3D(1, 1, 1)).normalize());
	}

}
