package com.maldiny.spring.batch.parallel.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomNumberTasklet implements Tasklet {

	private Integer counter = 0;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO: Custom business task

		if(counter > 10){
			return RepeatStatus.FINISHED;
		}

		System.err.println(counter);
		
		counter++;
		
		return RepeatStatus.CONTINUABLE;
	}

}
