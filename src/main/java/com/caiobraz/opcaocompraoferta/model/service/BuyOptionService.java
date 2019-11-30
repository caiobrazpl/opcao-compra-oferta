package com.caiobraz.opcaocompraoferta.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.BuyOptionRepository;

@Service
public class BuyOptionService extends AbstractService<BuyOption, Long> {

    private BuyOptionRepository repository;

    @Autowired
    public BuyOptionService(BuyOptionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<BuyOption> findAllActivesByDeal(Deal deal) {
        return repository.findAllByDealAndQuantityCouponGreaterThanAndStartDateBeforeAndEndDateAfter(
                deal, 0L, LocalDateTime.now(), LocalDateTime.now()
        );
    }
}
