package eu.arima.mejorarTesting.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class AlmacenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlmacenApplication.class, args);
    }

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.getClass().getPackageName()))
                .build();
    }

}
