/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

/**
 * @author Lucas Mariano
 *
 */
@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "API de um serviço de contato", 
				version = "0.1", 
				description = "Essa é uma API hipotética para um serviço de contato para com uma empresa.", 
				contact = @Contact(
						name = "Lucas Mariano",
                        email = "lucasmgaldino@gmail.com"
						),
				license = @License(
						name = "Apache License Version 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
						)
				)
		)
public class SwaggerConfig {
}
