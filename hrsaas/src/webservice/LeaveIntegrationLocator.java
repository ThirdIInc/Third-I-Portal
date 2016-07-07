/**
 * LeaveIntegrationLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class LeaveIntegrationLocator extends org.apache.axis.client.Service implements webservice.LeaveIntegration {

    public LeaveIntegrationLocator() {
    }


    public LeaveIntegrationLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LeaveIntegrationLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LeaveIntegrationSoap12
    private java.lang.String LeaveIntegrationSoap12_address = "http://192.168.210.13/whizibleSEM9_SP1/LeaveIntegration.asmx";

    public java.lang.String getLeaveIntegrationSoap12Address() {
        return LeaveIntegrationSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LeaveIntegrationSoap12WSDDServiceName = "LeaveIntegrationSoap12";

    public java.lang.String getLeaveIntegrationSoap12WSDDServiceName() {
        return LeaveIntegrationSoap12WSDDServiceName;
    }

    public void setLeaveIntegrationSoap12WSDDServiceName(java.lang.String name) {
        LeaveIntegrationSoap12WSDDServiceName = name;
    }

    public webservice.LeaveIntegrationSoap getLeaveIntegrationSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LeaveIntegrationSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLeaveIntegrationSoap12(endpoint);
    }

    public webservice.LeaveIntegrationSoap getLeaveIntegrationSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.LeaveIntegrationSoap12Stub _stub = new webservice.LeaveIntegrationSoap12Stub(portAddress, this);
            _stub.setPortName(getLeaveIntegrationSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLeaveIntegrationSoap12EndpointAddress(java.lang.String address) {
        LeaveIntegrationSoap12_address = address;
    }


    // Use to get a proxy class for LeaveIntegrationSoap
    private java.lang.String LeaveIntegrationSoap_address = "http://192.168.210.13/whizibleSEM9_SP1/LeaveIntegration.asmx";

    public java.lang.String getLeaveIntegrationSoapAddress() {
        return LeaveIntegrationSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LeaveIntegrationSoapWSDDServiceName = "LeaveIntegrationSoap";

    public java.lang.String getLeaveIntegrationSoapWSDDServiceName() {
        return LeaveIntegrationSoapWSDDServiceName;
    }

    public void setLeaveIntegrationSoapWSDDServiceName(java.lang.String name) {
        LeaveIntegrationSoapWSDDServiceName = name;
    }

    public webservice.LeaveIntegrationSoap getLeaveIntegrationSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LeaveIntegrationSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLeaveIntegrationSoap(endpoint);
    }

    public webservice.LeaveIntegrationSoap getLeaveIntegrationSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.LeaveIntegrationSoapStub _stub = new webservice.LeaveIntegrationSoapStub(portAddress, this);
            _stub.setPortName(getLeaveIntegrationSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLeaveIntegrationSoapEndpointAddress(java.lang.String address) {
        LeaveIntegrationSoap_address = address;
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
            if (webservice.LeaveIntegrationSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.LeaveIntegrationSoap12Stub _stub = new webservice.LeaveIntegrationSoap12Stub(new java.net.URL(LeaveIntegrationSoap12_address), this);
                _stub.setPortName(getLeaveIntegrationSoap12WSDDServiceName());
                return _stub;
            }
            if (webservice.LeaveIntegrationSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.LeaveIntegrationSoapStub _stub = new webservice.LeaveIntegrationSoapStub(new java.net.URL(LeaveIntegrationSoap_address), this);
                _stub.setPortName(getLeaveIntegrationSoapWSDDServiceName());
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
        if ("LeaveIntegrationSoap12".equals(inputPortName)) {
            return getLeaveIntegrationSoap12();
        }
        else if ("LeaveIntegrationSoap".equals(inputPortName)) {
            return getLeaveIntegrationSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.210.13/", "LeaveIntegration");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.210.13/", "LeaveIntegrationSoap12"));
            ports.add(new javax.xml.namespace.QName("http://192.168.210.13/", "LeaveIntegrationSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LeaveIntegrationSoap12".equals(portName)) {
            setLeaveIntegrationSoap12EndpointAddress(address);
        }
        else 
if ("LeaveIntegrationSoap".equals(portName)) {
            setLeaveIntegrationSoapEndpointAddress(address);
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
