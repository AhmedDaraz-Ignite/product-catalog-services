package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.beanmapping.BeanMapperUtil;
import com.example.endpoint.StatucCode;
import com.example.exception.ProductServiceException;
import com.example.model.BaseEntity;
import com.example.view.BaseView;

import ma.glasnost.orika.MapperFacade;

public abstract class BaseServiceImpl<E extends BaseEntity, V extends BaseView> implements BaseService<E, V> {

	private static final String UNABLE_TO_DELETE_RESOURCE = "Unable to delete resource {0} with parameter {1}";
	private static final String UNABLE_TO_UPDATE_RESOURCE = "Unable to update resource";
	private static final String UNABLE_TO_GET_RESOURCE = "Unable to get resource";
	private static final String UNABLE_TO_CREATE_RESOURCE = "Unable to create resource";
	private static final String UNABLE_TO_LIST_RESOURCES = "Unable to list resources";
	private static final String UNABLE_TO_GET_RESOURCE_ = "Unable to get resource {0} with parameter {1}";
	
	protected abstract JpaRepository<E, Long> getRepository();

	protected abstract String getObjectName();

	protected abstract V getViewInstance();

	protected abstract E getEntityInstance();

	@Override
	public V get(long id) throws ProductServiceException {
		E entity;
		try {
			entity = getRepository().findOne(id);
		} catch (DataAccessException e) {
			throw new ProductServiceException(UNABLE_TO_GET_RESOURCE_, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), String.valueOf(id));
		}

		if (entity == null) {
			throw new ProductServiceException(UNABLE_TO_GET_RESOURCE_, StatucCode.RESOURCE_NOT_FOUND, getObjectName(), String.valueOf(id));
		}
		return convertToView(entity);
	}

	@Override
	public List<V> getAll() {
		List<V> results = new ArrayList<>();
		
		try {
			List<E> entities = getRepository().findAll();
			convertToViews(results, entities);
		} catch (DataAccessException e) {
			throw new ProductServiceException(UNABLE_TO_LIST_RESOURCES, StatucCode.INTERNAL_SERVICE_ERROR, e);
		}
		return results;
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public V create(V view) throws ProductServiceException {
		E entity = createEntity(view);

		try {
			entity = getRepository().save(entity);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(UNABLE_TO_CREATE_RESOURCE, StatucCode.CANNOT_CREATE_RESOURCE, e, getObjectName(), entity.getClass().getName());
		}
		return convertToView(entity);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public V update(V view) throws ProductServiceException {
		E foundEntity = getRepository().findOne(view.getId());
		if (foundEntity == null) {
			throw new ProductServiceException(UNABLE_TO_GET_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, getObjectName(), view.getId());
		}

		E entity = updateEntity(foundEntity, view);

		try {
			entity = getRepository().save(entity);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(UNABLE_TO_UPDATE_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), entity.getClass().getName());
		}

		return convertToView(entity);
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(long id) throws ProductServiceException {
		try {
			getRepository().delete(id);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(UNABLE_TO_DELETE_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), id);
		}
	}

	protected V convertToView(E entity) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		V view = getViewInstance();
		mapper.map(entity, view);
		return view;
	}

	protected void convertToViews(List<V> results, List<E> objects) {
		for (E entity : objects) {
			V view = convertToView(entity);
			results.add(view);
		}
	}

	protected E createEntity(V view) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		E entity = getEntityInstance();
		mapper.map(view, entity);
		return entity;
	}

	protected E updateEntity(E entity, V view) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		mapper.map(view, entity);
		return entity;
	}
	
}
