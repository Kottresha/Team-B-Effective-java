package in.conceptarchitect.finance.exceptions;

public class InsufficientBalanceException extends BankingException {
	
	double deficit;
	

	public double getDeficit() {
		return deficit;
	}

	public InsufficientBalanceException(int accountNumber,double deficit) {
		this(accountNumber,deficit,"Insufficient Balance");
		
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(int accountNumber, double deficit, String message) {
		super(accountNumber, message);
		this.deficit=deficit;
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
