package in.conceptarchitect.financeapp;

import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.machines.ATM;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bank icici=new Bank("ICICI",12);
		//lets create some dummy accounts
		icici.openAccount("Vivek","savings","pass", 10000);
		icici.openAccount("Sanjay","current", "pass", 10000);
		icici.openAccount("Shivanshi","overdrft", "pass", 10000);
		
		ATM atm=new ATM(icici);
		
		atm.start();

	}

}
