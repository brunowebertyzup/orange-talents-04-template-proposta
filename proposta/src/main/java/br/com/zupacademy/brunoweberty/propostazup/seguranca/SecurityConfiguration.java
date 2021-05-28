package br.com.zupacademy.brunoweberty.propostazup.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/propostas**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.GET, "/v1/propostas/**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.POST, "/v1/bloqueios**").hasAuthority("SCOPE_propostas")
                .antMatchers(HttpMethod.POST, "/v1/viagens**").hasAuthority("SCOPE_propostas")
                .anyRequest().authenticated()
                .and().cors().disable().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}