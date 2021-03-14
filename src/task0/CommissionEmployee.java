package task0;

public class CommissionEmployee extends Employee {

	
	private float grossSales;
	private int commision;
	
	/**
	 * full const
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param grossSales
	 * @param commision
	 */
	public CommissionEmployee(String firstName, String lastName, int id, float grossSales1, int commision1) {
		super(firstName, lastName, id);
		if(grossSales1<0||commision1<=0)throw new IllegalArgumentException("wrong input!!"); //input check
		this.grossSales = grossSales1;
		this.commision = commision1;
	}
	/**
	 * default const
	 */
	public CommissionEmployee() {
		super();
		this.grossSales = 0;
		this.commision = 0;
	}
	
	/**
	 * setters and getters
	 * @return
	 */
	
	public float getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(float grossSales) {
		this.grossSales = grossSales;
	}
	public int getCommision() {
		return commision;
	}
	public void setCommision(int commision) {
		this.commision = commision;
	}
	
	/**
	 * equals func
	 */
	
	public boolean equals(CommissionEmployee e1) {
		return (super.equals(e1)&&e1.commision==commision&&e1.grossSales==grossSales);
	}
		
	/**
	 * salary calc
	 */
	@Override
			public double earnings() {
		double salary = commision*grossSales;
		salary= salary/100;
		return salary;
	}
	@Override
	public String toString() {
		return super.toString()+" CommissionEmployee [grossSales=" + grossSales + ", commision=" + commision + "]";
	}
	

}
