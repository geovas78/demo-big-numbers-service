package com.gvasilski.ms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gvasilski.ms.Constants;

/**
 * Controller that deals with service availability checks
 * 
 * @author Gerogi Vasilski
 *
 */
@Controller
@RequestMapping( value = Constants.HOME_MAPPING)
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DiskSpaceHealthIndicator dshi;
	
	/**
	 * HealthCheck endpoint used for quick check if service is running
	 * 
	 * @return Message  saying that the service is up and running
	 */
	@GetMapping( value = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Health> home() throws Exception{
		
		Health health = null;
		try {
			health = dshi.health();
		} catch(Exception e) {
			// log it for DevOps debug
			logger.error(e.getMessage(), e);
			// then return that something gone wrong
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		// check the status of the health check
		if(!Status.UP.equals(health.getStatus())) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();		
		}
		
		return ResponseEntity.ok(health);
	}
}
