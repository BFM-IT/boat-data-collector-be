package hu.fulopmark.boatdatacollectorbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BoatDataCollectorBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoatDataCollectorBeApplication.class, args);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Boat Data Collector API")
                        .description("Data collector API for sailboats.")
                        .version("1.0").contact(new Contact("Fülöp Márk", "https://fulopmark.hu/", "fulop.mark@icloud.com")).build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("hu.fulopmark.boatdatacollectorbe.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
