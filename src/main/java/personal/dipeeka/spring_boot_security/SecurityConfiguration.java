package personal.dipeeka.spring_boot_security;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.test.context.web.WebAppConfiguration;

// we have added below annotation to tell spring security that this is a web security configuration. web security is one the ways ,
// others are method/application security. WE need to do the same thing if we are configuring security for web application.
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//now add the new users and roles to override default behavior
		auth.inMemoryAuthentication()
		.withUser("blah")
		.password("blah")
		.roles("USER")
		.and()
		.withUser("foo")
		.password("foo")
		.roles("ADMIN");
		
		// above we have used builder pattern n method chaining, so we can use and method which returns object of same state as 
		// inMemoryAuthentication. So we getting hold of an object that we can start the user configuration chain with here. 
		// so just by using and method we can add bunch of users, passwords n roles
		
		// as shown above , we have added password as clear text which is not advisable. The passwords should be saved in encoded format.
		//The concept is password hashing.
		//we can achieve same using spring security creating a bean of type PasswordEncoder
	
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
		// the above NoOpPasswordEncoder is basically a PasswordEncoder which does nothing. should not be used in real application
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		//now add the new users and roles to override default behavior
		http.authorizeRequests()
		.antMatchers("/**").hasRole("ADMIN")
		.and().formLogin();
		// "/**" matches all paths
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//now add the new users and roles to override default behavior
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll()
		// key is to have the more restricted url at the top followed by less restrictive urls. if we put less restrictive at the top, it is going to match all the requests and it will never match more restrictive stuff
		//.antMatchers("/","static/css","static/js").permitAll();
		//above line means , user is allowed to access all the static assets like css,js . permit all means user is not w/o authentication
		.and().formLogin();
		
	}
}
