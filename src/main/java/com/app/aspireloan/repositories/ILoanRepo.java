package com.app.aspireloan.repositories;

import com.app.aspireloan.models.Loan;

import java.util.List;

public interface ILoanRepo {
    void updateStatus(String loanId, String updatedBy, String status);
    Loan getLoanById(String loanId);
    List<Loan> getAllPendingStatusLoans();
    List<Loan> getAllLoansForUser(String userId);
    void addLoan(Loan loan);
}
