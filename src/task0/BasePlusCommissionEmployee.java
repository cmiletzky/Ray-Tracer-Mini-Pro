package task0;

public class BasePlusCommissionEmployee extends CommissionEmployee {

	
	private float baseSalary;

	/**
	 * full const using the parents constructors
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param grossSales1
	 * @param commision1
	 * @param baseSalary1
	 */
	public BasePlusCommissionEmployee(String firstName, String lastName, int id, float grossSales1, int commision1,
			float baseSalary1) {
		super(firstName, lastName, id, grossSales1, commision1);
		if(baseSalary1<0)throw new IllegalArgumentException("wrong input!!"); //input check
		else
		this.baseSalary = baseSalary1;
	}
	/**
	 * default constructor
	 */
	public BasePlusCommissionEmployee() {
		super();
		this.baseSalary = 0;
	}
	/**
	 * getters and setters
	 * @return
	 */
	public float getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	/**
	 * to string function
	 */
	@Override
	public String toString() {
		return super.toString()+ " BasePlusCommissionEmployee [baseSalary=" + baseSalary + "]";
	}
	
	/**
	 * equals func
	 * @param e1
	 * @return
	 */
	
	public boolean equals(BasePlusCommissionEmployee e1) {
		return (super.equals(e1)&&e1.baseSalary==baseSalary);
	}
	
	/**
	 * salary - using the parent class
	 */
	@Override
	public double earnings() {
		return baseSalary+super.earnings();
	}
	
}
