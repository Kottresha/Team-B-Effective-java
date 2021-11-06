package in.conceptarchitect.finance;

import in.conceptarchitect.Database.DatabaseConnection;

public class Savings extends Accounts{
	
	public Savings(String name, String password, int accountNumber, double balance) {
		super(name, password, accountNumber, balance);
		
		String sqlQuery = "write insert command savings table";
		DatabaseConnection.insertQuery(sqlQuery);
	}

	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return this.balance - 5000;
	}
	
}