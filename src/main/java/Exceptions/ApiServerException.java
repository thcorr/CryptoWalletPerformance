package Exceptions;

public class ApiServerException extends Exception {

	public ApiServerException(int responseCode, String message) {
		super("HTTP Response: " + responseCode + " " + message);
	}
	
}
