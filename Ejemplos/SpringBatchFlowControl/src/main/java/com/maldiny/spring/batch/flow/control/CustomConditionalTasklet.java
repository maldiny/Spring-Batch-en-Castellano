package com.maldiny.spring.batch.flow.control;

import java.util.Calendar;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomConditionalTasklet implements Tasklet {

	String taskletName;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

		Long time = Calendar.getInstance().getTimeInMillis();
		String exit;
		if(time % 2 == 0){
			exit = ExitStatus.FAILED.getExitCode().toString();
			arg0.setExitStatus(ExitStatus.FAILED);
		}else{
			exit = ExitStatus.COMPLETED.getExitCode().toString();
			arg0.setExitStatus(ExitStatus.COMPLETED);
		}
		
		System.out.println("Executing step with name " + taskletName + " and exitCode " + exit);
		
		return RepeatStatus.FINISHED;
	}

	public String getTaskletName() {
		return taskletName;
	}

	public void setTaskletName(String taskletName) {
		this.taskletName = taskletName;
	}

}
