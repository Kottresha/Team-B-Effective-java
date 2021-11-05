package in.conceptarchitect.financeApp;

import in.conceptarchitect.finance.Bank;

public class App {

	public static void main(String args[]) {
	
		Bank bank = new Bank(1, "sbi");
		
		System.out.println("Account Number : " + bank.openAccount("Kotresh", "savings", "pass", 10000));
		
		bank.deposite(2, 2000);
		bank.withdraw(2, 1000, "pass");
		
		bank.changePassword("pass", "p@ss", "p@ss", 2);
		
		
	}
}