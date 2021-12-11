/**
 * 
 */
package br.edu.ifrn.sga.contactservice.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.sga.contactservice.ContatoRequest;

/**
 * @author Lucas Mariano
 *
 */
@RestController
public class ContatoController {

	@PostMapping(value = "/api/contato")
	public void entrarEmContato(@RequestBody @Valid ContatoRequest request) {
		System.out.println(request);
	}
	

}