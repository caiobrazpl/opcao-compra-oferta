package com.caiobraz.opcaocompraoferta.model.service;

import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.DealRepository;

@Service
public class DealService extends AbstractService<Deal, Long> {

    private BuyOptionService optionService;

    @Value("${url.front.viewDeal}")
    private String urlViewDeal;

    @Autowired
    public DealService(DealRepository repository, BuyOptionService optionService) {
        super(repository);
        this.optionService = optionService;
    }

    public Deal findByIdWithOptions(Long idDeal) {
        Deal deal = super.findById(idDeal);
        deal.setBuyOptions(this.optionService.findAllActivesByDeal(new Deal(idDeal)));
        deal.getBuyOptions().forEach(buyOption -> buyOption.setDeal(null));

        return deal;
    }

    public Deal insert(Deal deal) {
        this.prepareDealToInsert(deal);
        super.insert(deal);

        deal.setUrl(this.urlViewDeal + deal.getId());

        super.update(deal);

        return deal;
    }

    public Deal addOption(Long idDeal, BuyOption option) {
        Deal deal = super.findById(idDeal);

        option.setDeal(deal);
        this.optionService.insert(option);

        deal.setBuyOptions(this.optionService.findAllActivesByDeal(deal));

        return deal;
    }

    public Deal updateTotalSold(Long idDeal) {
        Deal deal = super.findById(idDeal);
        deal.updateTotalSold();

        return update(deal);
    }

    private void prepareDealToInsert(Deal deal) {
        deal.setTotalSold(0L);
        deal.setCreateDate(LocalDateTime.now());
        deal.setEndDate(deal.getPublishDate().atTime(23, 59, 59)
                .plus(Period.ofDays(deal.getValidity())));
    }
}
