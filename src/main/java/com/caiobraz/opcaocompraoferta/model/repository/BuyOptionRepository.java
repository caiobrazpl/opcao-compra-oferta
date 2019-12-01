package com.caiobraz.opcaocompraoferta.model.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;
import com.caiobraz.opcaocompraoferta.model.entity.Deal;

@Repository
public interface BuyOptionRepository extends JpaRepository<BuyOption, Long> {

    List<BuyOption> findAllByDealAndQuantityCouponGreaterThanAndStartDateBeforeAndEndDateAfterOrderBySalePrice(Deal deal, Long aLong, LocalDateTime startDate, LocalDateTime endDate);

    Optional<BuyOption> findByIdAndQuantityCouponGreaterThanAndStartDateBeforeAndEndDateAfter(Long deal, Long aLong, LocalDateTime startDate, LocalDateTime endDate);
}
