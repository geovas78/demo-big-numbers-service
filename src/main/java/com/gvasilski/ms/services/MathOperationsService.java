package com.gvasilski.ms.services;

import java.math.BigInteger;
/**
 * 
 * Interface contract for mathematical operation on numbers
 * 
 * @author Georgi Vasilski
 *
 */
public interface MathOperationsService {
	
	/**
	 * Multiplying large numbers
	 * @param num1 String representation of whole Number that have to multiplied
	 * @param num2 String representation of whole Number that multiplies a given number 
	 * @return Result of two whole big numbers multiplication
	 */
	public BigInteger multiply(String num1, String num2);

}
