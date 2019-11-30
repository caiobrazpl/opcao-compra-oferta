package com.caiobraz.opcaocompraoferta.model.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.DealRepository;

@Service
public class DealService extends AbstractService<Deal, Long> {

    private BuyOptionService optionService;

    @Autowired
    public DealService(DealRepository repository, BuyOptionService optionService) {
        super(repository);
        this.optionService = optionService;
    }

    public Deal findByIdWithOptions(Long idDeal) {
        Deal deal = super.findById(idDeal);
        deal.setBuyOptions(this.optionService.findAllActivesByDeal(new Deal(idDeal)));

        return deal;
    }

    public Deal insert(Deal deal) {
        super.insert(deal);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deal.getId()).toUri();
        deal.setUrl(uri.toString());

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
}
