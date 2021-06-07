/**
 * 
 */
package renderer;

import elements.Camera;
import primitives.*;
import scene.Scene;

import java.awt.Color;
import java.util.Iterator;
import java.util.MissingResourceException;
/**
 * @author chaim & michael
 *
 */
public class Render {
	
	ImageWriter imageWriter = null;
	//(deleted according to task 7)Scene scene = null;
	Camera camera = null;
	RayTracerBase rayTracerBase = null;

	
	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}
	/**
	 * @param scene the scene to set
	 */
//	public Render setScene(Scene scene) {
//		this.scene = scene;
//		return this;
//
//	}
	/**
	 * @param camera the camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;

	}
	/**
	 * @param rayTracerBase the rayTracerBase to set
	 */
	public Render setRayTracerBase(RayTracerBase rayTracerBase) {
		this.rayTracerBase = rayTracerBase;
		return this;

	}
	/**
	 * @return the imageWriter
	 */
	public ImageWriter getImageWriter() {
		return imageWriter;
	}
//	/**
//	 * @return the scene
//	 */
//	public Scene getScene() {
//		return scene;
//	}
	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}
	/**
	 * @return the rayTracerBase
	 */
	public RayTracerBase getRayTracerBase() {
		return rayTracerBase;
	}

	
	
	
	
	/**
	 * use with all element to write the details into "one box"
	 */

	
	 public void renderImage() {
		 try {
			 if (imageWriter==null) {
				throw new MissingResourceException("missing resource", ImageWriter.class.getCanonicalName(), "");
			}
//			 if (scene==null) {
//					throw new MissingResourceException("missing resource", Scene.class.getCanonicalName(), "");
//				}
			 if (camera==null) {
					throw new MissingResourceException("missing resource", Camera.class.getCanonicalName(), "");
				}
			 if (rayTracerBase==null) {
					throw new MissingResourceException("missing resource", RayTracerBase.class.getCanonicalName(), "");
				}
			 // if all right ... rendering the image 
			 int Nx = imageWriter.getNx();
			 int Ny = imageWriter.getNy();
			 for (int i = 0; i < Ny; i++) {
				 for (int j = 0; j < Nx; j++) {
					
					 Ray ray = camera.constructRayThroughPixel(Nx, Ny, j, i);
					 primitives.Color pixelColor = rayTracerBase.traceRay(ray);
					 imageWriter.writePixel(j,i,pixelColor);
				}
			}
			
		} catch (MissingResourceException e) {
			throw new UnsupportedOperationException( e.getClassName() + " not initialized");
		}
	 }
	 
	 public void renderImage(int numberOfsampel) {
		 try {
			 if (imageWriter==null) {
				throw new MissingResourceException("missing resource", ImageWriter.class.getCanonicalName(), "");
			}
//			 if (scene==null) {
//					throw new MissingResourceException("missing resource", Scene.class.getCanonicalName(), "");
//				}
			 if (camera==null) {
					throw new MissingResourceException("missing resource", Camera.class.getCanonicalName(), "");
				}
			 if (rayTracerBase==null) {
					throw new MissingResourceException("missing resource", RayTracerBase.class.getCanonicalName(), "");
				}
			 // if all right ... rendering the image 
			 int Nx = imageWriter.getNx();
			 int Ny = imageWriter.getNy();
			 for (int i = 0; i < Ny; i++) {
				 for (int j = 0; j < Nx; j++) {
					 
					 
					 Ray ray1 = camera.constructRayThroughPixel(Nx, Ny, j, i);
					 primitives.Color pixelColor = rayTracerBase.traceRay(ray1);
					 double red =pixelColor.getColor().getRed();
					 double green =pixelColor.getColor().getGreen();
					 double blue =pixelColor.getColor().getBlue();
					 
					 
					for (int j2 = 0; j2 < numberOfsampel; j2++) {
						for (int k = 0; k < numberOfsampel; k++) {
							Ray ray = camera.constructRayThroughPixel(numberOfsampel, numberOfsampel, j2, k,numberOfsampel);
							 primitives.Color pixelColor1 = rayTracerBase.traceRay(ray);
							 red += pixelColor.getColor().getRed();
							 green+= pixelColor.getColor().getGreen();
							 blue+= pixelColor.getColor().getBlue();
							
						}
						
					}
					
					 primitives.Color pixelColor2 = new primitives.Color(avrageColor(red, green, blue, numberOfsampel));
					 imageWriter.writePixel(j,i,pixelColor2);
				}
			}
			
		} catch (MissingResourceException e) {
			throw new UnsupportedOperationException( e.getClassName() + " not initialized");
		}
	 }
	
	
	/**
	 * printing grid for work with it
	 * @param interval
	 * @param color 
	 */
	public void printGrid( int interval, primitives.Color color)
	{
		int Nx = imageWriter.getNx();
		int Ny = imageWriter.getNy();
		
		for (int i = 0; i < Ny; i++) {//TODO improovment by += interval
				for (int j = 0; j < Nx; j++) {
					if (i % interval == 0 || j % interval == 0) {
						imageWriter.writePixel(j, i, color);
					}
				}
		}

		
	}
	/**
	 *  Finally use with imagewriter to draw the image builded
	 */
	public void writeToImage() {
		imageWriter.writeToImage();
		
	}
	
	primitives.Color avrageColor (double red, double green, double blue, int numberOfsampel){
		double red1=  red/numberOfsampel;
		double green1= green/numberOfsampel;
		double blue1 =blue/numberOfsampel;
		primitives.Color pixelColor = new primitives.Color(red1,green1,blue1);
		return pixelColor;
	}
	
}
