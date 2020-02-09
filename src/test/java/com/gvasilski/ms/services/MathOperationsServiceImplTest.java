package com.gvasilski.ms.services;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MathOperationsServiceImplTest {
	
	@Autowired
	private MathOperationsServiceImpl moService;

	@Test
	void testMultiplyAllGoodPositiveNumbers() {
		BigInteger result = moService.multiply("5000000000", "5000000000");
		assertEquals("25000000000000000000", result.toString());
	}
	
	@Test
	void testMultiplyAllGoodNegativeNumbers() {
		BigInteger result = moService.multiply("-5000000000", "-5000000000");
		//we are expecting positive number as the two numbers both are negative
		assertEquals("25000000000000000000", result.toString());
	}
	
	@Test
	void testMultiplyAllGoodNumbers() {
		BigInteger result = moService.multiply("5000000000", "-5000000000");
		//we are expecting negative number as the one of the numbers is negative
		assertEquals("-25000000000000000000", result.toString());
	}
	
	@Test
	void testConvertToBigIntegerGoodPositive() throws Exception {
		BigInteger value = moService.convertToBigInteger("256783");
		assertEquals(BigInteger.valueOf(256783), value);
	}
	
	@Test
	void testConvertToBigIntegerGoodNegative() throws Exception {
		BigInteger value = moService.convertToBigInteger("-256783");
		assertEquals(BigInteger.valueOf(-256783), value);
	}
	
	@Test
	void testConvertToBigIntegerBadNegative() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			moService.convertToBigInteger("--654346697");
		});
	}
	
	

	@Test
	void testConvertToBigIntegerExceededDigitNumber() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				moService.convertToBigInteger("100000000000000");
			});
	}
	
	@Test
	void testConvertToBigIntegerNotANumber() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				moService.convertToBigInteger("hallo123");
			});
	}
	
	@Test
	void testConvertToBigIntegerSpecialChar() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				moService.convertToBigInteger("£$%^&^%$&^%$(&^");
			});
	}
	
	@Test
	void testConvertToBigIntegerNull() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				moService.convertToBigInteger(null);
			});
	}
	
	@Test
	void testConvertToBigIntegerEmpty() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				moService.convertToBigInteger("");
			});
	}

}
