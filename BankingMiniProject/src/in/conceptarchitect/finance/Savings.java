package in.conceptarchitect.finance;

import in.conceptarchitect.Database.DatabaseConnection;

public class Savings extends Accounts{
	
	AccountData ad=new AccountData();
	DatabaseConnection db=new DatabaseConnection();
	
	public Savings(String name, String password, int accountNumber, double balance) throws Exception {
		super(name, password, accountNumber, balance);
		ad.setHolderName(name);
		ad.setPassword(password);
		ad.setBalance(balance);
		db.insertSavings(ad);		

	}

	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return this.balance - 5000;
	}
	
}