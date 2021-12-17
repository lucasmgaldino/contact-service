package br.edu.ifrn.sga.contactservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendGridConfigTest {

    @Autowired
    private SendGridConfig sendGridConfig;

    @DisplayName("deve injetar um SendGridConfig")
    @Test
    void testInjecao() {
        Assertions.assertNotNull(this.sendGridConfig, "Deveria ter injetado o SendGridConfig");
    }

    @DisplayName("deve recuperar todas as configurações do SendGrid")
    @Test
    void testConfigs() {
        Assertions.assertNotNull(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_API_KEY), "Deveria ter recuperado a APIKEY do SendGrid");
        Assertions.assertNotNull(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM), "Deveria ter recuperado a APIKEY do SendGrid");
        Assertions.assertNotNull(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_TO), "Deveria ter recuperado a APIKEY do SendGrid");
        Assertions.assertTrue(this.sendGridConfig.getConfig("XPTO").isEmpty(), "Não deveria ter recuperado a a configuração XPTO");
    }

}
