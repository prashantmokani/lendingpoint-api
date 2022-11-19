package com.prashant.lendingpoint.api.customer.entities;

import com.prashant.lendingpoint.api.common.entity.BaseEntity;
import com.prashant.lendingpoint.api.customer.models.AddressModel;
import com.prashant.lendingpoint.api.customer.models.BankDetailsModel;
import com.prashant.lendingpoint.api.customer.models.LoanDetailsModel;
import com.prashant.lendingpoint.api.customer.models.OnlineBankingDetailsModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "customer_details")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Customer extends BaseEntity {

    private static final long serialVersionUID = -1L;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "ssn")
    private String ssn; //social security number

    @Column(name = "monthly_salary")
    private String monthlySalary;

    @Column(name = "additional_income")
    private String additionalIncome;

    @Type(type = "jsonb")
    @Column(name = "address", columnDefinition = "jsonb")
    private AddressModel address;

    @Type(type = "jsonb")
    @Column(name = "loan_details", columnDefinition = "jsonb")
    private LoanDetailsModel loanDetails;

    @Type(type = "jsonb")
    @Column(name = "bank_details", columnDefinition = "jsonb")
    private BankDetailsModel bankDetails;

    @Type(type = "jsonb")
    @Column(name = "online_banking_details", columnDefinition = "jsonb")
    private OnlineBankingDetailsModel onlineBankingDetails;

    @Column(name = "signature")
    private String signature;

}
