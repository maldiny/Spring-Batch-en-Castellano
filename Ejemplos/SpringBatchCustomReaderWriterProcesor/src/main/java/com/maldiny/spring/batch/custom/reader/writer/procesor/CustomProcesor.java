package com.maldiny.spring.batch.custom.reader.writer.procesor;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcesor implements ItemProcessor<MyObject, MyObject> {

	@Override
	public MyObject process(MyObject arg0) throws Exception {
		
		arg0.setId(arg0.getId().toUpperCase());
		
		arg0.setName(arg0.getName().toUpperCase());
		
		return arg0;
	}

}
