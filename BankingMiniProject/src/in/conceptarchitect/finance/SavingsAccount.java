package in.conceptarchitect.finance;

import in.conceptarchitect.finance.exceptions.InsufficientBalanceException;

public class SavingsAccount extends BankAccount{

	public SavingsAccount(int accountNumber	, String name, String password, double amount) {
		// TODO Auto-generated constructor stub
		super(accountNumber,name,password,amount);
	}

	public int getMinBalance() {
		// TODO Auto-generated method stub
		return 5000;
	}
	
	@Override
	protected double getMaxWithdrawAmount() {
		// TODO Auto-generated method stub
		return balance-getMinBalance();
	}
	
	
//	@Override
//	public void withdraw(double amount, String password) {
//		
//		if(amount> getBalance()-getMinBalance())
//			throw new InsufficientBalanceException(getAccountNumber(), amount-(getBalance()-getMinBalance()));
//		
//		//call regular withdraw
//		super.withdraw(amount, password);
//	}

}	
