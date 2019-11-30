package com.caiobraz.opcaocompraoferta.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caiobraz.opcaocompraoferta.core.entity.AbstractEntity;
import com.caiobraz.opcaocompraoferta.core.exception.ServiceException;

public abstract class AbstractService<T extends AbstractEntity<I>, I> implements Serializable {

    @Autowired
    protected MessagesService messages;

    protected Logger LOG;
    protected JpaRepository<T, I> repository;

    public AbstractService(JpaRepository<T, I> repository) {
        this.repository = repository;
        this.LOG = LoggerFactory.getLogger(this.getClass().getName());
    }

    public T save(T t) {
        return this.repository.save(t);
    }

    public List<T> save(Collection<T> ts) {
        return this.repository.saveAll(ts);
    }

    public void delete(T t) {
        this.repository.delete(t);
    }

    public void delete(Collection<T> ts) {
        this.repository.deleteAll(ts);
    }

    public T findById(I id) {
        return this.repository.findById(id).orElseThrow(
              () -> new ServiceException(messages.string("geral.registroNaoEncontrado"))
        );
    }

    public List<T> listAll() {
        return this.repository.findAll();
    }

    public List<T> listAll(String... orderBy) {
        return this.repository.findAll(Sort.by(orderBy));
    }

    public List<T> listAll(T filtro) {
        return this.repository.findAll(Example.of(filtro));
    }

    public List<T> listAll(T filtro, String... orderBy) {
        return this.repository.findAll(Example.of(filtro), Sort.by(orderBy));
    }

}
