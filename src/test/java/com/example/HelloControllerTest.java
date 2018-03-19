package com.example;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}

	@Test
	@WithMockUser("tony_stark")
	public void tonyStarkCanSelfDestructTheStarkTowerArmory() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/stark_tower/armory/self_destruct").accept(MediaType.TEXT_HTML)) //@formatter:off
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("You are authorized to engage self-destruct of armory in Stark Tower")
		)); //@formatter:on
	}

	@Test
	@WithMockUser("tony_stark")
	public void tonyStarkCanSelfDestructTheStarkTowerParticleAccelerator() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/stark_tower/particle_accelerator/self_destruct").accept(MediaType.TEXT_HTML)) //@formatter:off
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("You are authorized to engage self-destruct of particle_accelerator in Stark Tower")
		)); //@formatter:on
	}

	@Test
	@WithMockUser("tony_stark")
	public void tonyStarkCanUseTheStarkTowerArmory() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("tony_stark")
	public void tonyStarkCanUseTheStarkTowerParticleAccelerator() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("rhodey")
	public void rhodeyCannotSelfDestructTheStarkTowerArmory() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("rhodey")
	public void rhodeyCannotSelfDestructTheStarkTowerParticleAccelerator() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("rhodey")
	public void rhodeyCanUseTheStarkTowerArmory() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("rhodey")
	public void rhodeyCannotUseTheStarkTowerParticleAccelerator() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("phil_coulson")
	public void philCoulsonHasNoPrivilegesForStarkTower() {

		fail("not yet implemented");
	}

	@Test
	@WithMockUser("tony_stark")
	public void tonyStarkHasNoPrivilegesForShieldBase() {

		fail("not yet implemented");
	}

}