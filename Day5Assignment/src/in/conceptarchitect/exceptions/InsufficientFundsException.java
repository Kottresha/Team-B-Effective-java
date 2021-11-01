package in.conceptarchitect.exceptions;

public class InsufficientFundsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String errorMessage) {
		super(errorMessage);
	}

	public InsufficientFundsException(InsufficientFundsException ex) {
		super(ex);
	}
}