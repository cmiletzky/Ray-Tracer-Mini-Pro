package elements;

import primitives.Color;

/**
 * 
 * @author chaim & michael
 * this class are represent ambient light. meaning that each object in our scene
 * have some default light no matter what other light we adding letter.
 *
 */
public class AmbientLight {
	/**
	 * intensity of ambient light
	 */
	final private Color intensity ;
	
	
	/**
	 * constructor
	 * @param Ia intensity color
	 * @param Ka constant
	 */
	
	public AmbientLight(Color Ia, double Ka) {
		this.intensity = Ia.scale(Ka);
	}



/**
 * get intensity color
 * @return
 */
	public Color getIntensity() {
		return intensity;
	}
	

}
