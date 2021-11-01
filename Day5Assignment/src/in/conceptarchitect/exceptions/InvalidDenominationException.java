package in.conceptarchitect.exceptions;

public class InvalidDenominationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDenominationException (String errorMessage) {
		super(errorMessage);
	}
}