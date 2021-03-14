package task0;


public class HourlyEmployee extends Employee{

	private int hours;
	private float wage;

	/**
	 * constructor
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param hours
	 * @param wage
	 */
	
	public HourlyEmployee(String firstName, String lastName, int id, int hours, float wage) {
		super(firstName, lastName, id);
		if(hours<0 || wage<=0)throw new IllegalArgumentException("wrong input!!");//input check
		else{
			this.hours = hours;
			this.wage = wage;

		}
	}
	
	/**
	 * default const
	 */
	public HourlyEmployee() {
		super();
		this.hours =0;
		this.wage = 0;
	}

	/**
	 * getters and setters
	 * @return
	 */
	public int getHours() {
		if(hours<0 || wage<=0)throw new IllegalArgumentException("wrong input!!");//input check

		return hours;
	}

	public void setHours(int hours1) {
		if(hours1<0)throw new IllegalArgumentException("wrong input!!"); //input check
		else
		this.hours = hours1;
	}

	public float getWage() {
		return wage;
	}

	public void setWage(float wage1) {
		if(wage1<=0)throw new IllegalArgumentException("wrong input!!");//input check
		else
		this.wage = wage1;
	}

	/**
	 * to string func
	 */
	@Override
	public String toString() {
		return super.toString() +"\nHourlyEmployee [hours=" + hours + ", wage=" + wage + "]";
	}
	
	/**
	 * equals func
	 */
	
	public boolean equals(HourlyEmployee e1) {
		return (super.equals(e1)&&e1.hours==hours&&e1.wage==wage);		
	}
	
	/**
	 * returns the salary of the week for the worker
	 */
	@Override
	public double earnings() {
		
		return wage*hours;
	}

}
