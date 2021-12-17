/**
 *
 */
package br.edu.ifrn.sga.contactservice;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Lucas Mariano
 *
 */
public class SendGridClientTest {

	private SendGrid sendGrid;
	private SendGridConfig sendGridConfig;
	private SendGridClient sendGridClient;

	@DisplayName("deve lançar exceção caso tente enviar um email nulo, por tanto não pode chamar a API de SendGrid")
	@Test
	public void testEnvioEmailNulo() throws IOException {
		this.sendGrid = mock(SendGrid.class);
		this.sendGridConfig = mock(SendGridConfig.class);
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM)).thenReturn(Optional.of("site@email.com"));
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_TO)).thenReturn(Optional.of("site@email.com"));
		this.sendGridClient = new SendGridClientImpl(this.sendGrid, this.sendGridConfig);

		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> this.sendGridClient.enviar(null), "Deveria ter lançado uma exceção do tipo 'IllegalArgumentException'");

		verify(this.sendGrid, times(0)).api(any(Request.class));
	}

	@DisplayName("deve lançar exceção caso tente instanciar um SendGridClientImpl sem informar a instância de SendGrid")
	@Test
	public void testInstanciacaoSendGridClientImplSemSendGrid() {
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> this.sendGridClient = new SendGridClientImpl(null, this.sendGridConfig), "Deveria ter lançado uma exceção do tipo 'IllegalArgumentException'");
	}

	@DisplayName("deve lançar exceção caso tente instanciar um SendGridClientImpl sem informar o mailFrom")
	@ParameterizedTest(name = "[{index}] mailFrom inválido: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"   ", "\t", "\n"})
	public void testInstanciacaoSendGridClientImplSemMailFrom(String email) {
		this.sendGridConfig = mock(SendGridConfig.class);
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM)).thenReturn(Optional.ofNullable(email));
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> this.sendGridClient = new SendGridClientImpl(this.sendGrid, this.sendGridConfig), "Deveria ter lançado uma exceção do tipo 'IllegalArgumentException'");
	}

	@DisplayName("deve lançar exceção caso tente instanciar um SendGridClientImpl sem informar o mailTo")
	@ParameterizedTest(name = "[{index}] mailTo inválido: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {"   ", "\t", "\n"})
	public void testInstanciacaoSendGridClientImplSemMailTo(String email) {
		this.sendGridConfig = mock(SendGridConfig.class);
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM)).thenReturn(Optional.ofNullable(email));
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> this.sendGridClient = new SendGridClientImpl(this.sendGrid, this.sendGridConfig), "Deveria ter lançado uma exceção do tipo 'IllegalArgumentException'");
	}

	@DisplayName("deve enviar para o serviço SendMail, os dados para que o email seja de fato enviado.")
	@Test
	public void testSend() throws IOException {
		this.sendGrid = mock(SendGrid.class);
		this.sendGridConfig = mock(SendGridConfig.class);
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM)).thenReturn(Optional.of("site@email.com"));
		when(this.sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_TO)).thenReturn(Optional.of("site@email.com"));
		this.sendGridClient = new SendGridClientImpl(this.sendGrid, this.sendGridConfig);

		final Response response = new Response(200, "", Collections.emptyMap());

		when(this.sendGrid.api(any(Request.class))).thenReturn(response);

		this.sendGridClient.enviar(criarContatoRequest());

		verify(this.sendGrid).api(any(Request.class));
	}

	private ContatoRequest criarContatoRequest() {
		return new ContatoRequest("Dino da Silva Sauro", "dino.sauro@email.com",
				"Teste de email", "Este é o conteúdo do email.");
	}

}
