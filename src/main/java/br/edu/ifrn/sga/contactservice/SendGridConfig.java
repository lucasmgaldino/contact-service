package br.edu.ifrn.sga.contactservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;

@Component
public class SendGridConfig {

    public static final String SEND_GRID_API_KEY = "SEND_GRID_API_KEY";
    public static final String SEND_GRID_MAIL_FROM = "SEND_GRID_MAIL_FROM";
    public static final String SEND_GRID_MAIL_TO = "SEND_GRID_MAIL_TO";

    private final Properties properties = new Properties();

    @Value("${mails.service.sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${mails.service.sendgrid.from}")
    private String sendGridMailFrom;

    @Value("${mails.service.sendgrid.to}")
    private String sendGridMailTo;

    /**
     * Método responsável por recuperar o valor de uma configuração do SengGrid.
     *
     * @param umaConfiguracao um {@link String} que representa o identificador de
     *                        uma configuração.
     * @return um {@link String} que representa o valor da configuração solicitada.
     */
    public Optional<String> getConfig(final String umaConfiguracao) {
        this.properties.putAll(carregarConfiguracoesDefault());
        this.properties.putAll(carregarConfiguracoesDoSistemaOperacional());
        return Optional.ofNullable(this.properties.getProperty(umaConfiguracao));
    }

    private Properties carregarConfiguracoesDefault() {
        Properties properties = new Properties();
        properties.put(SendGridConfig.SEND_GRID_API_KEY, this.sendGridApiKey);
        properties.put(SendGridConfig.SEND_GRID_MAIL_FROM, this.sendGridMailFrom);
        properties.put(SendGridConfig.SEND_GRID_MAIL_TO, this.sendGridMailTo);
        return properties;
    }

    private Properties carregarConfiguracoesDoSistemaOperacional() {
        Properties properties = new Properties();
        if (System.getenv().containsKey(SendGridConfig.SEND_GRID_API_KEY)) {
            properties.put(SendGridConfig.SEND_GRID_API_KEY, System.getenv(SendGridConfig.SEND_GRID_API_KEY));
        }
        if (System.getenv().containsKey(SendGridConfig.SEND_GRID_MAIL_FROM)) {
            properties.put(SendGridConfig.SEND_GRID_MAIL_FROM, System.getenv(SendGridConfig.SEND_GRID_MAIL_FROM));
        }
        if (System.getenv().containsKey(SendGridConfig.SEND_GRID_MAIL_TO)) {
            properties.put(SendGridConfig.SEND_GRID_MAIL_TO, System.getenv(SendGridConfig.SEND_GRID_MAIL_TO));
        }
        return properties;
    }

}
