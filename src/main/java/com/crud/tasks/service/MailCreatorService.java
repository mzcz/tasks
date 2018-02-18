package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private Company company;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("tasks_url","http://localhost:8888/crud");
        context.setVariable("button","Vist website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("preview_message","New message: ");
        context.setVariable("company", " name: " + company.getCompanyName() + ", goal: "
                 + company.getGoal() + ", phone: " + company.getPhone() + ", mail: " + company.getEmail());

        context.setVariable("goodbye_message","Kind regards, Kodilla");
        return templateEngine.process("mail/created-trello-card-mail",context);
    }


}
