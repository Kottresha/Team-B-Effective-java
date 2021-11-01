package in.conceptarchitect.exceptions;

public class InvalidCredentialsException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException (String errorMessage){
		super(errorMessage);
	}
	
	public InvalidCredentialsException (InvalidCredentialsException ex) {
		super(ex);
	}
	
}