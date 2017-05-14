package com.example.endpoint;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.endpoint.mapper.ServiceResponse;
import com.example.model.BaseEntity;
import com.example.service.Service;
import com.example.view.BaseView;


public abstract class BaseEndpoint<T extends BaseEntity, V extends BaseView> implements API<V>{
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseEndpoint.class);
	
	protected Service<T,V> baseService;
	
	public BaseEndpoint(Service<T,V> baseService) {
		this.baseService = baseService;
	}
	
	protected abstract String getEndpointName();
	
	protected abstract String getObjectName();
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ServiceResponse<V> get(@PathVariable("id") int id) {
		LOG.debug("{}.get called with id {}", getEndpointName(), id);
		V v = baseService.get(id);
		return ServiceResponse.buildGoodResponse(v);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ServiceResponse<List<V>> getAll() {
		LOG.debug("{}.getAll called", getEndpointName());
		List<V> views = baseService.getAll();
		return ServiceResponse.buildGoodResponse(views);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResponse<V>> create(@RequestBody V view) {
		LOG.debug("{}.create called", getEndpointName());
		
		V v = baseService.create(view);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(v.getId()).toUri();
		
		LOG.debug("Successfully created a new {}. It can be found at {}", getObjectName(), location);
		
		return ResponseEntity.status(HttpStatus.CREATED).location(location).body(ServiceResponse.buildSuccessfulCreateResponse(v));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ServiceResponse<Void> delete(@PathVariable("id") int id) {
		LOG.debug("{}.delete called with id {}", getEndpointName(), id);
		baseService.delete(id);
		return ServiceResponse.buildGoodResponse(null);
	}
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResponse<V>> update(@PathVariable("id") Long id, @RequestBody V view) {
		LOG.debug("{}.update called with id {}", getEndpointName(), id);
		
		validateUpdateRequest(id, view);
		
		V v = baseService.update(view);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(v.getId()).toUri();
		
		LOG.debug("{} found at {} Successfully updated", getObjectName(), location);
		
		return ResponseEntity.status(HttpStatus.OK).location(location).body(ServiceResponse.buildGoodResponse(v));
		
	}
	
	protected void validateUpdateRequest(Long id, V v) {
	    Long objId = v.getId();
	    if (objId == null || objId < 0) {
	        throw new IllegalArgumentException("Id required for update");
	    } 
	    if (!id.equals(v.getId())) {
	        throw new IllegalArgumentException(MessageFormat.format("Provided Id {0} doesn't match Id of given resource", new Object[] {String.valueOf(id)}));
	    }
	}
}
