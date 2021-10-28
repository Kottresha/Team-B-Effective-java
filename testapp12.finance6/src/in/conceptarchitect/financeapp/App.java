package in.conceptarchitect.financeapp;

import in.conceptarchitect.finance.ATM;
import in.conceptarchitect.finance.Bank;

public class App {

	public static void main(String[] args) {
		Bank B=new Bank("SBI", 10);
		ATM A=new ATM();
		
		
		B.openAccount("SBI","Password", 10000);
			
		A.withdraw(1, 100, "password");
		A.deposit(10, 1000);
		A.changePassword("password", "p@ss");
		

		
		B.withdraw(10, 100, "p@ss");
		B.deposit(10,100);
		B.transferto(10, 10, "p@ss", null);//it is comming some error in target account entering
		B.closeAccount(10);
	}

}
