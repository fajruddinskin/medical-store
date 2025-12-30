package com.medicalstore.config;

import com.medicalstore.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expose AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            UserService userService
    ) throws Exception {

        http
                // ❌ Disable CSRF only if you're not using POST forms extensively
                // ✅ (Logout still works safely)
                .csrf(csrf -> csrf.disable())

                // 🔐 Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/signup",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // 🔑 Login config
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                // 🚪 LOGOUT CONFIG (IMPORTANT PART)
                .logout(logout -> logout
                        .logoutUrl("/logout")                  // POST /logout
                        .logoutSuccessUrl("/login?logout")    // redirect after logout
                        .invalidateHttpSession(true)          // destroy session
                        .clearAuthentication(true)            // clear auth
                        .deleteCookies("JSESSIONID")           // remove cookie
                        .permitAll()
                )

                // 👤 UserDetailsService
                .userDetailsService(userService);

        return http.build();
    }
}
