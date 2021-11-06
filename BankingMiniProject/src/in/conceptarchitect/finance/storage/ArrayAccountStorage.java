package in.conceptarchitect.finance.storage;

import in.conceptarchitect.finance.Accounts;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;

public class ArrayAccountStorage implements AccountStorage  {

		
		int lastId=0;
		int accountCount=0;
		
		
		Accounts [] accounts= new Accounts[100]; //MAX 100. May not be great for large banks

		
		@Override
		public int addAccount(Accounts account) {
			int accountNumber= ++ lastId;
			account.setAccountNumber(accountNumber);
			accounts[accountNumber] = account; //store this account in the array.
			accountCount++;
			return accountNumber;
		}
		
		 @Override
		public Accounts getAccountByNumber(int accountNumber) {
				// TODO Auto-generated method stub
				 
				if(accountNumber<0 || accountNumber>lastId || accounts[accountNumber]==null)
					 throw new InvalidAccountException(accountNumber);
				
				return accounts[accountNumber];
				
					
			}
		 
		 @Override
		public void removeAccount(Accounts account) {
			 accounts[account.getAccountNumber()]=null;
			 accountCount--;
		 }


		@Override
		public int size() {
			// TODO Auto-generated method stub
			return accountCount;
		}
		
		@Override
		public Accounts[] getAllAccounts() {
			return accounts;
		}
	
		
		@Override //default method
		public void process(Processor<Accounts> accountProcessor) {
		
			if(!accountProcessor.initialize())
				return ;
			
			
			for(int i=0;i<=lastId;i++)
				if(accounts[i]!=null)
					accountProcessor.process(accounts[i]);
			
			accountProcessor.close();
		}
		
		
}