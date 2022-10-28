package com.prashant.dcms.api.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleName {
    ADMIN("Admin"),
    DOCTOR("Doctor");

    private final String value;

}
