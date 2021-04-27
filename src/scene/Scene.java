/**
 * 
 */
package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * @author chaim & michael
 * Passive Data Structure class that hold the data of scene object
 */
public class Scene {

	public final String name;
	
	public Color background = Color.BLACK ;
	public AmbientLight ambientlight = new AmbientLight( Color.BLACK , 1d);
	public Geometries geometries = null;
	
	
	public Scene(String name) {
		this.name = name;
		this.geometries = new Geometries();
		
	}


	/**
	 * @param background the background to set
	 * @return 
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}


	/**
	 * @param ambientlight the ambientlight to set
	 * @return 
	 */
	public Scene setAmbientlight(AmbientLight ambientlight) {
		this.ambientlight = ambientlight;
		return this;
	}


	/**
	 * @param geometries the geometries to set
	 * @return
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	
	
}
