// package tacos.reactor.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
// import org.springframework.security.config.web.server.ServerHttpSecurity;
// import org.springframework.security.web.server.SecurityWebFilterChain;

// @Configuration
// @EnableWebFluxSecurity
// public class ReactiveSecurityConfig {
// @Bean
// public SecurityWebFilterChain reactiveSecurityWebFilterChain(
// ServerHttpSecurity http) {
// http
// .authorizeExchange(authorize -> authorize.pathMatchers("/api/tacos",
// "/orders").hasAuthority(
// "USER").anyExchange().permitAll());

// return http.build();
// }
// }
