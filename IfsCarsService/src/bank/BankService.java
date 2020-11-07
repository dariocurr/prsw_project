/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank;

public interface BankService extends javax.xml.rpc.Service {
    public java.lang.String getBankAddress();

    public bank.Bank getBank() throws javax.xml.rpc.ServiceException;

    public bank.Bank getBank(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
