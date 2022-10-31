package com.portfolio.RAG.Security;

import com.portfolio.RAG.Security.Service.UserDetailsImpl;
import com.portfolio.RAG.Security.jwt.JwtEntryPoint;
import com.portfolio.RAG.Security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/*public class MainSecurity {

    @Autowired

    UserDetailsImplements userDetailsImplements;



    @Autowired

    JwtEntryPoint jwtEntryPoint;



    @Bean

    public JwtTokenFilter jwtTokenFilter() {

        return new JwtTokenFilter();

    }



    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }



    @Bean

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }



    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()

                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                .antMatchers("/**").permitAll()

                .anyRequest().authenticated();



        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);



        return http.build();

    }
}
*/

//ORIGINAL CON MODIFICACIONES PARA QUE FUNCIONE
public class MainSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsImpl userDetailsServicesImpl;
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServicesImpl).passwordEncoder(passwordEncoder());
    }

}


//ORIGINAL
/*
public class MainSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsImpl userDetailsServicesImpl;
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServicesImpl).passwordEncoder(passwordEncoder());
    }

}
*/

/* INTENTO CAMBIO DE CLASE MainSecurity para resolver problema con biblioteca obsoleta
public class MainSecurity {

    @Autowired     

    UserDetailsImplements userDetailsImplements;     

    @Autowired     

    JwtEntryPoint jwtEntryPoint;     

    

    @Bean     

    public JwtTokenFilter jwtTokenFilter() {         

        return new JwtTokenFilter();     

    }     

    

    @Bean     

    public PasswordEncoder passwordEncoder() {         

        return new BCryptPasswordEncoder();     

    }     

    

    @Bean     

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {         

        return authenticationConfiguration.getAuthenticationManager();     

    }     

    

    @Bean     

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {         

        http.cors()

                .and()

                .csrf()

                .disable()                 

                .exceptionHandling()

                .authenticationEntryPoint(jwtEntryPoint).and()                 

                .sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()                 

                .authorizeRequests()                 

                .antMatchers("/**")

                .permitAll()                 

                .anyRequest()

                .authenticated();         

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);         

        return http.build();     

    } 

}
*/