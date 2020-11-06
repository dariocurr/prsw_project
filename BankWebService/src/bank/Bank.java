package bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<BankAccount> accounts;
	
	public Bank() {
		this.accounts = new ArrayList<BankAccount>();
	}
	
	public boolean accountHasAvailability(String accountNumber, double amount) {
		for (BankAccount account : this.accounts) {
			if(account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
				return account.getAmount() >= amount;
			}
		}
		return false;
	}
	
	
}
