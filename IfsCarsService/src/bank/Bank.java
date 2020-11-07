/**
 * Bank.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank;

public interface Bank extends java.rmi.Remote {
    public boolean makePayment(java.lang.String accountNumber, double amount, java.lang.String currency) throws java.rmi.RemoteException;
    public double getExchangeFromEUR(java.lang.String currency, double amount) throws java.rmi.RemoteException;
}
