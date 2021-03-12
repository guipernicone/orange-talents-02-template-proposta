package com.zup.orange.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Route access configuration
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable csrf (cross site request forgery)
                .csrf().disable()
                // Entry Points authorize
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()

                .and()

                // Disable the creation of sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //Configure to use auth2ResourceServer
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
