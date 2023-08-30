package com.app.aspireloan.repositories;

import com.app.aspireloan.constants.StatusConstants;
import com.app.aspireloan.models.Payment;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component("paymentRepoBean")
public class PaymentRepo implements IPaymentRepo {
    private List<Payment> repayments;

    private static PaymentRepo paymentRepo = new PaymentRepo();

    private PaymentRepo(){ repayments = new ArrayList<>(); }

    public static PaymentRepo getPaymentRepoInstance() { return paymentRepo; }


    /**
     *
     * @param amt amount to be paid by user and recorded in our database.
     * @param repaymentId EMI for which payment is recorded
     * @throws Exception exception for some validations.
     */
    @Override
    public void recordPayment(Double amt, String repaymentId) throws Exception {
        for (int i=0 ; i<repayments.size() ; i++ ) {
            Payment p = repayments.get(i);
            if (p.getRepaymentId().equals(repaymentId)) {
                if (p.getStatus().equals(StatusConstants.REPAYMENT_STATUS_PAID)) {
                    throw new Exception("Bill/EMI for this repaymentId is already paid.");
                }
                if (amt >= p.getAmount()) {
                    p.setStatus(StatusConstants.REPAYMENT_STATUS_PAID);
                    p.setAmountReceived(amt);
                    p.setPaymentDate(Timestamp.from(Instant.now()));
                    return;
                } else {
                    throw new Exception("Amount is less than the required EMI/Repayment amount");
                }
            }
        }
        return;
    }

    /**
     *
     * @param payment creates a new entry in payments table/DB
     */
    @Override
    public void createRepayment(Payment payment) {
        repayments.add(payment);
        return;
    }

    /**
     *
     * @param loanId loanId for which all the EMIs needs to be fetched
     * @return returns list of repayment entries
     */
    @Override
    public List<Payment> getAllPaymentForLoanId(String loanId) {
        List<Payment> payments = new ArrayList<>();
        for (int i=0 ; i<repayments.size() ; i++) {
            Payment p = repayments.get(i);
            if (p.getLoanId().equals(loanId)) {
                payments.add(p);
            }
        }
        return payments;
    }

    @Override
    public Payment getPaymentById(String repaymentId) {
        for (int i=0 ; i<repayments.size() ; i++) {
            Payment p = repayments.get(i);
            if (p.getRepaymentId().equals(repaymentId)) {
                return p;
            }
        }
        return null;
    }

    public String getNextPaymentId() {
        return Integer.toString(repayments.size()+1);
    }
}
