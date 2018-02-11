package com.kghn.flashtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class FlashtaskApplication extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(FlashtaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlashtaskApplication.class, args);
		logger.info("Application Started");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Docket simpleDiffServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("flashtask").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().pathMapping("/");

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Flash task")
				.description("Flash Task is a web-based creative collaboration tool")
				// .contact(new Contact("KGHN Inc.", "http://www.kghn.com",
				// "kghn-info@gmail.com"))
				.version("1.0.5").build();
	}

}
