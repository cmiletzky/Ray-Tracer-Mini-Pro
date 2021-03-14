package geometries;

import primitives.Ray;

public class Cylinder extends Tube{

	
	private double _height;
	
	/**
	 * get
	 * @return
	 */
	public double getHeight() {
		return _height;
	}

	/**
	 * constructor - uses parent constr
	 * @param _radius
	 * @param ax
	 */

	public Cylinder(double _radius, Ray ax, double height) {
		super(_radius, ax);
		_height = height;
	}

	
	/**
	 * to string func, using the parent func
	 */
	@Override
	public String toString() {
		return super.toString() +" height=" + _height ;
	}
	
	
	
	

}
