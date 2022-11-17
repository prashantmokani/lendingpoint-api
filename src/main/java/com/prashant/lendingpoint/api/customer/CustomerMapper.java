package com.prashant.lendingpoint.api.customer;

import com.prashant.lendingpoint.api.customer.entities.Customer;
import com.prashant.lendingpoint.api.customer.models.AddressModel;
import com.prashant.lendingpoint.api.customer.models.BankDetailsModel;
import com.prashant.lendingpoint.api.customer.models.CustomerModel;
import com.prashant.lendingpoint.api.customer.models.LoanDetailsModel;
import com.prashant.lendingpoint.api.customer.models.OnlineBankingDetailsModel;

import org.springframework.util.ObjectUtils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(source = "customerModel", target = "address")
    @Mapping(source = "customerModel", target = "loanDetails")
    @Mapping(source = "customerModel", target = "bankDetails")
    @Mapping(source = "customerModel", target = "onlineBankingDetails")
    Customer modelToCustomer(CustomerModel customerModel);

    CustomerModel customerToModel(Customer customer,
                                  AddressModel address,
                                  LoanDetailsModel loanDetails,
                                  BankDetailsModel bankDetails,
                                  OnlineBankingDetailsModel onlineBankingDetails);

    default CustomerModel customerToModel(Customer customer) {
        if (ObjectUtils.isEmpty(customer)) {
            return null;
        }
        return customerToModel(
            customer, customer.getAddress(),
            customer.getLoanDetails(),
            customer.getBankDetails(),
            customer.getOnlineBankingDetails()
        );
    }

}
