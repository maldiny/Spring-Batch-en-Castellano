package com.maldiny.spring.batch.custom.listeners;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class CustomSkipPolicy implements SkipPolicy {

	private int skipLimit;
	
	@Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		if (t instanceof CustomSkipableException && skipCount <= skipLimit) {
            return true;
        } else {
            return false;
        }
	}

	public void setSkipLimit(int skipLimit) {
        this.skipLimit = skipLimit;
    }
}
