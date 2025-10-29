// package com.userbase.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig {
// @Bean
// public WebMvcConfigurer corsConfigurer(
// @Value("${cors.allowed-origins}") String[] origins,
// @Value("${cors.allowed-methods}") String[] methods,
// @Value("${cors.allowed-headers}") String[] headers) {
// return new WebMvcConfigurer() {
// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry.addMapping("/**")
// .allowedOrigins(origins)
// .allowedMethods(methods)
// .allowedHeaders(headers)
// .allowCredentials(true);
// }
// };
// }
// }
