package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.currencysystem.webservices.currencyserver.CurncsrvReturnRate;
import com.currencysystem.webservices.currencyserver.CurrencyServerLocator;
import com.currencysystem.webservices.currencyserver.CurrencyServerSoap;

import common.IBank;
import common.IBankAccount;

public class Bank implements IBank {

	private List<IBankAccount> accounts;
	
	public Bank() {
		this.accounts = Bank.loadBankAcoountsFromFile("res" + File.separator + "bank_accounts_list.json");
	}
	
	@Override
	public boolean makePayment(String accountNumber, double amount, String currency) {
		Objects.requireNonNull(accountNumber);
		Objects.requireNonNull(currency);
		if (currency != "EUR") {
			CurrencyServerSoap currencySystem;
			try {
				currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
				amount = (double) currencySystem.convert("", currency, "EUR", amount, false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(amount);
		for (IBankAccount account : this.accounts) {
			if(account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
				account.setAmount(account.getAmount() - amount);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public double getExchangeFromEUR(String currency, double amount) {
		Objects.requireNonNull(currency);
		try {
			CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
			return (double) currencySystem.convert("", currency, "EUR", amount, false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static List<IBankAccount> loadBankAcoountsFromFile(String url) {
		JSONParser jsonParser = new JSONParser();	
		List<IBankAccount> bankAccountsList = new ArrayList<>();
		try (FileReader reader = new FileReader(url)) {
            Object obj = jsonParser.parse(reader);
            JSONArray bankAccounts = (JSONArray) obj;
            for(Object bankAccount : bankAccounts) {
            	bankAccountsList.add(parseBankAccountObject( (JSONObject) bankAccount));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Reading from bank accounts json");
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("ERROR: Reading from bank accounts json");
            e.printStackTrace();
        } catch (ParseException e) {
        	System.out.println("ERROR: Reading from bank accounts json");
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
