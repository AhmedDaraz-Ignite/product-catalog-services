package com.example.beanmapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public final class BeanMapperUtil {
	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();

	private BeanMapperUtil() {
	}

	public static MapperFactory getMapperFactory() {
		return mapperFactory;
	}
	
	public static MapperFacade getMapper() {
		return mapperFactory.getMapperFacade();
	}
}
