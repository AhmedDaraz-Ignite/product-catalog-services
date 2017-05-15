package com.example.common.service;

import com.example.common.model.BaseEntity;
import com.example.common.types.BaseView;

/**
 * BaseService interface, all application services should implement this interface as part of application framework
 * @author Ahmed.Rabie
 *
 * @param <E extends BaseEntity> base service entity.
 * @param <V extends BaseView> base service view.
 */
public interface BaseService<E extends BaseEntity, V extends BaseView> extends Service<E, V> {
}
