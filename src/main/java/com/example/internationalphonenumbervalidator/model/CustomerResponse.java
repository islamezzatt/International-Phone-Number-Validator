package com.example.internationalphonenumbervalidator.model;

import java.util.List;

public class CustomerResponse {
    private long resultItems;
    private long totalItems;
    private long currentPage;
    private long totalPages;
    private List<CustomerDto> customers;

    private CustomerResponse(){

    }

    private CustomerResponse(Builder builder) {
        this.resultItems = builder.resultItems;
        this.totalItems = builder.totalItems;
        this.currentPage = builder.currentPage;
        this.totalPages = builder.totalPages;
        this.customers = builder.customers;
    }

    public long getResultItems() {
        return resultItems;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        long resultItems;
        long totalItems;
        long currentPage;
        long totalPages;
        List<CustomerDto> customers;

        public Builder resultItems(long resultItems) {
            this.resultItems = resultItems;
            return this;
        }

        public Builder totalItems(long totalItems) {
            this.totalItems = totalItems;
            return this;
        }

        public Builder currentPage(long currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder totalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder customers(List<CustomerDto> customers) {
            this.customers = customers;
            return this;
        }

        public final CustomerResponse build() {
            CustomerResponse customerResponse = new CustomerResponse(this);
            return customerResponse;
        }
    }
}
