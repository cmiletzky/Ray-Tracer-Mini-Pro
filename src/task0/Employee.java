package task0;

public abstract class Employee {

	private String firstName;
	private String lastName;
	private int id;
	
	/**
	 * constructor
	 * @param firstName
	 * @param lastName
	 * @param id
	 */
	public Employee(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	/**
	 * default const
	 */
	public Employee() {
		firstName = "plony";
		lastName = "almony";
		id=0;
	}

	/**
	 * setters and getters
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * to string func
	 */
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + "]";
	}
	/**
	 * equals func
	 */
	public boolean equals(Employee e1) {
		return ((firstName.equals(e1.firstName))&&(lastName.equals(e1.lastName))&&(id==e1.id));
		
	}
	
	public abstract double earnings();
	

}
