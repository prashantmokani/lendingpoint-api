package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class CustomerModel {

    private Long id;

    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "email cannot be empty")
    private String email;

    @NotBlank(message = "phone cannot be empty")
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String ssn; //social security number
    private String monthlySalary;
    private String additionalIncome;

    //address
    @NotBlank(message = "addressLineOne cannot be empty")
    private String addressLineOne;

    private String addressLineTwo;
    @NotBlank(message = "city cannot be empty")
    private String city;
    @NotBlank(message = "state cannot be empty")
    private String state;
    @NotBlank(message = "zipcode cannot be empty")
    private String zipcode;
    @NotBlank(message = "signature cannot be empty")
    private String signature;

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
