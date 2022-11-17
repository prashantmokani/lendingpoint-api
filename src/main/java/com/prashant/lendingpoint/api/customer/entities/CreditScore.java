package com.prashant.lendingpoint.api.customer.entities;

import lombok.Getter;

@Getter
public enum CreditScore {

    BAD_CREDIT("Bad Credit", "300-599"),
    POOR_CREDIT("Poor Credit", "600-649"),
    FAIR_CREDIT("Fair Credit", "650-699"),
    GOOD_CREDIT("Good Credit", "700-749"),
    EXCELLENT_CREDIT("Excellent Credit", "750 and above");

    private final String name;
    private final String range;

    CreditScore(final String name, final String range) {
        this.name = name;
        this.range = range;
    }
}
