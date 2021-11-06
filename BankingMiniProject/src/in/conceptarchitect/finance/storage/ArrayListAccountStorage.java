package in.conceptarchitect.finance.storage;

import java.util.ArrayList;

import in.conceptarchitect.finance.BankAccount;
import in.conceptarchitect.finance.exceptions.InvalidAccountException;

public class ArrayListAccountStorage implements AccountStorage {
	
	ArrayList<BankAccount> accounts=new ArrayList<>();
	int lastId;

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		int id=++lastId;
		account.setAccountNumber(id);
		accounts.add(account);
		return id;
	}

	@Override
	public BankAccount getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		for(var account : accounts)
			if(account.getAccountNumber()==accountNumber)
				return account;
		
		throw new InvalidAccountException(accountNumber);
	}

	@Override
	public void removeAccount(BankAccount account) {
		// TODO Auto-generated method stub
		accounts.remove(account);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return accounts.size();
	}

	@Override
	public BankAccount[] getAllAccounts() {
		// TODO Auto-generated method stub
		BankAccount [] array=new BankAccount[size()];
		
		return accounts.toArray(array);
	}

}
