package com.maldiny.spring.batch.chunk.context.params;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StepCTasklet implements Tasklet {

	String taskletName;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

		System.out.println("Executing step with name " + taskletName);

		String value1 = (String) arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("param1");
		MyObject myObject = (MyObject) arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("myObject");

		System.out.println("[" + taskletName + "] get param: " + value1);
		System.out.println("[" + taskletName + "] get param: " + myObject);
		
		return RepeatStatus.FINISHED;
	}

	public String getTaskletName() {
		return taskletName;
	}

	public void setTaskletName(String taskletName) {
		this.taskletName = taskletName;
	}

}
