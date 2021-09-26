package com.Link.Shop.model;

public class SalesForm {
    private Integer buyersId;
    private Integer productsId;

    public SalesForm(Integer buyersId, Integer productsId) {
        this.buyersId = buyersId;
        this.productsId = productsId;
    }

    public Integer getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(Integer buyersId) {
        this.buyersId = buyersId;
    }

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }
}
