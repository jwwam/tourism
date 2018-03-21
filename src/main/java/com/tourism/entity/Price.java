package com.tourism.entity;

import java.util.ArrayList;
import java.util.List;

public class Price{

    //
    private String type;

    //
    private List<PriceData> priceDataList = new ArrayList();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PriceData> getPriceDataList() {
        return priceDataList;
    }

    public void setPriceDataList(List<PriceData> priceDataList) {
        this.priceDataList = priceDataList;
    }
}
