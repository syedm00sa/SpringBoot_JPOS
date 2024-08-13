package com.users.persistence.model;

public class TransactionRequest {

    private String userId;
    private String amount;

    public TransactionRequest(String userId, String amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
