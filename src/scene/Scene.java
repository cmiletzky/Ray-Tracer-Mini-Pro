/**
 * 
 */
package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
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
	public List<LightSource> lights = new LinkedList<LightSource>();

	
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
	public Scene setAmbientLight(AmbientLight ambientlight) {
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
	
	 /**
     * setter use builder pattern
     * @param lights list of lights in scene
     * @return this for chaining purpose
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
	
	
}
