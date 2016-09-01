package com.maldiny.spring.batch.springbatchdatabasereaderwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomWriter implements ItemWriter<Users> {

	@Override
	public void write(List<? extends Users> arg0) throws Exception {

		System.out.println("CustomWriter >> " + arg0);
		
	}

}
