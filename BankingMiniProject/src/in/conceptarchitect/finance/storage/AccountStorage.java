package in.conceptarchitect.finance.storage;

import in.conceptarchitect.finance.Accounts;

//@FunctionalInterface
public interface AccountStorage {

	int addAccount(Accounts account);

	Accounts getAccountByNumber(int accountNumber);

	void removeAccount(Accounts account);

	int size();

	Accounts[] getAllAccounts();
	
	default void process(Processor<Accounts> accountProcessor) {
		//if initialization fails do not process
		if(!accountProcessor.initialize())
			return;
		
		for(var account : this.getAllAccounts())
			accountProcessor.process(account);
		
		accountProcessor.close();		
	}
	
	public static AccountStorage getDefaultStorage() {
		return new HashmapAccountStorage();
	}

}