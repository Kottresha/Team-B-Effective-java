package test.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.finance.exceptions.InsufficientBalanceException;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;
import in.conceptarchitect.finance.exceptions.InvalidAccountTypeException;
import in.conceptarchitect.finance.exceptions.InvalidCredentialsException;
import in.conceptarchitect.finance.storage.AccountStorage;
import in.conceptarchitect.finance.storage.ArrayAccountStorage;
import in.conceptarchitect.finance.storage.ArrayListAccountStorage;
import in.conceptarchitect.finance.storage.HashmapAccountStorage;

public class BankTest {
	
	//Object under Test
	Bank bank;
	double interestRate=12;
	String correctPassword="password";
	int sa,ca,oda;
	
	//user defined assertions
	public void assertBalanceEquals(int accountNumber, double balance) {
		assertEquals(balance, bank.getAccountBalance(accountNumber,correctPassword),0.01);
	}
	
	public void assertBalanceUnchanged(int accountNumber) {
		assertBalanceEquals(accountNumber, amount);
	}
	
	public void assertTransactionFailed(int account) {
		
		assertBalanceUnchanged(account);
	}
	
	public void assertTransactionSuccess(int account,double balance) {
		assertBalanceEquals(account, balance);
	}
	
	
	double amount=10000;
	int totalAccounts;
	
	@Before
	public void setup() {
		//ARRANGE
		//AccountStorage storage=new ArrayAccountStorage();
		//AccountStorage storage=new ArrayListAccountStorage();
		var storage= new HashmapAccountStorage();
		bank=new Bank(storage,"ICICI",interestRate);
		sa=bank.openAccount("savings","one",correctPassword,amount);
		ca=bank.openAccount("current","two",correctPassword,amount);
		oda=bank.openAccount("od", "three", correctPassword, amount);
		totalAccounts=bank.getAccountCount();
	}
	

	//
	@Test
	public void openAccountShouldGenerateUniqueAccountNumberInAscendingSequence() {
		int newAccount=bank.openAccount("savings","new", correctPassword, amount);
		
		assertEquals(totalAccounts+1, newAccount);
	}
	
	//@Ignore 
	@Test
	public void openAccountShouldIncreaseTheAccountCount() {
		
		int newAccount=bank.openAccount("savings","new", correctPassword, amount);
		int accountCount=bank.getAccountCount();
		assertEquals(totalAccounts+1, accountCount);
		
	}
	
	
	
	@Test(expected=InvalidAccountTypeException.class)
	public void openAccount_failsForInvalidAccountType() {
		bank.openAccount("invalid", "new", correctPassword, amount);
	}
	
	
	@Test
	public void creditInterest_currentAccountWillNotGetInterest() {
		
		bank.creditInterst();
		assertEquals(amount, bank.getAccountBalance(ca, correctPassword),0);
	}
	
	@Test
	public void creditInterest_SavingsAccountWillGetInterest() {
		bank.creditInterst();
		double expectedBalance= amount+ amount/100;
		
		assertEquals(expectedBalance, bank.getAccountBalance(sa, correctPassword),0.01);
	}
	
	@Test
	public void creditInterest_odAccountWillGetInterest() {
		bank.creditInterst();
		double expectedBalance= amount+ amount/100;
		
		assertEquals(expectedBalance, bank.getAccountBalance(oda, correctPassword),0.01);
	}
	
	
	
	//@Ignore
	@Test
	public void deposit_shouldWorkForValidAmountAndAccountNumber() {
		
//		boolean result = bank.deposit(a1, 1);
		
//		assertEquals(true,result);
//		assertEquals(amount+1, bank.getAccountBalance(a1),0.01);
		
		bank.deposit(sa, 1);
		assertTransactionSuccess(sa, amount+1);
		
	}
	
	@Ignore
	@Test
	public void deposit_shouldFailForInvalidAmount() {
		
		//bank.deposit(a1, -1)
	//	assertTransactionFailed a1);
	}
	
	@Ignore
	@Test
	public void deposit_shouldFailForInvalidAccountNumber() {
		bank.deposit(1000, 1);
				
	}
	
	@Ignore
	@Test
	public void getBalance_failsForInvalidAccountNumber() {
		//assertEquals(Double.NaN, bank.getAccountBalance(-1),0.0001);
	}
	

	//@Ignore
	@Test
	public void creditInterest_shouldGiveInterestToAllActiveAccounts() {
		
		//ARRANGE
		bank.closeAccount(sa, correctPassword);
		
		//ACT 
		bank.creditInterst();
		
		
		double expectedInterest=amount * 0.01;
		
		assertEquals(amount,bank.getAccountBalance(ca, correctPassword),0.01);
		
		assertEquals(amount+expectedInterest, bank.getAccountBalance(oda, correctPassword),0.01);
	
		
	}
	
	
	
	
	
	
	
	
	
	@Ignore
	@Test
	public void withdraw_shouldFailForInvalidAmount() {
		
	}
	
	@Ignore
	@Test
	public void withdraw_shouldFailForInvalidAccountNumber() {
		
	}
	
	@Ignore
	@Test
	public void withdraw_shouldFailForInvalidPassword() {
		
	}
	
	@Ignore
	@Test
	public void withdraw_shouldFailForExcessAmount() {
		
	}
	
	//@Ignore
	@Test
	public void transfer_shouldWorkWithValidDetails() {
		
		bank.transfer(sa,1,correctPassword,ca);
		
		assertTransactionSuccess( sa, amount-1);		
		assertTransactionSuccess( ca, amount+1);
	}
	
	
	@Ignore
	@Test
	public void transfer_shouldFailForInvalidAmount() {
		bank.transfer(sa, -1, correctPassword, ca);
		assertTransactionFailed( sa);
		assertTransactionFailed( ca);
	}
	
	@Ignore
	@Test
	public void transfer_shouldFailForInvalidSourceAccountNumber() {
		bank.transfer(-1, 1, correctPassword, ca);
		
		assertTransactionFailed( ca);
	}
	
	@Ignore
	@Test
	public void transfer_shouldFailForInvalidTargetAccountNumber() {
		bank.transfer(sa, 1, correctPassword, -1);
		assertTransactionFailed( sa);
	}
	
	
	@Ignore
	@Test
	public void transfer_shouldFailForInvalidPassword() {
		bank.transfer(sa, 1, "invalid-password", ca);
		assertTransactionFailed( sa);
		assertTransactionFailed( ca);
		
	}
	
	@Ignore
	@Test
	public void transfer_shouldFailForExcessAmount() {
		bank.transfer(sa, amount+1, correctPassword, ca);
		assertTransactionFailed(sa);
		assertTransactionFailed(ca);
	}
	

	
	//@Ignore
	@Test(expected = InvalidAccountException.class)
	public void closeAccount_shouldWorkdInHappyPath() {
		bank.closeAccount(sa,correctPassword);		
		//assertTrue(result);
		//assertEquals(Double.NaN, bank.getAccountBalance(a1),0,0);
		bank.getAccountBalance(sa, correctPassword);
	}
	
	//@Ignore
	@Test
	public void closeAccount_shouldDecreaseTheAccountCount() {
		bank.closeAccount(sa,correctPassword);		
		assertEquals(totalAccounts-1, bank.getAccountCount());
	}
	
	
	//@Ignore
	@Test(expected = InvalidCredentialsException.class)
	public void closeAccount_shouldFailForInvalidPassword() {
		 bank.closeAccount(sa, "incorrect-password");
		
	}
	
	//@Ignore
	@Test(expected=InsufficientBalanceException.class)
	public void closeAccount_shouldFailForNegativeBalance() {
		
		//arrange
		bank.withdraw(oda, amount+1, correctPassword); 
		
		assumeTrue(bank.getAccountBalance(oda, correctPassword)<0);
		
		//act
		bank.closeAccount(oda, correctPassword);
		
	}
	
	//@Ignore
	@Test(expected=InvalidAccountException.class)
	public void closeAccount_shouldFailForInvalidAccountNumber() {
		bank.closeAccount(-1, correctPassword);
		
	}
	
	//@Ignore
	@Test(expected=InvalidAccountException.class)
	public void closeAccount_cantCloseSameAccountTwice() {
	
		bank.closeAccount(sa, correctPassword);
		assumeTrue(true); //it is closed the first time
		
		//proceed if the first result is true		
		bank.closeAccount(sa, correctPassword);
		
	}
	
	

	
}
