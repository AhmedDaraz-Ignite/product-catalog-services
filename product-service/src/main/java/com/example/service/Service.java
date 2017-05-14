package com.example.service;

import java.util.List;

import com.example.exception.ProductServiceException;
import com.example.model.BaseEntity;
import com.example.view.BaseView;

/**
 * Main service interface for application framework.
 * @author Ahmed.Rabie
 *
 * @param <E extends BaseEntity>
 * @param <V extends BaseView>
 */
public interface Service<E extends BaseEntity, V extends BaseView> {
	
	V get(long id) throws ProductServiceException;

	List<V> getAll() throws ProductServiceException;

	V create(V v) throws ProductServiceException;

	V update(V v) throws ProductServiceException;

	void delete(long id) throws ProductServiceException;
}
