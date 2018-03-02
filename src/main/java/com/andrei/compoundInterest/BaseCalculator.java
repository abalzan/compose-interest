package com.andrei.compoundInterest;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.andrei.compoundInterest.domain.MarketLendMoney;

public abstract class BaseCalculator {
	
	public Double formatValue(Double value) {
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public Double getMontlyRepaiment(Integer value, Double compoundInterest, Integer months) {
		Double monthRepaymentValue = (value + compoundInterest) / months;
		return new BigDecimal(monthRepaymentValue.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	
	public Double getCompoundInterest(Integer value, Double rate, Integer months) {
		double compoundInterest = value * Math.pow(1.0 + rate/12, months) - value;
		return formatValue(compoundInterest);
	}

	public MarketLendMoney getLowestInterest(List<MarketLendMoney> lendersWithAvailableValue) {
		Collections.sort(lendersWithAvailableValue, Comparator.comparing(MarketLendMoney::getRate, (s1, s2) -> {
			return s1.compareTo(s2);
		}));
		return lendersWithAvailableValue.get(0);
	}

	public Integer validateValue(String stringValue) {

		try {
			if (stringValue == null) {
				throw new RuntimeException(ConstantUtils.VALUE_TO_BORROW);
			}
	
			Integer value = Integer.parseInt(stringValue);
	
			if (value <= 0) {
				throw new RuntimeException(ConstantUtils.VALUE_TO_BORROW_HIGHER_THAN_ZERO);
			}

			return value;
			
		}catch (NumberFormatException e) {
			throw new NumberFormatException(ConstantUtils.VALUE_TO_BORROW_NEED_TO_BE_NUMBER);
		}
	}

	public List<MarketLendMoney> getLendersWithAvailableValue(Integer value, List<MarketLendMoney> allLenders) {

		List<MarketLendMoney> lendersWithAvailableValue = new ArrayList<>();

		for (MarketLendMoney lender : allLenders) {
			if (lender.getAvailableAmount() >= value) {
				lendersWithAvailableValue
						.add(new MarketLendMoney(lender.getLender(), lender.getRate(), lender.getAvailableAmount()));
			}
		}

		return lendersWithAvailableValue;
	}

	public List<MarketLendMoney> getAllLenders(BufferedReader br) throws IOException {
		List<MarketLendMoney> allLenders = new ArrayList<>();

		String line;
		br.readLine(); // consume first line and ignore

		while ((line = br.readLine()) != null) {

			String[] marketData = line.split(ConstantUtils.QUOTE_SEPARATOR);

			allLenders.add(new MarketLendMoney(marketData[0], Double.parseDouble(marketData[1]),
					Integer.parseInt(marketData[2])));
		}

		return allLenders;
	}
}
