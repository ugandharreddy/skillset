package com.attra.pms.util;

public class EmployeeSalary implements EmployeeSearchDetails {

	@Override
	public int employeeMonthlysalary(int a, int b) {
		return a + b;
	}
	@Override
	public int employeeApprasial(int a, int b) {
		return a - b;
	}
	@Override
	public int empSkills(int a, int b) {
		return a * b;
	}
	@Override
	public int empcategory(int a, int b) throws Exception {
		if (b == 0) {
			throw new Exception("Divider can't be zero");
		}
		return a / b;
	}
	@Override
	public boolean equalIntegers(int a, int b) {
		boolean result = false;
		if (a == b) {
			result = true;
		}
		return result;
	}
		

}
