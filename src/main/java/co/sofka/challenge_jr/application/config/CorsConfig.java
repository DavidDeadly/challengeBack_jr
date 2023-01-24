package co.sofka.challenge_jr.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;

@Configuration
public class CorsConfig {
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    var corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return corsSource;
  }

  @Bean
  public WebFluxConfigurer corsConfigure() {
    return new WebFluxConfigurerComposite() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
            .allowedMethods("*");
      }
    };
  }
}
