package primitives;

import java.util.Objects;

public class Ray {
	
	private Vector vec;
	private Point3D point;

	/**
	 * constructor
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ray)) return false;
		Ray ray = (Ray) o;
		return this.point.equals(ray.point) && this.vec.equals(ray.vec);
	}

	@Override
	public int hashCode() {
		return 0;
	}

	/**
	 * getters
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
	
	

	
}
