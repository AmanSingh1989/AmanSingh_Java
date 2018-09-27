package com.abc.generalledger.app.service.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.abc.generalledger.app.ApplicationConfig;
import com.abc.generalledger.app.service.DailyPositionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
public class DailyPositionServiceTest {
	
	@Autowired
	DailyPositionService dailyPositionService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testGetDailyPostionFromCSVWithActualFile() {
		assertTrue(dailyPositionService.getDailyPostionFromCSV("Input_StartOfDay_Positions.txt").keySet().size()>0);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetDailyPostionFromCSVWithInvalidFilePath() {
		dailyPositionService.getDailyPostionFromCSV("Input_StartOfDay_Positionsss.txt");	
		}
	
	@Test(expected = RuntimeException.class)
	public void testGetDailyPostionFromCSVWithInvalidHeader() {
		dailyPositionService.getDailyPostionFromCSV("Input_StartOfDay_Position_invalid_header.txt");
	}
	

	

}
