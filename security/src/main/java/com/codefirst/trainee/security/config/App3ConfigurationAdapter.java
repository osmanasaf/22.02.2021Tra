package com.codefirst.trainee.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(3)
public class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
    public App3ConfigurationAdapter() {
        super();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/operator*")
                .authorizeRequests()
                .anyRequest()
                .hasRole("OPERATOR")

                .and()
                .formLogin()
                .loginPage("/loginOperator")
                .loginProcessingUrl("/operator_login")
                .failureUrl("/loginOperator?error=loginError")
                .defaultSuccessUrl("/operatorPage")

                .and()
                .logout()
                .logoutUrl("/operator_logout")
                .logoutSuccessUrl("/protectedLinks")
                .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .csrf().disable();
    }
}
