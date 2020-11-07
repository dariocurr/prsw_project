package bank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Bank implements IBank {

	private List<IBankAccount> accounts;
	
	public Bank() {
		this.accounts = this.loadBankAcoountsFromFile("res/bank_accounts_list.json");
	}
	
	@Override
	public boolean accountHasAvailability(String accountNumber, double amount) {
		Objects.requireNonNull(accountNumber);
		for (IBankAccount account : this.accounts) {
			if(account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
				return account.getAmount() >= amount;
			}
		}
		return false;
	}
	
	private List<IBankAccount> loadBankAcoountsFromFile(String url) {
		JSONParser jsonParser = new JSONParser();	
		List<IBankAccount> bankAccountsList = new ArrayList<>();
		try (FileReader reader = new FileReader(url)) {
            Object obj = jsonParser.parse(reader);
            JSONArray bankAccounts = (JSONArray) obj;
            for(Object bankAccount : bankAccounts) {
            	bankAccountsList.add(parseBankAccountObject( (JSONObject) bankAccount));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return bankAccountsList;
	}
	
	private static IBankAccount parseBankAccountObject(JSONObject bankAccountObject) {
		String accountNumber = (String) bankAccountObject.get("accountNumber");
		String amount = (String) bankAccountObject.get("amount"); 
		IBankAccount bankAccount = new BankAccount(accountNumber, Double.parseDouble(amount));
        return bankAccount;
    }
	
}
