/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Lucas Mariano
 *
 */
public class SendGridClientImpl implements SendGridClient {
	
	private final SendGrid sendGrid;
	private final String sendGridMailFrom;
	private final String sendGridMailTo;

	public SendGridClientImpl(@NotNull final SendGrid umSendGrid, @NotNull final SendGridConfig sendGridConfig) {
		Assert.notNull(umSendGrid, "O SendGrid não pode ser nulo.");
		Assert.notNull(sendGridConfig, "O sendGridConfig não pode ser nulo.");
		Assert.isTrue(sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM).isPresent(),
				"O mailFrom é obrigatório. Portanto, não pode ser nulo nem vazio.");
		Assert.isTrue(sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_TO).isPresent(),
				"O mailTo é obrigatório. Portanto, não pode ser nulo nem vazio.");
		this.sendGridMailFrom = sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_FROM).get();
		this.sendGridMailTo = sendGridConfig.getConfig(SendGridConfig.SEND_GRID_MAIL_TO).get();
		this.sendGrid = umSendGrid;
	}

	@Override
	public void enviar(final ContatoRequest contatoRequest) {
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			String body = toMail(contatoRequest).buildPretty();
			System.out.println(body);
			request.setBody(body);
			Response response = this.sendGrid.api(request);
			if (Objects.nonNull(response)) {
				System.out.println("----------------------------------------------------------------------");
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
				System.out.println("----------------------------------------------------------------------");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método responsável por transformar/converter um {@link ContatoRequest} em um {@link Mail}.
	 * @param request um {@link ContatoRequest}. Não pode ser nulo.
	 * @return um {@link Mail}.
	 */
	private Mail toMail(@NotNull ContatoRequest request) {
		Assert.notNull(request, "O contato request não pode ser nulo.");
		final String mensagemFormatada = formatarMensagemMustache(request);

		Email from = new Email(this.sendGridMailFrom, "Página de Contato");
		String subject = request.getAssunto();
		Email to = new Email(this.sendGridMailTo, "SAC");
		//Content content = new Content("text/plain", mensagemFormatada);
		Content content = new Content("text/html", mensagemFormatada);
		Mail mail = new Mail(from, subject, to, content);
		mail.setReplyTo(new Email(request.getEmail(), request.getNome()));
		return mail;
	}

	/**
	 * Método responsável por preencher um template 'Mustache' para montar o conteúdo do email.
	 * @param request um {@link ContatoRequest}. Não pode ser nulo.
	 * @return um {@link String} que representa o conteúdo do email.
	 */
	private String formatarMensagemMustache(@NotNull ContatoRequest request) {
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache mustache = mustacheFactory.compile("email.mustache");
		StringWriter writer = new StringWriter();
		String message = "";
		try {
			mustache.execute(writer, request).flush();
			message = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

}
