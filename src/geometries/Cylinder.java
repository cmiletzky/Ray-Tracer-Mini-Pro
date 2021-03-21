package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{

	
	private double height;
	
	/**
	 * get
	 * @return
	 */
	public double getHeight() {
		
		return height;
	}

	/**
	 * constructor - uses parent constructor
	 * @param _radius
	 * @param ax
	 */

	public Cylinder(double _radius, Ray ax, double height) {
		super(_radius, ax);
		this.height = height;
	}

	
	/**
	 * to string func, using the parent func
	 */
	@Override
	public String toString() {
		return super.toString() +" height=" + height ;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		
		return null;
		
	} 
	
	
	

}
