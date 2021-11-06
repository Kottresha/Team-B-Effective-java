package in.conceptarchitect.finance.exceptions;

public class InvalidAccountTypeException extends BankingException {
	
	String accountType;
	

	public String getAccountType() {
		return accountType;
	}

	public InvalidAccountTypeException(String accountType) {
		this(accountType,"Invalid Account Type :"+accountType); //passes parameter to other constructor of the same class
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String accountType, String message) {
		super(0, message);
		// TODO Auto-generated constructor stub
		this.accountType=accountType;
	}

	public InvalidAccountTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
