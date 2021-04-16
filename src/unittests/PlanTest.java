/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author mgaru
 *
 */
public class PlanTest {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	
	@Test
	public void testConstructor() {
		
        // =============== Boundary Values Tests ==================

		
		// case 1 tow first points are the same
				
		try {
			Plane p3 = new Plane(new Point3D(0.0, 1.0, 0.0), new Point3D(0.0, 1.0, 0.0),
					new Point3D(1.0, 0.0, 0.0));
			fail("Failed constructing a correct Plane");

		}catch (IllegalArgumentException e){
		}
		//case 2 -all three point on the same axis
		
		try {
			Plane p2 = new Plane(new Point3D(0.0, 1.0, 0.0), new Point3D(0.0, 2.0,0.0),
					new Point3D(0.0, 3.0, 0.0));
			fail("case 2: constructed wrong plane");
		}catch (IllegalArgumentException e){
			
		}
		
		
	}
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		
		//case 1
		Plane p1 = new Plane(new Point3D(0.0, 1.0, 1.0), new Point3D(0.0, 0.0, 1.0), new Point3D(0.0, 1.0, 0.0));
		Vector ans1 = new Vector(1.0,0.0,0.0);
		Vector ans2 = new Vector(-1.0,0.0,0.0);
		assertTrue(p1.getNormal().equals(ans1)||p1.getNormal().equals(ans2));
		

	}
	
	
	 @Test
	    public void testfindIntersections() {
	        // ============ Equivalence Partitions Tests ==============
	        // Ray is neither orthogonal nor parallel to the plane

	        // TC01: Ray intersects the plane (1 point)
	        Plane plane = new Plane(new Point3D(2,0,0),new Point3D(0,2,0),new Point3D(0,0,2));
	        Point3D p = new Point3D(-1.142857142857143,3.142857142857143,0);
	        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(0,4,0), new Vector(-4,-3,0)));
	        assertEquals("Wrong number of points", 1, result.size());
	        assertEquals("Ray intersects the plane", List.of(p), result);

	        // TC02: Ray does not intersect the plane (0 points)
	        assertNull("Ray starts after plane",
	                plane.findIntersections(new Ray(new Point3D(-4,1,0),new Vector(-4,-3,0))));

	        // =============== Boundary Values Tests ==================
	        // **** Group: Ray is parallel to the plane

	        // TC11: the ray included in the plane (0 points)
	        assertNull("Ray included in plane",
	                plane.findIntersections(new Ray(new Point3D(0.657087677596453,-1.051994262427145,2.394906584830692),
	                        new Vector(-1.706405988359289,-0.621475834191163,2.327881822550452))));
	        // TC12: the ray is not included in the plane (0 points)
	        assertNull("Ray parallel with plane",
	                plane.findIntersections(new Ray(new Point3D(0,4,0), new Vector(-2,0,2))));
	        // **** Group: Ray is orthogonal to the plane
	        // TC13: – according to p0 (before) (1 point)
	        p = new Point3D(0.33333333333333333,1.33333333333333333,0.33333333333333333);
	        result = plane.findIntersections(new Ray(new Point3D(0,1,0), new Vector(1,1,1)));
	        assertEquals("Wrong number of points", 1, result.size());
	        assertEquals("Ray intersects the plane", List.of(p), result);
	        // TC14: – according to p0 (in) (0 points)
	        assertNull("Ray starts at plane",
	                plane.findIntersections(new Ray(new Point3D(0,2,0), new Vector(1,1,1))));
	        // TC15: – according to p0 (after the plane) (0 points)
	        assertNull("Ray starts after plane",
	                plane.findIntersections(new Ray(new Point3D(0.327523993905339,-0.403942564427651,4.408486769939391),
	                        new Vector(1,1,1))));
	        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane
	        //(p0 is in the plane, but not the ray) (0 points)
	        assertNull("Ray starts at plane",
	                plane.findIntersections(new Ray(new Point3D(-2.227529894658485,-0.144919886756481,4.372449781414965),
	                        new Vector(0,1,1))));
	        // TC17: Ray is neither orthogonal nor parallel to the plane and begins in
	        //the same point which appears as reference point in the plane (Q) (0 points)
	        assertNull("Ray starts at plane",
	                plane.findIntersections(new Ray(new Point3D(2,0,0), new Vector(0,1,1))));
	    }
}

