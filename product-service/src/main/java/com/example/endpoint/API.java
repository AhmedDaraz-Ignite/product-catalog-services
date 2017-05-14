package com.example.endpoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.endpoint.mapper.ServiceResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Api interface with all Rest CRUD operations, all Endpoints will implement this interface
 * @author Ahmed.Rabie
 *
 * @param <V> Payload main type of Endpoint.
 */
@Api
public interface API<V> {

	@ApiOperation(value = "Retrieves the registry that matches the passed Id", notes = "Returns a registry")
	public ServiceResponse<V> get(@ApiParam(value = "Id of the registry", required = true, type = "Integer") int id);

	@ApiOperation(value = "Creates a new registry", notes = "Creates a registry")
	public ResponseEntity<ServiceResponse<V>> create(@ApiParam(value = "Registry to be created", required = true, type = "BaseView") @RequestBody V vParam);

	@ApiOperation(value = "Deletes a registry that matches the passed Id", notes = "Deletes a registry")
	public ServiceResponse<Void> delete(@PathVariable("id") @ApiParam(value = "Id of the registry", required = true, type = "Integer") int id);
	
	@ApiOperation(value = "Updates the registry that matches the passed Id", notes = "Updates a registry")
	public ResponseEntity<ServiceResponse<V>> update(@PathVariable("id") @ApiParam(value = "Id of the registry", required = true, type = "Long") Long id, 
			@ApiParam(value = "Registry to be updated", required = true, type = "BaseView") @RequestBody V vParam);

	@ApiOperation(value = "Retrieves all the registries", notes = "Returns a list of registries")
	public ServiceResponse<List<V>> getAll();
}
