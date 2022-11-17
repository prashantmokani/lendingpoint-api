package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankDetailsModel implements Serializable {

    private static final long serialVersionUID = 9046621904802552181L;

    private String bankName;
    private String bankDuration;
    private String abaRoutingNumber;
    private String accountNumber;

}
