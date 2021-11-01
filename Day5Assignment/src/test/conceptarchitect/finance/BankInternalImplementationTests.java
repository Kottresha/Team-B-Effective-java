package test.conceptarchitect.finance;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.finance.Bank;
import in.conceptarchitect.finance.BankAccount;

public class BankInternalImplementationTests {
	
	Bank bank;
	double interestRate=12;
	String correctPassword="password";
	int a1,a2;
	
	//user defined assertions
	public void assertBalanceEquals(int accountNumber, double balance) {
		assertEquals(balance, bank.getAccountBalance(accountNumber),0.01);
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
		bank=new Bank("ICICI",interestRate);
		a1=bank.openAccount("a1","savings",correctPassword,amount);
		a2=bank.openAccount("a2","savings",correctPassword,amount);
		totalAccounts=bank.getAccountCount();
	}
	
	@Test
	public void getAccountByNumber_returnsValidAccountForValidAccountNumber() {
		var account=bank.getAccountByNumber(a1);		
		assertNotNull(account);
		
	}
	
	@Test
	public void getAccountByNumber_returnsNullForInvalidAccountNumber() {
		var account=bank.getAccountByNumber(-1);
		assertNull(account);
	}
	
	@Test
	public void closeAccount_removesClosedAccount() {
		
		bank.closeAccount(a1, correctPassword);		
		assertNull(bank.getAccountByNumber(a1));
	}

	@Test
	public void closeAccount_failsForAccountWithNegativeBalance() {
		//Arrange
		var account=bank.getAccountByNumber(a1);
		account.balance=-1; //because I am in the same package I can do this logic
		
		var result= bank.closeAccount(a1, correctPassword);
		
		assertFalse(result);
		assertNotNull(bank.getAccountByNumber(a1));
		
		
	}
	
	
	@Test
	public void accountNumbersAreAlwaysUnique() {
		
		//Arrange
		bank.closeAccount(a1, correctPassword);
		
		int newAccount= bank.openAccount("new","savings", correctPassword, amount);
		
		
		var a2Account= bank.getAccountByNumber(a2);
		
		
		
		assertEquals("a2", a2Account.getName());
		
		assertNotEquals(a2, newAccount);
		
	}

        @Test
	public void depositamountshouldFailForAnInvalidAmount() {
		boolean result= bank.deposit(a1, -1);
		assertFalse(result);
		assertEquals(amount, bank.getAccountBalance(a1),0.01);
	}
	
	@Test
	public void deposit_shouldFailForInvalidAccountNumber() {
	       boolean result=bank.deposit(1000, 1);
	       assertFalse(result);		
	}
        
        @Test
	public void depositamountshouldWorkForAnValidAmountAndAccountNumber() {
		
	       boolean result = bank.deposit(a1, 1);
	       assertEquals(true,result);
	       assertEquals(amount+1, bank.getAccountBalance(a1),0.01);
	}

        @Test
	public void withdrawamountShouldFailForInvalidAmount() {
	
		boolean result=account.withdraw(-1, password);		
		assertEquals(false, result);
	}
	

	@Test 
        public void withdrawamountShouldFailForInvalidPassword() {
		
		boolean result=account.withdraw(1, "wrong-password");
		assertFalse(result);
	}
	
	
	@Test 
        public void withdrawamountShouldFailForExcessAmount() {
		boolean result=account.withdraw(amount+1, password);
		assertEquals(false,result);
	}
        
        @Test
	public void withdrawamountshouldWorkWithValidDetails() {
		
		boolean status = bank.transfer(a1,1,correctPassword,a2);
		assertTransactionSuccess(status, a1, amount-1);		
		assertTransactionSuccess(status, a2, amount+1);
	}
 
        @Test
	public void transferamountshouldFailForInvalidAmount() {
		boolean result= bank.transfer(a1, -1, correctPassword, a2);
		assertTransactionFailed(result, a1);
		assertTransactionFailed(result, a2);
	}
	
	@Test
	public void transferamountshouldFailForInvalidSourceAccountNumber() {
		boolean result=bank.transfer(-1, 1, correctPassword, a2);
		
		assertTransactionFailed(result, a2);
	}
	
	@Test
	public void transferamountshouldFailForInvalidTargetAccountNumber() {
		boolean result=bank.transfer(a1, 1, correctPassword, -1);
		assertFalse(result);
		
	}
	
	@Test
	public void transferamountshouldFailForInvalidPassword() {
		var result= bank.transfer(a1, 1, "invalid-password", a2);
		assertTransactionFailed(result, a1);
		assertTransactionFailed(result, a2);
		
	}
	
	@Test
	public void transferamountshouldFailForExcessAmount() {
		var result=bank.transfer(a1, amount+1, correctPassword, a2);
		assertTransactionFailed(result, a1);
		assertTransactionFailed(result, a2);
	}
       
        @Test 
        public void transferamountShouldvalidForAccountHavingValidAmountAndPassword() {
              int accountNumber=1;
              
              double amount=30000;

              String password="password";

              BankAccount account=new BankAccount(1,"Vivek",password, amount); 

              boolean result=account.withdraw(accountNumber,amount, password);
             
              assertTrue(result);
              assertEquals(0, account.getBalance(),0.001);
       }

       @Test
       public void ShowBalanceFailForInvalidAccountNumber() {
	       boolean result=bank.deposit(1000, 1);
	       assertFalse(result);
       }

       @Test
       public void ShowBalancePassForvalidAccountNumber() {
	       boolean result=bank.deposit(1, 1);
	       assertTrue(result);
	}
	
	
}