package com.prashant.lendingpoint.api.customer.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressModel implements Serializable {

    private static final long serialVersionUID = 8408499471025187550L;

    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zipcode;


}
