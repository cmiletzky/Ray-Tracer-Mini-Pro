package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * interface geometry declares one function - get normal
 * and extend the Intersectable interface
 * @author mgaru
 *
 */
public interface Geometry extends Intersectable {

	public Vector getNormal (Point3D p);
	
	
}
