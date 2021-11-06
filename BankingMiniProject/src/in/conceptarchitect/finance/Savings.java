package in.conceptarchitect.finance;

import in.conceptarchitect.Database.DatabaseConnection;

public class Savings extends Accounts{

	DatabaseConnection db=new DatabaseConnection();
	
	public Savings(String name, String password, int accountNumber, double balance,String accountType,int bankId) throws Exception {
		super(name, password, accountNumber, balance, accountType, bankId);
		
		String sqlQuery = "insert into Savings values ("+this.accountNumber+",'"+this.name+"',"+this.password+","+this.balance+",10);";
		DatabaseConnection.insertQuery(sqlQuery);		

	}

	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return this.balance - 5000;
	}
	
}