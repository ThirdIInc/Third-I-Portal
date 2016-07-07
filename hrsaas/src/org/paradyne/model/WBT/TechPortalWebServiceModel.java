package org.paradyne.model.WBT;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Service;
import org.paradyne.lib.ModelBase;

import webservice.LeaveIntegrationSoapStub;
import webservice.TPWebServiceSoapStub;
/**
 * 
 * @author Lakkichand_Golegaonkar
 * @Date 04-12-2012
 *
 */

public class TechPortalWebServiceModel extends ModelBase {

	public String  addPrograms(String xmlWBTProgram) {
		Service service =new Service();
		String returnXML="";
			try {
				TPWebServiceSoapStub stub = new TPWebServiceSoapStub(
						new URL(
								"http://115.112.131.100/techportalwebservice/TPWebservice.asmx?WSDL"),
						service);
				stub._createCall();
				returnXML = stub.addPrograms(xmlWBTProgram);
				return returnXML;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnXML;
			
			
	}
	public String  checkTechnicianSession(String xmlCheckSession) {
		Service service =new Service();
		String returnXML="";
			try {
				TPWebServiceSoapStub stub = new TPWebServiceSoapStub(
						new URL(
								"http://115.112.131.100/techportalwebservice/TPWebservice.asmx?WSDL"),
						service);
				stub._createCall();
				returnXML = stub.checkTechnicianSession(xmlCheckSession);
				return returnXML;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnXML;
			
			
	}
	public String  updateEmp_USERGUID_ToTechPortal(String xmlUpdateEmpGUID) {
		Service service =new Service();
		String returnXML="";
			try {
				TPWebServiceSoapStub stub = new TPWebServiceSoapStub(
						new URL(
								"http://115.112.131.100/techportalwebservice/TPWebservice.asmx?WSDL"),
						service);
				stub._createCall();
				returnXML = stub.updateEmp_USERGUID_ToTechPortal(xmlUpdateEmpGUID);
				return returnXML;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnXML;
			
			
	}
	public String  updateWBTResultToTechPortal(String xmlWBTResult) {
		Service service =new Service();
		String returnXML="";
			try {
				TPWebServiceSoapStub stub = new TPWebServiceSoapStub(
						new URL(
								"http://115.112.131.100/techportalwebservice/TPWebservice.asmx?WSDL"),
						service);
				stub._createCall();
				returnXML = stub.updateWBTResultToTechPortal(xmlWBTResult);
				return returnXML;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return returnXML;
			
			
	}
}
