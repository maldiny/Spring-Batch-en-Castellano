package com.maldiny.spring.batch.springbatchdatabasereaderwriter;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcesor implements ItemProcessor<Users, Users> {

	@Override
	public Users process(Users arg0) throws Exception {
		arg0.setName(arg0.getName().toUpperCase());
		return arg0;
	}

}
