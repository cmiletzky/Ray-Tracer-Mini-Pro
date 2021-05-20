package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
/**
 * interface geometry declares one function - get normal
 * and extend the Intersectable interface
 * @author mgaru
 *
 */
public abstract class Geometry implements Intersectable {

	public abstract Vector getNormal (Point3D p);
	protected Color emmission = Color.BLACK;
	// material of geometry shape
    private Material material = new Material();

   
	/**
	 * @param emmission the emmission to set
	 */
	public Geometry setEmmission(Color emmission) {
		this.emmission = emmission;
		return this;
	}
	/**
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	} 
	
	 public Material getMaterial() {
	        return material;
	    }
	    /**
	     * setter use builder pattern
	     * @param material the object made of
	     * @return this for chaining purposes
	     */
	    public Geometry setMaterial(Material material) {
	        this.material = material;
	        return this;
	
}
}
