package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * interface geometry declares one function - get normal
 * @author mgaru
 *
 */
public interface Geometry {

	public Vector getNormal (Point3D p);
	
	
}
