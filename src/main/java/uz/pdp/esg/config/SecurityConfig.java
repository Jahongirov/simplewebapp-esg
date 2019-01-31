package uz.pdp.esg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import uz.pdp.esg.service.AuthService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/index","/user/index","/user/about/about","/user/","/user/video/list","/user/news/list","/user/posts/list","/user/postById","/user/newsById").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/moderator/**").access("hasRole('ROLE_MODERATOR')")
                .and().formLogin().loginPage("/sign/in")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccessHandler()).failureForwardUrl("/sign/in");
        http.authorizeRequests()
                .and().logout().logoutUrl("/logout")
                .and().rememberMe().rememberMeParameter("remember-me").useSecureCookie(false);
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler handler = (httpServletRequest, httpServletResponse, authentication) -> {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                    httpServletResponse.sendRedirect("/admin/");
                    break;
                } else if ("ROLE_USER".equals(authority.getAuthority())) {
                    httpServletResponse.sendRedirect("/user/index");
                    break;
                } else if ("ROLE_MODERATOR".equals(authority.getAuthority())) {
                    httpServletResponse.sendRedirect("/moderator/index");
                    break;
                }
            }
        };

        return handler;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }
}
