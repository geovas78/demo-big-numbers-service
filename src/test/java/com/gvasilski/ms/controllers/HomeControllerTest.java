package com.gvasilski.ms.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HomeControllerTest {
	
	@LocalServerPort
	protected int port;
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	@Autowired ObjectMapper mapper;

	@Test
	void testPing() throws Exception{
		
		ResponseEntity<String> response = this.restTemplate.getForEntity(
				"http://localhost:"+port+"/ping", String.class);
		JsonNode json = mapper.readTree(response.getBody());
		
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertEquals("UP", json.get("status").asText());
	}

}