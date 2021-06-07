/**
 * 
 */
package tests.render;

import java.util.Random;

import org.junit.Test;

import elements.*;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere(50, new Point3D(0, 0, -50)) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setNShininess(100).setKt(0.3)),
				new Sphere(25, new Point3D(0, 0, -50)) //
						.setEmmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(400, new Point3D(-950, -900, -1000)) //
						.setEmmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setNShininess(20).setKt(0.5)),
				new Sphere(200, new Point3D(-950, -900, -1000)) //
						.setEmmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setNShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKr(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)), //
				new Sphere(30, new Point3D(60, 50, -50)) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	@Test
	public void trianglesTransparentSphere11() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(100, -100, -135), new Point3D(50, 50, -100)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-130, 137, -140), new Point3D(0, 0, -140)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)), //
				new Sphere(15, new Point3D(60, 50, -50)).setEmmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(30).setKt(0.7)),
				new Triangle(new Point3D(90, 50, -50), new Point3D(60, 80, -50), new Point3D(60, 50, -20))
						.setEmmission(new Color(java.awt.Color.pink))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)),
				new Triangle(new Point3D(60, 50, -20), new Point3D(200, 100, -50), new Point3D(30, 100, -70))
						.setEmmission(new Color(java.awt.Color.red))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new PointLight(new Color(300, 200, 300), new Point3D(60, 50, 0)));

		ImageWriter imageWriter = new ImageWriter("refractionShadow11", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	// ------------------------------------------------
	// ------------------------------------------------
//	@Test
//	public void proTests() {
//
//		Camera camera2 = new Camera(new Point3D(-400, -600, 800), new Vector(0, -1, 0), new Vector(0, 0, -1))
//				.setDistance(250).setViewPlaneSize(500, 500);
//
//
//		Scene scene = new Scene("last test").setAmbientLight(new AmbientLight(new Color(20, 20, 20), 1)) //
//				.setBackground(Color.BLACK);
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, -1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 1, 1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 1, 1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, -1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 1, 1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(1, 1, 1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(-1, 1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(-1, -1, -1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(-1, 1, 1)));
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(-1, 1, 1)));
////        scene.geometries
////        	.add(new Triangle(new Point3D(600,0,-400),new Point3D(-1400,0,-400),new Point3D(600,-1000,-2320))//0
////        		.setEmmission(new Color(0,0,0))
////        		.setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(100)),
////        		
////        		new Triangle(new Point3D(-1400,0,-400),new Point3D(600,-1000,-2320),new Point3D(-1400,-1000,-2320))//1
////        		.setEmmission(new Color(0,0,0))
////        		.setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(1000)),
////        		
////        		
////        		new Plane((new Point3D(670,225,-400)),new Vector(1,0,0))//2
////        		.setEmmission(new Color(20,20,20))
////        		.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.4)),
////        		
////        		new Plane((new Point3D(-1470,225,-400)),new Vector(1,0,0))//3
////        		.setEmmission(new Color(20,20,20))
////        		.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.4)),
////        		
////        		new Plane((new Point3D(-1400,225,-2390)),new Vector(0,0,1))//4
////        		.setEmmission(new Color(0,0,0))
////        		.setMaterial(new Material().setKd(0).setKs(0).setNShininess(100).setKr(0)));
//
//		for (int i = 0; i < 1; i++) {
//			for (int j = 0; j < 1; j++) {
//				scene.geometries.add(new Sphere(100, new Point3D(500 - i * 200, -(100 + j * 100), -(500 + j * 180)))
//						.setEmmission(new Color((double) (Math.random() * 100), (double) (Math.random() * 100),
//								(double) (Math.random() * 100)))
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(1000).setKt(0.2)));
//			}
//		}
//
//	@Test
//	public void proTests() {
//
//		Scene scene = new Scene("Test my pro");
//		Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//				.setViewPlaneSize(500, 500).setDistance(1200);
//		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
//
//		scene.geometries.add(new Plane(new Point3D(0,0,0), new Point3D(0, 1, 0), new Point3D(1, 0, 0))
//				.setMaterial(new Material().setKs(0.8).setNShininess(60)),//
////				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
////						.setMaterial(new Material().setKs(0.8).setNShininess(60)), //
////				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
////						.setMaterial(new Material().setKs(0.8).setNShininess(60)));
//				new Sphere(30, new Point3D(0, 0, 250)) //
//						.setEmmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(30)));
//
////		scene.lights.add( //
////				new SpotLight(new Color(700, 400, 400), new Point3D(-800, -800, 18000), new Vector(8, 8, -17)) //
////						.setKl(4E-4).setKq(2E-5));
//		scene.lights.add( //
//				new DirectionalLight(new Color(200,100, 100),  new Vector(-1, -1, -6)));
//		scene.lights.add( //
//				new DirectionalLight(new Color(200,100, 100),  new Vector(-6, -1, -1)));
//		scene.lights.add( //
//				new DirectionalLight(new Color(200,100, 100),  new Vector(-1, -1.5, -6)));
//		scene.lights.add( //
//				new DirectionalLight(new Color(200,100, 100),  new Vector(-1.5, -1, -6)));
//		
////		scene.lights.add( //
////				new PointLight(new Color(300, 240, 200), new Point3D(-100, -100, 200)).setKc(1d));
//
//		Render render = new Render(). //
//				setImageWriter(new ImageWriter("shadowSphereTriangleInitial_MyPro", 400, 400)) //
//				.setCamera(camera2) //
//				.setRayTracerBase(new RayTracerBasic(scene));
//		render.renderImage();
//		render.writeToImage();
//	}
	@Test
	public void proTests2() {

		Scene scene = new Scene("Test my proj");
		Camera camera2 = new Camera(new Point3D(-2, -400, 6), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
				.setViewPlaneSize(500, 500).setDistance(5000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.gray), 0.15));
		scene.lights.add( //
				new DirectionalLight(new Color(700, 200, 200), new Vector(7, -50, -7)));
//		scene.lights.add( //
//				new DirectionalLight(new Color(300,200, 200),  new Vector(-1, 1, -1)));
		scene.lights.add( //
				new SpotLight(new Color(400, 300, 200), new Point3D(-20, -20, 200), new Vector(16, 6, 0)));

		scene.geometries.add(new Plane(new Point3D(0, 0, 1), new Point3D(1, 1, 1), new Point3D(1, 0, 1))
				.setMaterial(new Material().setKs(0.8).setKd(0).setNShininess(60)).setEmmission(new Color(0, 0, 0)),
				new Plane(new Point3D(20, 0, 0), new Point3D(20, 1, 0), new Point3D(20, 1, 1))
						.setEmmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.2)),
//				new Plane(new Point3D(-20,0,0), new Point3D(-20, 1, 0), new Point3D(-20, 1, 1))
//				.setEmmission(new Color(0,0,500))
//				.setMaterial(new Material().setKs(0.8).setKd(0.5).setNShininess(60).setKt(0.8).setKr(0.4)),

				new Sphere(0.7, new Point3D(0, 0, 1.7)) //
						.setEmmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.8).setKs(0.5).setNShininess(30)),
				new Sphere(0.5, new Point3D(-2, 30, 1.5)) //
						.setEmmission(new Color(java.awt.Color.green)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(30)));

		scene.geometries
				.add(new Triangle(new Point3D(-50, 2500, 21), new Point3D(-30, 2500, 1), new Point3D(-70, 2500, 1))
						.setMaterial(new Material().setKd(0).setKs(0.7).setNShininess(0).setKr(0))
						.setEmmission(new Color(java.awt.Color.black)));
						

		for (int i = -300; i < 2500; i+=3) {
			scene.geometries.add(new Sphere(0.7, new Point3D(getRandomNumber(-1,1)*i, i, 1.7)) //
				.setEmmission(new Color(Math.random()*(i+300)/2, Math.random()*(i+300)/4, Math.random()*(i+300)/6)) //
				.setMaterial(new Material().setKd(0.8).setKs(0.5).setNShininess(30)));			
		}
		

		
		Render render = new Render(). //
				setImageWriter(new ImageWriter("shadowSphereTriangleInitial_MyPro2", 400, 400)) //
				.setCamera(camera2) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}
	
	public double getRandomNumber(double min, double max) {
	    return  ((Math.random() * (max - min)) + min);
	}
//	@Test
//	public void proTests3() {
//
//		Scene scene = new Scene("Test my proj3");
//		Camera camera2 = new Camera(new Point3D(-400, -600, 800), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
//				.setViewPlaneSize(500, 500).setDistance(250);
//		scene.setAmbientLight(new AmbientLight(new Color(20, 20, 20), 1)).setBackground(Color.BLACK);
//		scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 1, -1)));
//		scene.geometries.add(
//				new Triangle(new Point3D(600, 0, -400), new Point3D(-1400, 0, -400), new Point3D(600, -1000, -2320))
//						.setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(100)));
//
//		Render render = new Render() //
//				.setImageWriter(new ImageWriter("shadowSphereTriangleInitial_MyPro3", 500, 500)) //
//				.setCamera(camera2) //
//				.setRayTracerBase(new RayTracerBasic(scene));
//		render.renderImage();
//		render.writeToImage();
//
//	}
////                    Scene scene = new Scene();
////                    scene.setCamera(new Camera(new Point3D(-400,-600,800),new Vector(0,-1,0),new Vector(0,0,-1)));
////                    scene.setScreenDistance(250);
////                    scene.setBackground(Color.BLACK);
////                    scene.setAmbientLight(new AmbientLight(new Color(20,20,20),1));
////                    scene.addLight(new DirectionLight(new Color(200,200,200),new Vector(0,1,-1)));
////                    //   scene.addLight(new SpotLight(new Color(255,255,255),0.05,0.00005,0.000008,new Point3D(200,-400,0),new Vector(5,1,-1)));
////                    scene.addGeometry(new Triangle(new Color(0,0,0),new Material(0.2,0.2,100,0.0,0),new Point3D(600,0,-400),new Point3D(-1400,0,-400),new Point3D(600,-1000,-2320)));
////                    scene.addGeometry(new Triangle(new Color(0,0,0),new Material(0.2,0.2,1000,0.0,0),new Point3D(-1400,0,-400),new Point3D(600,-1000,-2320),new Point3D(-1400,-1000,-2320)));
////                    scene.addGeometry(new Plane(new Vector(1,0,0),new Point3D(670,225,-400),new Color(20,20,20),new Material(0.5,0.5,100,0.4,0),100));
////                    scene.addGeometry(new Plane(new Vector(1,0,0),new Point3D(-1470,225,-400),new Color(20,20,20),new Material(0.5,0.5,100,0.4,0),100));
////                    scene.addGeometry(new Plane(new Vector(0,0,1),new Point3D(-1400,225,-2390),new Color(0,0,0),new Material(0,0,100,0,0),100));
////                    for(int i=0;i<1;i++){
////                        for(int j=0;j<1;j++){
////                            Sphere sp = new Sphere(new Color((int)(Math.random()*100),(int)(Math.random()*100),(int)(Math.random()*100)),new Material(0.5,0.5,1000,0.2,0),100,new Point3D(500-i*200,-(100+j*100),-(500+j*180)));
////                            sp.setShininess(100);
////                            scene.addGeometry(sp);
////                        }
////                    }
////                    ImageWriter imw = new ImageWriter("IMG_001_Balls",500,500,500,500);
////                    Render rn = new Render(scene,imw);
////                    rn.renderImage();
////                    imw.writeToimage();
////                }
////            }
////        }
////        
//
//		ImageWriter imw = new ImageWriter("IMG_001_Balls", 500, 500);
//
//		Render render = new Render() //
//				.setImageWriter(imw) //
//				.setCamera(camera2) //
//				.setRayTracerBase(new RayTracerBasic(scene));
//		render.renderImage();
//		render.writeToImage();
//
//	}
}
