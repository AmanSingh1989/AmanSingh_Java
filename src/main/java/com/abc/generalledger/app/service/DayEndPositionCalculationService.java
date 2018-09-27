package com.abc.generalledger.app.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.generalledger.app.entity.DailyPosition;

@Service
public class DayEndPositionCalculationService {

	@Autowired
	DailyPositionService dailyPositionService;

	@Autowired
	TransactionService transcationService;
	
	@Autowired
	CSVWriterService csvWriter;
	
	public static final String CSV_HEADER= "Instrument,Account,AccountType,Quantity,Delta";

	private static final String START_OF_THE_DAY_FILEPATH = "Input_StartOfDay_Positions.txt";
	private static final String TRANSCATION_FILEPATH = "1537277231233_Input_Transactions.json";

	public int calculateQuatityForBuy(int positionQuantity, int transactionQuantity, String transactionType) {

		int quantityAfterBuyCalculation = 0;

		if (transactionType != null && transactionType.equals("E")) {
			quantityAfterBuyCalculation = positionQuantity + transactionQuantity;
		}

		if (transactionType != null && transactionType.equals("I")) {
			quantityAfterBuyCalculation = positionQuantity - transactionQuantity;
		}
		return quantityAfterBuyCalculation;

	}

	public int calculateQuatityForSell(int transactionQuantityFromBuy, int transactionQuantity,
			String transactionType) {

		int quantityAfterSellCalculation = 0;

		if (transactionType != null && transactionType.equals("E")) {
			quantityAfterSellCalculation = transactionQuantityFromBuy - transactionQuantity;
		}

		if (transactionType != null && transactionType.equals("I")) {
			quantityAfterSellCalculation = transactionQuantityFromBuy + transactionQuantity;
		}
		return quantityAfterSellCalculation;

	}

	public int deltaCalculation(int quantityAfterSellCalculation, int positionQuantity) {
		return quantityAfterSellCalculation - positionQuantity;

	}

	public void endOfTheDailyPositionCalculation(){
		Map<String, List<DailyPosition>> dailyPositionMapAccountTypeWise = dailyPositionService
				.getDailyPostionFromCSV(START_OF_THE_DAY_FILEPATH);
		Map<String, Integer> dailyTransactionMap = transcationService.getDailyTransactions(TRANSCATION_FILEPATH);
		
		StringBuilder endOftheDayTranscationDetails=new StringBuilder(CSV_HEADER).append("\n");

		for (String instrument : dailyPositionMapAccountTypeWise.keySet()) {

			String accountTypeExternalKey = instrument + "_" + "B";
			String accountTypeInternalKey = instrument + "_" + "S";

			Integer dailyTransactionBuyQuantity = dailyTransactionMap.get(accountTypeExternalKey) == null ? 0
					: dailyTransactionMap.get(accountTypeExternalKey);
			Integer dailyTransactionSellQuantity = dailyTransactionMap.get(accountTypeInternalKey) == null ? 0
					: dailyTransactionMap.get(accountTypeInternalKey);

			

			
			DailyPosition dailyPositionExternal = null;
			DailyPosition dailyPositionInternal = null;
			
			List<DailyPosition> dailyPositions = dailyPositionMapAccountTypeWise.get(instrument);
			
			for (DailyPosition dailyPosition : dailyPositions) {

				if (dailyPosition.getAccountType().equals("I")) {
					dailyPositionInternal = compute(dailyTransactionBuyQuantity, dailyTransactionSellQuantity, dailyPosition);
				}

				if (dailyPosition.getAccountType().equals("E")) {
					dailyPositionExternal = compute(dailyTransactionBuyQuantity, dailyTransactionSellQuantity, dailyPosition);;
				}

			}
			endOftheDayTranscationDetails.append(dailyPositionInternal.toString()).append("\n");
			endOftheDayTranscationDetails.append(dailyPositionExternal.toString()).append("\n");

		}
		
		csvWriter.write("EndOftheDayPoistionCalculated.txt", endOftheDayTranscationDetails.toString());

	}
	
	
	private DailyPosition compute(Integer dailyTransactionBuyQuantity, Integer dailyTransactionSellQuantity,
			DailyPosition dailyPosition) {
		int positionAfterBuy = 0;
		int positionAfterSell = 0;
		int delta = 0;

		positionAfterBuy = this.calculateQuatityForBuy(dailyPosition.getQuantity(),
				dailyTransactionBuyQuantity.intValue(), dailyPosition.getAccountType());

		

		positionAfterSell = this.calculateQuatityForSell(positionAfterBuy,
				dailyTransactionSellQuantity.intValue(), dailyPosition.getAccountType());
		
		delta = this.deltaCalculation(positionAfterSell, dailyPosition.getQuantity());
		
		dailyPosition.setQuantity(positionAfterSell);
		dailyPosition.setDelta(delta);
		
		return dailyPosition;
		
	}
}
