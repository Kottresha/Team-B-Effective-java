package in.conceptarchitect.finance;

import java.sql.Date;
import java.time.LocalDate;

public class Transactions {
	
	String mode,description;
	int AccountNumber;
	double amount;
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(String mode, String description, int accountNumber, double amount, Date date) {
		super();
		this.mode = mode;
		this.description = description;
		AccountNumber = accountNumber;
		this.amount = amount;
		this.date = date;
	}

	Date date = null;
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.AccountNumber = accountNumber;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	

	@Override
	public String toString() {
		return "Transactions [mode=" + mode + ", description=" + description + ", AccountNumber=" + AccountNumber
				+ ", amount=" + amount + ", date=" + date + "]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMode() {
		return mode;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}


	
	

}
	

