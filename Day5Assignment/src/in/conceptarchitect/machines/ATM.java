package in.conceptarchitect.machines;

import in.conceptarchitect.exceptions.InsufficientFundsException;
import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;
import in.conceptarchitect.finance.Bank;

public class ATM {
	
	Input keyboard=new Input();
	private int accountNumber;
	Bank bank;
	
	
	public ATM(Bank bank) {
		super();
		this.bank = bank;
	}

	public void start() throws InsufficientFundsException, InvalidAccountException, InvalidCredentialsException {
		
		while(true) {
			System.out.println("Welcome to "+bank.getName()+" bank");
			String password=keyboard.readString("CHALLENGE? ");
			if(password.equals("NIMDA"))
				adminMenu();
			else
				mainMenu();
		}
		
	}

	private void adminMenu() {
		// TODO Auto-generated method stub
		while(true) {
			int choice= keyboard.readInt("1. Open Account 2. Credit Interest 3. Show Account 0. Exit :");
			switch(choice) {
			case 1:
				doOpenAccount(); break;
			case 2:
				doCreditInterest(); break;
			case 3:
				printMessage("Account List is no supported");
			case 0:
				return;
			}
		}
	}

	private void doCreditInterest() {
		bank.creditInterst();
	}

	private void doOpenAccount() {
		
		String name=keyboard.readString("Name? ");
		String accountType=keyboard.readString("accountType? ");
		String password=keyboard.readString("Password:");
		int amount=keyboard.readInt("Amount?");
		
		int accountNumber=bank.openAccount(name,accountType, password, amount);
		printSlip("Your new account number is "+accountNumber);
	}

	private void mainMenu() throws InsufficientFundsException, InvalidAccountException, InvalidCredentialsException {
		// TODO Auto-generated method stub
		while(true) {
			int choice= keyboard.readInt("1. Deposit 2. Withdraw 3. Transfer 4. Show Balance 5. Close Account 0. Exit :");
			switch(choice) {
			case 1:
				doDeposit(); 
				break;
			case 2:
				doWithdraw();
				break;
			case 3:
				doTransfer();
				break;
			case 4:
				doShowBalance();
				break;
			case 5:
				doCloseAccount();
				return;
			case 0:
				return;
			}
		}
	}

	private void doCloseAccount() throws InsufficientFundsException, InvalidAccountException, InvalidCredentialsException {
		// TODO Auto-generated method stub
		String password=keyboard.readString("ENTER YOUR PASSWORD:");
		double amount=bank.getAccountBalance(accountNumber);
		if(bank.closeAccount(accountNumber, password))
		{
			printSlip("Account Closed. Cash Returned");
			dispenseCash((int)amount);
		} else {
			printMessage("Account close Failed");
		}
			
	}

	private void doShowBalance() throws InsufficientFundsException, InvalidAccountException {
		// TODO Auto-generated method stub
		var response= bank.getAccountBalance(accountNumber);
		if(response==Double.NaN)
			printMessage("Invalid Account Number");
		else
			printSlip("Your total balance : "+response);
	}

	

	private void doTransfer() throws InvalidAccountException, InvalidCredentialsException, InsufficientFundsException {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		int targetAccount=keyboard.readInt("target account?");
		try{
			bank.transfer(accountNumber, amount, password, targetAccount);
			printSlip("Amount Transferred");
		}
		catch(InvalidAccountException iae) {
			throw new InvalidAccountException(iae);
		}
		catch(InsufficientFundsException ife) {
			throw new InsufficientFundsException(ife);
		}
		catch(InvalidCredentialsException ice) {
			throw new InvalidCredentialsException(ice);
		}
	}

	private void doWithdraw() throws InvalidAccountException, InsufficientFundsException, InvalidCredentialsException {
		// TODO Auto-generated method stub
		
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		
		try{
			bank.withdraw(accountNumber, amount, password);
			dispenseCash(amount);
		}
		catch(InvalidAccountException iae) {
			throw new InvalidAccountException(iae);
		}
		catch(InsufficientFundsException ife) {
			throw new InsufficientFundsException(ife);
		}
		catch(InvalidCredentialsException ice) {
			throw new InvalidCredentialsException(ice);
		}
		
	}

	private void doDeposit() throws InsufficientFundsException, InvalidAccountException {
		// TODO Auto-generated method stub

		int amount=keyboard.readInt("amount to transfer? ");
		try{
			bank.deposit(accountNumber, amount);
			printSlip("Amount Deposited");
		}
		catch(InvalidAccountException iae) {
			throw new InvalidAccountException(iae);
		}
		catch(InsufficientFundsException ife) {
			throw new InsufficientFundsException(ife);
		}
	}
	
	//ATM hardware methods
	private void printMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	
	private void printSlip(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	
	private void dispenseCash(int amount) {
		System.out.println("Please collect your cash: "+amount);
	}

}