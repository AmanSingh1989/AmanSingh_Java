package com.abc.generalledger.app.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.abc.generalledger.app.entity.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService {

	private final static ObjectMapper mapper = new ObjectMapper();

	public Map<String, Integer> getDailyTransactions(String fileName) {
		List<Transaction> obj;
		try {
			obj = mapper.readValue(ResourceUtils.getFile("classpath:"+fileName), new TypeReference<List<Transaction>>() {
			});
		} catch (IOException e) {
			throw new RuntimeException("Daily transaction File reading failed :" + e.getMessage());
		}

		Map<String, Integer> transactionMap = new HashMap<String, Integer>();
		for (Transaction transcation : obj) {
			int quantity = transcation.getTransactionQuantity();
			String key = transcation.getInstrument() + "_" + transcation.getTransactionType();
			if (transactionMap.containsKey(key)) {
				quantity += transactionMap.get(key);
			}

			transactionMap.put(key, quantity);
		}
		return transactionMap;

	}
}
