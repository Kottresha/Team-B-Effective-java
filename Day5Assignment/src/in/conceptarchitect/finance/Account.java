package in.conceptarchitect.finance;

import in.conceptarchitect.exceptions.InsufficientFundsException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;
import in.conceptarchitect.exceptions.InvalidDenominationException;

public class Account {
	
	int accountNumber;
	String name;
	String password;
	String accountType;
	double balance;
	static double interestRate = 10;
	
	public Account(int accountNumber, String name, String accountType, String password, double amount) throws InvalidDenominationException {
		
		this.balance = amount; //this is optional here as there is a single balance in the context	
		setName(name);
		setPassword(password);		
		this.accountNumber = accountNumber;
		setAccountType(accountType);
	}

	public void setAccountType(String accountType) throws InvalidDenominationException {
		switch(accountType) {
		case "Savings":
		case "savings":
			this.accountType = "Savings";
			break;
		case "Current":
		case "current":
			this.accountType = "Current";
			break;
		case "overdreaft":
		case "Overdreaft":
			this.accountType = "Overdreaft";
			break;
		default:
			throw new InvalidDenominationException("Account Type Doesn't Exists..!");
		}
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void creditInterest(double interestRate) {
		balance+=(balance*interestRate/1200);
	}

	public static void setInterestRate(double interestRate) {
		Account.interestRate = interestRate;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		
		if(authenticate(oldPassword))
			setPassword(newPassword);
	}
	
	public boolean authenticate(String password) {
		return this.password.equals(salt(password));
			
	}
	
	private String salt(String password) {
		
		String salted="";
		
		for(int i=0;i<password.length();i++) {
			int ch= (int) password.charAt(i);
			salted+=Integer.toHexString(ch);
		}
		return salted;
	}
	
	private void setPassword(String password) {
		this.password = salt(password);
	}
	
	
	
	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		if(amount>0) {
			balance+=amount;
			return true;
		} 
		else {
			return false;
		}
	}

	public boolean withdraw(double amount, String password) throws InvalidCredentialsException, InsufficientFundsException {
		// TODO Auto-generated method stub
		if(amount<1 || amount > balance) {
			throw new InsufficientFundsException("Insufficient Amount..!");
		}
		if(!authenticate(password)) {
			throw new InvalidCredentialsException("Wrong Password..!");
		}
		else {
			
			balance-=amount;
			return true;
		}
	}
	
	public void show() {
		System.out.println("account number\t"+accountNumber);
		System.out.println("name         \t"+name);
		System.out.println("password     \t"+password);
		System.out.println("balance      \t"+balance);
		System.out.println("Account Type \t"+this.getAccountType());
		System.out.println();
		
	}
}