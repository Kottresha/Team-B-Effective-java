package in.conceptarchitect.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.finance.exceptions.InsufficientBalanceException;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;
import in.conceptarchitect.finance.storage.AccountStorage;
import in.conceptarchitect.finance.storage.ArrayAccountStorage;

public class BankInternalImplementationTests {
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
	
	public void assertTransactionFailed(boolean result,int account) {
		assertFalse(result);
		assertBalanceUnchanged(account);
	}
	
	public void assertTransactionSuccess(boolean result, int account,double balance) {
		
		assertTrue(result);
		assertBalanceEquals(account, balance);
	}
	
	
	double amount=10000;
	int totalAccounts;
	
	@Before
	public void setup() {
		//ARRANGE
		AccountStorage storage=new ArrayAccountStorage();
		bank=new Bank(storage,"ICICI",interestRate);
		sa=bank.openAccount("savings","a1",correctPassword,amount);	
		ca=bank.openAccount("savings","a2",correctPassword,amount);
		oda=bank.openAccount("od", "a3", correctPassword, amount);
		totalAccounts=bank.getAccountCount();
	}
	
	@Test
	public void getAccountByNumber_returnsValidAccountForValidAccountNumber() {
		var account=bank.storage.getAccountByNumber(sa);		
		assertNotNull(account);
		
	}
	
	@Test(expected=InvalidAccountException.class)
	public void getAccountByNumber_returnsNullForInvalidAccountNumber() {
		var account=bank.storage.getAccountByNumber(-1);
		
	}
	
	@Test(expected=InvalidAccountException.class)
	public void closeAccount_removesClosedAccount() {
		
		bank.closeAccount(sa, correctPassword);		
		bank.storage.getAccountByNumber(sa);
	}

	@Test(expected=InsufficientBalanceException.class)
	public void closeAccount_failsForAccountWithNegativeBalance() {
		//Arrange
		var account=bank.storage.getAccountByNumber(sa);
		account.balance=-1; //because I am in the same package I can do this logic
		bank.closeAccount(sa, correctPassword);
		
	}
	
	
	@Test
	public void accountNumbersAreAlwaysUnique() {
		
		//Arrange
		bank.closeAccount(sa, correctPassword);
		
		int newAccount= bank.openAccount("savings","new", correctPassword, amount);
		
		
		var a2Account= bank.storage.getAccountByNumber(ca);
		
		
		
		assertEquals("a2", a2Account.getName());
		
		assertNotEquals(ca, newAccount);
		
	}
	
	
	
}
