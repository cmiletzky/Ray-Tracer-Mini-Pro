package primitives;

public class Ray {
	
	private Vector vec;
	private Point3D point;

	/**
	 * constructor
	 * @param p
	 * @param direc
	 */
	
	public Ray(Vector vec, Point3D point) {
		super();
		this.vec = vec;
		this.point = point;
	}
	
	public Ray(Point3D point3d, Vector vec) {
		super();
		this.vec = vec;
		this.point = point3d;
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
