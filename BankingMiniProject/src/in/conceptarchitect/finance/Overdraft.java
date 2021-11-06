package in.conceptarchitect.finance;

import in.conceptarchitect.Database.DatabaseConnection;

public class Overdraft extends Accounts {

	double odLimit;
	
	public Overdraft(String name, String password, int accountNumber, double balance,String accountType,int bankId) throws Exception {
		super(name, password, accountNumber, balance,accountType,bankId);
		updateOdLimit();
		String sqlQuery = "insert into odt values ("+this.accountNumber+",'"+this.name+"',"+this.password+","+this.balance+",10);";
		DatabaseConnection.insertQuery(sqlQuery);		

	}
	
	private void updateOdLimit() {
		// TODO Auto-generated method stub
		if(odLimit<balance/10)
			odLimit= balance/10;
	}

	public double getOdLimit() {
		// TODO Auto-generated method stub
		return odLimit;
	}

	@Override
	public void deposit(double amount) {
		super.deposit(amount);
		updateOdLimit();
	}
	
	@Override
	public void creditInterest(double interestRate) {
		super.creditInterest(interestRate);
		updateOdLimit();
	}
	
	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return balance+odLimit;
	}
	
	@Override
	public void withdraw(double amount, String password) {
		// TODO Auto-generated method stub
		super.withdraw(amount, password);
		
		if(balance<0) {
			balance-= (-balance*0.01);
		}
	}

}
