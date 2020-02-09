package com.gvasilski.ms.controllers;

import java.math.BigInteger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gvasilski.ms.Constants;
import com.gvasilski.ms.services.MathOperationsService;


/**
 * Processes the requests from a client for mathematical
 * operations on numbers.
 * 
 * @author Georgi Vasilski
 *
 */
@Controller
@RequestMapping( value = Constants.NUM_OPERATIONS)
public class MathOperationsController {
	
	private static Logger logger = LoggerFactory.getLogger(MathOperationsController.class);
	
	@Autowired
	private MathOperationsService moService;

	/**
	 * Endpoint processing requests for multiplication of two numbers
	 * @param number The number that is to be multiplied
	 * @param multiplier The number that is the multiplier
	 * @return The result of these math operation
	 */
	@GetMapping(value = "/inputs-multiply")
	public ResponseEntity<?> multiply(
			@RequestParam(value = "number") @NotEmpty @NotNull String number,
			@RequestParam(value = "multiplier") @NotEmpty @NotNull String multiplier) {
		
		// As Srping boot will validate the query parameters for not being
		// null or an empty string, the we just pick up from there.
		logger.info("Received two parameters: param1 ["+number+"], param2["+multiplier+"].");
		try {
			// get the result
			BigInteger result = moService.multiply(number, multiplier);
			return ResponseEntity.ok(result);
		} catch(Exception ex) {
			// if we got any error while processing request or data
			// log it for DevOps debug and return the message to the client
			logger.error(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
		}
	}
}
