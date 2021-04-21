/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * @author chaim & michael
 *
 */
public class Camera {
	
	final private Point3D p0;
	final private Vector vUp;
	final private Vector vTo;
	final private Vector vRight;
	private double width;
	private double height;
	private double distance;
	
	public Camera(Point3D p0, Vector vTo, Vector vUp) {

	if (!isZero(vUp.dotProduct(vTo))) {
		
		throw new IllegalArgumentException("vUp and vTo must be orthogonal")
	  }
	this.p0=p0;
	this.vTo=vTo.normalized();
	this.vUp=vUp.normalized();
	vRight= vTo.crossProduct(vUp)p0;
	
	}


/**
 * getters for fields 
 * @return
 */
	public Vector getvUp() {
		return vUp;
	}



	public Vector getvTo() {
		return vTo;
	}



	public Vector getvRight() {
		return vRight;
	} 
	
	
	/**
	 *  setters for view plan and distance by chaning
	 * @param width
	 * @param height
	 * @return
	 */
	public Camera setViewPlaneSize(double width,double height) {
		this.width = width;
		this.height = height;
		return this;
	}


	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}



	Ray constructRay(int nx, int ny, int i, int j)
	{
		Point3D pc= p0.add(vTo.scale(distance));
		double Rx = width/nx;
		double Ry = height/ny;
		
		
		Point3D pij = pc;
		double yi = -Ry * (i - (ny - 1)/2d);
		double xj = Rx * (i - (nx - 1)/2d);

	}



	public Camera setVpDistance(int i) {
		// TODO Auto-generated method stub
		return null;
	}



	public Object setVpSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}



	



}
