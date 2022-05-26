package ma.nttsquad.nttecomcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
	
	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2)
							.apiInfo(getApiInfo())
							.select()
							.apis(RequestHandlerSelectors.basePackage("ma.nttsquad.nttecomcore.controller"))
							.paths(PathSelectors.any())
							.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger NTT SQUAD")
				.description("\"Swagger for NTTEcom app\"")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}
}