package com.maldiny.spring.batch.scopes;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/batch/jobs/job-config.xml",
    "classpath:spring/batch/config/test-context.xml"})
public class CustomTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    
    @Test
    public void launchJob() throws Exception {

		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("customStepScopeParameter", new JobParameter("myCustomStepScopeParameterValue"));
		JobParameters jp = new JobParameters(parameters);
		
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jp);
        
        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        
    }
}
