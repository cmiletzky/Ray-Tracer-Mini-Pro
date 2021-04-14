package geometries;


import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;
import primitives.*;
import java.lang.Math;


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
	@Override
	public Vector getNormal(Point3D p) {
					
	Vector v=new Vector(p.subtract(center).getHead());
	return v.normalize();
		
	}

	@Override
	public List<Point3D> findIntsersections(Ray ray) {
		// in case that P0 is same as center
		Point3D p0=ray.getPoint();
		Vector V= ray.getVec();
		if (p0.equals(center)) {
			return List.of(center.add(V.scale(radius)));
		}
		
		
		Vector u=center.subtract(ray.getPoint());
		try {
			double tm=alignZero( V.dotProduct(u));
//			if (tm==-(u.length())) {
//				return List.of(center.add(V.scale(radius)));
//			}
		} catch (IllegalArgumentException e) {
			// if V.dotProduct(u)==0 so they vertical 
			 return null;
		}
		
		double tm=alignZero( V.dotProduct(u));
		double d=alignZero(Math.sqrt(alignZero(u.lengthSquared()-tm*tm)));
		
		
		// no intersection : the ray diraction is above the sphere
		if (d>=radius) {
			return null;
		}
		
		
		double th = alignZero(Math.sqrt(alignZero(radius*radius- d*d)));
		double t1= alignZero(tm-th);
		double t2= alignZero(tm+th);

		if (t1 > 0 && t2 > 0) {
			Point3D p1 = p0.add(V.scale(t1));
			Point3D p2 = p0.add(V.scale(t2));
			return List.of(p1,p2);
		}
		 if (t1>0) {
				Point3D p1 = p0.add(V.scale(t1));
				return List.of(p1);
		}
		 if (t2>0) {
				Point3D p2 = p0.add(V.scale(t2));
				return List.of(p2);
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	}
