/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static  primitives.Util.*;

/**
 * @author חייקי
 *
 */
public class PointLight extends Light implements LightSource   {
	
	private final Point3D position;
	private double kC = 1,
			kL = 0,
			kQ = 0;

	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
	}
	
	 /**
     * setter using builder pattern
     * @param kC attenuation factor
     * @return this for chaining purpose
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter using builder pattern
     * @param kL attenuation factor
     * @return this for chaining purpose
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter using builder pattern
     * @param kq attenuation factor
     * @return this for chaining purpose
     */
    public PointLight setKq(double kq) {
        this.kQ = kq;
        return this;
    } 

	@Override
	 public Color getIntensity(Point3D p) {
        double distance = alignZero( p.distance(position));
        Color iL = getIntensity().scale(1 / (kC + kL * distance + kQ * distance * distance));
        return iL;
    }

	@Override
	public Vector getL(Point3D p) {
		
		return p.subtract(position).normalized();
	}

	@Override
	public double getDistance(Point3D point) {
		double distance = position.distance(point);
		return distance ;
	}
	
	

}
