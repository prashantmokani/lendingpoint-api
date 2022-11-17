package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class OnlineBankingDetailsModel implements Serializable {

    private static final long serialVersionUID = 8678823682272431094L;

    private String onlineBankingUsername;
    private String password;
    private String confirmPassword;

}
