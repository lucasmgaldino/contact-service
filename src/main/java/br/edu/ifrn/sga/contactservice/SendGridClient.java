/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import br.edu.ifrn.sga.contactservice.ContatoRequest;

/**
 * @author Lucas Mariano
 *
 */
public interface SendGridClient {

	/**
	 * Encaminha um email para que o serviço SendGrid possa enviar para o destinatário.
	 * @param contatoRequest um {@link ContatoRequest}. Não pode ser nulo.
	 */
	void enviar(ContatoRequest contatoRequest);

}
