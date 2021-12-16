/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @author Lucas Mariano
 *
 */
@Getter
@ToString
public class ContatoRequest {

	@NotBlank
	@Length(min = 3, max = 100)
	private final String nome;
	@NotEmpty
	@Email
	@Length(max = 100)
	private final String email;
	@NotBlank
	@Length(min = 3, max = 100)
	private final String assunto;
	@NotBlank
	@Length(min = 10, max = 1000)
	private final String mensagem;

	/**
	 * Contrutor.
	 * 
	 * @param umNome      um {@link String} que representa o nome do contatante. Não
	 *                    pode ser nulo nem vazio e deve ter no mínimo 3 e no máximo
	 *                    100 caracteres.
	 * @param umEmail     um {@link String} que representa o email do contatante.
	 *                    Não pode ser nulo e tem que ter um formato válido de email
	 *                    e deve ter no máximo 100 caracteres.
	 * @param umAssunto   um {@link String} que representa o assunto do contato. Não
	 *                    pode ser nulo nem vazio e deve ter no mínimo 3 e no máximo
	 *                    100 caracteres.
	 * @param umaMensagem um {@link String} que representa o conteúdo propriamente
	 *                    dito da mensagem. Não pode ser nulo nem vazio e deve ter
	 *                    no mínimo 10 e no máximo 1000 caracteres.
	 */
	public ContatoRequest(@NotBlank @Length(min = 3, max = 100) String umNome,
			@NotNull @Length(max = 100) @Email String umEmail, @NotBlank @Length(max = 100) String umAssunto,
			@NotBlank @Length(max = 1000) String umaMensagem) {
		super();
		this.nome = umNome;
		this.email = umEmail;
		this.assunto = umAssunto;
		this.mensagem = umaMensagem;
	}

}
