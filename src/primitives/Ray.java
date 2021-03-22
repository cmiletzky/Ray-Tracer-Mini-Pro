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
	
	/**
	 * equals func
	 * 
	 */
	 @Override
     public boolean equals(Object v1) {
   	  if (this==v1) return true; // check the reference first
   	  
   	  if (!(v1 instanceof Ray)) return false;  //test for: is p1 is Ray type?
		
   	  Ray v = (Ray) v1;  // cast v1 to Ray after the test above
   	  return(vec.equals(v.vec)&&point.equals(v.point));
     }
	
}
