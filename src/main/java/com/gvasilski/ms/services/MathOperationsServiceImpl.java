package com.gvasilski.ms.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvasilski.ms.LimitNumbersDigit;

/**
 * Service that processes mathematical operation on large numbers
 * 
 * @author Georgi Vasilski
 *
 */
@Service
public class MathOperationsServiceImpl implements MathOperationsService {
	
	@Autowired
	private LimitNumbersDigit limit;

	@Override
	public BigInteger multiply(String num1In, String num2In) {
		BigInteger num1 = convertToBigInteger(num1In);
		BigInteger num2 = convertToBigInteger(num2In);
		return num1.multiply(num2);
	}

	protected BigInteger convertToBigInteger(String val) {
		// first check if the string is not empty or null
		if(val == null || val.trim().isEmpty()) {
			throw new IllegalArgumentException("Value could not be null or empty");
		}
		// let's check now if this string is a number
		boolean ok = isNumeric(val);
		if(!ok) {
			throw new IllegalArgumentException(val + " isn't a number.");
		}
		
		// make some restrictions on how big the number could be
		// and default is configured to 10 in application.properties
		// TODO: it can also be set as a env variable or system property.
		int allowed = limit.getDigitsLimit();
		if(val.startsWith("-")) {
			allowed += 1;
		}
		if(val.length() > allowed) {
			throw new IllegalArgumentException( val + " is too big. Allowed upto " + allowed+ " digits");
		}
		
		return new BigInteger(val);
	}
	
	/**
	 * Checking whether the string has a numeric value
	 * @param str String to be tested
	 * @return True if its value is numeric, False if not.
	 */
	private boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

}
