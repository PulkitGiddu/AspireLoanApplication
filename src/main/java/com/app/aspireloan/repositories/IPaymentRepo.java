package com.app.aspireloan.repositories;

import com.app.aspireloan.models.Payment;

import java.util.List;

public interface IPaymentRepo {
    void recordPayment(Double amt, String repaymentId) throws Exception;
    void createRepayment(Payment payment);
    List<Payment> getAllPaymentForLoanId(String loanId);
    Payment getPaymentById(String repaymentId);
}
