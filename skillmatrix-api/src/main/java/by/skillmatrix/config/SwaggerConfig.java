package by.skillmatrix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .info(
                        new Info()
                                .title("Skill Matrix")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Vadim Denisik")
                                                .email("denisik.vadim@yandex.by")
                                                .url("https://www.linkedin.com/in/vadim-denisik/")
                                )
                );
    }
}
