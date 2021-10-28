package in.conceptarchitect.finance;

public class Bank {
	
	String name; //name of the bank
	double interestRate;
	int lastId=0;
		
	public void creditInterst() {
		//credit interest to all accounts
		for(int i=1;i<=lastId;i++) {
			
			accounts[i].creditInterest(interestRate);
		}
	}
	
	
	public Bank(String name, double interestRate) {
		super();
		this.name = name;
		this.interestRate = interestRate;
	}
	
	BankAccount [] accounts= new BankAccount[1000];
	
	public int openAccount(String name, String password, double amount) {
		int accountNumber= ++ lastId;
		BankAccount account= new BankAccount(accountNumber, name, password,amount);
		accounts[accountNumber] = account; 
		return accountNumber;
	}
	
	private BankAccount getAccountByNumber(int accountNumber) {
		if(accountNumber>0 && accountNumber<=lastId)
			return accounts[accountNumber];
		else
			return null;
	}
	
	public boolean deposit(int accountNumber, double amount) {
		
		BankAccount account = getAccountByNumber(accountNumber);
		
		return account.deposit(amount);
	}

	public boolean withdraw(int accountNumber,double amount,String password) {
		BankAccount BA=getAccountByNumber(accountNumber);
		
		return BA.withdraw(amount, password);
	}
	
	public boolean transferto(int accountNumber,double amount,String password,BankAccount target) {
		BankAccount BA=getAccountByNumber(accountNumber);	
		
		return BA.transferTo(amount, password, target);
		
		
	}
	
	public boolean closeAccount(int accountNumber) {
		accountNumber=(Integer) null;
		if(accountNumber==0) {
			return true;
		}else {
		
		return false;
		
	}
	}
	
	
	
	

}
