/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Lucas Mariano
 *
 */
@Getter
@ToString
public class ContatoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@Email
	private String email;
	@NotBlank
	private String assunto;
	@NotBlank
	private String mensagem;

	/**
	 * Contrutor.
	 * 
	 * @param umNome      um {@link String} que representa o nome do contatante.
	 * @param umEmail     um {@link String} que representa o email do contatante.
	 * @param umAssunto   um {@link String} que representa o assunto do contato.
	 * @param umaMensagem um {@link String} que representa o conteúdo propriamente
	 *                    dito da mensagem.
	 */
	public ContatoRequest(@NotBlank String umNome, @NotNull @Email String umEmail, @NotBlank String umAssunto,
			@NotBlank String umaMensagem) {
		super();
		this.nome = umNome;
		this.email = umEmail;
		this.assunto = umAssunto;
		this.mensagem = umaMensagem;
	}

}
