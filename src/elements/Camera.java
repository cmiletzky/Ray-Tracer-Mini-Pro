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
	
	public Camera(Point3D zero, Vector vector, Vector vector2) {

	if (!isZero) {
		
	}
	}



	public Vector getvUp() {
		return vUp;
	}



	public Vector getvTo() {
		return vTo;
	}



	public Vector getvRight() {
		return vRight;
	} 
	
	Ray constructRay(int nx, int ny, int i, int j)
	{
		
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
