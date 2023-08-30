package com.app.aspireloan.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Payment {
    String repaymentId;
    String loanId;
    String userId;
    String status; // unpaid, paid
    Double amount;
    Double amountReceived;
    Timestamp dueDate;
    Timestamp paymentDate;
    Timestamp createdAt;
}
