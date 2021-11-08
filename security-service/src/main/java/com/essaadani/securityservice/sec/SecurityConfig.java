package com.essaadani.securityservice.sec;

import com.essaadani.securityservice.sec.filter.JWTAuthenticationFilter;
import com.essaadani.securityservice.sec.filter.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2 form don't have csrf token so we can't use it while spring security based on csrf
        // to use it we have to disable csrf
        http.csrf().disable();

        // h2 forms using html frames & spring forbids forms with frames & it didn't let the content display
        http.headers().frameOptions().disable();

        // The STATELESS will ensure no session is created by Spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // any request with h2-console is permitted
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

        // any request require authentication
        http.authorizeRequests().anyRequest().authenticated();

        // add AuthenticationFilter
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));

        // add Authorization filter before
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
