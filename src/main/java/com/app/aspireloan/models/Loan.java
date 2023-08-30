package com.app.aspireloan.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Loan {
    private String loanId;
    private String userId;
    private Double amount;
    private String status;
    private int term;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
