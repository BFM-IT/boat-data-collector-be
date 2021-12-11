package hu.fulopmark.boatdatacollectorbe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;

@SpringBootApplication
public class BoatDataCollectorBeApplication {

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @Value("${http.auth-token-header-name}")
    private String API_KEY_HEADER_NAME;

    public static void main(String[] args) {
        SpringApplication.run(BoatDataCollectorBeApplication.class, args);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Boat Data Collector API")
                        .description("Data collector API for sailboats.")
                        .version(buildVersion + "-" + buildTimestamp).contact(new Contact("Fülöp Márk", "https://fulopmark.hu/", null)).build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("hu.fulopmark.boatdatacollectorbe.controller"))
                .paths(PathSelectors.any())
                .paths(Predicate.not(PathSelectors.ant("/error")))
                .build()
                .securitySchemes(singletonList(apiKey()))
                .securityContexts(singletonList(securityContext()));
    }

    private ApiKey apiKey() {
        return new ApiKey("APIKey", API_KEY_HEADER_NAME, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("APIKey", authorizationScopes));
    }


}
