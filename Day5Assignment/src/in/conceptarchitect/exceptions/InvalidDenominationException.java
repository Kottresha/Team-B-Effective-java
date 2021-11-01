package in.conceptarchitect.exceptions;

public class InvalidDenominationException extends Exception {
	
	public InvalidDenominationException (String errorMessage) {
		super(errorMessage);
	}
}