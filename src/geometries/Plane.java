package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Plane extends Geometry {
	
	private Point3D q0;
	private Vector normal;
	
	/**
	 * 3 points const - will be updated in the next task  as requested
	 * 
	 * updated - by the tirgul
	 * TODO in case of two Points coalesce or 3 point same axis throw exception
	 * TODO calculate normal by getNormal
	 */
	
	/**
	 * point and normal vector constructor 
	 */
	public Plane(Point3D p1,Vector normal11) {
			normal = normal11.normalized();
			q0= p1;
			
	}
	
	public Plane(Point3D x,Point3D y, Point3D z)
    {
		//doing subtract to check if there's two point that coalesce
		//then throwing exception from the constructor of Vector
		Vector check1=x.subtract(y);
		Vector check2=x.subtract(z);
		Vector check3=z.subtract(y);
			Vector a = new Vector(y.subtract(x).getHead());
	        Vector b = new Vector(z.subtract(x).getHead());
	        normal = new Vector(a.crossProduct(b).normalize().getHead());
	        q0 = new Point3D(x);
			
		
    }
	
		


	/**
	 * to string
	 * @return
	 */

	@Override
	public String toString() {
		return "p=" + q0 + ", _normal=" + normal ;
	}
	/**
	 * returns the normal
	 */
	public Vector getNormal() {
		
		return this.normal;
	}

	@Override
	public Vector getNormal(Point3D p) {
		
		return this.normal;
	}

	

	/**
	 * private method assist to find intersected points
	 * @param ray
	 * @return
	 */
    public List<Point3D> findIntersectionsPoints(Ray ray) {
        Point3D P0 = ray.getPoint();   
        Vector v = ray.getVec();  

        Vector n = normal; 

        if(q0.equals(P0)){
            return  null;
        }

        Vector P0_Q0 = q0.subtract(P0); 

        double mechane = alignZero(n.dotProduct(P0_Q0)); 

        
        if (isZero(mechane)){
            return null;
        }

        //mone
        double nv = alignZero(n.dotProduct(v));  

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(mechane / nv);

        if (t <=0){
            return  null;
        }
        Point3D P = ray.getPoint(t);

        return List.of(P);
    }
	
	
	/**
	 * findGeoIntersections implemention
	 */
	  @Override
	    public List<GeoPoint> findGeoIntersections(Ray ray){
	        List<Point3D> intersectedPoint = findIntersectionsPoints(ray);
	        if(intersectedPoint != null){
	            GeoPoint geoPoint = new GeoPoint(this, intersectedPoint.get(0));
	            return List.of(geoPoint);
	        }
	        return null;
	    }
	
}

