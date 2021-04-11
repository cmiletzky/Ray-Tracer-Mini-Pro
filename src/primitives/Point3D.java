
package primitives;
import static primitives.Util.*;


//import com.sun.org.apache.bcel.internal.Const;

public class Point3D {

	
	final Coordinate x,y,z; 

	public double getX() {
		return x.coord;
	}
	
	public double getY() {
		return y.coord;
	}
	public double getZ() {
		return z.coord;
	}
	/**
	 * coordinate and double constructors
	 * @param _x
	 * @param _y
	 * @param _z
	 */
	
	public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) { //constructor by coordinate
		x=_x;
		y=_y;
		z=_z;
	}
	
	public Point3D(Point3D p1) { //constructor by coordinate
		x=p1.x;
		y=p1.y;
		z=p1.z;
	}
	
	public Point3D(double _x,double _y,double _z) {  //constructor by numbers (double)
		x=new Coordinate(_x);
		y=new Coordinate(_y);
		z=new Coordinate(_z);	
	}
	
		
	/**
	 * zero point or "reshit ha zirim" as requested
	 */
	
	public static final Point3D ZERO = new Point3D(0,0,0);
	
	
/**
 * 
 * @param point
 * @return new vector with substruct between the points and the direction of the vector
 */
	public Vector subtract(Point3D p) {
		//double si = Math.sqrt((p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y)+(p.z-this.z)*(p.z-this.z));
		double x1 = (this.x.coord)-p.x.coord;
		double y1 = this.y.coord-p.y.coord;
		double z1 = this.z.coord-p.z.coord;
		Point3D p1 = new Point3D(x1,y1,z1);
		
		return new Vector(p1);
	}
	/**
	 * 
	 * @param v1
	 * @return this point plus v1 point
	 */
	
	public Point3D add(Vector v1) {
		double xx=x.coord+v1.getHead().x.coord;
		double yy = y.coord+v1.getHead().y.coord;
		double zz = z.coord+v1.getHead().z.coord;
		
		return new Point3D(xx, yy, zz);
	}
	
	
	
	/**
	 * 
	 * @param p2
	 * @return distance Squared
	 */
	
	public double distanceSquared(Point3D p2) {
		return ((x.coord-p2.x.coord)*(x.coord-p2.x.coord)+(y.coord-p2.y.coord)*(y.coord-p2.y.coord)+(z.coord-p2.z.coord)*(z.coord-p2.z.coord));
	}

	/**
	 * 
	 * @param p2
	 * @return distance
	 */
	public double distance(Point3D p2) {

		return(Math.sqrt(distanceSquared(p2)));	
		
}
	
	/**
	 * to string function
	 */
	public String toString() {
  	  return ("x: "+x
  			  +" y: "+y
  			  +" z: " +z);
    }

	
	@Override
	public boolean equals(Object p1) {
		if(this==p1) return true; // tests reference
		
		if(!(p1 instanceof Point3D)) return false; // test for: is p1 is point3d type?
		
		Point3D p = (Point3D) p1; // casting to point (after the check)
		
		return(isZero(x.coord-p.x.coord)&&isZero(y.coord-p.y.coord)&&isZero(z.coord-p.z.coord));
	}

	
	}
