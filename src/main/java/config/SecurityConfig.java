package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//버전 미스매칭인지 작동 안 됨(security-context.xml로 시작)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN");
//    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/")
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/loginForm")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/");
    }
}
