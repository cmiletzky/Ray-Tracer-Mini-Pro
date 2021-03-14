package geometries;


import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere {
	
	private Point3D _center;
	private double radius;
	/**
	 * constructor - uses the parent one for the radius and saves the center
	 * @param radius
	 * @param p
	 */
	
	public Sphere(double radius, Point3D p) {
		this.radius=radius;
		_center = new Point3D(p);
		// TODO Auto-generated constructor stub
	}
//	public Sphere( Point3D p,double radius) {
//		super(radius);
//		_center = new Point3D(p);
//		// TODO Auto-generated constructor stub
//	}
	
	/**
	 * getters
	 * @return
	 */
	public Point3D getCenter() {
		return _center;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 * to string
	 * @return
	 */
	@Override
	public String toString() {
		return super.toString()+ " center=" + _center + "]";
	}
	public Vector getNormal(Point3D p) {
			
		
		Vector v=new Vector(p.subtract(_center));
			return v.normalize();
		
	}

	
	// https://dev.to/bacchu/intersection-of-a-vector-with-a-sphere-5b5k
	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		ArrayList<Point3D> outIntPoins = new ArrayList<>();
		Point3D point1 = new Point3D(ray.getPoint());
		Point3D point2 = new Point3D(ray.getPoint().getX()+ray.getVec().getX(),ray.getPoint().getY()+ray.getVec().getY(),ray.getPoint().getZ()+ray.getVec().getZ());
		
		 double discr;   // Square of the discriminant of the quadratic
	     double t1 = 0.0; // parameter 1
	     double t2 = 0.0; // parameter 2
		    
		double a = ray.getVec().lengthSquared();
		double b = 2 * ((ray.getVec().getX()) * (ray.getPoint().getX()-this._center.getX()) + (ray.getVec().getY()) * (ray.getPoint().getY()-this._center.getY())+ (ray.getPoint().getZ()) * (ray.getPoint().getZ()-this._center.getZ()));
        double c = (ray.getPoint().getX()-this._center.getX())*(ray.getPoint().getX()-this._center.getX())+
        		   (ray.getPoint().getY()-this._center.getY())*(ray.getPoint().getY()-this._center.getY())+
        		   (ray.getPoint().getZ()-this._center.getZ())*(ray.getPoint().getZ()-this._center.getZ())-
        		   ((this.getradius()*this.getradius()));
        discr = Math.pow(b, 2) - (4*a*c);

        // No intersection points`
        if (discr < 0)
        {
            System.out.println("\nRay does not intersect the sphere");
            return null;     
        }
        else if(discr == 0)  // Single intersection point
        {

            if (a > 0)
            {
                t1 = -b/(2*a);

                Point3D XPoint = findXPoint(point1, point2, t1);
                outIntPoins.add(XPoint);
                System.out.println(XPoint);


                return outIntPoins;
            }
            else{
                
            }
        }

	        else // Two intersection points
	        {
	            System.out.println("\nVector intersects the sphere at two points");
	            t1 = (-b - Math.sqrt(discr))/(2*a); // 1st Vector equation parameter
	            t2 = (-b + Math.sqrt(discr))/(2*a); // 2nd Vector equation parameter

	            // Find the points of intersection
	            Point3D XPoint1 = findXPoint(point1, point2, t1);
	            Point3D XPoint2 = findXPoint(point1, point2, t2);
	            outIntPoins.add(XPoint1);
	            outIntPoins.add(XPoint2);
                System.out.println(XPoint1);
                System.out.println(XPoint2);

	            return outIntPoins;

	        }
		
		return null;
	}
	  
	 private static Point3D findXPoint(Point3D point1, Point3D point2, double t) {

	        Point3D intPoint = new Point3D(0,0,0);

	        // Parametric equation of the form L = P + tU
	        // where 'L' is the intersection point, 'P' is the point on the line and
	        // U is the unit vector (Point2 - Point1)
	        intPoint.setX(point1.getX() + t * (point2.getX() - point1.getX()));
	        intPoint.setY(point1.getY() + t * (point2.getY() - point1.getY()));      
	        intPoint.setZ(point1.getZ() + t * (point2.getZ() - point1.getZ()));


	        return intPoint;   
	    }
}
