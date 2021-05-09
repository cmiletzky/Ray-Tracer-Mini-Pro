/**
 * 
 */
package tests.geometries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

//import primitives.Vector;
import primitives.*;
import org.junit.Test;
import geometries.Triangle;

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

	
	public void testFindIntersection() {
		
        // ============ Equivalence Partitions Tests ==============
		
		
        // TC 1: Ray intersect the triangle inside
		Point3D A=new Point3D(1,0,2);
		Point3D B=new Point3D(3,0,2);
		Point3D C=new Point3D(2,2,2);

		Triangle triangle=new Triangle(A, B, C);
		Vector v= new Vector(0, 0, 1);
		Ray ray=new Ray(new Point3D(2,1,0), v);
		Point3D P = new Point3D(2,1,2); // expected intersect point
		List<Point3D> result = triangle.findIntersectionsPoints(ray);
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray intersects the plane", List.of(P), result);
		
		
		// TC 2: Ray not intersect the triangle and goes against edge
		ray=new Ray(new Point3D(1,-1,0), v);
		result = triangle.findIntersectionsPoints(ray);
		assertNull("ray shouldnt intersect, ray against edge", result);
		
		// TC 3: Ray not intersect the triangle and goes against vertax
		ray=new Ray(new Point3D(2,-3,0), v);
		result = triangle.findIntersectionsPoints(ray);
		assertNull("ray shouldnt intersect, ray against vertax", result);
		
		
		// =============== Boundary Values Tests ==================
		
		// TC 10: ray goes through edge
		ray=new Ray(new Point3D(2,0,0), v);
		result = triangle.findIntersectionsPoints(ray);
		assertNull("ray shouldnt intersect, ray against vertax", result);
		
		// TC 11: ray goes through vertax
		ray=new Ray(new Point3D(3,0,0), v);
		result = triangle.findIntersectionsPoints(ray);
		assertNull("ray shouldnt intersect, ray against vertax", result);

		// TC 11: ray goes through vertax
		ray=new Ray(new Point3D(4,0,0), v);
		result = triangle.findIntersectionsPoints(ray);
		assertNull("ray shouldnt intersect, ray against vertax", result);
		
	}
	
	
	
}
