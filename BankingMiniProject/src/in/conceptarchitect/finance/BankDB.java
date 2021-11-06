package in.conceptarchitect.finance;

public class BankDB {

	private int BankId;
	private String BankName;
	
	
	public BankDB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankDB(int bankId, String bankName) {
		super();
		BankId = bankId;
		BankName = bankName;
	}
	public int getBankId() {
		return BankId;
	}
	public void setBankId(int bankId) {
		BankId = bankId;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	@Override
	public String toString() {
		return "BankDB [BankId=" + BankId + ", BankName=" + BankName + "]";
	}
	
	
}
