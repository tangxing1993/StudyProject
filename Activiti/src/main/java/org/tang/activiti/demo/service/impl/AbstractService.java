package org.tang.activiti.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tang.activiti.demo.service.BaseService;

public abstract class AbstractService<DAO extends JpaRepository<T, ID>,T,ID> implements BaseService<DAO, T, ID> {

	@Autowired
	protected DAO dao;

	@Override
	public <S extends T> S save(S entity) {
		return this.dao.save(entity);
	}

	@Override
	public boolean existsById(ID id) {
		return this.dao.existsById(id);
	}

	@Override
	public Optional<T> findById(ID id) {
		return this.dao.findById(id);
	}

	@Override
	public Iterable<T> findAll() {
		return this.dao.findAll();
	}

	@Override
	public T getOne(ID id) {
		return this.dao.getOne(id);
	}

	@Override
	public long count() {
		return this.dao.count();
	}

	@Override
	public void delete(T entity) {
		this.dao.delete(entity);
	}
	
}
