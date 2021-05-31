package com.example.sprint3.configuration;

import com.example.sprint3.services.jwt.JwtAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService jwtAccountDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager để nó load người dùng phù hợp với thông tin xác thực
        // sử dụng BCryptPasswordEncoder
        auth.userDetailsService(jwtAccountDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // tắt csrf
        httpSecurity.cors().and().csrf().disable()
                // tắt xác thực cho các trang này
                .authorizeRequests().antMatchers("/login",
                "/allfood",
                "/food-detail/**",
                "/check-duplicate",
                "/save-account",
                "/get-comment/**",
                "/foodbyname/**",
                "/foodbycategory/**"
        ).permitAll().antMatchers(HttpMethod.OPTIONS,
                "/save-comment",
                "/save-order",
                "/foodsCart/**",
                "/remove-order/**").hasAuthority("MEMBER").antMatchers().hasAnyAuthority("ADMIN").
                // các trang còn lại phải xác thực
                        anyRequest().fullyAuthenticated().and().
                // đảm bảo sử dụng đúng session Stateless;
                // session sẽ không được sử dụng để lưu trữ thông tin người dùng
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // thêm bộ lọc để xác thực token
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
