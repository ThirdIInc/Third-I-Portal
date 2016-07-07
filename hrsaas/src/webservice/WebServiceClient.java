/**
 * 
 */
package webservice;

import org.apache.axis.client.Service;

import webservice.LeaveIntegrationSoapStub;



/**
 * @author Administrator
 *
 */
public class WebServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str;
		try {
			String getXML = "<CreateLeave><Records><Flag>I</Flag><EmployeeCode>C0196</EmployeeCode><EmployeeName>Admina</EmployeeName><LeaveOrWFH>H</LeaveOrWFH><LeaveType>Test new</LeaveType><FromDate>2012-12-30</FromDate><ToDate>2012-12-30</ToDate><HalfDay>0</HalfDay><Status>Approved</Status><AppliedDate>2012-12-30</AppliedDate><ApprovedDate>2011-12-19</ApprovedDate><NumberOfDays>1</NumberOfDays></Records></CreateLeave>";
			
			String empData ="<CreateEmployee><Records><Flag>U</Flag><EmployeeCode>PPEMP0001</EmployeeCode><EmployeeName>Lakkichand Golegaonkar</EmployeeName><UserName>Premchand.Golegaonkar</UserName><Role>IT Manager</Role><Designation>Software Engineer</Designation><Dept>Corporate</Dept><EmpType>vendor</EmpType><ReportTo>61</ReportTo> <JoinDate>11-11-2011</JoinDate> <OrgUnit>Quality</OrgUnit><Grade>E2</Grade> <DOB>11-11-2022</DOB><Gender>Male</Gender ></Records></CreateEmployee>";
						// TODO Auto-generated method stub
			String endPoint="http://192.168.210.40/WhizibleSem9_SP1_test/LeaveIntegration.asmx?wsdl";
			
			Service service =new Service();
			LeaveIntegrationSoapStub stub = new LeaveIntegrationSoapStub(new java.net.URL("http://corp.crmit.com/WhizibleSEM8Demo/LeaveIntegration.asmx?wsdl"),service);
			
			stub._createCall();
			str = (String) stub.leaveUploads(getXML);
			//String emp_str =(String) stub.employeeUploads(empData);
			
			 System.out.println("return.ggg.."+str);
			// System.out.println("emp_str.."+emp_str);
			
			 //System.out.println("return..."+str);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	
	}

}
