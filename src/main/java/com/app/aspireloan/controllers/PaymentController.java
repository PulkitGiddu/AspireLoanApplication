package com.app.aspireloan.controllers;

import com.app.aspireloan.models.Payment;
import com.app.aspireloan.services.PaymentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PutMapping(value = "/payment/update")
    public ResponseEntity<Object> recordPayment(@RequestParam("amount") Double amount, @RequestParam("repayment_id") String repaymentId) {
        try {
            paymentService.recordPayment(amount, repaymentId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Payment Recorded", HttpStatus.OK);
    }

    @GetMapping(value = "/payment/all")
    public ResponseEntity<Object> getAllEMIDetailsForLoan(@RequestParam("loan_id") String loanId) {
        List<Payment> repayments = paymentService.getAllRepaymentForLoan(loanId);
        return new ResponseEntity<>(repayments, HttpStatus.OK);
    }

    @GetMapping(value = "/payment/{id}")
    public ResponseEntity<Object> getRepayment(@PathVariable("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
