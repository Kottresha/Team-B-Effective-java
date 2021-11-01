package in.conceptarchitect.finance;

public class  Bank {
	
	String name; //name of the Bank
	int lastId= 0;
	int accountCount=0;
	double interestRate;
	
	
	Account [] accounts= new Account[100]; //MAX 100. May not be great for large  s
	
	
	public  Bank (String name, double interestRate) {
		super();
		this.name = name;
		this.interestRate = interestRate;
	}
	
	public int openAccount(String name,String accountType, String password, double amount) {
		int accountNumber= ++ lastId;
		System.out.println(accountType);
		Account account= new Account(accountNumber, name, accountType, password,amount);
		accounts[accountNumber] = account; //store this account in the array.
		accountCount++;
		return accountNumber;
	}

	public int getAccountCount() {
		return accountCount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void creditInterst() {
		//credit interest to all accounts
		for(int i=1;i<=lastId; i++) {
			accounts[i].creditInterest(interestRate);
		}
	}
	
	public  double getInterestRate() {
		return interestRate;
	}

	public  void setInterestRate(double interestRate) {
		Account.interestRate = interestRate;
	}
	
	Account getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		if(accountNumber> 0 && accountNumber<=lastId)
			return accounts[accountNumber];
		else
			return null;
	}
	
	public boolean deposit(int accountNumber, double amount) {
		Account account = getAccountByNumber(accountNumber);
		if(account==null)
			return false;
		
		return account.deposit(amount);
	}

	public double getAccountBalance(int accountNumber) {
		Account account=getAccountByNumber(accountNumber);
		if(account==null)
			return Double.NaN;
		
		return account.getBalance();
	}

	public boolean transfer(int source, double amount, String password, int target) {
		// TODO Auto-generated method stub
		 Account s = getAccountByNumber(source);
		 Account t = getAccountByNumber(target);
		if(s == null || t == null)
			return false;
		
		if(s.withdraw(amount,password))
			return t.deposit(amount);
		else
			return false;
		
	}

	public boolean closeAccount(int accountNumber, String password) {
		
		var account=getAccountByNumber(accountNumber);
		if(account==null  || !account.authenticate(password) || account.getBalance()<0)
			return false;
		
		accounts[accountNumber]=null;
		
		accountCount--;
		return true;
	}

	public boolean withdraw(int accountNumber, int amount, String password) {
		var account=getAccountByNumber(accountNumber);
		if(account==null)
			return false;
		return account.withdraw(amount, password);
	}
	
	public void show(int accountNumber) {
		Account account = this.getAccountByNumber(accountNumber);
		account.show();
	}
}
