/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static String[] WHITE_LIST = {
            "**/.eot", "**/.ttf", "**/.woff",
            "/assets/**", "/webjars/**", "**/.css", "**/.js", "**/.png"
    };

    private static final String[] H2_WHITELIST = {
            "/h2-console/**"
    };

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.granted-auth-query}")
    private String rolesQuery;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(WHITE_LIST).permitAll()
                .antMatchers(H2_WHITELIST).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                //StudentFacing Routes
                .antMatchers("/StudentFacing/**").hasAnyAuthority("ACCESS_STUDENT_PROFILE, ACCESS_STUDENT_FEE")

                //Backend Routes
                .antMatchers("/SLMS/**").hasAnyAuthority("SUPER_ADMIN, ACCESS_ADMIN_PANEL ")
                .anyRequest()

                .authenticated()
                .and()

                .formLogin()
                    .loginPage("/login")
                    .successHandler(successHandler())
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/", true)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .and()

                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .and()

                .exceptionHandling(); //.accessDeniedPage("/access-denied");
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .mvcMatchers("/assets/**", "/webjars/**", "**/.css", "**/.js", "**/.png");
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }

}
