package com.caiobraz.opcaocompraoferta.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.caiobraz.opcaocompraoferta.core.entity.AbstractEntity;
import com.caiobraz.opcaocompraoferta.model.enuns.DealType;

@Entity
public class Deal extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    private LocalDate publishDate;

    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private String url;
    private Long totalSold;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private DealType type;

    @OneToMany(mappedBy = "deal")
    private List<BuyOption> buyOptions;

    @NotNull
    @Min(1)
    @Max(30)
    @Transient
    private Integer validity;

    public Deal() {
    }

    public Deal(@NotBlank Long id) {
        this.id = id;
    }

    public void updateTotalSold() {
        if (Optional.ofNullable(this.getTotalSold()).isPresent()) {
            this.setTotalSold(this.getTotalSold() + 1);
            return;
        }

        this.setTotalSold(1L);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Long totalSold) {
        this.totalSold = totalSold;
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public List<BuyOption> getBuyOptions() {
        if (buyOptions == null) buyOptions = new ArrayList<>();
        return buyOptions;
    }

    public void setBuyOptions(List<BuyOption> buyOptions) {
        this.buyOptions = buyOptions;
    }
}
