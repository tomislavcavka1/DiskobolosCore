package hr.diskobolos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import hr.diskobolos.config.security.jwt.JwtAuthenticationEntryPoint;
import hr.diskobolos.config.security.authentication.AuthenticationInfoRepository;
import hr.diskobolos.config.security.authentication.CustomLogoutHandler;
import hr.diskobolos.config.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Basic Spring security configuration
 *
 * @author Tomislav Čavka
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private AuthenticationInfoRepository authenticationInfoRepository;

    @Autowired
    private CustomLogoutHandler customLoginHandler;

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(authenticationInfoRepository)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/authentication/**").permitAll()
                .antMatchers("/sports/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/locations/**").permitAll()
                .antMatchers("/memberRegister/**").permitAll()
                .antMatchers("/evaluation/**").permitAll()
                .antMatchers("/dashboard/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(customLoginHandler)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        // Custom JWT based security filter
        httpSecurity.addFilterAfter(authenticationTokenFilterBean(), LogoutFilter.class);

        // Disable page caching
        httpSecurity.headers().cacheControl();

        // Disable X-Frame-Options response header (needed for H2 Console)
        httpSecurity.headers().frameOptions().disable();
    }
}
