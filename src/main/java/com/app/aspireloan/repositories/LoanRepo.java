package com.app.aspireloan.repositories;

import com.app.aspireloan.models.Loan;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("loanRepoBean")
public class LoanRepo implements ILoanRepo {
    private Map<String, Loan> loansMap;

    private static LoanRepo loanRepo = new LoanRepo();

    private LoanRepo() {
        this.loansMap = new HashMap<>();
    }

    public static LoanRepo getLoanRepoInstance() {
        return loanRepo;
    }

    public void addLoan(Loan loan) {
        loan.setLoanId(getNextLoanId());
        loansMap.put(loan.getLoanId(), loan);
    }
    /**
     *
     * @param userId user id for which all the loans to be fetched
     * @return returns List of loan objects for a particular user
     */
    public List<Loan> getAllLoansForUser(String userId) {
        List<String> loanIds = new ArrayList<>(loansMap.keySet());
        List<Loan> loans = new ArrayList<>();
        for (String loanId : loanIds) {
            Loan loan = loansMap.get(loanId);
            if (loan.getUserId().equals(userId)) {
                loans.add(loan);
            }
        }
        return loans;
    }

    /**
     *
     * @return returns all the loan objects with status pending
     */
    public List<Loan> getAllPendingStatusLoans() {
        List<String> loanIds = new ArrayList<>(loansMap.keySet());
        List<Loan> pendingStatusLoans = new ArrayList<>();
        for (String loanId : loanIds) {
            Loan loan = loansMap.get(loanId);
            if (loan.getStatus().equals("pending")) {
                pendingStatusLoans.add(loan);
            }
        }
        return pendingStatusLoans;
    }

    /**
     *
     * @param loanId loan id for which status has to be updated
     * @param updatedBy user id of the admin who is updating status
     * @param status new updated status of the loan
     */
    public void updateStatus(String loanId, String updatedBy, String status) {
        Loan loan = loansMap.get(loanId);
        loan.setStatus(status);
        loan.setUpdatedBy(updatedBy);
        loan.setUpdatedAt(Timestamp.from(Instant.now()));
        loansMap.put(loanId, loan);
        return;
    }

    /**
     *
     * @param loanId loan id of the loan to be fetched
     * @return return loan object if present null otherwise
     */
    public Loan getLoanById(String loanId) {
        if (loansMap.containsKey(loanId)) {
            return loansMap.get(loanId);
        }
        return null;
    }

    private String getNextLoanId() {
        return Integer.toString(loansMap.size()+1);
    }
}
