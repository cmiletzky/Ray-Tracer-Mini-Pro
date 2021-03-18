package geometries;


import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere implements Geometry {
	
	private Point3D center;
	private double radius;
	/**
	 * constructor - uses the parent one for the radius and saves the center
	 * @param radius
	 * @param p
	 */
	
	public Sphere(double radius, Point3D p) {
		this.radius=radius;
		center = p;
	}
	
	/**
	 * getters
	 * @return
	 */
	public Point3D getCenter() {
		return center;
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
		return super.toString()+ " center=" + center + "]";
	}
	public Vector getNormal(Point3D p) {
		return null;		
	}

	
	}
