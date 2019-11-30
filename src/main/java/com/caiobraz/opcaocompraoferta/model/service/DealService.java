package com.caiobraz.opcaocompraoferta.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.DealRepository;

@Service
public class DealService extends AbstractService<Deal, Long> {

    @Autowired
    public DealService(DealRepository repository) {
        super(repository);
    }
}
