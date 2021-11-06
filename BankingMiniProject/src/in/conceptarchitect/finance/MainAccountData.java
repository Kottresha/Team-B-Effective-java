package in.conceptarchitect.finance;

public class MainAccountData {
	
	private String AccountType;
	private int AccountNumber;
	private int Bankid;
	
	
	public MainAccountData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainAccountData(String accountType, int accountNumber, int bankid) {
		super();
		AccountType = accountType;
		AccountNumber = accountNumber;
		Bankid = bankid;
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
	public int getBankid() {
		return Bankid;
	}
	public void setBankid(int bankid) {
		Bankid = bankid;
	}
	@Override
	public String toString() {
		return "MainAccountData [AccountNumber=" + AccountNumber + ",AccountType=" + AccountType + ",  Bankid=" + Bankid
				+ "]";
	}
	
	

}
