/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

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

	@PostMapping(value = "/api/contato")
	public void entrarEmContato(@RequestBody @Valid ContatoRequest request) {
		System.out.println(request);
	}

}
