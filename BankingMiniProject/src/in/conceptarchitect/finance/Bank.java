package in.conceptarchitect.finance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import in.conceptarchitect.Database.DatabaseConnection;
import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidAccountTypeException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;

public class Bank {
	
	String bankName;
	int bankId,lastId;
	int accountCount = 0;
	double interestRate = 10;
	
	ArrayList<Accounts> account = new ArrayList<Accounts> ();

	DatabaseConnection db=new DatabaseConnection();
	
	public Bank(int bankId, String bankName) throws Exception {
		this.bankId = bankId;
		this.bankName = bankName;
		lastId = this.bankId;
		

		
		String sqlQuery = "insert into Bank values (" + this.bankId + ",'" + this.bankName + "',0," + this.lastId + ");";;
		DatabaseConnection.insertQuery(sqlQuery);
		
	}
	
	public int openAccount(String name, String accountType, String password, double balance) throws Exception {
		Accounts a = null;
		
		switch(accountType.toLowerCase()) {
		case "savings": 
			a = new Savings(name, password, ++ this.lastId, balance,accountType,this.bankId);
			break;
		case "current":
			a = new Current(name, password, ++ this.lastId, balance,accountType,this.bankId);
			break;
		case "overdraft":
			a = new Overdraft(name,password, ++this.lastId, balance,accountType,this.bankId);
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
		if(accountNumber > -1 && accountNumber <= this.lastId) {
			return account.get(accountNumber);
		}
		else {
			throw new InvalidAccountException(accountNumber, "Account doesn't Exists");
		}
	}
	
	public void deposite(int accountNumber, double amount) {
		accountNumber = getIndex(accountNumber);
		Accounts a = findAccountByNumber(accountNumber);
		a.deposit(amount);
//		Transactions t = new Transactions();
//		t.setAmount(amount);
//		t.setMode("Deposited");
////		t.setDescription("");
//		a.transaction.add(t);
		
//		String sqlQuery = "write insert command transaction table as deposited";
//		DatabaseConnection.updateQuery(sqlQuery);
		
		account.set(accountNumber, a);
	}
	
	public void withdraw(int accountNumber, double amount, String password) {
		accountNumber = getIndex(accountNumber);
		Accounts a = findAccountByNumber(accountNumber);
		a.withdraw(amount, password);
//		Transactions t = new Transactions();
//		t.setAmount(amount);
//		t.setMode("Withdraw");
//		t.setDescription();
//		a.transaction.add(t);
		
//		String sqlQuery = "write insert command transaction table as withdrawan";
//		DatabaseConnection.updateQuery(sqlQuery);
		
		account.set(accountNumber, a);
	}
	
	public void transferTo(int src, double amount, String password, int dest,Date date) throws Exception {
		
		src = getIndex(src);
		dest = getIndex(dest);
		
		Accounts source = this.findAccountByNumber(src);
		Accounts destination = this.findAccountByNumber(dest);
		
		destination.deposit(amount);
		Transactions t = new Transactions();
		t.setAccountNumber(src);
		t.setAmount(amount);
		t.setMode("Transaction");
		t.setDescription("recived by");
		t.setDate(date);
		db.insertTransactions(t);
		

		account.set(accountCount, destination);
		
		source.withdraw(amount, password);

		
		account.set(accountCount, source);
		
	}
	
	public void changePassword(String old, String changed, String retype, int accountNumber) {
		accountNumber = getIndex(accountNumber);
		findAccountByNumber(accountNumber);
		verify(changed, retype);
		
//		String sqlQuery = "";
//		DatabaseConnection.insertQuery(sqlQuery);
		
		account.get(accountNumber).changePassword(old, changed);
	}
	
	private void verify(String changed, String retype) {
		// TODO Auto-generated method stub
		if(!changed.equals(retype))
			throw new InvalidCredentialsException(accountCount, "Wrong Password, Check new Password...");
		
	}

	public void closeAccount(int accountNumber, String password) {
		accountNumber = getIndex(accountNumber);
		findAccountByNumber(accountNumber);
		
		account.get(accountNumber).authenticate(password);
		
		account.remove(accountNumber);
	}

	private int getIndex(int accountNumber) {
		// TODO Auto-generated method stub
		return accountNumber - (this.bankId + 1);
	}

	public void show(int accountNumber) {
		// TODO Auto-generated method stub
		accountNumber = getIndex(accountNumber);
		account.get(accountNumber).show();
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.bankName;
	}

	public double getAccountBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		accountNumber = getIndex(accountNumber);
		return account.get(accountNumber).getBalance();
	}
}