/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chaim & michael
 * This class represents the Camera as one of the elements to build the Scene
 *
 */
public class Camera {
	
	final private Point3D p0;
	final private Vector vUp;
	final private Vector vTo;
	final private Vector vRight;
	/**
     * width of view plane
     */
    private double width;
    /**
     * height of view plane
     */
    private double height;
    /**
     * distance btn camera & view plane
     */
    private double distance;
    //region constructor
    /**
     * Construct Camera in the 3D coordinate system
     * @param p0  position of Camera
     * @param vTo Vector in direction toward View Plane
     * @param vUP vector in up direction
     */
	
	public Camera(Point3D p0, Vector vTo, Vector vUp) {

	if (!isZero(vUp.dotProduct(vTo))) {
		
		throw new IllegalArgumentException("vUp and vTo must be orthogonal");
	  }
	this.p0=p0;
	this.vTo=vTo.normalized();
	this.vUp=vUp.normalized();
	this.vRight= vTo.crossProduct(vUp);
	
	}
	//endregion

/**
 * getters for fields 
 * @return
 */
	public Vector getvUp() {
		return vUp;
	}



	public Vector getvTo() {
		return vTo;
	}



	public Vector getvRight() {
		return vRight;
	} 
	
	
	 //region setters
    /**
     * borrowing from builder pattern
     * @param width  of View Plane
     * @param height of view plane
     * @return "this" Camera
     */
	public Camera setViewPlaneSize(double width,double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	 /**
     * borrowing from builder pattern
     *
     * @param distance between Camera and View Plane
     * @return this Camera
     */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}
	//endregion
	
	
	 //region simple getters
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public Point3D getP0() {
        return p0;
    }

    public Vector getVUP() {
        return vUp;
    }

    public Vector getVTo() {
        return vTo;
    }

    public Vector getVRight() {
        return vRight;
    }
    //endregion


    /**
     * construct Ray through (center) given Pixel[i,j]
     *
     * @param nX number of columns in the View Plane matrix
     * @param nY number of rows in the View Plane matrix
     * @param j  index of column
     * @param i  index of row
     * @return Ray(Point3D, Vector direction) that goes through Pixel[i,j]
     */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
		Point3D pij= findPIJ ( nX,  nY,  j,  i);

         
         Vector vij = pij.subtract(p0);
 		
 		return new Ray(p0, vij);
 		}
	
	
//	//----------------------------------------new------------
//	//----------------------------------------new------------
//	//----------------------------------------new------------
//	public Ray constructRayThroughPixel(int nX, int nY, int j, int i,int numOfSample){
//		
//		Point3D pij= findPIJ ( nX,  nY,  j,  i);
// 
//         Vector vij = pij.subtract(p0);
// 		
// 		return new Ray(p0, vij);
// 		}

	
	
	
	
	/**
     * util func that gets any pixel in view plane and returns location of any pixel's center
     *
     * @param nX number of columns in the View Plane matrix (or alternatively in the mini view plane - when using superSampling)
     * @param nY number of rows in the View Plane matrix (or alternatively in the mini view plane - when using superSampling)
     * @param j  index of column
     * @param i  index of row
     *  pc point in the center of the image (or alternatively in the mini view plane - when using superSampling)
     *  height of view plane (or alternatively in the mini view plane - when using superSampling)
     *  width of view plane (or alternatively in the mini view plane - when using superSampling)
     *  (Point3D) the location of pixel[i,j]'s center (or mini pixel's center)
     */
	Point3D findPIJ (int nX, int nY, int j, int i) {
        Point3D pc = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;

        Point3D pij = pc;

        double yI = - (i - (nY - 1) / 2d) * rY;
         double xJ = (j - (nX - 1) / 2d) * rX;

         
         if(!isZero(xJ)){
             pij = pij.add(vRight.scale(xJ));
         }
         if(!isZero(yI)){
             pij = pij.add(vUp.scale(yI)); 
         }
         return pij;
	}
	
	
	 /**
     * this method is for improvements of the pic using SSAA (super sampling anti aliasing)
     * methode to improve the picture
     * beam of rays is being shot through each pixel and the color of the rays are weighed
     * to calculate the average color which suitable to that pixel
     * the methode is to treat a pixel as a view plane and thus shoot rays through it
     * @param nX number of columns in the View Plane matrix
     * @param nY number of rows in the View Plane matrix
     * @param j index of column
     * @param i index of row
     * @param mini_Nx number of mini pixel in a pixel on x-axis
     * @param mini_Ny number of mini pixels in a pixel on y-axis
     * @return beam of rays (as a list) that being shot through a pixel
     */
    public List<Ray> constructBeamOfRaysThroughPixel(int nX, int nY, int j, int i, int mini_Nx, int mini_Ny) {
        // pixel is now as a mini-ViewPlane - pc is center of view plane
        Point3D pc = p0.add(vTo.scale(distance));
        // gets the center of the mini view-plane
        Point3D pc_ij = getPointInCenterOfPixel(nX, nY, j, i, pc, height, width);

        double height1 = height / nY; // height of mini-VP
        double width1 = width / nX; // width of mini-VP

        List<Ray> sampleRays = new LinkedList<>(); // stores here all rays of mini-pixels
        //goes through pixel's own mini-pixels
        for (int k = 0; k < mini_Ny; k++) {
            for (int l = 0; l < mini_Nx; l++) {
                // gets the center of mini pixel and later shoots ray through that point
                Point3D pkl = getPointInCenterOfPixel(mini_Ny, mini_Nx, l, k, pc_ij, height1, width1);
                sampleRays.add(new Ray(p0, pkl.subtract(p0)));
            }
        }
        return sampleRays;
    }

    /**
     * util func that gets any pixel in view plane and returns location of any pixel's center
     *
     * @param nX number of columns in the View Plane matrix (or alternatively in the mini view plane - when using superSampling)
     * @param nY number of rows in the View Plane matrix (or alternatively in the mini view plane - when using superSampling)
     * @param j  index of column
     * @param i  index of row
     * @param pc point in the center of the image (or alternatively in the mini view plane - when using superSampling)
     * @param height of view plane (or alternatively in the mini view plane - when using superSampling)
     * @param width of view plane (or alternatively in the mini view plane - when using superSampling)
     * @return (Point3D) the location of pixel[i,j]'s center (or mini pixel's center)
     */
    private Point3D getPointInCenterOfPixel(int nX, int nY, int j, int i, Point3D pc, double height, double width) {
        //  Pixel's height (alternatively mini pixel)
        double rY = height / nY;
        // Pixel's Width (alternatively mini pixel)
        double rX = width / nX;
        //Pij is for Point on a certain pixel
        Point3D pij = pc;
        //delta y
        double yI = -(i - (nY - 1) / 2d) * rY;
        // delta x
        double xJ = (j - (nX - 1) / 2d) * rX;
        // taking into account when Xj is zero in order to avoid scaling a vector in zero
        if (!isZero(xJ)) {
            pij = pij.add(vRight.scale(xJ));
        }
        // taking into account when Yi is zero in order to avoid scaling a vector in zero
        if (!isZero(yI)) {
            pij = pij.add(vUp.scale(yI));
        }
        return pij;
    }
    
    
	

         
         
 		
 		
 }


	


	



	




