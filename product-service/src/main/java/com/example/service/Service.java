package com.example.service;

import java.util.List;

import com.example.exception.ServiceException;
import com.example.model.BaseEntity;
import com.example.view.BaseView;

public interface Service<E extends BaseEntity, V extends BaseView> {
	
	V get(long id) throws ServiceException;

	List<V> getAll() throws ServiceException;

	V create(V v) throws ServiceException;

	V update(V v) throws ServiceException;

	void delete(long id) throws ServiceException;
}
