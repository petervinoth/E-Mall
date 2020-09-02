package com.vir.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	 private final String USERS_QUERY = "select username, password, enabled from mall where username=?";
	 private final String ROLES_QUERY = "select username, role from mall where username=?";


	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery(USERS_QUERY)
			.authoritiesByUsernameQuery(ROLES_QUERY)
			;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		   // .antMatchers("/home").permitAll()
		    
		.antMatchers("/").permitAll()
		   .antMatchers("/login").permitAll()
		   .antMatchers("/signup").permitAll()
		   .antMatchers("/forgotpassword").permitAll()
			
		   .antMatchers("/useradd").permitAll()
		   .antMatchers("/home").permitAll()
		   
			
			  
		   
		   
		   
		   .antMatchers("/useradd","/admin","/liststaff","/utilityReport","/utilityReport").hasRole("ADMIN").anyRequest()
		   
		   
		   
		   
		   .authenticated().and().csrf().disable()
			   .formLogin().loginPage("/login").failureUrl("/Login?error=true")
			  .defaultSuccessUrl("/home")
			  
	       
			
			   .permitAll(true)
			   .usernameParameter("username")
			   .passwordParameter("password")
			  
			
			   .and().logout()
			   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			   .logoutSuccessUrl("/login").and()
			 			.exceptionHandling().accessDeniedPage("/403");
			
	}
	

}
