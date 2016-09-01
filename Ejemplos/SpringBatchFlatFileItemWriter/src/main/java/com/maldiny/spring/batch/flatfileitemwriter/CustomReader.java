package com.maldiny.spring.batch.flatfileitemwriter;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomReader implements ItemReader<MyObject> {
	
	static int counter = 0;

	@Override
	public MyObject read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO: Reader business.
		
		final MyObject item = new MyObject("" + counter, "Name " + counter);
		
		counter++;
		
		// The step ends when the reader returns null.
		if(counter > 10){
			return null;
		}
		
		return item;
		
	}

}
