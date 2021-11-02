package in.conceptarchitect.finance;

import java.util.ArrayList;

import javax.security.auth.login.CredentialException;

import in.conceptarchitect.exceptions.InsufficientFundsException;
import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;

@SuppressWarnings("unused")
public class  Bank {
	
	String name; //name of the Bank
	int lastId= 0;
	int accountCount=0;
	double interestRate;
	
	
	//Account [] accounts= new Account[100]; //MAX 100. May not be great for large  s
	ArrayList <Account>  accounts= new ArrayList<Account>(); 

	
	public  Bank (String name, double interestRate) {
		super();
		this.name = name;
		this.interestRate = interestRate;
	}
	
	public int openAccount(String name,String accountType, String password, double amount) {
		int accountNumber= ++ lastId;
		System.out.println(accountType);
		Account account= new Account(accountNumber, name, accountType, password,amount);
		accounts.add(account); //store this account in the array.
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
			accounts.get(i).creditInterest(interestRate);
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
			return accounts.get(accountNumber);
		else
			return null;
	}
	
	public boolean deposit(int accountNumber, double amount) throws InsufficientFundsException, InvalidAccountException {
		Account account = getAccountByNumber(accountNumber);
		if(account==null) {
			throw new InvalidAccountException("Invalid Account Number");
		}
		else if(amount < 0) {
			throw new InsufficientFundsException("Amount Can't be Deposited");
		}
		else {
			return account.deposit(amount);
		}
	}

	public double getAccountBalance(int accountNumber) throws InsufficientFundsException, InvalidAccountException {
		Account account=getAccountByNumber(accountNumber);
		if(account==null) {
			throw new InvalidAccountException("Invalid Account Number");
		}
		if(account.getBalance() > 0) {
			return account.getBalance();
		}
		else {
			throw new InsufficientFundsException("Insufficient Amount");
		}
	}

	public void transfer(int source, double amount, String password, int target) throws InvalidCredentialsException, InsufficientFundsException, InvalidAccountException {
		 Account s = getAccountByNumber(source);
		 Account t = getAccountByNumber(target);
		if(s == null) {
			throw new InvalidAccountException("Invalid Transfer From Account Number");
		}
		if(t == null) {
			throw new InvalidAccountException("Invalid Transfer To Account Number");
		}
		try {
			s.withdraw(amount, password);
			t.deposit(amount);
		}
		catch(InsufficientFundsException ex) {
			throw new InsufficientFundsException(ex);
		}
		catch(InvalidCredentialsException ex) {
			throw new InvalidCredentialsException(ex);
		}
	}

	public boolean closeAccount(int accountNumber, String password) throws InsufficientFundsException, InvalidCredentialsException, InvalidAccountException {
		
		var account=getAccountByNumber(accountNumber);
		if(account==null) {
			throw new InvalidAccountException("Invalid Account Number");
		}
		if(!account.authenticate(password)) {
			throw new InvalidCredentialsException("Wrong Password");
		}
		if(account.getBalance()<0){
			throw new InsufficientFundsException("Account Can't be Closed, Clear Minus Balance..!");
		}
		
<<<<<<< HEAD
		//accounts[accountNumber]=null;
=======
		accounts[accountNumber] = null;
>>>>>>> 13dbd106e54cc0352f3c0eb7dec85ab6e383e8c8
		accountCount--;
		return true;
	}

	public void withdraw(int accountNumber, int amount, String password) throws InvalidCredentialsException, InsufficientFundsException, InvalidAccountException {
		var account=getAccountByNumber(accountNumber);
		if(account==null) {
			throw new InvalidAccountException("Invalid Account Number");
		}
		try {
			account.withdraw(amount, password);
		}
		catch(InvalidCredentialsException ex) {
			throw ex;
		}
		catch(InsufficientFundsException ex) {
			throw new InsufficientFundsException(ex);
		}
	}
	
	public void show(int accountNumber) {
		Account account = this.getAccountByNumber(accountNumber);
		account.show();
	}
}
