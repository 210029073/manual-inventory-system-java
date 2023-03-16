package com.manual.exception;

public class InvalidStockAmountException extends Exception {
    private String message;
    public InvalidStockAmountException(String message) {
        this.message = message;
    }

    public String getKey() {
        return this.message;
    }

    @Override
    public String toString() {
        return super.toString() + " due to: " + getKey();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
