package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Company {

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.goal}")
    private String goal;

    @Value("${info.company.email}")
    private String email;

    @Value("${info.company.phone}")
    private String phone;

}
