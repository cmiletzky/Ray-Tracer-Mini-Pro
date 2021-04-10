package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
 import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;
public class SphereTests {

    // ============ Equivalence Partitions Tests ==============
	// need check only one case
	@Test
	public void testGetNormal() {
		Sphere s = new Sphere(1, new Point3D(0,0,0));
		Vector ans = new Vector(1, 1, 1);
		assertTrue("wrong calculation",s.getNormal(new Point3D(1.0, 1.0, 1.0)).equals(ans));
		
	}

}
