package com.finance.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.finance.app.domain.model.Category;
import com.finance.app.domain.model.TransactionType;
import com.finance.app.domain.repository.TransactionRepository;

import com.finance.app.domain.model.Transaction;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(TransactionType type) {
        if (type != null) {
            return transactionRepository.findAllTransactionsByType(type);
        }
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByDate(String month, String year) throws ParseException {
        month = month.length() == 1 ? String.format("%s0", month) : month;
        String date1 = year + "/" + month + "/01"; //'2022/01/01'
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDate = ft.parse(date1);

        int lastDay = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        String date2 = year + "/" + month + "/" + lastDay;
        Date endDate = ft.parse(date2);

        return transactionRepository.findAllTransactionsByDate(startDate, endDate);
    }

    public List<Transaction> getTransactionsByDateAndType(String month, String year, TransactionType type) throws ParseException {
        month = month.length() == 1 ? String.format("%s0", month) : month;
        String date1 = year + "/" + month + "/01"; //'2022/01/01'
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDate = ft.parse(date1);

        int lastDay = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        String date2 = year + "/" + month + "/" + lastDay;
        Date endDate = ft.parse(date2);

        return transactionRepository.findAllTransactionsByDateAndType(startDate, endDate, type);
    }

    public List<Transaction> getTransactionsByDateAndTypeAndCategory(String month,
                                                                     String year,
                                                                     TransactionType type,
                                                                     Category category) throws ParseException {
        month = month.length() == 1 ? String.format("0%s", month) : month;
        String date1 = year + "/" + month + "/01"; //'2022/01/01'
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDate = ft.parse(date1);

        int lastDay = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        String date2 = year + "/" + month + "/" + lastDay;
        Date endDate = ft.parse(date2);

        return transactionRepository.findAllTransactionsByDateAndTypeAndCategory(startDate, endDate, type, category);
    }

    public void deleteTransaction(UUID transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
