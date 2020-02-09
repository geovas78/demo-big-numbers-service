package com.gvasilski.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CORS filter configuration to restrict or allow access from
 * certain domains.
 * 
 * WARNING: Here I have allowed access from any origin for demo purposes
 * 			Please change to what is appropriate.
 * 
 * @author Georgi Vasilski
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class CorsFilter {
	
	/**
	 * Configures the CORS access to the service resources
	 * @return Web mvc configurer object.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
