package com.finance.app.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finance.app.domain.model.Category;
import com.finance.app.domain.model.Transaction;
import com.finance.app.domain.model.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.type = :type ")
    List<Transaction> findAllTransactionsByType(@Param("type")TransactionType type);

    @Query("SELECT t FROM Transaction " +
            "t WHERE t.date BETWEEN :startDate " +
            "AND :endDate " +
            "ORDER by t.date")
    List<Transaction> findAllTransactionsByDate(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT t FROM Transaction " +
            "t WHERE t.date BETWEEN :startDate " +
            "AND :endDate " +
            "AND t.type = :type " +
            "ORDER by t.date")
    List<Transaction> findAllTransactionsByDateAndType(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("type") TransactionType type);

    @Query("SELECT t FROM Transaction " +
            "t WHERE t.date BETWEEN :startDate " +
            "AND :endDate " +
            "AND t.type = :type " +
            "AND t.category = :category " +
            "ORDER by t.date")
    List<Transaction> findAllTransactionsByDateAndTypeAndCategory(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("type") TransactionType type,
            @Param("category") Category category);
}
