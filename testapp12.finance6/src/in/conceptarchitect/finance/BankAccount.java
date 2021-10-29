package in.conceptarchitect.finance;

public class BankAccount {
	
	int accountNumber;
	String name;
	String password;
	double balance;
	static double interestRate = 10;
	
	public final static double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		
		BankAccount.interestRate = interestRate;
	}
	
	
	public BankAccount(int accountNumber, String name, String password, double amount) {
		
		balance=amount; //this is optional here as there is a single balance in the context	
		this.name=name;
		setPassword(password);		
		this.accountNumber=accountNumber;
		
	}
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public final int getAccountNumber() {
		return accountNumber;
	}	

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	public final double getBalance() {
		return balance;
	}
	
	public final boolean authenticate(String password) {
		return this.password.equals(salt(password));
	}

	final void setPassword(String password) {
		
		this.password = salt(password);
	}
	
	
	public final void changePassword(String oldPassword, String newPassword) {
		if(authenticate(oldPassword))
			setPassword(newPassword);
	}
	

	

	private final String salt(String password) {
		
		String salted="";
		
		for(int i=0;i<password.length();i++) {
			int ch= (int) password.charAt(i);
			salted+=Integer.toHexString(ch);
		}
		return salted;
	}


	
	public final void show() {
		System.out.println("account number\t"+accountNumber);
		System.out.println("name         \t"+name);
		System.out.println("password     \t"+password);
		System.out.println("balance      \t"+balance);
		//System.out.println("interest rate\t"+Bank.getInterestRate());
		System.out.println();
		
	}

	public final boolean deposit(double amount) {
		// TODO Auto-generated method stub
		if(amount>0) {
			balance+=amount;
			return false;
		} else {
			return true;
		}
	}

	public final boolean withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		if(amount<=0)
			return false;
		if (amount>balance) {
			return false;
		} 
		//if (!this.password.equals(password))
		if(!authenticate(password))
			return false;
		else {
			
			
			return true;
		}
	}
	
	
	public boolean creditInterest(double interestRate) {
		balance+=(balance*interestRate/1200);
		return true;
	}

	public final boolean transferTo(double amount, String password2, BankAccount target) {
		// TODO Auto-generated method stub
		return false;
	}
}

class SavingsAccount{

	@Override
	public void setInterestRate(double interestRate) {
		
		BankAccount.interestRate = interestRate;
	}
}

class CurrentAccount{
	
	
	public boolean creditInterest(double interestRate) {
		return false;
	}
}






