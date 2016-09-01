package com.maldiny.spring.batch.scopes;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {
	
	private String jobParameterExample;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO: Custom business task
		
		System.out.println("Executing job with parameter jobParameterExample=" + this.jobParameterExample);
		
		String otherWay = (String) arg1.getStepContext().getJobParameters().get("customStepScopeParameter");
		
		System.out.println("Executing job with parameter from StepContext -> jobParameterExample=" + otherWay);
		
		return RepeatStatus.FINISHED;
	}

	public String getJobParameterExample() {
		return jobParameterExample;
	}

	public void setJobParameterExample(String jobParameterExample) {
		this.jobParameterExample = jobParameterExample;
	}

}
