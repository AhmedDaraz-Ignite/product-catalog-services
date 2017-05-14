package com.example.endpoint;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
public interface API<V> {

	@ApiOperation(value = "Retrieves the registry that matches the passed Id", notes = "Returns a registry")
	public ResponseEntity<V> get(@ApiParam(value = "Id of the registry", required = true, type = "Integer") int id);

	@ApiOperation(value = "Creates a new registry", notes = "Creates a registry")
	public ResponseEntity<V> create(@ApiParam(value = "Registry to be created", required = true, type = "RequestBody") V vParam);

	@ApiOperation(value = "Deletes a registry that matches the passed Id", notes = "Deletes a registry")
	public ResponseEntity<Void> delete(@ApiParam(value = "Id of the registry", required = true, type = "Integer") int id);
	
	@ApiOperation(value = "Updates the registry that matches the passed Id", notes = "Updates a registry")
	public ResponseEntity<V> update(@ApiParam(value = "Id of the registry", required = true, type = "Long") Long id, 
			@ApiParam(value = "Registry to be updated", required = true, type = "RequestBody") V vParam);

	@ApiOperation(value = "Retrieves all the registries", notes = "Returns a list of registries")
	public ResponseEntity<List<V>> getAll();
}
