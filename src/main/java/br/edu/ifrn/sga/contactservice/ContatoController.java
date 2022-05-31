/**
 *
 */
package br.edu.ifrn.sga.contactservice;

import com.sendgrid.SendGrid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Entrar em contato com a empresa.", notes = "Este endpoint permite quem um cliente entre em" +
            " contato com a empresa.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O contato foi processado e encaminhado para o serviço de entrega de " +
                    "emails SendGrid."),
            @ApiResponse(code = 400, message = "Você não tem permissão para acessar este recurso"),
    })
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
