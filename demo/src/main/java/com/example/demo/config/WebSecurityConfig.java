package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()//WebMvcConfig에서 이미 설정했으모로 기본 cors 설정.
            .and()
            .csrf()//CSRF는 현재 사용하지 않으므로 disable
                .disable()
            .httpBasic()// Token을 사용하므로 basic 인증 disable
                .disable()
            .sessionManagement() //Session 기반이 아님을 선언
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests() // '/'와 'auth/**'는 경로 인증안해도 됨.
                .antMatchers("/","/auth/**").permitAll()
            .anyRequest()
                .authenticated();

        http.addFilterAfter(
            jwtAuthenticationFilter,
            CorsFilter.class
        );
    }
}
