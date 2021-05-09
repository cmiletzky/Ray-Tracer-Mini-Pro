/**
 * 
 */
package geometries;

import java.util.List;
import java.util.Objects;

import primitives.*;

/**
 * @author chaim & michael
 * the purpose of this interface it for geometries that
 *  can have intersected point by some ray
 */
public interface Intersectable {
	
	public List<Point3D> findIntersectionsPoints(Ray ray);
	public List<GeoPoint> findGeoIntersections ( Ray ray);
	
	/**
	 * PDS class represent point with its polygon 
	 */
	
	public static class GeoPoint {
	    
		public Geometry geometry;
	    public Point3D point;
	    
	    /**
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			super();
			this.geometry = geometry;
			this.point = point;
		}

		/**
		 * equals method 
		 * equals between two Geopoints 
		 */

		@Override
		 public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) &&
                    Objects.equals(point, geoPoint.point);
        }

		
		
	
		

		
		
	}
	

}
