package com.recipe;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
/**
 * Enable the auto-configuration, context, component etc.
 * @author PKumar14
 *
 */
@SpringBootApplication
@EnableSwagger2
public class RecipeApplication {

	public static void main(String[] args) {
		/**
		 * run method always used to initialized and run the context.
		 */
		ApplicationContext context =SpringApplication.run(RecipeApplication.class, args);
		// To check whether your classes get loaded or not, in the list provided by context.
		List<String> beans=Arrays.asList(context.getBeanDefinitionNames());
		System.out.println("List of all initialized beans:");
		for(String bean:beans) {
			System.out.println(bean);
		}
	}
	//integration of Swagger UI, to identify the all available services at controller
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.recipe.controller")).build();
	   }

}
