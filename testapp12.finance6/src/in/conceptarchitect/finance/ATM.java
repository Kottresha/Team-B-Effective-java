package in.conceptarchitect.finance;

public class ATM {

	Bank B=new Bank(null, 0);
	BankAccount BA=new BankAccount(0, null, null, 0);
	
	public void deposit(int accountNumber,double amount) {
		B.deposit(accountNumber, amount);
			
	}
	
	public void withdraw(int accountNumber,double amount,String password) {
		B.withdraw(accountNumber, amount, password);
	}
	
	public void changePassword(String oldpassword,String newpassword) {
		BA.changePassword(oldpassword, newpassword);		
	}
}
