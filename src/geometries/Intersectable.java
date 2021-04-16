/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * @author chaim & michael
 * the purpose of this interface it for geometries that
 *  can have intersected point by some ray
 */
public interface Intersectable {
	List<Point3D> findIntersections(Ray ray);
}
