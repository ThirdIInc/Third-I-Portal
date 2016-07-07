/**
 * TPWebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface TPWebServiceSoap extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public java.lang.String checkTechnicianSession(java.lang.String xmlCheckSession) throws java.rmi.RemoteException;
    public java.lang.String updateWBTResultToTechPortal(java.lang.String xmlWBTResult) throws java.rmi.RemoteException;
    public java.lang.String updateEmp_USERGUID_ToTechPortal(java.lang.String xmlUpdateEmpGUID) throws java.rmi.RemoteException;
    public java.lang.String addPrograms(java.lang.String xmlWBTProgram) throws java.rmi.RemoteException;
}
