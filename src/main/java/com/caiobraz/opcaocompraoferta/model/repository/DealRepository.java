package com.caiobraz.opcaocompraoferta.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.caiobraz.opcaocompraoferta.model.entity.Deal;
import com.caiobraz.opcaocompraoferta.model.repository.filter.DealFilter;

@Repository
public interface DealRepository extends JpaRepositoryImplementation<Deal, Long>, DealFilter {

    @Override
    default Page<Deal> findAll(Pageable pageable) {
        return this.findAll(
                this.filterValidDeals(),
                pageable
        );
    }

}
