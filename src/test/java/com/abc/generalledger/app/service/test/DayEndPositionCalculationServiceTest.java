package com.abc.generalledger.app.service.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.abc.generalledger.app.ApplicationConfig;
import com.abc.generalledger.app.service.DailyPositionService;
import com.abc.generalledger.app.service.DayEndPositionCalculationService;

import junit.framework.TestCase;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
public class DayEndPositionCalculationServiceTest extends TestCase{
	
	@Autowired
	DayEndPositionCalculationService dayEndPositionCalculationService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateQuatityAccountTypeInternalForBuy() {
		assertEquals(-1200, dayEndPositionCalculationService.calculateQuatityForBuy(-200, 1000, "I"));
	}
	
	@Test
	public void testCalculateQuatityAccountTypeExternalForBuy() {
		assertEquals(800, dayEndPositionCalculationService.calculateQuatityForBuy(-200, 1000, "E"));
	}

	@Test
	public void testCalculateQuatityAccountTypeInternalForSell() {
		assertEquals(-1200, dayEndPositionCalculationService.calculateQuatityForBuy(-200, 1000, "I"));
	}
	
	

	@Test
	public void testCalculateQuatityAccountTypeExternalForSell() {
		assertEquals(800, dayEndPositionCalculationService.calculateQuatityForBuy(-200, 1000, "E"));
	}

	@Test
	public void testDeltaCalculation() {
		assertEquals(-1200, dayEndPositionCalculationService.deltaCalculation(-200, 1000));
	}

}
