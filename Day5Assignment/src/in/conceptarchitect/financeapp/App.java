package in.conceptarchitect.financeapp;

import in.conceptarchitect.exceptions.InsufficientFundsException;
import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;
import in.conceptarchitect.exceptions.InvalidDenominationException;
import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.machines.ATM;
import in.conceptarchitect.machines.Input;

public class App {

	public static void main(String[] args) throws InvalidCredentialsException, InsufficientFundsException, InvalidAccountException, InvalidDenominationException {
		
		Bank icici=new Bank("ICICI",12);
		ATM atm=new ATM(icici);
		Input read = new Input();
		
		System.out.println("1. Bank interface\t\t2. ATM\t\t3. Exit");
		
		switch(read.readInt("Your Choice ")) {
		case 1:
			bank(read, icici);
			break;
		case 2:
			atmFunc(atm);
			break;
		case 0:
			return;
		}
	}

	private static void atmFunc(ATM atm) throws InsufficientFundsException, InvalidAccountException, InvalidCredentialsException, InvalidDenominationException {
		atm.start();
	}

	private static void bank(Input read, Bank icici) throws InvalidCredentialsException, InsufficientFundsException, InvalidAccountException, InvalidDenominationException {
		String name, accountType, password;
		int accountNumber;
		int amount;
		while(true) {
			System.out.println("\n\n\n1. Open Account \t2. Account Count \t3. Credit Interest \t4. Deposite");
			System.out.println("5. Withdraw \t6. Transfer Amount \t7. Close Account\t8. Account Details");
			System.out.println("0. exit");
			
			switch(read.readInt("Your Choice ")) {
			case 1:
				name = read.readString("Name ");
				accountType = read.readString("Account Type ");
				password = read.readString("Password ");
				amount = read.readInt("Amount ");
				try {
					System.out.println("\n\nAccount Number   \t" + icici.openAccount(name, accountType, password, (double) amount));
				}
				catch(InvalidDenominationException ide) {
					System.out.println(ide);
				}
				break;
			case 2:
				System.out.println("Account Count    \t" + icici.getAccountCount());
				break;
			case 3:
				icici.creditInterst();
				System.out.println("Interest Credited to all Accounts");
				break;
			case 4:
				accountNumber = read.readInt("Account Number ");
				amount = read.readInt("Amount "); 
				try{
					icici.deposit(accountNumber, (double) amount);
					System.out.println("Amount Deposited");
				}
				catch(InvalidAccountException iae) {
					System.out.println(iae);
				}
				catch(InsufficientFundsException ife) {
					System.out.println(ife);
				}
				break;
			case 5:
				accountNumber = read.readInt("Account Number ");
				amount = read.readInt("amount ");
				password = read.readString("password ");
				try{
					icici.withdraw(accountNumber, amount, password);
					System.out.println("Collect Your Cash..!");
				}
				catch(InvalidAccountException iae){
					System.out.println(iae);
				}
				catch(InsufficientFundsException ife) {
					System.out.println(ife);
				}
				catch(InvalidCredentialsException ice) {
					System.out.println(ice);
				}
				break;
			case 6:
				int source = read.readInt("From ");
				amount = read.readInt("Amount ");
				password = read.readString("password ");
				int target = read.readInt("To ");
				try{
					icici.transfer(source, (double) amount, password, target);
					System.out.println("Transaction Completed Successfully..!");
				}
				catch(InvalidAccountException iae){
					System.out.println(iae);
				}
				catch(InvalidCredentialsException ice) {
					System.out.println(ice);
				}
				catch(InsufficientFundsException ife) {
					System.out.println(ife);
				}
				break;
			case 7:
				accountNumber = read.readInt("Account Number ");
				password = read.readString("password ");
				try{
					icici.closeAccount(accountNumber, password);
					System.out.println("Account Closed");
				}
				catch(InvalidAccountException iae) {
					System.out.println(iae);
				}
				catch(InvalidCredentialsException ice){
					System.out.println(ice);
				}
				catch(InsufficientFundsException ife) {
					System.out.println(ife);
				}
				break;
			case 0:
				return;
			case 8:
				accountNumber = read.readInt("Account Number ");
				icici.show(accountNumber);
				break;
			}
		}
	}

}
