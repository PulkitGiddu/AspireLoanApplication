package com.app.aspireloan.controllers;

import com.app.aspireloan.constants.StatusConstants;
import com.app.aspireloan.models.Loan;
import com.app.aspireloan.models.User;
import com.app.aspireloan.services.LoanService;
import com.app.aspireloan.services.PaymentService;
import com.app.aspireloan.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    UserService userService;
    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/loans/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addLoan(@RequestBody Loan loan) {
        loanService.addLoan(loan);
        return new ResponseEntity<>("Successfully submitted loan reqeust", HttpStatus.CREATED);
    }

    @GetMapping(value = "/loans/pending")
    public ResponseEntity<Object> getAllPendingLoan(@RequestParam("user_id") String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return new ResponseEntity<>("Invalid User, User not present.", HttpStatus.UNAUTHORIZED);
        }
        if (!(user.getRole().equals("admin"))) {
            return new ResponseEntity<>("User is not authorized. User is not an admin user", HttpStatus.UNAUTHORIZED);
        }
        List<Loan> pendingStatusloans = loanService.getAllPendingStatusLoans();
        return new ResponseEntity<>(pendingStatusloans, HttpStatus.OK);
    }

    @GetMapping(value = "/loans/user/{id}")
    public ResponseEntity<Object> getAllLoansForUser(@PathVariable String id) {
        List<Loan> loans = loanService.getAllLoansForUser(id);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping(value = "/loans/{id}")
    public ResponseEntity<Object> getLoanById(@PathVariable String id) {
        Loan loan = loanService.getLoanById(id);
        if (loan == null) {
            return new ResponseEntity<>("Loan is not present with this loan id", HttpStatus.OK);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PutMapping(value = "/loans/update")
    public ResponseEntity<Object> updateLoan(@RequestParam("status") String status, @RequestParam("updated_by") String updatedBy, @RequestParam("loan_id") String loanId) {
        if (status.equals(StatusConstants.LOAN_STATUS_REJECTED) || status.equals(StatusConstants.LOAN_STATUS_APPROVED) || status.equals(StatusConstants.LOAN_STATUS_CLOSED)) {
            loanService.updateStatus(loanId, updatedBy, status);
        } else {
            return new ResponseEntity<>("Invalid loan status.", HttpStatus.OK);
        }
        // not an ideal way to do, violates the single responsibility principle
        if (status.equals(StatusConstants.LOAN_STATUS_APPROVED)) {
            paymentService.createPaymentsForLoan(loanId);
        }
        return new ResponseEntity<>("Loan status updated successfully.", HttpStatus.OK);
    }
}
