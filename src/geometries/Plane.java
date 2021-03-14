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
			q0 = new Vector(p1);
			Vector vec1 = q0.subtract(p2);
			Vector vec2 = q0.subtract(p3);
			if ((vec1.crossProduct(vec2)).dotProduct(new Vector(q0)) == 0)
			{ normal = (vec1.crossProduct(vec2).normalize());}
			else System.out.println(" " +(vec1.crossProduct(vec2)));
	
	}
	
	/**
	 * point and normal vector constructor 
	 */
	public Plane(Point3D p1, Vector v1) {
		q0 = new Point3D(p1);
		normal = new Vector(v1);
	}
	
	
	
		
//	public Plane(Vector vector, Point3D point3d) {
//		// TODO Auto-generated constructor stub
//		q0=new Point3D(point3d);
//		normal=vector;
//	}

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

//	@Override
//	public List<Point3D> findIntsersections(Ray ray) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
