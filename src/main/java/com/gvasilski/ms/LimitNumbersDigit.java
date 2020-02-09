package com.gvasilski.ms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LimitNumbersDigit {
	
	@Value("${limit.numbers.digit}")
	private int digitsLimit;
	
	public void setDigitsLimit(int i) {
		this.digitsLimit = i;
	}
	
	public int getDigitsLimit() {
		return digitsLimit;
	}

}
