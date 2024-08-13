package com.bank.persistence.model;

public class TransactionRequest {

    private String userId;
    private String amount;
    private String processingCode;

    public TransactionRequest(String userId, String amount,String processingCode) {
        this.userId = userId;
        this.amount = amount;
        this.processingCode = processingCode;
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

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }
}
