package com.caiobraz.opcaocompraoferta.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.caiobraz.opcaocompraoferta.core.exception.ServiceException;
import com.caiobraz.opcaocompraoferta.core.service.AbstractService;
import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.BuyOptionRepository;
import com.caiobraz.opcaocompraoferta.model.repository.DealRepository;

@Lazy
@Service
public class BuyOptionService extends AbstractService<BuyOption, Long> {

    private BuyOptionRepository repository;
    private DealRepository dealRepository;

    @Autowired
    public BuyOptionService(BuyOptionRepository repository, DealRepository dealRepository) {
        super(repository);
        this.repository = repository;
        this.dealRepository = dealRepository;
    }

    @Override
    public BuyOption insert(BuyOption buyOption) {
        buyOption.calculateSalePrice();

        return super.insert(buyOption);
    }

    public BuyOption sellUnit(Long idOption) {
        BuyOption option = this.findActivesById(idOption);

        Deal deal = Optional.of(option.getDeal()).orElseThrow(
                () -> new ServiceException(messages.string("buyOption.withoutDeal"))
        );
        deal.updateTotalSold();
        this.dealRepository.save(deal);

        option.setQuantityCoupon(option.getQuantityCoupon() - 1);

        return super.update(option);
    }

    public List<BuyOption> findAllActivesByDeal(Deal deal) {
        return repository.findAllByDealAndQuantityCouponGreaterThanAndStartDateBeforeAndEndDateAfterOrderBySalePrice(
                deal, 0L, LocalDateTime.now(), LocalDateTime.now()
        );
    }

    public BuyOption findActivesById(Long idOption) {
        return repository.findByIdAndQuantityCouponGreaterThanAndStartDateBeforeAndEndDateAfter(
                idOption, 0L, LocalDateTime.now(), LocalDateTime.now()
        ).orElseThrow(() -> new ServiceException(messages.string("buyOption.activeNotFound")));
    }

}
