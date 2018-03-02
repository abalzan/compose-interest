package com.andrei.compoundInterest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.andrei.compoundInterest.domain.ClientBorrowMoney;
import com.andrei.compoundInterest.domain.MarketLendMoney;

public class BorrowCalculator extends BaseCalculator {
  
	public static void main(String[] args) {
		BorrowCalculator bc = new BorrowCalculator();
		
		String csvFile = args[0];
		String stringValue = args[1];
		
		ClientBorrowMoney clientData = bc.processCompoundInterest(csvFile, stringValue);
		
		System.out.println("Compound Interest £" + clientData.getCompoundInterest());
		System.out.println("Requested Amount: £" + clientData.getRequestAmount());
		System.out.println("Rate: " + clientData.getRate()+ "%");
		System.out.println("Monthly Repayment: £" + clientData.getMonthRepayment());
		System.out.println("Total Repayment: £" + clientData.getTotalRepayment());
		
		
	}

	private ClientBorrowMoney processCompoundInterest(String csvFile, String stringValue) {
		
		ClientBorrowMoney clientBorrowMoney = new ClientBorrowMoney();
		
		Integer value = validateValue(stringValue);

		 if(value % 100 != 0) {
			 throw new RuntimeException(ConstantUtils.VALUE_NOT_DERIVED_FROM_100);
		 }

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			List<MarketLendMoney> allLenders = getAllLenders(br);

			List<MarketLendMoney> lendersWithAvailableValue = getLendersWithAvailableValue(value, allLenders);

			if (lendersWithAvailableValue.isEmpty()) {
				throw new RuntimeException(ConstantUtils.VALUE_TOO_HIGH);
			}

			MarketLendMoney winnerLender = getLowestInterest(lendersWithAvailableValue);

			Double compoundInterest = getCompoundInterest(value, winnerLender.getRate(), ConstantUtils.QUANTITY_MONTHS_REPAYMENT);

			clientBorrowMoney.setCompoundInterest(compoundInterest);
			clientBorrowMoney.setRequestAmount(value);
			clientBorrowMoney.setRate(formatValue(winnerLender.getRate() * 100));
			clientBorrowMoney.setMonthRepayment(getMontlyRepaiment(value, compoundInterest, ConstantUtils.QUANTITY_MONTHS_REPAYMENT));
			clientBorrowMoney.setTotalRepayment(value + compoundInterest);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return clientBorrowMoney;
	}	
}
