package in.conceptarchitect.finance;

import java.util.Scanner;

public class BankAccount {
	
	int accountNumber;
	String name;
	String password;
	double balance;
	double interestRate;
	
	
	
	public BankAccount(int accountNumber, String name, String password, double amount,double interestRate) {
		
		balance=amount; //this is optional here as there is a single balance in the context	
		this.name=name;
		this.password=password;
		this.interestRate=interestRate;
		this.accountNumber=accountNumber;
		
	}
	
	public void show() {
		System.out.println("account number\t"+accountNumber);
		System.out.println("name         \t"+name);
		System.out.println("password     \tnot shown");
		System.out.println("balance      \t"+balance);
		System.out.println("interest rate\t"+interestRate);
		System.out.println();
		
	}

	public void deposit(double amount) {
		// TODO Auto-generated method stub
		if(amount>0) {
			balance+=amount;
			System.out.println("Amount Deposited "+amount);
		} else {
			System.out.println("Invalid Deposit Amount "+amount);
		}
	}

	public void withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		if(amount <=0) {
			System.out.println("Invalid Withdraw Amount "+amount);
		} else if (amount>balance) {
			System.out.println("Insufficient balance");
		} else if (!this.password.equals(password))
			System.out.println("Invalid Credentials");
		else {
			System.out.println("Please take your cash");
			balance-=amount;
		}
	}
	
	public void creditInterest() {
		balance+=(balance*interestRate/1200);
	}


}
