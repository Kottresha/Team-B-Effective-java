package in.conceptarchitect.finance;

import java.util.ArrayList;
import java.util.Iterator;

import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidAccountTypeException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;

public class Bank {
	
	String bankName;
	int bankId,lastId;
	int accountCount = 0;
	double interestRate = 10;
	
	ArrayList<Accounts> account = new ArrayList<Accounts> ();

	public Bank(int bankId, String bankName) {
		this.bankId = bankId;
		this.bankName = bankName;
		lastId = this.bankId;
	}
	
	public int openAccount(String name, String accountType, String password, double balance) {
		Accounts a = null;
		
		switch(accountType.toLowerCase()) {
		case "savings": 
			a = new Savings(name, password, ++ this.lastId, balance);
			break;
		case "current":
			a = new Current(name, password, ++ this.lastId, balance);
			break;
		case "overdraft":
			a = new Overdraft(name,password, ++this.lastId, balance);
			break;
		default:
			throw new InvalidAccountTypeException(accountType.toUpperCase());
		}
		
		account.add(a);
		return lastId;
	}

	public void creditInterestRate() {
		Iterator<Accounts> i = account.iterator();
		while(i.hasNext()) {
			i.next().creditInterest(this.interestRate);
		}
	}
	
	private Accounts findAccountByNumber(int accountNumber) {
		if(accountNumber > 0 && accountNumber <= this.lastId) {
			return account.get(accountNumber - this.bankId);
		}
		else {
			throw new InvalidAccountException(accountNumber, "Account doesn't Exists");
		}
	}
	
	public void deposite(int accountNumber, double amount) {
		Accounts a = this.findAccountByNumber(accountNumber);
		a.deposit(amount);
		Transactions t = new Transactions();
		t.setAmount(amount);
		t.setMode("Deposited");
		t.setDescription();
		a.transaction.add(t);
		account.set(accountNumber, a);
	}
	
	public void withdraw(int accountNumber, double amount, String password) {
		Accounts a = findAccountByNumber(accountNumber);
		a.withdraw(amount, password);
		Transactions t = new Transactions();
		t.setAmount(amount);
		t.setMode("Withdraw");
		t.setDescription();
		a.transaction.add(t);
		account.set(accountNumber, a);
	}
	
	public void transferTo(int src, double amount, String password, int dest) {
		
		Accounts source = this.findAccountByNumber(src);
		Accounts destination = this.findAccountByNumber(dest);
		
		destination.deposit(amount);
		Transactions t = new Transactions();
		t.setAccountNumber(src);
		t.setAmount(amount);
		t.setMode("Received");
		destination.transaction.add(t);
		account.set(accountCount, destination);
		
		source.withdraw(amount, password);
		Transactions s = new Transactions();
		s.setAccountNumber(dest);
		s.setMode("Transferred");
		s.setAmount(amount);
		s.setDescription();
		source.transaction.add(s);
		account.set(accountCount, source);
		
	}
	
	public void changePassword(String old, String changed, String retype, int accountNumber) {
		findAccountByNumber(accountNumber);
		verify(changed, retype);
		account.get(accountNumber).changePassword(old, changed);
	}
	
	private void verify(String changed, String retype) {
		// TODO Auto-generated method stub
		if(!changed.equals(retype))
			throw new InvalidCredentialsException(accountCount, "Wrong Password, Check new Password...");
		
	}

	public void closeAccount(int accountNumber) {
		findAccountByNumber(accountNumber);
		account.remove(accountNumber);
	}
}