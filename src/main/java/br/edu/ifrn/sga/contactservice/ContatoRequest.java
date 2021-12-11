/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Lucas Mariano
 *
 */
@Getter
@ToString
@AllArgsConstructor
public class ContatoRequest {

	private String nome;
	private String email;
	private String assunto;
	private String mensagem;

}
