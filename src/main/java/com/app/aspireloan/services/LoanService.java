package com.app.aspireloan.services;

import com.app.aspireloan.models.Loan;
import com.app.aspireloan.repositories.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    public void addLoan(Loan loan) {
        loanRepo.addLoan(loan);
        return;
    }

    public List<Loan> getAllLoansForUser(String userId) {
        return loanRepo.getAllLoansForUser(userId);
    }

    public List<Loan> getAllPendingStatusLoans() {
        return loanRepo.getAllPendingStatusLoans();
    }

    public void updateStatus(String loanId, String updatedBy, String status) {
        loanRepo.updateStatus(loanId, updatedBy, status);
        return;
    }

    public Loan getLoanById(String loanId) {
        Loan loan = loanRepo.getLoanById(loanId);
        return loan;
    }
}
