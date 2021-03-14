package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

	private Ray _axisRay;
	
	/**
	 * constructor - uses parent constr for radius
	 * @param _radius
	 * @param ax
	 */
	
	public Tube(double _radius, Ray ax) {
		super(_radius);
		_axisRay = new Ray(ax.getVec(),ax.getPoint());
		// TODO Auto-generated constructor stub
	}

	public Ray get_axisRay() {
		return _axisRay;
	}

	/**
	 * to string
	 * @return
	 */	
	@Override
	public String toString() {
		return super.toString()+" axisRay=" + _axisRay;
	}

	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = _axisRay.getVec().normalized();
		double d = v1.dotProduct(_axisRay.getPoint().subtract(p));
		Point3D p1 = _axisRay.getPoint();
		if(d!=0)  p1.add(v1.scale(d));
		return _axisRay.getPoint().subtract(p1).normalized();
		
	}

	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
