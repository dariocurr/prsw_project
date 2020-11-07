package bank;

public class BankProxy implements bank.Bank {
  private String _endpoint = null;
  private bank.Bank bank = null;
  
  public BankProxy() {
    _initBankProxy();
  }
  
  public BankProxy(String endpoint) {
    _endpoint = endpoint;
    _initBankProxy();
  }
  
  private void _initBankProxy() {
    try {
      bank = (new bank.BankServiceLocator()).getBank();
      if (bank != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bank)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bank)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bank != null)
      ((javax.xml.rpc.Stub)bank)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public bank.Bank getBank() {
    if (bank == null)
      _initBankProxy();
    return bank;
  }
  
  public boolean makePayment(java.lang.String accountNumber, double amount, java.lang.String currency) throws java.rmi.RemoteException{
    if (bank == null)
      _initBankProxy();
    return bank.makePayment(accountNumber, amount, currency);
  }
  
  public double getExchangeFromEUR(java.lang.String currency, double amount) throws java.rmi.RemoteException{
    if (bank == null)
      _initBankProxy();
    return bank.getExchangeFromEUR(currency, amount);
  }
  
  
}