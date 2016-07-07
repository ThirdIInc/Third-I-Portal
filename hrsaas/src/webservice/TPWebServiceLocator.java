/**
 * TPWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class TPWebServiceLocator extends org.apache.axis.client.Service implements webservice.TPWebService {

    public TPWebServiceLocator() {
    }


    public TPWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TPWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TPWebServiceSoap
    private java.lang.String TPWebServiceSoap_address = "http://115.112.131.100/techportalwebservice/TPWebservice.asmx";

    public java.lang.String getTPWebServiceSoapAddress() {
        return TPWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TPWebServiceSoapWSDDServiceName = "TPWebServiceSoap";

    public java.lang.String getTPWebServiceSoapWSDDServiceName() {
        return TPWebServiceSoapWSDDServiceName;
    }

    public void setTPWebServiceSoapWSDDServiceName(java.lang.String name) {
        TPWebServiceSoapWSDDServiceName = name;
    }

    public webservice.TPWebServiceSoap getTPWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TPWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTPWebServiceSoap(endpoint);
    }

    public webservice.TPWebServiceSoap getTPWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.TPWebServiceSoapStub _stub = new webservice.TPWebServiceSoapStub(portAddress, this);
            _stub.setPortName(getTPWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTPWebServiceSoapEndpointAddress(java.lang.String address) {
        TPWebServiceSoap_address = address;
    }


    // Use to get a proxy class for TPWebServiceSoap12
    private java.lang.String TPWebServiceSoap12_address = "http://115.112.131.100/techportalwebservice/TPWebservice.asmx";

    public java.lang.String getTPWebServiceSoap12Address() {
        return TPWebServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TPWebServiceSoap12WSDDServiceName = "TPWebServiceSoap12";

    public java.lang.String getTPWebServiceSoap12WSDDServiceName() {
        return TPWebServiceSoap12WSDDServiceName;
    }

    public void setTPWebServiceSoap12WSDDServiceName(java.lang.String name) {
        TPWebServiceSoap12WSDDServiceName = name;
    }

    public webservice.TPWebServiceSoap getTPWebServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TPWebServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTPWebServiceSoap12(endpoint);
    }

    public webservice.TPWebServiceSoap getTPWebServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.TPWebServiceSoap12Stub _stub = new webservice.TPWebServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getTPWebServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTPWebServiceSoap12EndpointAddress(java.lang.String address) {
        TPWebServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.TPWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.TPWebServiceSoapStub _stub = new webservice.TPWebServiceSoapStub(new java.net.URL(TPWebServiceSoap_address), this);
                _stub.setPortName(getTPWebServiceSoapWSDDServiceName());
                return _stub;
            }
            if (webservice.TPWebServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.TPWebServiceSoap12Stub _stub = new webservice.TPWebServiceSoap12Stub(new java.net.URL(TPWebServiceSoap12_address), this);
                _stub.setPortName(getTPWebServiceSoap12WSDDServiceName());
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
        if ("TPWebServiceSoap".equals(inputPortName)) {
            return getTPWebServiceSoap();
        }
        else if ("TPWebServiceSoap12".equals(inputPortName)) {
            return getTPWebServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://115.112.131.100/", "TPWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://115.112.131.100/", "TPWebServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://115.112.131.100/", "TPWebServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TPWebServiceSoap".equals(portName)) {
            setTPWebServiceSoapEndpointAddress(address);
        }
        else 
if ("TPWebServiceSoap12".equals(portName)) {
            setTPWebServiceSoap12EndpointAddress(address);
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
