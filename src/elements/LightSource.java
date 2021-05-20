package elements;

import primitives.*;
/**
 * 
 * @author chaim michael
 * interface for sorts of light 
 */
public interface LightSource {
	/**
	 * 
	 * @param p Point 3D
	 * @return intensity of light  Color of source of light 
	 */
	public Color getIntensity(Point3D p);
	
	/**
	 * 
	 * @param p Point 3D
	 * @return direction of the source of light
	 */
	public Vector getL(Point3D p);
	/**
	 * calculate distance between light source to point intersected. its for correct the shadow rays results
	 * @param point
	 * @return
	 */
	double getDistance(Point3D point);


}
