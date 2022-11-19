package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoanDetailsModel implements Serializable {

    private static final long serialVersionUID = 1305655898753528287L;

    private String loanApprovalCode;
    private String loanAmount;
    private String loanReason;
    private String creditScore;

}
