package com.caiobraz.opcaocompraoferta.model.repository.filter;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.caiobraz.opcaocompraoferta.model.entity.Deal;

public interface DealFilter {

    default Specification<Deal> filterValidDeals() {
        return (root, query, builder) -> {

            Predicate predicate = builder.lessThanOrEqualTo(root.get("publishDate"), builder.currentTimestamp());
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("endDate"), builder.currentTimestamp()));

            return predicate;
        };
    }
}
