package task0;

public class Payroll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee empArr[] = {new HourlyEmployee("michael", "garusi", 890809,90,100),
							new CommissionEmployee("nadav", "asr", 901229,10000,30),
							new BasePlusCommissionEmployee("zvi", "flu", 890328409, 10000,30,10000)};
		
		
		String output = "";
		
		
		for (int i = 0; i < empArr.length; i++) {
			output+= empArr[i].toString();
			
			if(empArr[i].getClass()==BasePlusCommissionEmployee.class)
				output+="\nearnings: "	+String.format("%.2f",empArr[i].earnings()*1.1);
			else
				output+="\nearnings: "	+String.format("%.2f", empArr[i].earnings()); //tow digits after the decimal point

			System.out.println(output);
			
			output = "";
			
		}
	}

}
