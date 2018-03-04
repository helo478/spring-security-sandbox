package com.example;

import java.io.Serializable;
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

		final GrantedAuthority ownerAndAdminOfRickLabParticleAccelerator = new GrantedAuthorityImpl("rick_lab",
				"particle_accelerator", new String[] { "owner", "admin" });

		final GrantedAuthority administratorOfRickLabParticleAccelerator = new GrantedAuthorityImpl("rick_lab",
				"particle_accelerator", new String[] { "admin" });

		final GrantedAuthority ownerAndAdminOfVindicatorBaseParticleAccelerator = new GrantedAuthorityImpl(
				"vindicator_base", "particle_accelerator", new String[] { "owner", "admin" });

		final GrantedAuthority adminOfVindicatorBaseWasteDisposal = new GrantedAuthorityImpl("vindicator_base",
				"waste_disposal", new String[] { "admin" });

		final List<GrantedAuthority> rickAuthorities = new ArrayList<>();
		rickAuthorities.add(ownerAndAdminOfRickLabParticleAccelerator);
		rickAuthorities.add(administratorOfRickLabParticleAccelerator);

		final List<GrantedAuthority> mortyAuthorities = new ArrayList<>();
		mortyAuthorities.add(administratorOfRickLabParticleAccelerator);
		mortyAuthorities.add(ownerOfVindicatorBaseParticleAccelerator); // TODO: fix this

		auth.inMemoryAuthentication() //@formatter:off
				.withUser("rick")
					.password("password")
					.roles("USER")
					.authorities(rickAuthorities)
			.and()
				.withUser("morty")
					.password("password")
					.roles("USER")
					.authorities(mortyAuthorities); //@formatter:on
	}

}