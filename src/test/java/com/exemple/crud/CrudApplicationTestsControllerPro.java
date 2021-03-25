package com.exemple.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import com.exemple.crud.controllers.ContactsController;
import com.exemple.crud.controllers.ControllerProfessionals;
import com.exemple.crud.models.ObjProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class CrudApplicationTestsControllerPro {

	private MockMvc mockMvc;

	@Autowired
	ControllerProfessionals controllerPro;

	@Autowired
	ContactsController controllerCont;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerPro).build();
	}

	@Test
	void testPOSTCreateUserPro() throws Exception {
		ObjProducer obj = new ObjProducer(1, "caue", 0, "1992-11-22T23:50:04", "2021-03-22T23:50:04", "teste");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/professionals/professional")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(obj)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPUTAlterUserPro() throws Exception {
		ObjProducer obj = new ObjProducer(1, "caue", 0, "1992-11-22T23:50:04", "2021-03-22T23:50:04", "teste");
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/professionals/1").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(obj)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGETFindByIdPro() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/professionals/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGETDeletePro() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/professionals/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
