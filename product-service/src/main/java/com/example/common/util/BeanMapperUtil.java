package com.example.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Utility class to define the needed mappers, it leverage orika mapping library to provide dynamic object mapping.  
 * @author Ahmed.Rabie
 *
 */
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
