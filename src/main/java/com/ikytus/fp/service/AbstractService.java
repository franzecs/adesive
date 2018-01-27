package com.ikytus.fp.service;

import com.ikytus.fp.model.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	void salvar(T entidade);

	void salvar(T entidade, String imagem);
}
