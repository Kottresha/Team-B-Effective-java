package in.conceptarchitect.exceptions;


public class BankingExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;

	int accountNumber;
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public BankingExceptions(int accountNumber) {
		// TODO Auto-generated constructor stub
		this(accountNumber, "Banking Exception");
	}

	public BankingExceptions(int accountNumber,String message) {
		super(message);		//passes super class constructor
		this.accountNumber=accountNumber;
		// TODO Auto-generated constructor stub
	}

	public BankingExceptions(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BankingExceptions(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BankingExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
