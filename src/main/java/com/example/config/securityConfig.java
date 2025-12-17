package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class securityConfig {
    /*
    * i want to make custom userdetailsService and i want spring to  inject it
    * and here spring doesnot know which to inject then he will use the default
    * it is easy make a class that impelement this
    *
    * */
    private final UserDetailsService userDetailsService;

    public securityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // each request that crate new sessionId
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    //here i tell spring not take the user name and password from app proprites take it form this class
/*    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1=User.withDefaultPasswordEncoder()
                .username("amrNada")
                .password("amrnada2004")
                .roles("USER")
                .build();
        UserDetails user2=User
                .withDefaultPasswordEncoder()
                .username("ahmedNada")
                .password("ahmednada2001")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }*/
    @Bean
    // this is class that impl the interface AuthenticationProvider
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
