package com.basmaonline.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.basmaonline.ws.shared.dto.UserDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

    @SpringBootTest
	@AutoConfigureMockMvc
	class UserControllerTest {
	
	
	    @Autowired
	    private MockMvc mockMvc;
	    
	   
	    @Test
        void saveUser() throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
            UserDto userDto = new UserDto("hana","hana","Hanae2@gmail.com","1234");
            this.mockMvc.perform(post("/users").contentType(APPLICATION_JSON).content(objectWriter.writeValueAsString(userDto))).andDo(print());
        }
}
	