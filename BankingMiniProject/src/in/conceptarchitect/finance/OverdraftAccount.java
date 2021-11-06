package in.conceptarchitect.finance;



import in.conceptarchitect.finance.exceptions.InsufficientBalanceException;

public class OverdraftAccount extends BankAccount {

	
	double odLimit;
	public OverdraftAccount(int accountNumber, String name, String password, double amount) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
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
	
	
//	@Override
//	public void withdraw(double amount, String password) {
//		// TODO Auto-generated method stub
//		validateDenomination(amount);
//		authenticate(password);
//		if(amount > getBalance() + getOdLimit())
//			throw new InsufficientBalanceException(getAccountNumber(), amount- (getBalance()+getOdLimit()));
//		
//		balance-=amount;
//		
//		if(balance<0) {
//			balance-= (-balance*0.01);
//		}
//	}
	
	
	
	
}
		