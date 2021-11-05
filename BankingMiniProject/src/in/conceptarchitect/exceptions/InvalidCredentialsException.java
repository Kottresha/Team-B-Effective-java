package in.conceptarchitect.exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends BankingExceptions {
	
	public InvalidCredentialsException(int accountNumber) {
		super(accountNumber,"Invalid Credentials");
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(int accountNumber, String message) {
		super(accountNumber, message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCredentialsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
