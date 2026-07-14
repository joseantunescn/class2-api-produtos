package br.com.cotiinformatica.api_produtos.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("API Produtos")
				.version("1.0.0")
				.description("API REST para gerenciamento de produtos desenvolvida pela Coti Informática")
				.contact(new Contact()
					.name("Coti Informática")
					.url("https://www.cotiinformatica.com.br")
					.email("contato@cotiinformatica.com.br")
				)
			);
	}

}
