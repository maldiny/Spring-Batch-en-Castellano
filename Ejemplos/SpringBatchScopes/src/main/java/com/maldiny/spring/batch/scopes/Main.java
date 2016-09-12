package com.maldiny.spring.batch.scopes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		String[] springConfig = { "spring/batch/jobs/job-config.xml" };

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("scopesJob");
		
		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("customStepScopeParameter", new JobParameter("myCustomStepScopeParameterValue"));
		parameters.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters  jp = new JobParameters(parameters);
		
		try {
			JobExecution execution = jobLauncher.run(job, jp);
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

}
