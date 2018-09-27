package com.abc.generalledger.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.abc.generalledger.app.entity.DailyPosition;

@Service
public class CSVReaderService {

	public static final String delimiter = ",";

	public List<List<String>> read(String csvFile) {
		List<List<String>> lines = new ArrayList<List<String>>();
		try {
			//File file = new File(getClass().getResource(csvFile).getFile());
			File file = ResourceUtils.getFile("classpath:"+csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "";

			List<String> tempArr = new ArrayList<String>();
			while ((line = br.readLine()) != null) {

				tempArr = Arrays.asList(line.split(delimiter));
				lines.add(tempArr);

			}
			br.close();

		} catch (IOException ex) {
			throw new RuntimeException("Daily Input File reading failed " + ex.getMessage());
		}
		return lines;
	}

}
