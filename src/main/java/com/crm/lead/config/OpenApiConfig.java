package com.crm.lead.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Lead CRM API")
                        .version("1.0.0")
                        .description("Production-ready REST APIs for the Customer Lead CRM System backend.")
                        .contact(new Contact()
                                .name("CRM Support Team")
                                .email("support@crm.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("crm-public-api")
                .pathsToMatch("/**")
                .packagesToScan("com.crm.lead.controller") // Restricts scanning exclusively to your fixed controllers
                .build();
    }
}