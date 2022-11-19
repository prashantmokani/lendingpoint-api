create table customer_details
(
    id                     bigserial not null,
    created_by             varchar(255),
    created_date           timestamp,
    modified_by            varchar(255),
    modified_date          timestamp,
    additional_income      varchar(255),
    address                jsonb,
    bank_details           jsonb,
    birth_date             date,
    email                  varchar(255),
    first_name             varchar(255),
    last_name              varchar(255),
    loan_details           jsonb,
    middle_name            varchar(255),
    monthly_salary         varchar(255),
    online_banking_details jsonb,
    phone                  varchar(255),
    ssn                    varchar(255),
    signature              varchar(255),
    primary key (id)
);
