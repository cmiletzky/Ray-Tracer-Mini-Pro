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
	 */
	public Plane(Point3D p1, Point3D p2,Point3D p3) {
			normal = null;
			q0= p1;
			
	}
	
	/**
	 * point and normal vector constructor 
	 */
	public Plane(Point3D p1, Vector v1) {
		q0 = p1;
		normal = new Vector(v1.getHead());
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
	 *   will be updated in the next task 
	 */
	public Vector getNormal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return normal;
	}
}
