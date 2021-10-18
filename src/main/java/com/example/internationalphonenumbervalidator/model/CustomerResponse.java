package com.example.internationalphonenumbervalidator.model;

import java.util.List;

public class CustomerResponse {
    private List<CustomerDto> customerDtoList;
    private long resultCount;
    private long totalCount;

    public List<CustomerDto> getCustomerResponseDtoList() {
        return customerDtoList;
    }

    public void setCustomerResponseDtoList(List<CustomerDto> customerDtoList) {
        this.customerDtoList = customerDtoList;
    }

    public long getResultCount() {
        return resultCount;
    }

    public void setResultCount(long resultCount) {
        this.resultCount = resultCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
