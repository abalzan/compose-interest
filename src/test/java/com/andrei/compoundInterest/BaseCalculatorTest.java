package com.andrei.compoundInterest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.andrei.compoundInterest.domain.MarketLendMoney;

public class BaseCalculatorTest extends BaseCalculator{
	
	@Test
	public void testFormatValue() {
		Double expectedValue = 1.25D;
		Double value = new Double(1.25000000000000000001);
		Double formatedValue = formatValue(value);
		assertEquals(expectedValue, formatedValue);
	}

	@Test
	public void testGetMontlyRepaiment() {
		Double expectedValue = 34.25D;
		Double monthRepaymentValue = getMontlyRepaiment(1000, 232.93, 36);
		assertEquals(expectedValue, monthRepaymentValue);
		
		expectedValue = 312.03D;
		monthRepaymentValue = getMontlyRepaiment(10000, 1232.93, 36);
		assertEquals(expectedValue, monthRepaymentValue);	
	}

	@Test
	public void testGetCompoundInterest() {
		Double expectedValue = 232.93D;
		Double compoundInterest = getCompoundInterest(1000, 0.07, 36);
		assertEquals(expectedValue, compoundInterest);
		
		expectedValue = 102.32D;
		compoundInterest = getCompoundInterest(2000, 0.05, 12);
		assertEquals(expectedValue, compoundInterest);
	}
	
	@Test
	public void testGetLowestInterest() {
		Double expected = 0.03;
		List<MarketLendMoney> lendersWithAvailableValue = new ArrayList<>();
		lendersWithAvailableValue.add(new MarketLendMoney("test", 0.07, 1000));
		lendersWithAvailableValue.add(new MarketLendMoney("john", 0.10, 1300));
		lendersWithAvailableValue.add(new MarketLendMoney("doe", 0.06, 1100));
		lendersWithAvailableValue.add(new MarketLendMoney("johana", 0.08, 1600));
		lendersWithAvailableValue.add(new MarketLendMoney("snow", 0.05, 1800));
		lendersWithAvailableValue.add(new MarketLendMoney("rock", 0.03, 1900));
		
		MarketLendMoney lowestInterest = getLowestInterest(lendersWithAvailableValue);
		assertEquals(expected, lowestInterest.getRate());
	}

	@Test
	public void testValidateValue() {
		Integer expected = 1000;
		Integer value = validateValue("1000");
		
		assertEquals(expected, value);
	}
	@Test(expected = RuntimeException.class)
	public void testValidateValueNullStringValue() {
		validateValue(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateValueLowerThanZero() {
		validateValue("-1");
	}
	
	@Test(expected = RuntimeException.class)
	public void testValidateValueString() {
		validateValue("asd");
	}

	@Test
	public void testGetLendersWithAvailableValue() {
		List<MarketLendMoney> lendersWithAvailableValue = new ArrayList<>();
		lendersWithAvailableValue.add(new MarketLendMoney("test", 0.07, 1000));
		lendersWithAvailableValue.add(new MarketLendMoney("john", 0.10, 1300));
		lendersWithAvailableValue.add(new MarketLendMoney("doe", 0.06, 1100));
		lendersWithAvailableValue.add(new MarketLendMoney("johana", 0.08, 1600));
		lendersWithAvailableValue.add(new MarketLendMoney("snow", 0.05, 1800));
		lendersWithAvailableValue.add(new MarketLendMoney("rock", 0.03, 1900));
		
		List<MarketLendMoney> expectedLenders = getLendersWithAvailableValue(1600, lendersWithAvailableValue);
		assertEquals(3, expectedLenders.size());
	}

	@Test
	public void testGetAllLenders() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("src/test/resouces/MarketData.csv"));
		List<MarketLendMoney> allLenders = getAllLenders(br);
		
		assertEquals(8, allLenders.size());
		
	}

}
