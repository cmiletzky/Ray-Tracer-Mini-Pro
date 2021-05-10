package geometries;


import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends Geometry {

	private Ray axisRay;
	private double radius;
	
	/**
	 * constructor - uses parent constr for radius
	 * @param _radius
	 * @param ax
	 */
	
	public Tube(double _radius, Ray ax) {
		radius = _radius;
		axisRay = new Ray (ax.getVec(),ax.getPoint());
	}

	public Ray get_axisRay() {
		return axisRay;
	}

	public double get_Radius() {
		return radius;
	}

	/**
	 * to string
	 * @return
	 */	
	@Override
	public String toString() {
		return " radius= " +radius +"axisRay= "+axisRay;
	}

	@Override
	public Vector getNormal(Point3D p) {
		
		double t=axisRay.getVec().dotProduct(p.subtract(axisRay.getPoint()));
		Point3D o;
		// regular case
		if(t!=0)
			{ o=axisRay.getVec().scale(t).getHead();}
		// Boundary value: if the axis of tube vertical to the (p-o)
		else {
			o= axisRay.getPoint();
		}
		Vector normal=new Vector(p.subtract(o).getHead());
		return normal;
	}

	public List<Point3D> findIntersectionsPoints(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
