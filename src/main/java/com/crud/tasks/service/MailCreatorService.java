package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.*;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetails companyDetails;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message", "New card created");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://silwiaa.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Thank you for being with us!");
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("company_goal", companyDetails.getCompanyGoal());
        context.setVariable("company_email", companyDetails.getCompanyEmail());
        context.setVariable("company_phone", companyDetails.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview", "Trello app - Your daily information");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://silwiaa.github.io/");
        context.setVariable("button", "See tasks");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/scheduled-mail", context);
    }
}