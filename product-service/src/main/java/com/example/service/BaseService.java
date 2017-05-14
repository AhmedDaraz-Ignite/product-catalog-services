package com.example.service;

import com.example.model.BaseEntity;
import com.example.view.BaseView;

/**
 * BaseService interface, all application services should implement this interface as part of application framework
 * @author Ahmed.Rabie
 *
 * @param <E extends BaseEntity> base service entity.
 * @param <V extends BaseView> base service view.
 */
public interface BaseService<E extends BaseEntity, V extends BaseView> extends Service<E, V> {
}
