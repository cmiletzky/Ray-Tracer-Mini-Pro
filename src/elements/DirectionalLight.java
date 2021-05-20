/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author chaim michael
 * class tp represent sort of light like the sun light
 */
public class DirectionalLight extends Light implements LightSource {

	private final Vector direction;
	
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		return this.intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		
		return direction;
	}

	@Override
	public double getDistance(Point3D point) {
		 return Double.POSITIVE_INFINITY;
	}

}
