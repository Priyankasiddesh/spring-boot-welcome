package io.priyanka.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguaration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// set your configuaration on auth object
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("Admin")
		.and().withUser("user").password("user").roles("User");//method chaining
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/admin").hasRole("Admin")
			.antMatchers("/user").hasAnyRole("User","Admin")
			.antMatchers("/").permitAll().and().formLogin();
	}
	
	

}
