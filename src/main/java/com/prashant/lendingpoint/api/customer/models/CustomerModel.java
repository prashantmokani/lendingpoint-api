package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class CustomerModel {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String ssn; //social security number
    private String monthlySalary;
    private String additionalIncome;

    //address
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zipcode;

    //loan details
    private String loanApprovalCode;
    private String loanAmount;
    private String loanReason;
    private String creditScore;

    //bank details
    private String bankName;
    private String bankDuration;
    private String abaRoutingNumber;
    private String accountNumber;

    //online banking details
    private String onlineBankingUsername;
    private String password;
    private String confirmPassword;

}
