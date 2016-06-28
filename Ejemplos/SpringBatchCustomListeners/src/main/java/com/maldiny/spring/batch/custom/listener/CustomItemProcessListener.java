package com.maldiny.spring.batch.custom.listener;

import org.springframework.batch.core.ItemProcessListener;

import com.maldiny.spring.batch.custom.MyObject;

public class CustomItemProcessListener implements ItemProcessListener<MyObject, MyObject> {

	@Override
	public void afterProcess(MyObject arg0, MyObject arg1) {
		
		System.out.println(">> >> >> CustomItemProcessListener >> After process the element: " + arg0);
	}

	@Override
	public void beforeProcess(MyObject arg0) {
		
		System.out.println(">> >> >> CustomItemProcessListener >> Before process the element: " + arg0);
		
	}

	@Override
	public void onProcessError(MyObject arg0, Exception arg1) {
		
		System.out.println(">> >> >> CustomItemProcessListener >> Error processing the element: " + arg0);
		
	}

}
