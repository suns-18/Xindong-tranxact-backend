package neu.xindong.xact.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SecurityScheme(type = SecuritySchemeType.HTTP, name = "JWT", scheme = "Bearer")
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title("信东XAct金融交易系统API文档")
                .version("V0.0.1")
                .description("这是信东XAct金融交易系统API文档");
    }
}
