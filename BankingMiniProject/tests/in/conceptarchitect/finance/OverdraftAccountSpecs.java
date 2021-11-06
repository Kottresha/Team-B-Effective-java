package in.conceptarchitect.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.finance.exceptions.InsufficientBalanceException;

public class OverdraftAccountSpecs {
	
	OverdraftAccount account;
	double amount=10000;
	String correctPassword="pass";
	
	@Before
	public void setup() {
		account=new OverdraftAccount(1,"account",correctPassword,amount);
	}

	
	//@Ignore
	@Test
	public void odLimit_shouldBe10PercentOfOriginalBalance() {
		
		double expectedOdLimit = account.getBalance()/10;
		
		
		assertEquals(expectedOdLimit, account.getOdLimit(), 0.01);
	}
	
	//@Ignore
	@Test
	public void deposit_shouldUpdateOdLimitIfBalanceCrossesHistoricMaxBalance() {
		
		account.deposit(90000);
		
		double expectedOdLimit= account.getBalance()/10;
		
		assertEquals(expectedOdLimit, account.getOdLimit(),0.01);
		
	}
	
	//@Ignore
	@Test
	public void odLimit_shouldBe10PercentOfHistoricMaxBalance() {
		
		//Arrange
		account.deposit(90000); //we reached a histroic max balance
		var odLimit = account.getOdLimit(); //odLimit at max balance
		
		account.withdraw(50000, correctPassword); //now balance reduces to 50000
		
		account.deposit(10000); //we made another deposit but didn't reached historic balance
		
		//my od limit should be same as it was at historic max balance 
		assertEquals(odLimit, account.getOdLimit(),0.01);
	}
	
	//@Ignore
	@Test
	public void withdraw_shouldSucceedForAmountUptoBalancePlusOdLimit() {
		
		account.withdraw(amount+100, correctPassword);
		
		//there should be no error
		assertTrue(account.getBalance()<0);
	}
	
	//@Ignore
	@Test()
	public void withdraw_shouldFailForAmountExceedingBalancePlusOdLimit() {
		try {
			account.withdraw(amount+account.getOdLimit()+1, correctPassword);
			fail("withdrawal should have failed");
		}catch(InsufficientBalanceException ex) {
			assertEquals(1, ex.getDeficit(),0);
			assertEquals(amount,account.getBalance(),0);
		}
	}
	
	//@Ignore
	@Test
	public void withdraw_shouldCharge1PercentFeeOnOdLimit() {
		
		double overDraft=500;
		account.withdraw(amount+overDraft, correctPassword);
		
		
		double odFee= overDraft*0.01; //5
		
		double expectedBalance = -(overDraft + odFee); //-505
		
		
		assertEquals(expectedBalance, account.getBalance(),0.01);
		
	}
	
	
	//@Ignore
	@Test
	public void creditInterest_shouldCreditInterest() {
		account.creditInterest(12);
		double expectedAmount= amount+ amount/100;
		assertEquals(expectedAmount,account.getBalance(),0.01);
	}
	
	//@Ignore
	@Test
	public void creditInterest_shouldIncreaseOdLimitIfNeeded() {
		account.creditInterest(12);
		double expectedAmount= amount+ amount/100;
		
		double expectedOdLimit= expectedAmount/10;
		
		assertEquals(expectedOdLimit,account.getOdLimit(),0.01);
	}
	
	
	
	
	
	
	
}
