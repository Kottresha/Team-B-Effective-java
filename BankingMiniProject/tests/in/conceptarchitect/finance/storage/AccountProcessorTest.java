package in.conceptarchitect.finance.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.finance.BankAccount;
import in.conceptarchitect.finance.CurrentAccount;
import in.conceptarchitect.finance.OverdraftAccount;
import in.conceptarchitect.finance.SavingsAccount;

public class AccountProcessorTest {

	AccountStorage storage;
	String correctPassword="pass";
	double amount=10000;
	
	@Before
	public void setup() {
		
		storage=AccountStorage.getDefaultStorage(); //static method example
		storage.addAccount(new SavingsAccount(1,"SA",correctPassword,amount));
		storage.addAccount(new CurrentAccount(1,"CA",correctPassword,amount));
		storage.addAccount(new OverdraftAccount(1,"ODA",correctPassword,amount));
		
		
	}
	
	@Test
	public void defaultStorageIsHashMapAccountStorage() {
	
		assertTrue(storage instanceof HashmapAccountStorage);
	}
	
	class Counter{
		int count;
		void increment() { count++ ; }
	}
	
	@Test
	public void canCountTotalNumberOfAccounts() {
		
		//final int count=0;
		final var counter=new Counter();
		
		storage.process(new Processor<BankAccount>() {
			public void process(BankAccount object) {
				
				counter.increment();
			}
		});
		
		assertEquals(storage.size(), counter.count);		
		
	}
	
	@Test
	public void canCountTotalNumberOfAccountsV2() {
		
		var counter=new  Counter();
		
		storage.process(account-> counter.increment());
		
		//storge.process( counter::increment); //increment doesn't take parameter so it can't process(T obj)
		
		assertEquals(storage.size(), counter.count);
		
	}
	
	
	
	
	@Test
	public void canCountAllSavingsAccount() {
		
		storage.addAccount(new SavingsAccount(1,"SA",correctPassword,amount));
		final int count[]= {0};
		
		Processor<BankAccount> accountCounter= (account) ->{
			if(account instanceof SavingsAccount)
				count[0]++;
		};
		
		storage.process(accountCounter);
		
		assertEquals(2, count[0]);		
		
	}
	
	
	@Test
	public void canFindBalanceInAllAccount() {
		
		//array of one double is similar to a double. 
		//but it is reference
		final double totalBalance[]= {0}; 
		
		storage.process(new Processor<BankAccount>() {

			@Override
			public void process(BankAccount account) {
				// TODO Auto-generated method stub
				totalBalance[0]+=account.getBalance();
			}
			
		});
		
		assertEquals(amount*storage.size(), totalBalance[0],0);
		
	}
	
	@Test
	public void canSumBalancesOfAllAccountVersion2() {
		
		final double totalBalance[]= {0};
		
		storage.process(account -> totalBalance[0]+=account.getBalance());
		
		assertEquals(amount*storage.size(), totalBalance[0],0);
		
		
	}
	
	
	
	

}
