package com.caiobraz.opcaocompraoferta.model.entity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.caiobraz.opcaocompraoferta.core.entity.AbstractEntity;

@Entity
public class BuyOption extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    private Double normalPrice;

    private Double salePrice;

    @NotNull
    private Double percentageDiscount;

    @NotNull
    private Long quantityCoupon;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    @FutureOrPresent
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "dealId")
    private Deal deal;

    public BuyOption() {
    }

    public BuyOption(Long id) {
        this.id = id;
    }

    public BuyOption(Deal deal) {
        this.deal = deal;
    }

    public void calculateSalePrice() {
        BigDecimal normalPrice = BigDecimal.valueOf(this.getNormalPrice());

        BigDecimal percentageDiscount = BigDecimal.valueOf(this.getPercentageDiscount());
        percentageDiscount = percentageDiscount.divide(new BigDecimal(100), new MathContext(2, RoundingMode.HALF_UP));

        BigDecimal discount = normalPrice.multiply(percentageDiscount);

        this.setSalePrice(normalPrice.subtract(discount).doubleValue());
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Long getQuantityCoupon() {
        return quantityCoupon;
    }

    public void setQuantityCoupon(Long quantityCoupon) {
        this.quantityCoupon = quantityCoupon;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
