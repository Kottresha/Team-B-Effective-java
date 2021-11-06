package in.conceptarchitect.finance;

public class AccountData {
	
	private String AccountType;
	private int AccountNumber;
	private String password;
	private double balance;
	private String HolderName;
	
	
	
	public AccountData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountData(String accountType, int accountNumber, String holderName, String password, double balance) {
		super();
		AccountType = accountType;
		AccountNumber = accountNumber;
		this.password = password;
		this.balance = balance;
		HolderName = holderName;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public int getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getHolderName() {
		return HolderName;
	}
	public void setHolderName(String holderName) {
		HolderName = holderName;
	}
	@Override
	public String toString() {
		return "AccountData [AccountType=" + AccountType + ", AccountNumber=" + AccountNumber +", HolderName=" + HolderName + ", password=" + password
				+ ", balance=" + balance +  "]";
	}

	
	

}
