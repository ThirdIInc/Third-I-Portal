package webservice;

public class TPWebServiceSoapProxy implements webservice.TPWebServiceSoap {
  private String _endpoint = null;
  private webservice.TPWebServiceSoap tPWebServiceSoap = null;
  
  public TPWebServiceSoapProxy() {
    _initTPWebServiceSoapProxy();
  }
  
  public TPWebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initTPWebServiceSoapProxy();
  }
  
  private void _initTPWebServiceSoapProxy() {
    try {
      tPWebServiceSoap = (new webservice.TPWebServiceLocator()).getTPWebServiceSoap();
      if (tPWebServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tPWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tPWebServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tPWebServiceSoap != null)
      ((javax.xml.rpc.Stub)tPWebServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.TPWebServiceSoap getTPWebServiceSoap() {
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap;
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap.helloWorld();
  }
  
  public java.lang.String checkTechnicianSession(java.lang.String xmlCheckSession) throws java.rmi.RemoteException{
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap.checkTechnicianSession(xmlCheckSession);
  }
  
  public java.lang.String updateWBTResultToTechPortal(java.lang.String xmlWBTResult) throws java.rmi.RemoteException{
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap.updateWBTResultToTechPortal(xmlWBTResult);
  }
  
  public java.lang.String updateEmp_USERGUID_ToTechPortal(java.lang.String xmlUpdateEmpGUID) throws java.rmi.RemoteException{
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap.updateEmp_USERGUID_ToTechPortal(xmlUpdateEmpGUID);
  }
  
  public java.lang.String addPrograms(java.lang.String xmlWBTProgram) throws java.rmi.RemoteException{
    if (tPWebServiceSoap == null)
      _initTPWebServiceSoapProxy();
    return tPWebServiceSoap.addPrograms(xmlWBTProgram);
  }
  
  
}