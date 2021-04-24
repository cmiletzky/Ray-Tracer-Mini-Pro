package unittests;

import geometries.*;
import org.junit.Test;

import elements.Camera;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

/**
 *  class for integration test for Camera and intersections. counting correct sum of intersections.
 */
public class IntegrationT_camera_intersect {
    /**
     * Test rays through View Plane with Sphere
     */
    @Test
    public void testIntersectionWithSphere(){
        // TC01: 2 intersection points only  3*3 view plane
        Sphere sphere = new Sphere(1,new Point3D(0,0,-3));
        int intersectionPoints = getIntersections(sphere,Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
       assertEquals("bad Ray",2,intersectionPoints);

        // TC02: 18 intersection Points
        sphere = new Sphere(2.5,new Point3D(0,0,-2.5));
        intersectionPoints = getIntersections(sphere,new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0));
        assertEquals("bad Ray", 18,intersectionPoints);

        // TC03: 10 intersection Points
        sphere = new Sphere(2,new Point3D(0,0,-2));
        intersectionPoints = getIntersections(sphere,new Point3D(0,0,0.5),new Vector(0,0,-1),new Vector(0,1,0));
        assertEquals("bad Ray",10,intersectionPoints);

        // TC04: 9 intersection points, Camera inside Sphere)
        sphere = new Sphere(4,new Point3D(0,0,-2.5));
        intersectionPoints = getIntersections(sphere,new Point3D(0,0,-1),new Vector(0,0,-1),new Vector(0,1,0));
        assertEquals("bad Ray",9,intersectionPoints);

        // TC05: 0 intersection Points 
        sphere = new Sphere(0.5,new Point3D(0,0,1));
        intersectionPoints = getIntersections(sphere,Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
        assertEquals("bad Ray",0,intersectionPoints);

        }

    /**
     * Test rays through View Plane with Plane
     */
        @Test
        public void testIntersectionWithPlane(){
            // TC01: 9 intersection Points 
            Plane plane = new Plane(new Point3D(0,0,-2),new Vector(0,0,1));
            int intersectionPoints = getIntersections(plane,
                    new Point3D(0,0,-0.5),new Vector(0,0,-1),new Vector(0,1,0));
            assertEquals("bad Ray",9,intersectionPoints);

            // TC02: 9 intersection Points not orthogonal to View Plane
            plane = new Plane(new Point3D(0,0,-2),new Point3D(3,0,-4),new Point3D(-3,40,-5));
            intersectionPoints = getIntersections(plane,
                    new Point3D(0, 0, -0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
            assertEquals("bad Ray",9,intersectionPoints);

            // TC03: 6 intersection Points 3 rays at bottom don't intersect Plane
            plane = new Plane(new Point3D(13,1,-0.5),new Point3D(0,4,-2),new Point3D(12,1,-1));
            intersectionPoints = getIntersections(plane,
                    new Point3D(0, 0, -0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
            assertEquals("bad Ray",6,intersectionPoints);
        }

    /**
     * Test method for rays through View Plane with Triangle
     */
        @Test
        public void testIntersectionWithTriangle(){
            // TC01: 1 intersection point
            Triangle triangle = new Triangle(new Point3D(1,-1,-2),
                    new Point3D(0,1,-2),new Point3D(-1,-1,-2));
            int intersectionPoint = getIntersections(triangle,
                    new Point3D(0,0,-1),new Vector(0,0,-1),new Vector(0,1,0));
            assertEquals("bad Ray",1,intersectionPoint);

            // TC02: 2 intersection points
            triangle = new Triangle(new Point3D(1,-1,-2),
                    new Point3D(0,20,-2),new Point3D(-1,-1,-2));
            intersectionPoint = getIntersections(triangle,
                    new Point3D(0,0,-1),new Vector(0,0,-1),new Vector(0,1,0));
            assertEquals("bad Ray",2,intersectionPoint);

        }

   /**
    * Go through all pixels in View Plane matrix
    * construct the Ray through PIXEL[i,j] and returns it
    * @param Shape
    * @param p0
    * @param vTo
    * @param vUp
    * @return
    */
    private int getIntersections(Intersectable Shape, Point3D p0, Vector vTo, Vector vUp) {
        Camera cam = new Camera(p0,vTo,vUp).setDistance(1).setViewPlaneSize(3,3);
        int intersectionPoints = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray rayFromCameraThroughPixel = cam.constructRayThroughPixel(3, 3, i, j);
                // Ray computed above is set as parameter for findIntersection with geoShape
                // intersection Points are reserved temporarily in a list
                // in order to count number of points and validate it with expected result
                List<Point3D> listOfIntersectionPoints = Shape.findIntersections(rayFromCameraThroughPixel);
                if (listOfIntersectionPoints != null) {
                    // Collecting the amounts of intersection Points
                    intersectionPoints += listOfIntersectionPoints.size();
                }
            }
        }
        return intersectionPoints;
    }


}
