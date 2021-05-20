/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static  primitives.Util.*;

/**
 * @author chaim & michael
 *
 */
public class SpotLight extends PointLight {
	
	private final Vector direction;

	public SpotLight(Color intensity, Point3D position , Vector direction) {
		super(intensity,position);
		this.direction = direction.normalized();
	}

    /**
     * @param p Point in object
     * @return the Color in that Point
     */
@Override
public Color getIntensity(Point3D p) {
    Vector dirNorm = direction.normalized();
    double vn = alignZero(dirNorm.dotProduct(getL(p)));
    return super.getIntensity(p).scale(Math.max(0, vn));
}

}
