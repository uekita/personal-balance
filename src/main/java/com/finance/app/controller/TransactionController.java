package com.finance.app.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finance.app.domain.model.Transaction;
import com.finance.app.domain.model.TransactionType;
import com.finance.app.service.TransactionService;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PutMapping("api/v1/transaction")
    @ResponseStatus(HttpStatus.OK)
    public UUID addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction).getId();
    }


    @GetMapping("api/v1/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getTransactionsByType(@RequestParam(required = false) TransactionType type,
                                                   @RequestParam(required = false) Date date) {
        return transactionService.getTransactions(type);
    }

    @GetMapping("api/v1/transactions/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getTransactionsByDate(@PathVariable("month") String month,
                                                   @PathVariable("year") String year) throws ParseException {
        return transactionService.getTransactionsByDate(month, year);
    }

    @DeleteMapping("api/v1/transaction/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransaction(@PathVariable("transactionId") final UUID transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
