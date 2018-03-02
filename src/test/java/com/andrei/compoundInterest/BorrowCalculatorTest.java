package com.andrei.compoundInterest;

import org.junit.Test;

public class BorrowCalculatorTest {

	@Test(expected = RuntimeException.class)
	public void testValueNotDerivedFrom100() throws Exception {
		String csvFile = "src/test/resouces/MarketData.csv";
		String stringValue = "1001";
		String args[] = {csvFile, stringValue};

		BorrowCalculator.main(args);
	}	
	
	@Test(expected = RuntimeException.class)
	public void testNoLendersWithAvaliableValue() throws Exception {
		String csvFile = "src/test/resouces/MarketData.csv";
		String stringValue = "10000";
		String args[] = {csvFile, stringValue};

		BorrowCalculator.main(args);
	}	
}
