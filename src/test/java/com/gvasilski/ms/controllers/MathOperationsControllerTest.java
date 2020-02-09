package com.gvasilski.ms.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gvasilski.ms.Constants;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MathOperationsControllerTest {
	
	@LocalServerPort
	protected int port;
	
	@Autowired
	protected TestRestTemplate restTemplate;

	@Test
	void testALlGood() {
		String uri = "/inputs-multiply?number=450234&multiplier=123857";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals("55764632538", response.getBody());
	}
	
	@Test
	void testALlGoodNegatives() {
		String uri = "/inputs-multiply?number=-450234&multiplier=-123857";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		assertEquals("55764632538", response.getBody());
	}
	
	@Test
	void testNoParams() {
		String uri = "/inputs-multiply";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
	}
	
	@Test
	void testOneParamsMissing() {
		String uri = "/inputs-multiply?number=67656765";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
	}
	
	@Test
	void testOneParamNotNumber() {
		String uri = "/inputs-multiply?number=67656765&multiplier=hallo";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCodeValue());
		assertEquals("hallo isn't a number.", response.getBody());
	}
	
	@Test
	void testOneParamTooBig() {
		String uri = "/inputs-multiply?number=67656765&multiplier=16434678975433";
		ResponseEntity<String> response = this.restTemplate.getForEntity(createURL(uri), String.class);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCodeValue());
		assertEquals("16434678975433 is too big. Allowed upto 10 digits", response.getBody());
	}
	
	private String createURL(String uri) {
		return "http://localhost:"+port+Constants.NUM_OPERATIONS+uri;
	}

}
