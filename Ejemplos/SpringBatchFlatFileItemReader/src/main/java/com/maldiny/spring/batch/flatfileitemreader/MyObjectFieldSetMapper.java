package com.maldiny.spring.batch.flatfileitemreader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MyObjectFieldSetMapper implements FieldSetMapper<MyObject> {

	@Override
	public MyObject mapFieldSet(FieldSet fieldSet) throws BindException {
		MyObject myObject = new MyObject("" + fieldSet.readInt(0), fieldSet.readString(1));
		return myObject;
	}

}