package com.uxpsystems.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthentication userAuthentication;

	/*
	 * @Autowired private AppUserDetailsService appUserDetailsService;
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable() .authorizeRequests() .antMatchers("/api/user/**")
		 * .hasAnyRole("ADMIN", "USER") .and().httpBasic() .realmName("MY APP REALM")
		 * .authenticationEntryPoint(userAuthentication);
		 */
		http.csrf().disable();
		 
        // All requests send to the Web Server request must be authenticated
        http.authorizeRequests().anyRequest().authenticated();
 
        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(userAuthentication);
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         
        UserDetails u1 = createUser("admin", "ADMIN");
        UserDetails u2 = createUser("user", "USER");
 
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
        mngConfig = auth.inMemoryAuthentication();
        mngConfig.withUser(u1);
        mngConfig.withUser(u2);
    }

	private UserDetails createUser(String userName, String role) {
		String password = userName;
 
        String encrytedPassword = this.passwordEncoder().encode(password);
        System.out.println("Encoded password of "+userName+"=" + encrytedPassword);
        UserDetails u1 = User.withUsername(userName).password(encrytedPassword).roles(role).build();
		return u1;
	}
}
