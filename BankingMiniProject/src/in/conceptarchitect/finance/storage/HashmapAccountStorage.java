package in.conceptarchitect.finance.storage;

import java.util.HashMap;

import in.conceptarchitect.finance.Accounts;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;

public class HashmapAccountStorage implements AccountStorage {
	
	HashMap<Integer,Accounts> accounts=new HashMap<>();
	int lastId=0;
	
	@Override
	public int addAccount(Accounts account) {
		// TODO Auto-generated method stub
		int id=++lastId;
		account.setAccountNumber(id);
		
		accounts.put(id, account);
		return id;
	}

	@Override
	public Accounts getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		if(accounts.containsKey(accountNumber))
			return accounts.get(accountNumber);
		else
			throw new InvalidAccountException(accountNumber);
	}

	@Override
	public void removeAccount(Accounts account) {
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
	public Accounts[] getAllAccounts() {
		// TODO Auto-generated method stub
		
		return accounts.values().toArray(new Accounts[0]);
	}

}
