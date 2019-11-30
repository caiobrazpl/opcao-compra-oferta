package com.caiobraz.opcaocompraoferta.core.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    public T insert(T t) {
        if (t.getId() != null) {
            return update(t);
        }

        return repository.save(t);
    }

    public T update(T t) {
        if (t.getId() == null) {
            return insert(t);
        }

        this.validateExistence(t.getId());

        return repository.save(t);
    }

    public void delete(I i) {
        this.validateExistence(i);

        this.repository.deleteById(i);
    }

    public T findById(I id) {
        return this.repository.findById(id).orElseThrow(
                () -> new ServiceException(messages.string("geral.registroNaoEncontrado"))
        );
    }

    protected void validateExistence(I i) {
        if (!repository.existsById(i)) {
            throw new ServiceException(messages.string("geral.registroNaoEncontrado"));
        }
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

    public Page<T> listPerPage(Integer page, Integer quantity, String sortOrder, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, quantity, Sort.Direction.valueOf(sortOrder), orderBy);
        return repository.findAll(pageRequest);
    }

}
