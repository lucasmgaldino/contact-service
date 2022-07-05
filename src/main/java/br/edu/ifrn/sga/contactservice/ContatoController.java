/**
 *
 */
package br.edu.ifrn.sga.contactservice;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sendgrid.SendGrid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

	@Operation(summary = "Entrar em contato com a empresa.", description = "Este endpoint permite que um cliente entre em"
			+ " contato com a empresa.", tags = { "Contato" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "O contato foi processado e encaminhado para o serviço de entrega de "
					+ "emails SendGrid."),
			@ApiResponse(responseCode = "400", description = "A requisição não está em conformidade com o esperado. Por exemplo, algum campo obrigatório não foi informado, ou não atende ao formato ou tamanho especificados."), })
	@PostMapping(value = "/api/contato")
	public void entrarEmContato(@RequestBody @Valid ContatoRequest request) {
		SendGridClient sendGridClient = new SendGridClientImpl(
				new SendGrid(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_API_KEY).orElseThrow(
						() -> new IllegalArgumentException("Não foi possível recuperar a API KEY do SendGrid."))),
				this.sendGridConfig);
		sendGridClient.enviar(request);
	}

}
