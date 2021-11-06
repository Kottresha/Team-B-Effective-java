package in.conceptarchitect.finance.storage;

import java.util.HashMap;

import in.conceptarchitect.finance.BankAccount;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;

public class HashmapAccountStorage implements AccountStorage {
	
	HashMap<Integer,BankAccount> accounts=new HashMap<>();
	int lastId=0;
	
	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		int id=++lastId;
		account.setAccountNumber(id);
		
		accounts.put(id, account);
		return id;
	}

	@Override
	public BankAccount getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		if(accounts.containsKey(accountNumber))
			return accounts.get(accountNumber);
		else
			throw new InvalidAccountException(accountNumber);
	}

	@Override
	public void removeAccount(BankAccount account) {
		// TODO Auto-generated method stub
		getAccountByNumber(account.getAccountNumber());
		accounts.remove(account.getAccountNumber());

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return accounts.size();
	}

	@Override
	public BankAccount[] getAllAccounts() {
		// TODO Auto-generated method stub
		
		return accounts.values().toArray(new BankAccount[0]);
	}

}
