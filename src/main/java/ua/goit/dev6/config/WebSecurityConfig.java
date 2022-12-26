package ua.goit.dev6.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import ua.goit.dev6.account.UserRepository;
import ua.goit.dev6.error.CustomAccessDeniedHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private final UserRepository repository;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/register","/login").permitAll()
                        .requestMatchers("/css/*","/js/*").permitAll()
                        //line below add only for test purposes, after adding page with public notes this should be deleted
                        .requestMatchers("/testaccess").hasRole("ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/note/list",false)
                )
                .exceptionHandling((exception) ->
                        exception.accessDeniedHandler(accessDeniedHandler())
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/login"));
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() { return new CustomAccessDeniedHandler();
    }
}

