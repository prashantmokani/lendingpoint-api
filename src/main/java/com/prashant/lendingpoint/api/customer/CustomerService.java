package com.prashant.lendingpoint.api.customer;

import static com.prashant.lendingpoint.api.common.constants.TemplateNameConstants.ADMIN_USER_INFO;
import static com.prashant.lendingpoint.api.common.constants.TemplateNameConstants.USER_REG_RESPONSE;

import com.prashant.lendingpoint.api.customer.entities.Customer;
import com.prashant.lendingpoint.api.customer.models.CustomerModel;
import com.prashant.lendingpoint.api.email.EmailService;
import com.prashant.lendingpoint.api.email.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Value("${admin.emails}")
    private String[] adminEmails;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void createCustomer(final CustomerModel customerModel) {
        log.info("Creating customer details = {}", customerModel);
        final Customer customerToCreate = customerMapper.modelToCustomer(customerModel);
        customerRepository.save(customerToCreate);

        sendEmailToAdmin(customerModel);
        sendEmailToUser(customerModel);
    }

    private void sendEmailToAdmin(final CustomerModel customerModel) {
        final Map<String, Object> infoModel = new HashMap<>();
        infoModel.put("info", customerModel);

        final Mail mail = Mail.builder()
            .subject("Alert!! New User Registration @ lendingpointllc.co ")
            .from(fromEmail)
            .to(Arrays.asList(adminEmails))
            .model(infoModel)
            .build();

        emailService.sendEmail(mail, ADMIN_USER_INFO);
    }

    private void sendEmailToUser(final CustomerModel customerModel) {
        final Map<String, Object> model = new HashMap<>();
        model.put("firstName", customerModel.getFirstName());
        model.put("lastName", customerModel.getLastName());

        final Mail mail = Mail.builder()
            .subject("Thank You! Your response is submitted!!")
            .from(fromEmail)
            .to(Collections.singletonList(customerModel.getEmail()))
            .model(model)
            .build();

        emailService.sendEmail(mail, USER_REG_RESPONSE);
    }

}
