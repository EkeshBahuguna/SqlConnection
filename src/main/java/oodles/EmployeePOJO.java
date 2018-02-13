package oodles;

public class EmployeePOJO {
	public EmployeePOJO(){
		
	}
	String empName;
	String empDept;
	boolean isWorking;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public boolean isWorking() {
		return isWorking;
	}
	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	public String toString(){
		return "Hi"+empName+"Your Dept is:"+empDept;
	}
	

}
