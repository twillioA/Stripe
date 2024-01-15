package com.payment.payload;

public class PaymentRequest {

    private long amount;
    private String currency;

    // getters and setters

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
