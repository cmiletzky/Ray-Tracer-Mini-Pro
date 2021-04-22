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
		
		throw new IllegalArgumentException("vUp and vTo must be orthogonal");
	  }
	this.p0=p0;
	this.vTo=vTo.normalized();
	this.vUp=vUp.normalized();
	this.vRight= vTo.crossProduct(vUp);
	
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



	public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D pc = p0.add(vTo.scale(distance));
        double rY = height / nY;
        // Pixel's Width
        double rX = width / nX;

        //Pij is for Point on a certain pixel
        Point3D pij = pc;

        //delta y
        double yI = - (i - (nY - 1) / 2d) * rY;
        // delta x
         double xJ = (j - (nX - 1) / 2d) * rX;

         // taking into account when Xj is zero in order to avoid scaling a vector in zero
         if(!isZero(xJ)){
             pij = pij.add(vRight.scale(xJ));
         }
        // taking into account when Yi is zero in order to avoid scaling a vector in zero
         if(!isZero(yI)){
             pij = pij.add(vUp.scale(yI));
         }

         Vector vij = pij.subtract(p0);
 		
 		
 		return new Ray(p0, vij)   ;
 		}



	


	



	



}
