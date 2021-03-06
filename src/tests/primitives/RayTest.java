/**
 * 
 */
package tests.primitives;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author chaim & michael
 * test for Ray class
 */
public class RayTest {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	
	// ============ Equivalence Partitions Tests ==============
	//TC01  closet point in middle of list
	@Test
	public void testFindClosestPoint() {
		Ray check = new Ray(new Point3D(1,1,1), new Vector(1,2,3))	;
		List<Point3D> points= new LinkedList<Point3D>();
		points.add(new Point3D(2,5,6));
		points.add(new Point3D(2,3,4));
		points.add(new Point3D(2,2,2));
		points.add(new Point3D(4,4,7));
		points.add(new Point3D(-2,-7,10));

		assertEquals("uncorect point", points.get(2), check.findClosestPoint(points));
	}
	
	// =============== Boundary Values Tests ==================	
	//TC11 there is no intersections
	@Test
	public void testFindClosestPoint2() {
		Ray check = new Ray(new Point3D(1,1,1), new Vector(1,2,3))	;
		List<Point3D> points= null;
		

		assertNull("there shouldn't be intersect points", check.findClosestPoint(points));
	}
	
	//TC12 the closet point at the begin of list
	@Test
	public void testFindClosestPoint3() {
		Ray check = new Ray(new Point3D(1,1,1), new Vector(1,2,3))	;
		List<Point3D> points= new LinkedList<Point3D>();
		points.add(new Point3D(2,2,2));
		points.add(new Point3D(2,5,6));
		points.add(new Point3D(2,3,4));
		points.add(new Point3D(4,4,7));
		points.add(new Point3D(-2,-7,10));

		assertEquals("uncorect point", points.get(0), check.findClosestPoint(points));
	}
	
	//TC13 the closet point at the end of list
		@Test
		public void testFindClosestPoint4() {
			Ray check = new Ray(new Point3D(1,1,1), new Vector(1,2,3))	;
			List<Point3D> points= new LinkedList<Point3D>();
			points.add(new Point3D(2,5,6));
			points.add(new Point3D(2,3,4));
			points.add(new Point3D(4,4,7));
			points.add(new Point3D(-2,-7,10));
			points.add(new Point3D(2,2,2));


			assertEquals("uncorect point", points.get(4), check.findClosestPoint(points));
		}
		
		/**
	     * Test method for {@link Ray#getClosestGeoPoint(List)}
	     */
	    @Test
	    public void getClosestGeoPoint() {
	        // ============ Equivalence Partitions Tests ==============
	        // TC01  closet geoPoint in middle of the list
	            Ray ray = new Ray(new Point3D(0,0,1), new Vector(0,0,1));
	            List<GeoPoint> geoPoints= new LinkedList<GeoPoint>();
	            geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,4) ));
	            geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,2) ));
	            geoPoints.add(new GeoPoint(new Triangle(new Point3D(-1,0,5),new Point3D(-1,-2,5),
	                    new Point3D(2,0,5)),new Point3D(0,0,5)));


	            assertEquals("incorrect geoPoint", geoPoints.get(1), ray.findClosestGeoPoint(geoPoints));

	        // =============== Boundary Values Tests ==================
	        //TC11 there is no intersections

	        ray = new Ray(new Point3D(1,1,1), new Vector(1,2,3))	;
	        geoPoints= new LinkedList<>();
	        assertNull("there shouldn't be intersect points", ray.findClosestGeoPoint(geoPoints));

	        //TC12 the closet point at the beginning of list
	        ray = new Ray(new Point3D(0,0,1), new Vector(0,0,1));
	        geoPoints= new LinkedList<GeoPoint>();
	        geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,2) ));
	        geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,4) ));
	        geoPoints.add(new GeoPoint(new Triangle(new Point3D(-1,0,5),new Point3D(-1,-2,5),
	                new Point3D(2,0,5)),new Point3D(0,0,5)));

	        assertEquals("incorrect geoPoint", geoPoints.get(0), ray.findClosestGeoPoint(geoPoints));

	        //TC13 the closet point at the end of list
	        geoPoints= new LinkedList<GeoPoint>();
	        geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,4) ));
	        geoPoints.add(new GeoPoint(new Triangle(new Point3D(-1,0,5),new Point3D(-1,-2,5),
	                new Point3D(2,0,5)),new Point3D(0,0,5)));
	        geoPoints.add(new GeoPoint(new Sphere(1,new Point3D(0,0,3)) ,new Point3D(0,0,2) ));

	        assertEquals("incorrect geoPoint", geoPoints.get(2), ray.findClosestGeoPoint(geoPoints));
	    }
	

}
