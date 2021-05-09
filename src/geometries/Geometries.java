/**
 * 
 */
package geometries;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author Chaim & Michael
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> intersectables=null;
	
	public Geometries() {
		intersectables= new LinkedList<>();
		}
	
	public Geometries(Intersectable... intersectables) {
		this.intersectables= new LinkedList<>();
		add(intersectables);
		}
	/**
	 * func to add new items 
	 * @param intersectables
	 */
	public void add(Intersectable... intersectables) {
		for(Intersectable item:intersectables)
		{
			this.intersectables.add(item);
		}
		
	}
	
	
	public List<Point3D> findIntersectionsPoints(Ray ray) {
		List<Point3D> result = null;
		for (Intersectable item : intersectables) {
			List<Point3D>intersections= item.findIntersectionsPoints(ray);
			if (intersections!=null) {
				if (result==null) {
					result=new LinkedList<>();
				}
				result.addAll(intersections);
				
			}
		}

		return result;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		List<GeoPoint> result = null;
        for (Intersectable geo: intersectables) {
            List<GeoPoint> geoPoints = geo.findGeoIntersections(ray);
            if(geoPoints != null){
                if(result == null){
                    result = new LinkedList<>();
                }
                result.addAll(geoPoints);
            }
        }
        return result;
	}
	
	
	

}
