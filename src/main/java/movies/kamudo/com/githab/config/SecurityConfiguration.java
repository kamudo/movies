package movies.kamudo.com.githab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("user1").roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password("user2").roles("USER");
        auth.inMemoryAuthentication().withUser("user3").password("user3").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }
		     
	
    protected void configure(HttpSecurity http) throws Exception {
      http.httpBasic().and().authorizeRequests()
      .antMatchers(HttpMethod.POST,"/comments").hasRole("USER")
      .antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")
      .and().csrf().disable().headers().frameOptions();
    }
}