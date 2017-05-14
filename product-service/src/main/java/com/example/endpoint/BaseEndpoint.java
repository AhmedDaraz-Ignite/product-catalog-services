package com.example.endpoint;

import java.net.URI;
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
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<V> get(@PathVariable("id") int id) {
		LOG.debug("{}.get called with id {}", getEndpointName(), id);
		V v = baseService.get(id);
		return ResponseEntity.ok(v);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<V>> getAll() {
		LOG.debug("{}.getAll called", getEndpointName());
		List<V> views = baseService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(views);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<V> create(@RequestBody V view) {
		LOG.debug("{}.create called", getEndpointName());
		
		V v = baseService.create(view);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(v.getId()).toUri();
		return ResponseEntity.status(HttpStatus.CREATED).location(location).body(v);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		LOG.debug("{}.delete called with id {}", getEndpointName(), id);
		baseService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<V> update(@PathVariable("id") Long id, @RequestBody V view) {
		LOG.debug("{}.update called with id {}", getEndpointName(), id);
		V v = baseService.update(view);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(v.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(v);
		
	}
}
