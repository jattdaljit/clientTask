package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mckesson.validation.passwordvalidator.PasswordValidation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordValidation.class)
@WebAppConfiguration
public class PasswordControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void containInvalidCharacterAndSequence() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "passss@")).andExpect(status().isBadRequest());
	}

	@Test
	public void containInvalidSequenceAndLength() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "passss1wwworddd"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void containInvalidLengthAndCharacter() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "paswordpasword@"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void SendingNullString() throws Exception {
		String password = null;
		this.mockMvc.perform(post("/validatePassword").param("password", password)).andExpect(status().isBadRequest());
	}

	@Test
	public void LengthCharactersSequenceAreValid_Test1() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "pasword1"))
				.andExpect(content().string("password is valid"));
	}

	@Test
	public void LengthCharactersSequenceAreValid_Test2() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "123helo1"))
				.andExpect(content().string("password is valid"));
	}

	@Test
	public void LengthCharactersSequenceAreValid_Test3() throws Exception {
		this.mockMvc.perform(post("/validatePassword").param("password", "hithere123"))
				.andExpect(content().string("password is valid"));
	}
}