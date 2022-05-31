/**
 * 
 */
package br.edu.ifrn.sga.contactservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Lucas Mariano
 *
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		// RequestHandlerSelectors.basePackage("br.edu.ifrn.sga.bookstore")
		// PathSelectors.ant("/bookstore/api/**")
		//Predicate<RequestHandler> pacoteBase = RequestHandlerSelectors.basePackage("br.edu.ifrn.sga.bookstore");
		//Predicate<String> paths = PathSelectors.ant("/api/**");
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.edu.ifrn.sga.contactservice"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API de um serviço de contato")
				.description("Essa é uma API hipotética para um serviço de contato para com uma empresa.")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.termsOfServiceUrl("/service.html")
				.version("1.0.0")
				.contact(new Contact("Lucas Mariano", "lucasmgaldino@gmail.com", "lucasmgaldino@gmail.com"))
				.build();
		return apiInfo;
	}

}
