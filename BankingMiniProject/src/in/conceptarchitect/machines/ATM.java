package in.conceptarchitect.machines;

import java.sql.Date;
import java.util.List;

import in.conceptarchitect.Database.DatabaseConnection;
import in.conceptarchitect.exceptions.BankingExceptions;
import in.conceptarchitect.exceptions.InsufficientBalanceException;
import in.conceptarchitect.exceptions.InvalidAccountException;
import in.conceptarchitect.exceptions.InvalidCredentialsException;
import in.conceptarchitect.exceptions.InvalidDenominationException;
import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.finance.Transactions;



public class ATM {
	
	Input keyboard=new Input();
	private int accountNumber;
	Bank bank;
	
	DatabaseConnection db=new DatabaseConnection();
	Transactions t=new Transactions();

	
	public ATM(Bank bank) {
		super();
		this.bank = bank;
	}

	public ATM() {
		// TODO Auto-generated constructor stub
	}

	public void start() throws Exception {
		
		while(true) {
			System.out.println("Welcome to "+bank.getName()+" bank");
			accountNumber= keyboard.readInt("account number? ");
			if(accountNumber==-1) {
				String password=keyboard.readString("CHALLENGE? ");
				if(password.equals("NIMDA"))
					adminMenu();
			}
			else
				mainMenu(accountNumber);	
		}
		
	}

	private void mainMenu(int accountNumber) throws Exception {
		// TODO Auto-generated method stub
		
			while(true) {
				try {
					int choice= keyboard.readInt("1. Deposit 2. Withdraw 3. Transfer 4. Show Balance 5.Transactions 6. Close Account 0. Exit :");
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
						doneTransactions(accountNumber); break;
					case 6:
						doCloseAccount(); return;
					case 0:
						return;
					}
				}  catch(InsufficientBalanceException ex) {
					
					printMessage("Insufficient Balance in account:"+ex.getAccountNumber()+"\tDeficit:"+ex.getDeficit());
					
				} catch(BankingExceptions ex) {
					
					printMessage("Error with Account:"+ex.getAccountNumber()+" : "+ex.getMessage());
					
				} 
			}
		
	}
	
	private void doneTransactions(int accountNumber) throws Exception {
//		 TODO Auto-generated method stub
		List<Transactions> lu=db.getAllTransactions(accountNumber);
		for(Transactions t1:lu) {
			System.out.println(t1);
	
		}
		
	}

	private void doWithdraw() throws Exception {
		// TODO Auto-generated method stub
		
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		t.setMode("withdraw");	
		String description=keyboard.readString("description? ");		
		bank.withdraw(accountNumber, amount, password);
		t.setAmount(amount);
		t.setAccountNumber(accountNumber);
		t.setDescription(description);
		Date date=keyboard.readDate("enter date");
		t.setDate(date);
		db.insertTransactions(t);
		
		//you reach here only if withdraw succeeds
		dispenseCash(amount);
		
		
	}



	private void doCloseAccount() {
		// TODO Auto-generated method stub
		String password=keyboard.readString("ENTER YOUR PASSWORD:");
		double amount=bank.getAccountBalance(accountNumber,password);
		bank.closeAccount(accountNumber, password);
		printSlip("Account Closed. Cash Returned");
		dispenseCash((int)amount);
		
			
	}

	private void doShowBalance() {
		// TODO Auto-generated method stub
		String password=keyboard.readString("password?");
		var response= bank.getAccountBalance(accountNumber,password);
		printSlip("Your total balance : "+response);
	}

	

	private void doTransfer() throws Exception {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("amount to transfer? ");
		String password=keyboard.readString("password? ");
		int targetAccount=keyboard.readInt("target account?");
		t.setMode("transfer");
		Date date=keyboard.readDate("enter date");
		bank.transferTo(accountNumber, amount, password, targetAccount,date);
		t.setAccountNumber(accountNumber);
		t.setDescription("transfered succesfully to");
		t.setAmount(amount);
		t.setDate(date);
		db.insertTransactions(t);
		printSlip("Amount Transferred");
	}

	private void doDeposit() throws Exception {
		// TODO Auto-generated method stub

		int amount=keyboard.readInt("amount to transfer? ");
		t.setMode("Deposit");
		String description=keyboard.readString("description? ");
		bank.deposite(accountNumber, amount);
		t.setAccountNumber(accountNumber);
		t.setAmount(amount);
		Date date=keyboard.readDate("enter date");
		t.setDate(date);
		t.setDescription(description);
		db.insertTransactions(t);
		printSlip("Amount Deposited");
		
	}
		
		
		
	private void adminMenu() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			try {
			int choice= keyboard.readInt("1. Open Account 2. Credit Interest 3. Show Account 0. Exit :");
			switch(choice) {
			case 1:
				doOpenAccount(); break;
			case 2:
				doCreditInterest(); break;
			case 3:
				doCloseAccount();break;
			
			case 0:
				return;
			}
			} 
			catch(BankingExceptions ex) {
				printMessage("error with account "+ex.getAccountNumber()+":"+ex.getMessage());
			}
		}
	}

	/*private void doShowAccount() {
		// TODO Auto-generated method stub
		var report= bank.getAccountList();
		printMessage(report);
	}*/

	private void doCreditInterest() {
		// TODO Auto-generated method stub
		bank.creditInterestRate();
	}

	private void doOpenAccount() throws Exception {
		// TODO Auto-generated method stub
		String accountType=keyboard.readString("account type [savings/current/od] ?");
		String name=keyboard.readString("Name? ");
		String password=keyboard.readString("Password:");
		int amount=keyboard.readInt("Amount?");
		int accountNumber=bank.openAccount(name,accountType, password, amount);
		printSlip("Your new account number is "+accountNumber);
		
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
	
	
	private void mainMenuV1() throws Exception {
		// TODO Auto-generated method stub
		
			while(true) {
				try {
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
				}  catch(InsufficientBalanceException ex) {
					
					printMessage("Insufficient Balance in account:"+ex.getAccountNumber()+"\tDeficit:"+ex.getDeficit());
					
				} catch(InvalidCredentialsException ex) {
					
					printMessage(ex.getMessage()+":"+ex.getAccountNumber());
					
				} catch(InvalidAccountException | InvalidDenominationException ex) {
					
					printMessage(ex.getMessage()+":"+ex.getAccountNumber());
				} 
			}
	}
}