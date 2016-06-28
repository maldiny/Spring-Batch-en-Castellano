package com.maldiny.spring.batch.custom.listener;

import org.springframework.batch.core.ItemReadListener;

import com.maldiny.spring.batch.custom.MyObject;

public class CustomItemReadListener implements ItemReadListener<MyObject> {

	@Override
	public void afterRead(MyObject arg0) {

		System.out.println(">> >> CustomItemReadListener >> After read the element: " + arg0);
		
	}

	@Override
	public void beforeRead() {

		System.out.println(">> >> CustomItemReadListener >> Before read an element.");
		
	}

	@Override
	public void onReadError(Exception arg0) {

		System.out.println(">> >> CustomItemReadListener >> onReadError: " + arg0);
		
	}

}
