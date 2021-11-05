package in.conceptarchitect.finance;

public class Overdraft extends Accounts {

	double odLimit;
	public Overdraft(String name, String password, int accountNumber, double balance) {
		super(name, password, accountNumber, balance);
		updateOdLimit();
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
