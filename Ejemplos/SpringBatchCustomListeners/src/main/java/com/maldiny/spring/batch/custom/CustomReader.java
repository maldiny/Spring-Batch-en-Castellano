package com.maldiny.spring.batch.custom;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.maldiny.spring.batch.custom.listeners.CustomSkipableException;

public class CustomReader implements ItemReader<MyObject> {
	
	static int counter = 0;

	@Override
	public MyObject read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO: Reader business.
		
		final MyObject item = new MyObject("id " + counter, "Name " + counter);
		
		counter++;
		
		if(item.getId().contains("3")){
			throw new CustomSkipableException();
		}
		
		// The step ends when the reader returns null.
		if(counter > 10){
			return null;
		}
		
		return item;
		
	}

}
