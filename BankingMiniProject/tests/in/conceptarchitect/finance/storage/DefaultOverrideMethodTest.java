package in.conceptarchitect.finance.storage;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.finance.BankAccount;
import in.conceptarchitect.finance.CurrentAccount;
import in.conceptarchitect.finance.OverdraftAccount;
import in.conceptarchitect.finance.SavingsAccount;

public class DefaultOverrideMethodTest {
	
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
	
	class ConditionalCounter implements Processor<BankAccount>{

		int count;
		private boolean shouldWeCount;
		
		ConditionalCounter(boolean shouldWeCount){
			this.shouldWeCount=shouldWeCount;
		}
		
		@Override
		public boolean initialize() {
			// TODO Auto-generated method stub
			return shouldWeCount;
		}
		
		@Override
		public void process(BankAccount object) {
			//TODO Auto-generated method stub
			count++;			
		}
		
		
	}

	@Test
	public void countWorksIfWeProvideTrueForShouldWeCount() {
		// TODO Auto-generated constructor stub
		var counter=new ConditionalCounter(true);
		storage.process(counter);
		assertEquals(storage.size(), counter.count);
	}
	
	@Test
	public void counterDoesntWorkIfInitializationFails() {
		var counter=new ConditionalCounter(false);
		storage.process(counter);
		
		assertEquals(0, counter.count);
	}

}
