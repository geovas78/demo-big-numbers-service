package com.gvasilski.ms;

/**
 * 
 * Constants used for easy access all across the service
 * 
 * @author Gerogi Vasilski
 *
 */
public class Constants {
	
	/*
	 * Here we just have the version of this api, it is a good practice
	 * as it may change in future, but this one still supported.
	 */
	public static final String API_VERSION = "/v1";
	
	/*
	 * Simple used for pinging the service to see if it is up and running
	 */
	public static final String HOME_MAPPING = "/";
	
	/*
	 * Math operation on big numbers
	 */
	public static final String NUM_OPERATIONS = API_VERSION + "/math";

}
