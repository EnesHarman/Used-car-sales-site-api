package com.sahibinden.arac.core.security;


import com.sahibinden.arac.core.security.filters.CustomAuthenticationFilter;
import com.sahibinden.arac.core.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/auth/login").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/vehicle/add").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/vehicle/update/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/comment/add/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/comment/delete/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/vehicle/publish/**").hasAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/vehicle/unpublished/list/**").hasAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/vehicle/unpublish/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/vehicle/compare/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/vehicle/list").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/fueltype/add/**").hasAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/fueltype/delete/**").hasAuthority("ROLE_MANAGER");

        http.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**" ,
                        /*Probably not needed*/ "/swagger.json")
                .permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return  super.authenticationManagerBean();
    }


}
