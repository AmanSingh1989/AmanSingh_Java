package com.abc.generalledger.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.abc.generalledger.app.service.DayEndPositionCalculationService;

public class Main {


	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DayEndPositionCalculationService dayEndPositionCalculationService=(DayEndPositionCalculationService) context.getBean("dayEndPositionCalculationService");
			dayEndPositionCalculationService.endOfTheDailyPositionCalculation();

		context.close();
	}

}