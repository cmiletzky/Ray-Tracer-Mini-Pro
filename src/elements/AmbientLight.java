package elements;

import primitives.Color;

/**
 * 
 * @author chaim & michael
 * this class are represent ambient light. meaning that each object in our scene
 * have some default light no matter what other light we adding letter.
 *
 */
public class AmbientLight extends Light {
	
	public AmbientLight() {
		super(Color.BLACK);
	}
	
	public AmbientLight(Color Ia, double Ka) {
		super(Ia.scale(Ka));
	}
	public Color getIntensity() {
		return this.intensity;
	}
	




}
