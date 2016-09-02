package com.maldiny.spring.batch.flow.control;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/batch/jobs/job-config.xml",
    "classpath:spring/batch/config/test-context.xml"})
public class CustomTest {

    @Autowired
    @Qualifier( "jobLauncherTestUtilsSecuentialControlFlow" )
    private JobLauncherTestUtils jobLauncherTestUtilsSecuentialControlFlow;

    @Autowired
    @Qualifier( "jobLauncherTestUtilsConditionalControlFlow" )
    private JobLauncherTestUtils jobLauncherTestUtilsConditionalControlFlow;

    
    @Test
    public void launchJob() throws Exception {
    	
        JobExecution jobExecutionSecuential = jobLauncherTestUtilsSecuentialControlFlow.launchJob();
        
        assertEquals(BatchStatus.COMPLETED, jobExecutionSecuential.getStatus());
    	
        JobExecution jobExecutionConditional = jobLauncherTestUtilsConditionalControlFlow.launchJob();
        
        assertEquals(BatchStatus.COMPLETED, jobExecutionConditional.getStatus());
        
    }
}
