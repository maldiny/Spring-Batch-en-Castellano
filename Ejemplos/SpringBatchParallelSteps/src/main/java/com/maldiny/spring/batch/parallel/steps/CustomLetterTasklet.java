package com.maldiny.spring.batch.parallel.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomLetterTasklet implements Tasklet {

	private Integer counter = 0;
	private char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO: Custom business task

		if(counter > 10){
			return RepeatStatus.FINISHED;
		}

		System.out.println(letters[counter]);
		
		counter++;
		
		return RepeatStatus.CONTINUABLE;
	}

}
