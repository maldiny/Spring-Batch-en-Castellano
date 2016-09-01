package com.maldiny.spring.batch.custom.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepExecutionListener implements StepExecutionListener {

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		
		System.out.println(">> CustomStepExecutionListener >> Ending the step.");
		
		return null;
		
	}

	@Override
	public void beforeStep(StepExecution arg0) {

		System.out.println(">> CustomStepExecutionListener >> Starting the step.");
		
	}

}
