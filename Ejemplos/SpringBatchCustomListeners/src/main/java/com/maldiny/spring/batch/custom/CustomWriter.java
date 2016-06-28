package com.maldiny.spring.batch.custom;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.maldiny.spring.batch.custom.listener.CustomSkipableException;

public class CustomWriter implements ItemWriter<MyObject> {

	@Override
	public void write(List<? extends MyObject> arg0) throws Exception {

		for (MyObject myObject : arg0) {
			if(myObject.getId().contains("5")){
				throw new CustomSkipableException();
			}
		}

		System.err.println(">> >> >> >> >> CustomWriter >> " + arg0);
		
	}

}
