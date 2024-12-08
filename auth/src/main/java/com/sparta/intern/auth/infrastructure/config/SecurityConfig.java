package com.sparta.intern.auth.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // CSRF 비활성화 (REST API는 stateless 방식이기 때문에)
                .csrf(csrf -> csrf.disable())

                // URL별 권한 설정
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            // /user/** 경로는 인증된 사용자만 접근 가능
                            .requestMatchers("/user/**").authenticated()

                            // /manager/** 경로는 ADMIN, MANAGER 역할을 가진 사용자만 접근 가능
                            .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")

                            // /admin/** 경로는 ADMIN 역할을 가진 사용자만 접근 가능
                            .requestMatchers("/admin/**").hasRole("ADMIN")

                            // 나머지 모든 요청은 누구나 접근 가능
                            .anyRequest().permitAll();
                })

                // 로그인 페이지 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // 커스텀 로그인 페이지 설정
                )

                // 설정 마무리
                .build();
    }
}
