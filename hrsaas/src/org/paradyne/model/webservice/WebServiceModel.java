/**
 * 
 */
package org.paradyne.model.webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.paradyne.lib.ModelBase;

import webservice.LeaveIntegrationSoapStub;


/**
 * @author Lakkichand Golegaonkar
 * @Date 29-12-2011
 * 
 */
public class WebServiceModel extends ModelBase {

	public void sendWebSeviceData( String xmlData,String event){
		String str="";
		
		
		
		String query="SELECT WEB_SERVICE_IS_ENABLE, WEB_SERVICE_EVENT, WEB_SERVICE_URL, WEB_SERVICE_SOAP_ACTION, WEB_SERVICE_METHOD, WEB_SERVICE_USERNAME, WEB_SERVICE_PASSWORD FROM HRMS_WEB_SERVICE_CONF WHERE WEB_SERVICE_EVENT='"+event+"'";
		
		Object[][]obj=getSqlModel().getSingleResult(query);
		
	
		if(obj!=null & obj.length>0){
			
			for (int i = 0; i < obj.length; i++) {
				if(String.valueOf(obj[i][0]).equals("Y")){
				
					try {
						
						String empData ="<CreateEmployee><Records><Flag>U</Flag><EmployeeCode>PPEMP0001</EmployeeCode><EmployeeName>Lakkichand Golegaonkar</EmployeeName><UserName>Premchand.Golegaonkar</UserName><Role>IT Manager</Role><Designation>Software Engineer</Designation><Dept>Corporate</Dept><EmpType>vendor</EmpType><ReportTo>61</ReportTo> <JoinDate>11-11-2011</JoinDate> <OrgUnit>Quality</OrgUnit><Grade>E2</Grade> <DOB>11-11-2022</DOB><Gender>Male</Gender ></Records></CreateEmployee>";
						String endPoint="http://corp.crmit.com/whizibleSEM8demo/LeaveIntegration.asmx?wsdl";
						Service service =new Service();
						LeaveIntegrationSoapStub stub = new LeaveIntegrationSoapStub(new java.net.URL("http://corp.crmit.com/whiziblesem/LeaveIntegration.asmx?WSDL"),service);
						stub._createCall();
						if(event.equals("Leave")){
							str = (String) stub.leaveUploads(xmlData);
						}
						
						//String emp_str =(String) stub.employeeUploads(empData);
						
						 System.out.println("return.ggg.."+str);
						// System.out.println("emp_str.."+emp_str);
						 
						/*String endpoint = "http://192.168.210.13/whizibleSEM9_SP1/LeaveIntegration.asmx?wsdl";//String.valueOf(obj[i][2]);// WebServiceURL
						System.out.println("endpoint     " +endpoint);
						Service service = new Service();
						Call call = (Call) service.createCall();
						call.setProperty(Call.SOAPACTION_USE_PROPERTY,
								new Boolean(true));
						call.setProperty(Call.SOAPACTION_URI_PROPERTY, "http://192.168.210.13/LeaveUploads");//SOAP Action
						System.out.println("SOAP     " +String
								.valueOf(obj[i][3]));
						call
								.setTargetEndpointAddress(new java.net.URL(
										endpoint));
						System.out.println("Method     " +String
								.valueOf(obj[i][4]));
						call
								.setOperationName(new QName(String
										.valueOf(obj[i][3]), String
										.valueOf(obj[i][4])));
						final String ret= (String) call
								.invoke(new Object[] { "" });
						System.out.println("Sent 'MSFT', got '" + ret + "'");*/
					} catch (Exception e) {
						e.printStackTrace();
					}
						
				}
			}
			
			
		}
	
		
			}
			
	
		
}
