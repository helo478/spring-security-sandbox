package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		final List<GrantedAuthority> tonyStarkAuthorities = new ArrayList<>();
		final List<GrantedAuthority> rhodeyAuthorities = new ArrayList<>();
		final List<GrantedAuthority> pepperPottsAuthorities = new ArrayList<>();
		final List<GrantedAuthority> philCoulsonAuthorities = new ArrayList<>();
		final List<GrantedAuthority> daisyJohnsonAuthorities = new ArrayList<>();
		
		tonyStarkAuthorities.add(new GrantedAuthorityImpl("stark_tower", "particle_accelerator", new String[] { "usage", "self_destruct" }));
		tonyStarkAuthorities.add(new GrantedAuthorityImpl("stark_tower", "armory", new String[] { "usage", "self_destruct" }));
		
		rhodeyAuthorities.add(new GrantedAuthorityImpl("stark_tower", "armory", new String[] { "usage" }));
		
		pepperPottsAuthorities.add(new GrantedAuthorityImpl("stark_tower", "particle_accelerator", new String[] { "usage" }));
		
		philCoulsonAuthorities.add(new GrantedAuthorityImpl("shield_base", "containment", new String[] { "usage", "self_destruct" }));
		philCoulsonAuthorities.add(new GrantedAuthorityImpl("shield_base", "armory", new String[] { "usage", "self_destruct" }));
		
		daisyJohnsonAuthorities.add(new GrantedAuthorityImpl("shield_base", "containment", new String[] { "usage" }));
		daisyJohnsonAuthorities.add(new GrantedAuthorityImpl("shield_base", "armory", new String[] { "usage" }));

		auth.inMemoryAuthentication() //@formatter:off
				.withUser("tony_stark")
					.password("password")
					.roles("USER")
					.authorities(tonyStarkAuthorities)
			.and()
				.withUser("rhodey")
					.password("password")
					.roles("USER")
					.authorities(rhodeyAuthorities)
			.and()
				.withUser("pepper_potts")
					.password("password")
					.roles("USER")
					.authorities(pepperPottsAuthorities)
			.and()
				.withUser("phil_coulson")
					.password("password")
					.roles("USER")
					.authorities(philCoulsonAuthorities)
			.and()
				.withUser("daisy_johnson")
					.password("password")
					.roles("USER")
					.authorities(daisyJohnsonAuthorities); //@formatter:on
	}

}