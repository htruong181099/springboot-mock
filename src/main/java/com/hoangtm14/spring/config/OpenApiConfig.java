package com.hoangtm14.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myAPI() {
        return new OpenAPI()
                .components(new Components()
//                        .addSecuritySchemes("bearer-key",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")
//                        )
                )
                .info(new Info()
                        .title("My API")
                        .description("Documentation of API v.1.0")
                        .version("1.0")
                );
//                .addSecurityItem(
//                        new SecurityRequirement()
//                                .addList("bearer-jwt", Arrays.asList("read", "write"))
//                                .addList("bearer-key", Collections.emptyList())
//                );
    }
}
