package com.maldiny.spring.batch.springbatchxmlreaderwriter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CustomConverter implements Converter {

	@Override
	public boolean canConvert(Class arg0) {
		return arg0.equals(MyObject.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		reader.moveDown();
		String id = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		String name = reader.getValue();
		reader.moveUp();
		
		MyObject object = new MyObject(id, name);
		
		return object;
	}

}
