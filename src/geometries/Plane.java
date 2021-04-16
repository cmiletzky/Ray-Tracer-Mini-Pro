package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

public class Plane implements Geometry {
	
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

	

	@Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getPoint();   //040
        Vector v = ray.getVec();    //010

        Vector n = normal;  //010

        if(q0.equals(P0)){
            return  null;
        }

        Vector P0_Q0 = q0.subtract(P0);  //200 -040 = 2 -4 0

        double mechane = alignZero(n.dotProduct(P0_Q0)); //010 . 2 -4 0 = -4

        //
        if (isZero(mechane)){
            return null;
        }

        //mone
        double nv = alignZero(n.dotProduct(v));  //010 . 010 = 1

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(mechane / nv); //-4

        if (t <=0){
            return  null;
        }
        Point3D P = ray.getPoint(t);

        return List.of(P);
    }
}

