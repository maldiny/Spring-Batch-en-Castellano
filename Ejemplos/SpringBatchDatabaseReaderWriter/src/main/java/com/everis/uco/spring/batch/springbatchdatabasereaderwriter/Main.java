package com.everis.uco.spring.batch.springbatchdatabasereaderwriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * Variar los valores del: 	beans.xml 		concurrencyLimit
	 * 							job-config.xml	commit-interval
	 * @param args
	 */
	public static void main(String[] args) {

		String[] springConfig = { "spring/batch/jobs/job-config.xml" };

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("databaseJob");

		try {
			long start = System.nanoTime(); 
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());
			long elapsedTime = System.nanoTime() - start;
			float elapsedTimeSec = elapsedTime/1000F;
			
			System.err.println("Time (seconds): " + elapsedTimeSec);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}

}
