package com.example.internationalphonenumbervalidator.model;

import java.util.List;

public class CustomerResponse {
    private long resultItems;
    private long totalItems;
    private long currentPage;
    private long totalPages;
    private List<CustomerDto> customers;

    public long getResultItems() {
        return resultItems;
    }

    public void setResultItems(long resultItems) {
        this.resultItems = resultItems;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDto> customers) {
        this.customers = customers;
    }
}
