package com.caiobraz.opcaocompraoferta.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.opcaocompraoferta.model.entity.BuyOption;

@Repository
public interface BuyOptionRepository extends JpaRepository<BuyOption, Long> {
}
