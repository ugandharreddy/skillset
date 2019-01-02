package com.attra.pms.util;

public class Domain implements ICalculator {

	@Override
	public int domainProficiency(int a, int b) {
		return a + b;
	}
	@Override
	public int empSkillUpdate(int a, int b) {
		return a - b;
	}
	@Override
	public int multiSkill(int a, int b) {
		return a * b;
	}
	@Override
	public int searchresults(int a, int b) throws Exception {
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
