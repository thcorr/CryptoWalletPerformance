package Exceptions;

public class ApiInternalClientException extends Exception {

	public ApiInternalClientException(int responseCode, String message) {
		super("HTTP Response: " + responseCode + " " + message);
	}
	
}
