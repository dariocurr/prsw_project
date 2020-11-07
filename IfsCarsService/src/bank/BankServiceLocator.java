/**
 * BankServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank;

public class BankServiceLocator extends org.apache.axis.client.Service implements bank.BankService {

    public BankServiceLocator() {
    }


    public BankServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BankServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Bank
    private java.lang.String Bank_address = "http://localhost:8080/BankWebService/services/Bank";

    public java.lang.String getBankAddress() {
        return Bank_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BankWSDDServiceName = "Bank";

    public java.lang.String getBankWSDDServiceName() {
        return BankWSDDServiceName;
    }

    public void setBankWSDDServiceName(java.lang.String name) {
        BankWSDDServiceName = name;
    }

    public bank.Bank getBank() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Bank_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBank(endpoint);
    }

    public bank.Bank getBank(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            bank.BankSoapBindingStub _stub = new bank.BankSoapBindingStub(portAddress, this);
            _stub.setPortName(getBankWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBankEndpointAddress(java.lang.String address) {
        Bank_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (bank.Bank.class.isAssignableFrom(serviceEndpointInterface)) {
                bank.BankSoapBindingStub _stub = new bank.BankSoapBindingStub(new java.net.URL(Bank_address), this);
                _stub.setPortName(getBankWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Bank".equals(inputPortName)) {
            return getBank();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bank", "BankService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bank", "Bank"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Bank".equals(portName)) {
            setBankEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
