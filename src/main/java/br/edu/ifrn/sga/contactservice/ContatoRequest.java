/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Lucas Mariano
 *
 */
@Getter
@ToString
public class ContatoRequest {

	@Schema(description = "Nome completo do contactante.", maxLength = 100, minLength = 3, required = true, example = "Fulano de Tal da Silva")
	@NotBlank
	@Length(min = 3, max = 100, message = "O nome deve ter no mínimo {min} e no máximo {max} caracteres.")
	private final String nome;

	@Schema(description = "Email do contactante.", maxLength = 100, minLength = 3, required = true, nullable = false, example = "fulano.silva@email.com")
	@NotEmpty
	@Email
	@Length(max = 100, message = "O email deve ter no máximo {max} caracteres.")
	private final String email;

	@Schema(description = "Assunto que motiva o contato.", maxLength = 100, minLength = 3, required = true, nullable = false, example = "Dúvida")
	@NotBlank
	@Length(min = 3, max = 100, message = "O assunto deve ter no mínimo {min} e no máximo {max} caracteres.")
	private final String assunto;

	@Schema(description = "Conteúdo da mensagem.", maxLength = 1000, minLength = 10, required = true, nullable = false, example = "Estou com dúvida sobre determinado produto.")
	@NotBlank
	@Length(min = 10, max = 1000, message = "A mensagem deve ter no mínimo {min} e no máximo {max} caracteres.")
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
