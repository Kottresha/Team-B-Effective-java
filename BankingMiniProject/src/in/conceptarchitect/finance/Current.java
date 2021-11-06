package in.conceptarchitect.finance;


import in.conceptarchitect.exceptions.InvalidAccountTypeException;

public class Current extends Accounts {

	public Current(String name, String password, int accountNumber, double balance) {
		super(name, password, accountNumber, balance);
		

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