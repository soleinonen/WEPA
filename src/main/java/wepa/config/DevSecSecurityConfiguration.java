package wepa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import wepa.service.CustomUserDetailsService;

@Profile("devsec")
@Configuration
public class DevSecSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Pyyntöjä ei tarkasteta
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .antMatchers(HttpMethod.GET,"/css/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/feed").permitAll().and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}