package com.maldiny.spring.batch.custom.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.maldiny.spring.batch.custom.MyObject;

public class CustomItemWriteListener implements ItemWriteListener<MyObject> {

	@Override
	public void afterWrite(List<? extends MyObject> arg0) {
		
		System.out.println(">> >> >> >> CustomItemWriteListener >> After write the element: " + arg0);
		
	}

	@Override
	public void beforeWrite(List<? extends MyObject> arg0) {
		
		System.out.println(">> >> >> >> CustomItemWriteListener >> Before write the element: " + arg0);
		
	}

	@Override
	public void onWriteError(Exception arg0, List<? extends MyObject> arg1) {
		
		StringBuilder elements = new StringBuilder();
		
		for (MyObject myObject : arg1) {
			elements.append(myObject).append(",");
		}
		
		System.out.println(">> >> >> >> CustomItemWriteListener >> Error writing the elements: " + elements + " with error:" + arg0);
		
	}

}
