package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.MobileleleUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //define which requests are allowed and which are not
                .authorizeRequests()
                //everyone could download static resources (css, js, images)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                //everyone can log in and register
                .antMatchers("/", "/users/login", "/users/register")
                .permitAll()
                .antMatchers("/offers/add")
                .authenticated()
                .antMatchers("offers/**")
                .permitAll()
                .antMatchers("/maintenance")
                .permitAll()
                //all other pages are available for logger in users
                .anyRequest()
                .authenticated()
                .and()
                //configuration of form login
                .formLogin()
                //the custom login form
                .loginPage("/users/login")
                //the name of the username form field
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                //the name of the password form field
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //where to go in case if the login is successful
                .defaultSuccessUrl("/")
                //where to go in case login failed
                .failureForwardUrl("/users/login-error")
                .and()
                //configure logout
                .logout()
                //which is the logout url, must be POST request
                .logoutUrl("/users/logout")
                //on logout go
                .logoutSuccessUrl("/")
                //invalidate the session and delete the cookies
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
//                .and()
//        //allow oauth login
//                .oauth2Login()
//                .loginPage("/users/login");


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new MobileleleUserDetailsService(userRepository);
    }
}
