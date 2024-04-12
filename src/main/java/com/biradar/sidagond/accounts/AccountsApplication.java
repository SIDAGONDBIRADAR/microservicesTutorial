package com.biradar.sidagond.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts MicroServices Rest API Documentation",
				description = "Accounts MicroServices Documentation using openapi",
				version = "v1",
				contact = @Contact(
						name = "Sidagond Biradar",
						email = "sidagondbiradar777@gmail,com",
						url = "sidagondbiradar777@gmail.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "www.apache.org"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "Accounts MicroServices Rest API Documentation",
				url = "http://localhost:4545/swagger-ui/index.html"
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
