package com.abc.generalledger.app.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CSVWriter {

	public void write(String filename, String line) {
		FileWriter fileWriter = null;
		File file = null;
		BufferedWriter output = null;
		try {
			file = ResourceUtils.getFile("classpath:" + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWriter = new FileWriter(file, true);
			output = new BufferedWriter(fileWriter);
			output.write(line);
		} catch (IOException e) {
			throw new RuntimeException("End of the day file write/open failed " + e.getMessage());
		} finally {
			try {
				if (output != null) {

					output.close();

				}
				
			} catch (IOException e) {
				throw new RuntimeException("End of the day file close failed " + e.getMessage());
			}
		}
	}

}
