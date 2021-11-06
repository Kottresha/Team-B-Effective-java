package in.conceptarchitect.financeApp;

import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.finance.storage.ArrayAccountStorage;
import in.conceptarchitect.machines.ATM;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		var storage=new ArrayAccountStorage();
		//var storage= new HashmapAccountStorage();
		Bank icici=new Bank(5,"ICICI");
		//lets create some dummy accounts
//		icici.openAccount("savings","Vivek", "pass", 10000);
//		icici.openAccount("current","Sanjay", "pass", 10000);
//		icici.openAccount("od","Shivanshi", "pass", 10000);
		
		icici.openAccount("mohan","savings","pass",4000);
		icici.openAccount("kotresh","current","pass",4000);
		icici.openAccount("kittu","overdraft","pass",4000);
		
		ATM atm=new ATM(icici);
		
		atm.start();
		

		
	}

}
