package com.caiobraz.opcaocompraoferta.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.repository.BuyOptionRepository;

@Service
public class BuyOptionService extends AbstractService<BuyOption, Long> {

    @Autowired
    public BuyOptionService(BuyOptionRepository repository) {
        super(repository);
    }
}
