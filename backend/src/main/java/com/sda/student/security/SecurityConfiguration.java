package com.sda.student.security;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private UserDetailsService userDetailsService;

    private static final List<String> ORIGIN = Collections.singletonList("http://localhost:3000");
    private static final List<String> METHODS = Collections.singletonList("*");
    private static final List<String> HEADERS = Collections.singletonList("*");
    private static final long MAX_AGE = 3600L;

    //Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(customizer -> customizer.configurationSource(request -> getConfiguration()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/students").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/students/{studentId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/students/view/{studentId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/students/save").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/students/{studentId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/saveCategory").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/findAll").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/reviews/save/{studentId}").permitAll()
                        .anyRequest().permitAll()
                )
//                .formLogin(formLogin -> formLogin
//                        .permitAll()
//                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    //In memory Authentication
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails enes = User.builder()
//                .username("enesxhafa")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails kujtim = User.builder()
//                .username("kujtimdallnesi")
//                .password(passwordEncoder().encode("password"))
//                .roles("GUEST")
//                .build();
//
//        UserDetails agilda = User.builder()
//                .username("agildakulli")
//                .password(passwordEncoder().encode("agilda@@"))
//                .roles("GUEST")
//                .build();
//
//        UserDetails frida = User.builder()
//                .username("fridakoci")
//                .password(passwordEncoder().encode("1234"))
//                .roles("INTERN")
//                .build();
//        return new InMemoryUserDetailsManager(agilda,enes, kujtim,frida);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }


    private CorsConfiguration getConfiguration() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(ORIGIN);
        cors.setAllowedMethods(METHODS);
        cors.setAllowedHeaders(HEADERS);
        cors.setAllowCredentials(true);
        cors.setExposedHeaders(List.of("Authorization"));
        cors.setMaxAge(MAX_AGE);
        return cors;
    }

}
