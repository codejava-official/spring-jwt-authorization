package net.codejava.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiTests {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	public void shouldCreateUser() throws JsonProcessingException, Exception {
		String email = "dave.Kumar@gmail.com";
		String password = "dave2022";
		User newUser = new User(email, password);
		
		ResultActions resultActions = mockMvc.perform(put("/users")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(newUser))
			);
		
		resultActions.andExpect(status().isCreated());
		resultActions.andDo(print());
		resultActions.andExpect(jsonPath("id", notNullValue()));
		resultActions.andExpect(jsonPath("email", is(email)));
	}
}
