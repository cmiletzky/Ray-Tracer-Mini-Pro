package tests.render;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * 
 * @author חייקי
 *
 */
public class ImageWriterTest {

	@Test
	public void testWriteToimage() {
		ImageWriter imagewrite= new ImageWriter("TestBlue", 800, 500);
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				
				if (i%50 == 0) {
					imagewrite.writePixel(i, j, Color.BLACK);
				}
				else if (j%50 == 0) {
					imagewrite.writePixel(i, j, Color.BLACK);
				}
				else
					imagewrite.writePixel(i, j, new Color(0, 0, 255));
				
			}
			
		}
		
		imagewrite.writeToImage();
	}
	
}
