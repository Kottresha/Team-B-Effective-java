package in.conceptarchitect.financeapp;

import java.util.Scanner;

import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.machines.ATM;

public class App {

	public static void main(String[] args) {
		
		Bank icici=new Bank("ICICI",12);
		ATM atm=new ATM(icici);
		Scanner read = new Scanner(System.in);
		
		System.out.println("1. Bank interface\t\t2. ATM\t\t3. Exit");
		
		switch(read.nextInt()) {
		case 1:
			bank(read, icici);
			break;
		case 2:
			atmFunc(atm);
			break;
		case 0:
			read.close();
			return;
		}
	}

	private static void atmFunc(ATM atm) {
		atm.start();
	}

	private static void bank(Scanner read, Bank icici) {
		String name, accountType, password;
		int accountNumber;
		double amount1;
		while(true) {
			System.out.println("1. Open Account \t2. Account Count \t3. Credit Interest \t4. Deposite");
			System.out.println("5. Withdraw \t6. Transfer Amount \t7. Close Account\t8. Account Details");
			System.out.println("0. exit");
			
			switch(read.nextInt()) {
			case 1:
				name = read.next();
				accountType = read.next();
				password = read.next();
				amount1 = read.nextDouble();
				
				System.out.println("Account Number   \t" + icici.openAccount(name, accountType, password, amount1));
				break;
			case 2:
				System.out.println("Account Count    \t" + icici.getAccountCount());
				break;
			case 3:
				icici.creditInterst();
				System.out.println("Interest Credited to all Accounts");
			case 4:
				accountNumber = read.nextInt();
				
				if(icici.deposit(accountNumber, read.nextDouble())){
					System.out.println("Amount Deposited");
				}
				else {
					System.out.println("Verify Account Number and Amount Once");
				}
				break;
			case 5:
				accountNumber = read.nextInt();
				int amount = read.nextInt();
				password = read.next();
				if(icici.withdraw(accountNumber, amount, password)) {
					System.out.println("Collect Your Cash..!");
				}
				else {
					System.out.println("Transaction Decilned..!");
				}
				break;
			case 6:
				int source = read.nextInt();
				amount1 = read.nextDouble();
				password = read.next();
				int target = read.nextInt();
				if(icici.transfer(source, amount1, password, target)) {
					System.out.println("Transaction Completed Successfully..!");
				}
				else {
					System.out.println("Transaction Decilned..!");
				}
				break;
			case 7:
				accountNumber = read.nextInt();
				password = read.next();
				if(icici.closeAccount(accountNumber, password)){
					System.out.println("Account Closed");
				}
				else {
					System.out.println("Account Doesn't Exists");
				}
			case 0:
				return;
			case 8:
				
			}
		}
	}

}
