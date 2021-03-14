package primitives;

public class Vector {

	//private double size; // I added it because it seems basic to me
	//private double angel; // I added it because it seems basic to me - i didn't have time to finish the auto calc inside the contructors
	private Point3D head; 

	/**
	 * getters
	 * @return
	 */
	public Point3D getHead() {
		return head;
	}


	//	public Vector(double p, double angel) {
//		this.size = p;
//		this.angel = angel;
//	}
	/**
	 * contstructors
	 * @param x1
	 * @param y1
	 * @param z1
	 */
	public Vector(double x1, double y1, double z1) {
		Point3D p2  = new Point3D(x1, y1, z1);
		//System.out.println(this.toString());
		if(p2.equals(Point3D.ZERO)) throw new IllegalArgumentException("you can't add 0!!"); // in case of zero vector the progrem will throw IllegalArgumentException
		//if(x1==0&&y1==0&&z1==0) throw new IllegalArgumentException("you can't add 0!!"); // in case of zero vector the progrem will throw IllegalArgumentException
		head = new Point3D(x1,y1,z1);
		
	//	size = Math.sqrt((this.x)*(this.x)+(this.y)*(this.y)+(this.z)*(this.z));

	}
	
	public Vector(Coordinate x1, Coordinate y1, Coordinate z1) {
		
		Point3D p2= new Point3D(x1,y1,z1);
		
		//if(p2.equals(Point3D.ZERO) ) throw new IllegalArgumentException("you can't add 0!!"); // in case of zero vector the progrem will throw IllegalArgumentException
		if(x1.coord==0&&y1.coord==0&&z1.coord==0) throw new IllegalArgumentException("you can't add 0!!"); // in case of zero vector the progrem will throw IllegalArgumentException
 		
		head = new Point3D(x1,y1,z1);
		}


	public Vector(Point3D p) {
		if(p.equals(Point3D.ZERO)) throw new IllegalArgumentException("you can't add 0!!");// in case of zero vector the progrem will throw IllegalArgumentException

		head = new Point3D(p.x,p.y,p.z);
	
	//	size = Math.sqrt((this.x)*(this.x)+(this.y)*(this.y)+(this.z)*(this.z));
		
	}
//	
	/**
	 * 
	 * @param v1
	 * @return this vector subtract v1 vector
	 */
	public Vector subtract(Vector v1) {
		double xx=  head.x.coord-v1.getHead().x.coord;
		double yy = head.y.coord-v1.getHead().y.coord;
		double zz = head.z.coord-v1.getHead().z.coord;;
		
		return new Vector(xx, yy, zz);
	}
	
	/**
	 * 
	 * @param v1
	 * @return this vector plus v1 vector
	 */
	 
	public Vector add(Vector v1) {
		double xx=head.x.coord+v1.getHead().x.coord;
		double yy = head.y.coord+v1.getHead().y.coord;
		double zz = head.z.coord+v1.getHead().z.coord;
		
		return new Vector(xx, yy, zz);
	}
	
	/**
	 * 
	 * @param num
	 * @return this vector mul scalar
	 */
	
	public Vector scale (double num) {
		double xx = head.x.coord*num;
		double yy = head.y.coord*num;
		double zz = head.z.coord*num;
		
		return new Vector(xx,yy,zz);

	}
	/**
	 * 
	 * @param v
	 * @return this vector dot v vector
	 * 
	 * https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
	 */
	
	
	public double dotProduct(Vector b) 
    { 
  
        double product = 0; 
  
            product = head.x.coord * b.getHead().x.coord; 
            product += head.y.coord * b.getHead().y.coord; 
            product += head.z.coord * b.getHead().z.coord; 

            
        return product; 
    } 
	/**
	 * 
	 * @param v
	 * @return vector mul between this vector to v vector
	 * i used this link help: https://www.geeksforgeeks.org/program-dot-product-cross-product-two-vector/
	 */
	
	public Vector crossProduct(Vector v) { // 
	  
		    double xx = head.y.coord * v.getHead().z.coord - head.z.coord * v.getHead().y.coord; 
		    double yy = head.z.coord * v.getHead().x.coord - head.x.coord * v.getHead().z.coord; 
		    double zz = head.x.coord * v.getHead().y.coord - head.y.coord * v.getHead().x.coord; 
		    
		    return new Vector (xx,yy,zz);
		
		  

	}

	
	
	/**
	 * 
	 * @return length Squared of this vector
	 */
	public double lengthSquared() {
		 return ((head.x.coord)*(head.x.coord)+(head.y.coord)*(head.y.coord)+(head.z.coord)*(head.z.coord));		
	}

	/**
	 * 
	 * @return the size of this vector - i saw this function after i build the size parameter above
	 */
	public double length() {
	return Math.sqrt(lengthSquared());
	
	}
/**
 * normalize this vector
 */
public Vector normalize () {
		
	
		double length = length();

		double xx=this.getHead().x.coord/length;
		Coordinate xxx = new Coordinate(xx);
		
		double yy=this.getHead().y.coord/length;
		Coordinate yyy = new Coordinate(yy);
		
		double zz=this.getHead().z.coord/length;
		Coordinate zzz = new Coordinate(zz);
			  
		this.head= new Point3D(xxx,yyy,zzz);
		
	
	return this;
	}
	
	/**
	 * 
	 * @return new normalize vector by this vector
	 * 
	 */
	      public Vector normalized() {
	  		
	  		double length = length();
	  	  
	  		
	  		
	  		Vector v1 = new Vector(this.getHead());
	  		
	  		v1.normalize();
	  		
	  		return v1;
	      }

	      @Override
	      public boolean equals(Object v1) {
	    	  if (this==v1) return true; // check the reference first
	    	  
	    	  if (!(v1 instanceof Vector)) return false;  //test for: is p1 is Vector type?
			
	    	  Vector v = (Vector) v1;  // cast v1 to vector after the test above
	    	  return(head.equals(v.head));
	      }
	      /**
	       * to string function
	       */
	    
	@Override
	public String toString() {
		return "Vector [p1=" + head.toString() + "]";
	}

	public static boolean isZero(double num) {
		
		return num==0;
	}
	      
}