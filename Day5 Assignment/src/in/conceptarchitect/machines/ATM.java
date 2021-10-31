package in.conceptarchitect.machines;

import in.conceptarchitect.finance.Bank;

public class ATM {
	
	Input keyboard=new Input();
	private int accountNumber;
	Bank bank;
	
	
	public ATM(Bank bank) {
		super();
		this.bank = bank;
	}

	public void start() {
		
		while(true) {
			System.out.println("Welcome to "+bank.getName()+" bank");
			accountNumber= keyboard.readInt("account number? ");
			if(accountNumber==-1) {
				String password=keyboard.readString("CHALLENGE? ");
				if(password.equals("NIMDA"))
					adminMenu();
			}
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
		// TODO Auto-generated method stub
		bank.creditInterst();
	}

	private void doOpenAccount() {
		// TODO Auto-generated method stub
		String name=keyboard.readString("Name? ");
		String accountType=keyboard.readString("accountType? ");
		String password=keyboard.readString("Password:");
		int amount=keyboard.readInt("Amount?");
		
		int accountNumber=bank.openAccount(name,accountType, password, amount);
		printSlip("Your new account number is "+accountNumber);
	}

	private void mainMenu() {
		// TODO Auto-generated method stub
		while(true) {
			int choice= keyboard.readInt("1. Deposit 2. Withdraw 3. Transfer 4. Show Balance 5. Close Account 0. Exit :");
			switch(choice) {
			case 1:
				doDeposit(); break;
			case 2:
				doWithdraw(); break;
			case 3:
				doTransfer(); break;
			case 4:
				doShowBalance(); break;
			case 5:
				doCloseAccount(); return;
			case 0:
				return;
			}
		}
	}

	private void doCloseAccount() {
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

	private void doShowBalance() {
		// TODO Auto-generated method stub
		var response= bank.getAccountBalance(accountNumber);
		if(response==Double.NaN)
			printMessage("Invalid Account Number");
		else
			printSlip("Your total balance : "+response);
	}

	

	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		int targetAccount=keyboard.readInt("target account?");
		if(bank.transfer(accountNumber, amount, password, targetAccount))
			printSlip("Amount Transferred");
		else
			printMessage("Transfer Failed");
	}

	private void doWithdraw() {
		// TODO Auto-generated method stub
		
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		
		if(bank.withdraw(accountNumber, amount, password))
			dispenseCash(amount);
		else
			printMessage("Transfer Failed");
		
	}

	private void doDeposit() {
		// TODO Auto-generated method stub

		int amount=keyboard.readInt("amount to transfer? ");
		if(bank.deposit(accountNumber, amount))
			printSlip("Amount Deposited");
		else
			printMessage("Transfer Failed");
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

