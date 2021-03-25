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
import com.exemple.crud.models.ObjProdContact;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class CrudApplicationTestsControllerCont {

	private MockMvc mockMvc;

	@Autowired
	ControllerProfessionals controllerPro;

	@Autowired
	ContactsController controllerCont;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerCont).build();
	}

	@Test
	void testPOSTAlterUserCount() throws Exception {
		ObjProdContact objCont = new ObjProdContact(1, "caue", "1111111", 1);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/contacts/contact").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(objCont)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPUTAlterUserCount() throws Exception {
		ObjProdContact objCont = new ObjProdContact(1, "caue", "1111111", 1);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/contacts/1").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(objCont)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGETFindByIdCont() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/contacts/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGETDeleteCont() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
