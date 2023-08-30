package com.app.aspireloan.services;

import com.app.aspireloan.constants.StatusConstants;
import com.app.aspireloan.models.Loan;
import com.app.aspireloan.models.Payment;
import com.app.aspireloan.repositories.LoanRepo;
import com.app.aspireloan.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    LoanRepo loanRepo;

    public void createPaymentsForLoan(String loanId) {
        Loan loan = loanRepo.getLoanById(loanId);
        Double amount = loan.getAmount();
        int term = loan.getTerm();
        for (int i=1 ; i<=term ; i++) {
            Payment p = preparePaymentData(i, amount/term, loan);
            paymentRepo.createRepayment(p);
        }
        return;
    }

    public void recordPayment(Double amt, String repaymentId) throws Exception {
        paymentRepo.recordPayment(amt, repaymentId);
    }

    public List<Payment> getAllRepaymentForLoan(String loanId) {
        return paymentRepo.getAllPaymentForLoanId(loanId);
    }

    public Payment getPaymentById(String repaymentId) {
        return paymentRepo.getPaymentById(repaymentId);
    }

    private Payment preparePaymentData(int termNumber, Double amount, Loan loan) {
        Payment p = new Payment();
        p.setRepaymentId(paymentRepo.getNextPaymentId());
        p.setUserId(loan.getUserId());
        p.setLoanId(loan.getLoanId());
        p.setPaymentDate(null);
        p.setAmount(amount);
        p.setAmountReceived(0.0d);
        p.setStatus(StatusConstants.REPAYMENT_STATUS_UNPAID);
        p.setCreatedAt(Timestamp.from(Instant.now()));

        LocalDate date = LocalDate.now();
        date = date.plusDays(termNumber*7);

        p.setDueDate(Timestamp.valueOf(date.atStartOfDay()));
        return p;
    }
}
