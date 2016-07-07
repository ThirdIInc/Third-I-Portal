package webservice;

public class LeaveIntegrationSoapProxy implements webservice.LeaveIntegrationSoap {
  private String _endpoint = null;
  private webservice.LeaveIntegrationSoap leaveIntegrationSoap = null;
  
  public LeaveIntegrationSoapProxy() {
    _initLeaveIntegrationSoapProxy();
  }
  
  public LeaveIntegrationSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initLeaveIntegrationSoapProxy();
  }
  
  private void _initLeaveIntegrationSoapProxy() {
    try {
      leaveIntegrationSoap = (new webservice.LeaveIntegrationLocator()).getLeaveIntegrationSoap();
      if (leaveIntegrationSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)leaveIntegrationSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)leaveIntegrationSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (leaveIntegrationSoap != null)
      ((javax.xml.rpc.Stub)leaveIntegrationSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.LeaveIntegrationSoap getLeaveIntegrationSoap() {
    if (leaveIntegrationSoap == null)
      _initLeaveIntegrationSoapProxy();
    return leaveIntegrationSoap;
  }
  
  public java.lang.Object leaveUploads(java.lang.String getXML) throws java.rmi.RemoteException{
    if (leaveIntegrationSoap == null)
      _initLeaveIntegrationSoapProxy();
    return leaveIntegrationSoap.leaveUploads(getXML);
  }
  
  public java.lang.Object employeeUploads(java.lang.String getXML) throws java.rmi.RemoteException{
    if (leaveIntegrationSoap == null)
      _initLeaveIntegrationSoapProxy();
    return leaveIntegrationSoap.employeeUploads(getXML);
  }
  
  
}