package com.abc.generalledger.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.generalledger.app.entity.DailyPosition;

@Service
public class DailyPositionService {
	
	public static final List<String> CSV_HEADER= Arrays.asList(new String[]{"Instrument","Account",
			"AccountType","Quantity"});
	
	
	@Autowired
	CSVReader csvReader;
	
	public  Map<String, List<DailyPosition>> getDailyPostionFromCSV(String fileName) {
		
		List<List<String>> lines=csvReader.read(fileName);
		Map<String,List<DailyPosition>> dailyPositionMap=new HashMap<String,List<DailyPosition>>();
		
		if(!lines.isEmpty() && validateHeader(lines.get(0))) {
			
			for(int i=1;i<lines.size();i++) {
				List<DailyPosition> startOfTheDayList=new ArrayList<DailyPosition>();
				List<String> line= lines.get(i);
				String key= line.get(0);
				DailyPosition entity=new DailyPosition();
				
				entity.setInstrument(line.get(0));
				entity.setAccountNumber(Integer.valueOf(line.get(1)));
				entity.setAccountType(line.get(2));
				entity.setQuantity(Integer.valueOf(line.get(3)));
				
				startOfTheDayList.add(entity);
				
				if(dailyPositionMap.containsKey(key)) {
					startOfTheDayList.addAll(dailyPositionMap.get(key));
				}
				
				dailyPositionMap.put(key, startOfTheDayList);
			}
			
		}else {
			String errorMessage="";
			if(lines.isEmpty()) {
				errorMessage="File is empty";
			}else if(!validateHeader(lines.get(0))) {
				
				errorMessage="Invalid header";
			}
			throw new RuntimeException("CSV file reading failed reason :: "+errorMessage);
		}
		
		
		return dailyPositionMap;
	
		
	}
	
	
	
	public boolean validateHeader(List<String> header){
		return header.containsAll(CSV_HEADER);
	}


}
