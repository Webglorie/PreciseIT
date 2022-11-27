package com.preciseIT.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.GenericFilterBean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    class LoginPageFilter extends GenericFilterBean {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && ((HttpServletRequest)request).getRequestURI().equals("/portal/login")) {
                ((HttpServletResponse)response).sendRedirect("/portal/dashboard");
            }
            chain.doFilter(request, response);
        }

    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/users/**",
                        "/users/*",
                        "/users",
                        "/h2-console**",
                        "/h2-console",
                        "/h2-console/**",
                        "/portal/registration",
                        "/images/*",
                        "/css/*",
                        "/contact",
                        "/js/*",
                        "/images*",
                        "/user/register/**/",
                        "/portal/users",
                        "/portal/logout-succes",
                        "/portal/create-ticket",
                        "/portal/tickets",
                        "/authenticate/**/",
                        "/authenticate/**/**",
                        "/authenticate/*/*")
                .permitAll()
                .antMatchers(
                        "/portal/admin/**",
                        "/portal/tickets/create-ticket/**",
                        "/portal/tickets/create-ticket/**/**",
                        "portal/portal-createTicketByHelpdesk/**",
                        "/portal/create-ticketByHelpDesk/**",
                        "/portal/tickets/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/portal/login")
                .defaultSuccessUrl("/portal/dashboard")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override public AuthenticationManager authenticationManagerBean() throws Exception { return super.authenticationManagerBean(); }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

}