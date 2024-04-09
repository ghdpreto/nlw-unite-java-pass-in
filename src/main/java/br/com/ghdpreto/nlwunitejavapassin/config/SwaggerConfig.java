package br.com.ghdpreto.nlwunitejavapassin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// config de documentacao
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Pass In")
                                .description("API responsável por gerenciar eventos e participantes")
                                .version("1")

                );
    }
}
