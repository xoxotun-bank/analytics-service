package com.example.analyticsservice.configuration;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        var info = new Info()
            .title("МКС Analytics API")
            .version(version)
            .description(
                "Метод добавления записи об оформленном продукте для дальнейшего " +
                "формирования аналитических отчётов");
        var securityRequirementItem = new SecurityRequirement().addList("BearerAuth");

        var securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT");

        var components = new Components()
            .addSecuritySchemes("BearerAuth", securityScheme);

        return new OpenAPI()
            .info(info)
            .addSecurityItem(securityRequirementItem)
            .components(components);
    }

}
