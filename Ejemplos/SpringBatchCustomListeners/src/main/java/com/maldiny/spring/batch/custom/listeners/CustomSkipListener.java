package com.maldiny.spring.batch.custom.listeners;

import org.springframework.batch.core.SkipListener;

import com.maldiny.spring.batch.custom.MyObject;

public class CustomSkipListener implements SkipListener<MyObject, MyObject> {

	@Override
	public void onSkipInProcess(MyObject arg0, Throwable arg1) {
		 
		System.err.println("*************** CustomSkipListener >> Skip in process: " + arg0);
		
	}

	@Override
	public void onSkipInRead(Throwable arg0) {

		System.err.println("*************** CustomSkipListener >> Skip in read: " + arg0);
		
	}

	@Override
	public void onSkipInWrite(MyObject arg0, Throwable arg1) {
		
		System.err.println("*************** CustomSkipListener >> Skip in write: " + arg0);
		
	}

}
