package com.andrei.compoundInterest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.andrei.compoundInterest.BorrowCalculator;
import com.andrei.compoundInterest.domain.MarketLendMoney;

public class BorrowCalculatorIT {
	
	private BorrowCalculator borrowCalculator;
	
	@Before
	public void setUp() {
		borrowCalculator = new BorrowCalculator();
	}

	@Test
	public void mainTest() throws IOException {
		String csvFile = "src/test/resouces/MarketData.csv";
		String stringValue = "1000";
		String args[] = {csvFile, stringValue};
		
		Integer expectedValue = Integer.parseInt(stringValue);
		
		Double expectedCompoundInterest = 232.93d;
		Double expectedLowestInterest = 7.0d;
		
		BorrowCalculator.main(args);
		
		Integer value = borrowCalculator.validateValue(stringValue);
		Assert.assertEquals(expectedValue, value);
		
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		List<MarketLendMoney> allLenders = borrowCalculator.getAllLenders(br);
		Assert.assertEquals(8, allLenders.size());
		
		List<MarketLendMoney> lendersWithAvailableValue = borrowCalculator.getLendersWithAvailableValue(Integer.parseInt(stringValue), allLenders);
		Assert.assertEquals(1, lendersWithAvailableValue.size());
		
		MarketLendMoney lowestInterest = borrowCalculator.getLowestInterest(lendersWithAvailableValue);
		Double interest = new BigDecimal(lowestInterest.getRate()*100).setScale(2, RoundingMode.HALF_UP).doubleValue();
		Assert.assertEquals(expectedLowestInterest, interest);
		
		Double compoundInterest = borrowCalculator.getCompoundInterest(Integer.parseInt(stringValue), 0.070, 36);
		Assert.assertEquals(expectedCompoundInterest, compoundInterest);
	
	}	

}
