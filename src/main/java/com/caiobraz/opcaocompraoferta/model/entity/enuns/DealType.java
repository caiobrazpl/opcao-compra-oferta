package com.caiobraz.opcaocompraoferta.model.entity.enuns;

public enum DealType {

    LOCAL(1, "Local"),
    PRODUCT(2, "Produto"),
    TRAVEL(3, "Viagem");

    private Integer value;
    private String description;

    DealType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
