package com.crud.tasks.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;
}
