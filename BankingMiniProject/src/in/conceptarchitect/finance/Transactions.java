package in.conceptarchitect.finance;

import java.time.LocalDate;

public class Transactions {
	
	String mode,description;
	int AccountNumber;
	double amount;
	LocalDate date = null;
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.AccountNumber = accountNumber;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setDescription() {
		switch(this.mode) {
		case "Withdraw":
		case "Deposited":
			date = LocalDate.now();
			this.description = mode + " " + amount + " on " + date;
			break;
		case "Transferred":
			date = LocalDate.now();
			this.description = mode + " " + amount + " to Account " + this.AccountNumber + " on " + date; 
			break;
		case "Received":
			date = LocalDate.now();
			this.description = mode + " " + amount + " from Account " + this.AccountNumber + " on " + date;
		case "Interest":
			date = LocalDate.now();
			this.description = "Interest Credited on " + date;
		}
	}
	
	public void setDescription(String message) {
		date = LocalDate.now();
		this.description = message + date;
	}
}
