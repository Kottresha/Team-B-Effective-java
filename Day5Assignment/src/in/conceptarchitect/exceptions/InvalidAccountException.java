package in.conceptarchitect.exceptions;

public class InvalidAccountException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountException (String errorMessage) {
		super(errorMessage);
	}

	public InvalidAccountException(InvalidAccountException iae) {
		super(iae);
	}

}
