package in.conceptarchitect.finance;

public class CurrentAccount extends BankAccount {

	public CurrentAccount(int accountNumber, String name, String password, double amount) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void creditInterest(double interestRate) {
		//do nothing. don't provide any interst
	}

	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return balance;
	}

}
