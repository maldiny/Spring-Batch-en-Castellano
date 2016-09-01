package com.maldiny.spring.batch.chunk;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomReader implements ItemReader<MyObject> {
	
	private AtomicInteger counter = new AtomicInteger();

	@Override
	public MyObject read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		final MyObject item = new MyObject("id " + counter, "Name " + counter);
		
		// The step ends when the reader returns null.
		if(counter.getAndIncrement() > 1000){
			return null;
		}
		
		return item;
		
	}

}
