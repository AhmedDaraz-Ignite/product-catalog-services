package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.common.Constants;
import com.example.common.beanmapping.BeanMapperUtil;
import com.example.common.exception.ProductServiceException;
import com.example.endpoint.StatucCode;
import com.example.model.BaseEntity;
import com.example.view.BaseView;

import ma.glasnost.orika.MapperFacade;

/**
 * Provide default implementation for all services CRUD operations, all services should implement this as part of application framework.
 * @author Ahmed.Rabie
 *
 * @param <E>
 * @param <V>
 */
public abstract class BaseServiceImpl<E extends BaseEntity, V extends BaseView> implements BaseService<E, V> {

	protected abstract JpaRepository<E, Long> getRepository();

	/**
	 * Template method to get service view object name.
	 * @return
	 */
	protected abstract String getObjectName();

	/**
	 * template method to get view instance.
	 * @return
	 */
	protected abstract V getViewInstance();

	/**
	 * Template method to get entity instance.
	 * @return
	 */
	protected abstract E getEntityInstance();
	
	/**
	 * Associate bidirectional associations before entity creation.
	 * @param entity
	 */
	protected abstract void fixEntityAssociations(E entity);

	/**
	 * Get View by given Id.
	 */
	@Override
	public V get(long id) throws ProductServiceException {
		E entity;
		try {
			entity = getRepository().findOne(id);
		} catch (DataAccessException e) {
			throw new ProductServiceException(Constants.UNABLE_TO_GET_RESOURCE_, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), String.valueOf(id));
		}

		if (entity == null) {
			throw new ProductServiceException(Constants.UNABLE_TO_GET_RESOURCE_, StatucCode.RESOURCE_NOT_FOUND, getObjectName(), String.valueOf(id));
		}
		return convertToView(entity);
	}

	/**
	 * List all views
	 */
	@Override
	public List<V> getAll() {
		List<V> results = new ArrayList<>();
		
		try {
			List<E> entities = getRepository().findAll();
			convertToViews(results, entities);
		} catch (DataAccessException e) {
			throw new ProductServiceException(Constants.UNABLE_TO_LIST_RESOURCES, StatucCode.INTERNAL_SERVICE_ERROR, e);
		}
		return results;
	}

	/**
	 * Persist view into database. only users with Admin role can access this method.
	 */
	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public V create(V view) throws ProductServiceException {
		E entity = createEntity(view);
		fixEntityAssociations(entity);
		try {
			entity = getRepository().save(entity);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(Constants.UNABLE_TO_CREATE_RESOURCE, StatucCode.CANNOT_CREATE_RESOURCE, e, getObjectName(), entity.getClass().getName());
		}
		return convertToView(entity);
	}

	/**
	 * Update view in database. only users with Admin role can access this method.
	 */
	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public V update(V view) throws ProductServiceException {
		E foundEntity = getRepository().findOne(view.getId());
		if (foundEntity == null) {
			throw new ProductServiceException(Constants.UNABLE_TO_GET_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, getObjectName(), view.getId());
		}

		E entity = updateEntity(foundEntity, view);

		try {
			entity = getRepository().save(entity);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(Constants.UNABLE_TO_UPDATE_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), entity.getClass().getName());
		}

		return convertToView(entity);
	}

	/**
	 * Delete view from database. only users with Admin role can access this method.
	 */
	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(long id) throws ProductServiceException {
		try {
			getRepository().delete(id);
			getRepository().flush();
		} catch (DataAccessException e) {
			throw new ProductServiceException(Constants.UNABLE_TO_DELETE_RESOURCE, StatucCode.INTERNAL_SERVICE_ERROR, e, getObjectName(), id);
		}
	}

	/**
	 * Convert from entity to View object, it uses orika mapping java library.
	 * @param entity
	 * @return
	 */
	protected V convertToView(E entity) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		V view = getViewInstance();
		mapper.map(entity, view);
		return view;
	}

	/**
	 * Convert from List of entities to list of view objects, it uses orika mapping java library.
	 * @param results
	 * @param objects
	 */
	protected void convertToViews(List<V> results, List<E> objects) {
		for (E entity : objects) {
			V view = convertToView(entity);
			results.add(view);
		}
	}

	/**
	 * Convert from view to entity object, it uses orika mapping java library.
	 * @param view
	 * @return Entity
	 */
	protected E createEntity(V view) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		E entity = getEntityInstance();
		mapper.map(view, entity);
		return entity;
	}

	/**
	 * Update given objects state.
	 * @param entity
	 * @param view
	 * @return
	 */
	protected E updateEntity(E entity, V view) {
		MapperFacade mapper = BeanMapperUtil.getMapper();
		mapper.map(view, entity);
		return entity;
	}
	
}
