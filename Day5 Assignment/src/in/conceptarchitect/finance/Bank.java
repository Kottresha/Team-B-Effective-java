package in.conceptarchitect.finance;

public class Bank {
	
	String name; //name of the bank
	int lastId=0;
	int accountCount=0;
	
	double interestRate;
	
	

	BankAccount [] accounts= new BankAccount[100]; //MAX 100. May not be great for large banks
	
	
	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
	public void creditInterst() {
		//credit interest to all accounts
		for(int i=1;i<=lastId;i++) {
			
			accounts[i].creditInterest(interestRate);
		}
	}
	
	public  double getInterestRate() {
		return interestRate;
	}

	public  void setInterestRate(double interestRate) {
		
		BankAccount.interestRate = interestRate;
	}
	
	
	
	public Bank(String name, double interestRate) {
		super();
		this.name = name;
		this.interestRate = interestRate;
	}
	
	
	public int openAccount(String name,String accountType, String password, double amount) {
		int accountNumber= ++ lastId;
		BankAccount account= new BankAccount(accountNumber,accountType, name, password,amount);
		accounts[accountNumber] = account; //store this account in the array.
		accountCount++;
		return accountNumber;
	}
	
	BankAccount getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		if(accountNumber>0 && accountNumber<=lastId)
			return accounts[accountNumber];
		else
			return null;
	}
	
	public boolean deposit(int accountNumber, double amount) {
		
		BankAccount account = getAccountByNumber(accountNumber);
		if(account==null)
			return false;
		return account.deposit(amount);
	}

	public double getAccountBalance(int accountNumber) {
		// TODO Auto-generated method stub
		BankAccount account=getAccountByNumber(accountNumber);
		if(account==null)
			return Double.NaN;
		return account.getBalance();
	}

	public boolean transfer(int source, double amount, String password, int target) {
		// TODO Auto-generated method stub
		BankAccount s= getAccountByNumber(source);
		BankAccount t= getAccountByNumber(target);
		if(s==null)
			return false;
		
		if(t==null)
			return false;
		
		
		if(s.withdraw(amount,password))
			return t.deposit(amount);
		else
			return false;
		
	}

	public boolean closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccountByNumber(accountNumber);
		if(account==null)
			return false;
		if(!account.authenticate(password))
			return false;
		if(account.getBalance()<0)
			return false;
		//I have to remove the account from being an active account
		// let us set null value on the index where account existed.
		
		accounts[accountNumber]=null; //account is actually closed.
		
		accountCount--;
		return true;
	}

	public boolean withdraw(int accountNumber, int amount, String password) {
		// TODO Auto-generated method stub
		var account=getAccountByNumber(accountNumber);
		if(account==null)
			return false;
		return account.withdraw(amount, password);
	}
	

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
