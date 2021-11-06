package in.conceptarchitect.finance;

import java.util.ArrayList;

import in.conceptarchitect.Database.DatabaseConnection;
import in.conceptarchitect.exceptions.InsufficientBalanceException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;
import in.conceptarchitect.exceptions.InvalidDenominationException;

public abstract class Accounts {

	protected String name;
	protected String password;
	protected int accountNumber;
	protected double balance;
	
	ArrayList<Transactions> transaction = new ArrayList<Transactions>();
	
	public Accounts(String name, String password, int accountNumber, double balance) {
		this.name = name;
		this.setPassword(password);
		this.accountNumber = accountNumber;
		this.balance = balance;
		
		String sqlQuery = "write insert command to accounts table";
		DatabaseConnection.insertQuery(sqlQuery);
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	void setPassword(String password) {
		this.password = salt(password);
	}
	
	private String salt(String password) {
		String salted="";
		
		for(int i=0;i<password.length();i++) {
			int ch= (int) password.charAt(i);
			salted+=Integer.toHexString(ch);
		}
		return salted;
	}
	
	public void authenticate(String password) {
		if(!this.password.equals(salt(password)))
			throw new InvalidCredentialsException(this.accountNumber, "Transaction Declined,   Wrong password..!");
	}
	
	public void changePassword(String oldPassword, String newPassword) {	
		authenticate(oldPassword);
		setPassword(newPassword);
	}
	
	public void withdraw(double amount, String password) {
		validateDenomination(amount);
		ensureFunds(amount);
		authenticate(password);
		this.balance -= amount;
	}
	
	public void creditInterest(double interestRate) {
		this.balance += (this.balance * interestRate / 1200);
		Transactions t = new Transactions();
		t.setDescription("Interest Amount " + (this.balance * interestRate /1200) + " Credited on ");
		this.transaction.add(t);
	}
	
	private void validateDenomination(double amount) {
		if(amount < 0) {
			throw new InvalidDenominationException(this.accountNumber, "Amount must be Positive Value..!");
		}
	}
	
	private void ensureFunds(double amount) {
		if(amount > (this.getBalance() - 5000)){
			throw new InsufficientBalanceException(this.accountNumber, amount - getMaxWithdrawAmount() , "Transaction Declined,  Insufficient Balance...");
		}
	}

	public void deposit(double amount) {
		validateDenomination(amount);
		this.balance += amount;
	}

	protected abstract double getMaxWithdrawAmount();

	protected void show() {
		System.out.println("Bank Account Number : " + this.accountNumber);
		System.out.println("Account Holder Name : " + this.name);
		System.out.println("Balance             : " + this.balance);
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
}