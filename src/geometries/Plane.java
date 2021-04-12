package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane implements Geometry {
	
	private Point3D q0;
	private Vector normal;
	
	/**
	 * 3 points const - will be updated in the next task  as requested
	 * 
	 * updated - by the tirgul
	 * TODO in case of two Points coalesce or 3 point same axis throw exception
	 * TODO calculate normal by getNormal
	 */
	
	/**
	 * point and normal vector constructor 
	 */
	public Plane(Point3D p1,Vector normal11) {
			normal = new Vector(normal11.getHead());
			q0= p1;
			
	}
	
	public Plane(Point3D x,Point3D y, Point3D z)
    {
		//doing subtract to check if there's two point that coalesce
		//then throwing exception from the constructor of Vector
		Vector check1=x.subtract(y);
		Vector check2=x.subtract(z);
		Vector check3=z.subtract(y);
			Vector a = new Vector(y.subtract(x).getHead());
	        Vector b = new Vector(z.subtract(x).getHead());
	        normal = new Vector(a.crossProduct(b).normalize().getHead());
	        q0 = new Point3D(x);
			
		
    }
	
		


	/**
	 * to string
	 * @return
	 */

	@Override
	public String toString() {
		return "p=" + q0 + ", _normal=" + normal ;
	}
	/**
	 * returns the normal
	 */
	public Vector getNormal() {
		
		return this.normal;
	}

	@Override
	public Vector getNormal(Point3D p) {
		
		return this.normal;
	}

	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
