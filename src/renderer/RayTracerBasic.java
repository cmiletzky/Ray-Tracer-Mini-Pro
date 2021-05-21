/**
 * 
 */
package renderer;

import java.util.Iterator;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import static primitives.Util.*;

/**
 * @author chaim & michael
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * intial the 'mekadem hanchata' at first its value is 1 
	 */
	private static final double INITIAL_K = 1.0;
	/**
	 * number of times to calculate reflection and trancperency
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * this is the limit of alowing to k- attenuation to get smaller
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;
	/**
	 * for wrongs in calculate ray start from surface of object
	 * we offset the point  
	 */
	private static final double DELTA = 0.1;

	public RayTracerBasic(Scene scene) {
		super(scene);

	}

	//private boolean unshaded(Vector l, Vector n, GeoPoint geopoint, LightSource lightSource)
	// TODO if there is problems
	// like complex scens
	// that it shaded inside kubiya for example,
	// consider to add argument to represent the point of position of light, and
	// check wether it realy need to shade
	// may be shaded object is behind the light source
	 private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	        Vector lightDirection = l.scale(-1); // from point to light source
	        Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move
	        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
	        if (intersections != null) {
	            double lightDistance = light.getDistance(geopoint.point);
	            for (GeoPoint intersection : intersections) {
	                if (alignZero(intersection.point.distance(geopoint.point) - lightDistance) <= 0 && intersection.geometry.getMaterial().kt == 0){
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	@Override
	 public Color traceRay(Ray ray) {
	        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
	        if (intersection != null) {
	            GeoPoint close = ray.findClosestGeoPoint(intersection);
	            return calcColor(close, ray);
	        }
	        return scene.background;
	    }
//	/**
//	 * paint every intersection point with appropriate color
//	 */
//	@Override
//	public Color traceRay(Ray ray) {
//		List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
//		if (intersection != null) {
//			GeoPoint close = ray.findClosestGeoPoint(intersection);
//			return calcColor(close, ray).add(close.geometry.getEmmission());
//		}
//		return scene.background;
//	}

	/**
	 * this function is responsible of painting in the appropriate color for a
	 * certain point of intersection btn shape and camera
	 * 
	 * @param gp geoPoint contains the shape in which the ray intersects and the
	 *           point of intersection
	 * @return appropriate color for painting the view plane
	 */
//	private Color calcColor(GeoPoint closestPoint, Ray ray) {
////	        return scene.ambientlight.getIntensity()
////	                .add(gp.geometry.getEmmission()
////	                        .add(calcLocalEffects(gp, ray)));
//		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientlight.getIntensity());
//	}

	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = intersection.geometry.getEmmission();
		color = color.add(calcLocalEffects(intersection, ray,k));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
	}
	/**
     * this function is responsible of painting in the appropriate color for a certain point of
     * intersection btn shape and camera
     * @param gp geoPoint contains the shape in which the ray intersects and the point of intersection
     * @return appropriate color for painting the view plane
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor( geoPoint, ray,  MAX_CALC_COLOR_LEVEL,  INITIAL_K).add(scene.ambientlight.getIntensity());
    }

	private Color calcLocalEffects(GeoPoint geopoint, Ray ray, double k) {
		// v is the ray from camera and is normalized
		Vector v = ray.getVec().normalized();
		//
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		double nv = alignZero(n.dotProduct(v));
		if (isZero(nv)) {
			return Color.BLACK;
		}
		Material material = geopoint.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD;
		double ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(geopoint.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				double ktr = transparency(lightSource, l, n, geopoint);
//				if (unshaded(l, n, geopoint, lightSource)) {
//
//					Color lightIntensity = lightSource.getIntensity(geopoint.point);
//					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
//							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
//				}
				 if (ktr * k > MIN_CALC_COLOR_K) {
	                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
	                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
	                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
	                }
			}
		}
		return color;

	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		// -2 * (l n) n
		double ab = alignZero((2) * l.dotProduct(n));
		Vector a = n.scale(ab);
		// l - a
		Vector r = l.subtract(a);
		double vr = alignZero(v.dotProduct(r));
		double minusVr = vr * (-1);
		return lightIntensity.scale(ks * Math.pow(Math.max(0, minusVr), nShininess));
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double factor = alignZero(l.dotProduct(n));
		return lightIntensity.scale(kd * Math.abs(factor));
	}

	/**
	 * calculate the idea of reflection and refraction
	 * 
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kr, kkr = k * kr;
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n,geopoint.point, ray);
			List<GeoPoint> geoList1 = scene.geometries.findGeoIntersections(reflectedRay);
			GeoPoint reflectedPoint = reflectedRay.findClosestGeoPoint(geoList1);
			if (geoList1 != null) {
				
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
			}
		}
//
		double kt = material.kt, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n,geopoint.point, ray);
			List<GeoPoint> geoList2 = scene.geometries.findGeoIntersections(refractedRay);
			GeoPoint refractedPoint = refractedRay.findClosestGeoPoint(geoList2);
			if (geoList2 != null) {
				
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));

			} 
		}

		return color;
	}

	/**
	 * construct Refracted Ray
	 * 
	 * @param geopoint
	 * @param ray
	 * @return the same direction original ray
	 */
//	private Ray constructRefractedRay(Vector n,GeoPoint geopoint, Ray ray) {
////		if (n.dotProduct(ray.getVec())<0) {
////			Point3D delta = geopoint.point.add(n.scale(-1));
////		}
//		Point3D delta = geopoint.point.add(n);
//		Ray ret = new Ray(delta, ray.getVec());
//		return ret ;
//	}
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getVec(), n);
    }

	/**
	 * construct Reflected Ray
	 * 
	 * @param geopoint
	 * @param ray
	 * @return
	 */
	private Ray constructReflectedRay(Vector n,Point3D point, Ray ray) {
		
//		// r = v - 2 * (v * n) * n
//		Vector v = ray.getVec();
//		if (isZero(v.dotProduct(normal))) {
//			return null;
//		}
//		Vector normalDelta = normal.scale(DELTA);
//		Vector r = v.subtract(normal.scale(2 * normal.dotProduct(v))).normalized();
//		Point3D delta=  point.add(normalDelta);
//				return new Ray(delta, r);
		 Vector v = ray.getVec();
	        Vector r = null;
	        try {
	            r = v.subtract(n.scale(v.dotProduct(n)).scale(2)).normalized();
	        } catch (Exception e) {
	            return null;
	        }
	        return new Ray(point, r, n);
	}
	
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kt;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

}
