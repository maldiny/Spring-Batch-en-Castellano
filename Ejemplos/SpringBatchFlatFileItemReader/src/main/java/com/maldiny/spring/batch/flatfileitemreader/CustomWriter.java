package com.maldiny.spring.batch.flatfileitemreader;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomWriter implements ItemWriter<MyObject> {

	@Override
	public void write(List<? extends MyObject> arg0) throws Exception {

		System.out.println("CustomWriter >> " + arg0);
		
	}

}
