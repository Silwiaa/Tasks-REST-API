package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//Exc 29.2
@SpringBootTest
public class ConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void testTrelloConfigValues() {
        //Given

        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloToken();
        String userName = trelloConfig.getUserName();

        //Then
        assertThat(trelloApiEndpoint).isEqualTo("https://api.trello.com/1");
        assertThat(trelloAppKey).isEqualTo("d71b2e28ffd306066cffc4cb5984da0f");
        assertThat(trelloToken).isEqualTo("41dfd53172d5260c38c37faac1a31e1c28586db5a786ad8a9cae1af31e8007e3");
        assertThat(userName).isEqualTo("sylwiakrzymowska1");
    }

    @Test
    void testAdminConfig() {
        //Given

        //When
        String adminMail = adminConfig.getAdminMail();

        //Then
        assertThat(adminMail).isEqualTo("silwiaa@gmail.com");
    }
}
