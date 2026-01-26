package caiohudak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
						.version("v1")
						.description("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
						.termsOfService("https://s2.glbimg.com/Ztqs_N82NTHkYuI2nK3hCljO4_g=/e.glbimg.com/og/ed/f/original/2015/08/06/5742020986_1328ee7bbe_b.jpg")
						.license(new License()
								.name("Apache 2.0")
								.url("https://s2.glbimg.com/Ztqs_N82NTHkYuI2nK3hCljO4_g=/e.glbimg.com/og/ed/f/original/2015/08/06/5742020986_1328ee7bbe_b.jpg")));
	}
}
