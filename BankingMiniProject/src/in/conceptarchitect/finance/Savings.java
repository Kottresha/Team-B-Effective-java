package in.conceptarchitect.finance;

public class Savings extends Accounts{
	
	public Savings(String name, String password, int accountNumber, double balance) {
		super(name, password, accountNumber, balance);
	}

	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return this.balance - 5000;
	}
	
}