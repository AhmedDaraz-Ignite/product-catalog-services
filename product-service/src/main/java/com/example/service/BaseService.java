package com.example.service;

import com.example.model.BaseEntity;
import com.example.view.BaseView;

public interface BaseService<E extends BaseEntity, V extends BaseView> extends Service<E, V> {

}
