/**
 *
 */
package br.edu.ifrn.sga.contactservice;

import com.sendgrid.SendGrid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Lucas Mariano
 *
 */
@RestController
public class ContatoController {

    private final SendGridConfig sendGridConfig;

    public ContatoController(SendGridConfig sendGridConfig) {
        this.sendGridConfig = sendGridConfig;
    }

    @PostMapping(value = "/api/contato")
    public void entrarEmContato(@RequestBody @Valid ContatoRequest request) {
        System.out.println(request);
        SendGridClient sendGridClient = new SendGridClientImpl(
                new SendGrid(sendGridConfig.getConfig(SendGridConfig.SEND_GRID_API_KEY).orElseThrow(
                        () -> new IllegalArgumentException("Não foi possível recuperar a API KEY do SendGrid."))),
                this.sendGridConfig
        );
        sendGridClient.enviar(request);
    }

}
