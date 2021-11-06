package in.conceptarchitect.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.conceptarchitect.finance.AccountData;
import in.conceptarchitect.finance.BankDB;
import in.conceptarchitect.finance.MainAccountData;
import in.conceptarchitect.finance.Transactions;

public class DatabaseConnection {
	
	public boolean insertTransactions(Transactions t)throws Exception{
		
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","Banking","Banking");
		
		Statement stmt=c.createStatement();
		
		boolean b=stmt.execute("insert into Transactions values('"+t.getMode()+"','"+t.getDescription()+"','"+t.getAccountNumber()+"','"+t.getAmount()+"','"+t.getDate()+"')");
		
		c.close();
		
		return b;
	}
	
	public boolean insertBank(BankDB db)throws Exception{
		
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","Banking","Banking");
		
		Statement stmt=c.createStatement();
		
		boolean b=stmt.execute("insert into Bank values('"+db.getBankId()+"','"+db.getBankName()+"')");
		
		c.close();
		
		return b;
	}

	public boolean insertSavings(AccountData ad)throws Exception{
		
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","Banking","Banking");
		
		Statement stmt=c.createStatement();
		
		boolean b=stmt.execute("insert into Savings values('"+ad.getAccountNumber()+"','"+ad.getHolderName()+"','"+ad.getAccountType()+"','"+ad.getPassword()+"','"+ad.getBalance()+"')");
		
		c.close();
		
		return b;
	}

	public boolean insertAccount(MainAccountData madb)throws Exception{

		
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","Banking","Banking");
		
		Statement stmt=c.createStatement();
		
		boolean b=stmt.execute("insert into Accounts values('"+madb.getAccountNumber()+"','"+madb.getAccountType()+"','"+madb.getBankid()+"')");
		
		c.close();
		
		return b;
	}


public List<Transactions> getAllTransactions() throws Exception {
List<Transactions> li=new ArrayList<Transactions>();
	
	Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","Banking","Banking");

	PreparedStatement ps=c.prepareStatement("select * from transactions");
	
	ResultSet rs=ps.executeQuery();
	
	while(rs.next()) {
		li.add(new Transactions(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getDate(5)));
	}
				
	c.close();

	return li;
}
}