package com.finance.app.domain.model;

public enum TransactionType {
    EXPENSE("Expense"),
    INCOME("Income");

    String name;

    TransactionType(String name) {
        this.name = name;
    }
}
