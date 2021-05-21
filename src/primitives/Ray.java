package primitives;

import java.awt.Point;
import java.util.Iterator;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import java.util.Objects;
import static primitives.Util.*;

public class Ray {

	private Vector vec;
	private Point3D point;
	/**
	 * for wrongs in calculate ray start from surface of object we offset the point
	 */
	private static final double DELTA = 0.1;

	/**
	 * constructor
	 * 
	 * @param p
	 * @param direc
	 */

	public Ray(Vector vec, Point3D point) {

		this.vec = vec;
		this.point = point;
	}

	public Ray(Point3D point3d, Vector vec) {

		this.vec = vec.normalize();
		this.point = point3d;
	}

	public Ray(Point3D head, Vector direction, Vector normal) {
		if (isZero(normal.dotProduct(direction))) {
			this.point = null;
			this.vec = null;

		} 
		else {
			double sign = normal.dotProduct(direction) > 0 ? 1.0 : -1.0;

			this.point = head.add(normal.scale(DELTA * sign));
			this.vec = direction.normalized();

		}

	}

	/**
	 * getters
	 * 
	 * @return
	 */

	public Vector getVec() {
		return vec;
	}

	public Point3D getPoint() {
		return point;
	}

	/**
	 * to string
	 */
	@Override
	public String toString() {
		return "Ray [vec=" + vec.toString() + ", point=" + point.toString() + "]";
	}

	/**
	 * equals func
	 * 
	 */
	@Override
	public boolean equals(Object v1) {
		if (this == v1)
			return true; // check the reference first

		if (!(v1 instanceof Ray))
			return false; // test for: is p1 is Ray type?

		Ray v = (Ray) v1; // cast v1 to Ray after the test above
		return (vec.equals(v.vec) && point.equals(v.point));
	}

	/**
	 * refactoring for get new Point3d by scaling the vector diraction of ray
	 * 
	 * @param t
	 * @return
	 */
	public Point3D getPoint(double t) {
		return getPoint().add(getVec().scale(t));
	}

	/**
	 * find the closet point to p0 and return it
	 * 
	 * @param intersections list of intersection points
	 * @return closet point to p0
	 */
	public Point3D findClosestPoint(List<Point3D> intersections) {
		Point3D result = null;
		double closeDistance = Double.MAX_VALUE;

		// first check if the list is not empty
		if (intersections != null) {

			for (Point3D p : intersections) {
				double temp = this.point.distance(p);
				if (temp < closeDistance) {
					closeDistance = temp;
					result = p;
				}

			}
		}

		return result;
	}

	/**
	 * find closest geo point to ray intersects it
	 * 
	 * @param intersections list of GeoPoints that ray from camera intersects it
	 * @return closest GeoPoint to ray from camera
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
		GeoPoint geoPoint = null;
		double closeDistance = Double.MAX_VALUE;

		// first check if the list is not empty
		if (intersections != null) {
			for (GeoPoint gp : intersections) {
				double temp = this.point.distance(gp.point);
				if (temp < closeDistance) {
					closeDistance = temp;
					geoPoint = gp;
				}

			}
		}
		return geoPoint;
	}

}
