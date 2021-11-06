package in.conceptarchitect.finance;


import in.conceptarchitect.Database.DatabaseConnection;
import in.conceptarchitect.exceptions.InvalidAccountTypeException;

public class Current extends Accounts {

	public Current(String name, String password, int accountNumber, double balance,String accountType,int bankId) throws Exception {
		super(name, password, accountNumber, balance,accountType,bankId);
		

		String sqlQuery = "insert into currents values ("+this.accountNumber+",'"+this.name+"',"+this.password+","+this.balance+",0);";
		DatabaseConnection.insertQuery(sqlQuery);
	}
	
	@Override
	public void creditInterest(double interestRate) {
		throw new InvalidAccountTypeException("Current Account ", " Interest are not available to this type accounts...");
	}

	@Override
	protected double getMaxWithdrawAmount() {
		return this.balance;
	}

}
