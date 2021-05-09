/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * @author chaim & michael
 *
 */
public class RayTracerBasic extends RayTracerBase  {

	public RayTracerBasic(Scene scene) {
		super(scene);
		
	}
/**
 * paint every intersection point with appropriate color
 */
	  @Override
	    public Color traceRay(Ray ray) {
	        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
	        if (intersection != null) {
	            GeoPoint close = ray.findClosestGeoPoint(intersection);
	            return calcColor(close);
	        }
	        return scene.background;
	    }

	
	 private Color calcColor(GeoPoint gp) {
	        return scene.ambientlight.getIntensity().add(gp.geometry.getEmmission());
	    }

}
