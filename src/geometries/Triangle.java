package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon{

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * 
	 * 'super' constructor, from Polygon
	 */
	
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		
		// if ray dosnt intersect the plan consist in triangle return null
		if (this.plane.findIntersections(ray)==null) {
			return null;
		}
		Vector v= ray.getVec();
		
		Point3D p1 = vertices.get(0);
		Point3D p2 = vertices.get(1);
		Point3D p3 = vertices.get(2);

		// otherwise check if ray intersect the triangle
		Vector v1 = p1.subtract(ray.getPoint());
		Vector v2 = p2.subtract(ray.getPoint());
		Vector v3 = p3.subtract(ray.getPoint());
		
		Vector N1 = v1.crossProduct(v2);
		Vector N2 = v2.crossProduct(v3);
		Vector N3 = v3.crossProduct(v1);

		if ((v.dotProduct(N1)>0&&v.dotProduct(N2)>0&&v.dotProduct(N3)>0) ||
				(v.dotProduct(N1)<0&&v.dotProduct(N2)<0&&v.dotProduct(N3)<0))
		{
			List<Point3D> ret =this.plane.findIntersections(ray);
			
			return ret;
		}
		else
			return null;

	}
	

	
}
