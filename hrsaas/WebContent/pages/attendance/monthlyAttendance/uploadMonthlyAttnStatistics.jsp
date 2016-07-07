<!--Bhushan Dasare--><!--June 6, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="UploadMonthlyAttnStatistics" name="UploadMonthlyAttnStatistics" id="paraFrm" validate="true" target="main" theme="simple">	
    <s:hidden name="recoveryFlag" /> 
	 <s:hidden name="resignAcceptDate" /> 
	  <s:hidden name="resignSeparationDate" /> 
	
	<s:hidden name="divisionCode" /> 
	<s:hidden name="branchCode" /> 
	<s:hidden name="fromdate" /> 
	<s:hidden name="todate" /> 
	<s:hidden name="branchName" /> 
	
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Monthly Attendance Upload  </strong>
						<s:hidden name="hBranchCode" /><s:hidden name="statisticsCenter" />
						<s:hidden name="hEmpType" /><s:hidden name="statisticsName" /><s:hidden name="statisticsCode" />
						</td>
						<td width="4%" valign="middle" class="txt" align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> <input type="button" name="report" value="View Uploaded Data" class="token" onclick="return showReport();" />
							<s:submit value=" Reset" cssClass="reset" action="UploadMonthlyAttnStatistics_reset" title="Clear the fields" /> 
							
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="15%">
							<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:select name="month" headerKey="0" headerValue="--Select--" title="Select the month"
							list="#{'01':'January','02':'Febuary','03':'March','04':'April','05':'May','06':'June','07':'July',
							'08':'August','09':'September','10':'October','11':'November','12':'December'}" /> 
						 </td>
						<td width="10%"></td>
						<td width="20%">
							<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
							onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<s:hidden name="divisionId" /><s:hidden name="divisionFlag" /> 
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
						</td>
						<td width="25%" >
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />								
						</td>
						<td width="10%"><s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'UploadMonthlyAttnStatistics_f9Division.action');">
						</s:a>
						</td>
						<td width="15%">
							<s:hidden name="branchId" /><s:hidden name="branchFlag" />
							<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="branchNameattn" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%"><s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'UploadMonthlyAttnStatistics_f9Branch.action');">
						</s:a>
						</td>
					</tr> 
					<tr><s:hidden name="departmentId" /> 
					
						<td width="15%">
							<s:hidden name="payBillId" /><s:hidden name="payBillFlag" />
							<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="payBillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
					 	</td>
						<td width="10%"><s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the pay bill group" onclick="callsF9(500,325,'UploadMonthlyAttnStatistics_f9PayBill.action');">
						</s:a>
						</td>
						
						
						<td width="15%">
							<s:hidden name="employeeTypeId" /><s:hidden name="employeeTypeFlag" />
							<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :
						</td>
						<td width="25%" >
							<s:textfield name="employeeTypeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
						<s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the type of an employee" onclick="callsF9(500,325,'UploadMonthlyAttnStatistics_f9EmployeeType.action');">
						</s:a>
						</td>
					</tr>   
					<tr>
						
						<td width="15%">
							<s:hidden name="empSerachId" /><s:hidden name="empToken" />
							<label id="emp.Name" name="emp.Name" ondblclick="callShowDiv(this);"><%=label.get("emp.Name")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="employeeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
					 	</td>
						<td width="10%"><s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="callF9Employee();"></s:a>
						</td>
					</tr>   
					
					<tr>
						<td colspan="5" align="center">
										<s:submit value="Show Statistics" theme="simple" cssClass="token" onclick="return showStatistics()" action="UploadMonthlyAttnStatistics_showStatistics"></s:submit> 
						</td>
					</tr>
					
					
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
					<td colspan="2">
					<strong><label id="downLoad.template1" name="downLoad.template" ondblclick="callShowDiv(this);"><%=label.get("downLoad.template")%></label></strong>
					</td>
					</tr>
					<tr>
						
						<td align="center">
							<s:submit value="Download Template" cssClass="token" action="UploadMonthlyAttnStatistics_downLoadFile" 
							title="Download an template" onclick="return callDownLoad();" />
						</td>
					</tr>
					<tr>
					<td><strong>Note:</strong> <label id="download.lable" name="download.lable" ondblclick="callShowDiv(this);"><%=label.get("download.lable")%></label></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg" border="0">  
				
				<tr>
					<td colspan="2">
					<strong><label id="upload.template" name="upload.template" ondblclick="callShowDiv(this);"><%=label.get("upload.template")%></label></strong>
					</td>
					</tr>
					<tr>
						<td width="100%" >
							<label id="upload.Name" name="upload.Name" ondblclick="callShowDiv(this);"><%=label.get("upload.Name")%></label> :<font color="red">*</font>
							
							<s:textfield name="uploadFileName" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />&nbsp;&nbsp;&nbsp;
							
							<input type="button" class="token" theme="simple" value="Select XLS File" title="Select XLS file" 
							onclick="uploadFile('uploadFileName');" />
							
							<s:submit cssClass="token" theme="simple" value="Upload Template" action="UploadMonthlyAttnStatistics_uploadMonSheet" 
							title="Upload XLS file" onclick="return calAttendance();" /> 
						</td>
					</tr>
					<tr>
					<td><strong>Note:</strong> <label id="upload.lable" name="upload.lable" ondblclick="callShowDiv(this);"><%=label.get("upload.lable")%></label></td>
					</tr>
				</table>
				<%	String wrongEmp = (String) request.getAttribute("wrongEmptoken"); 
					if(wrongEmp != null && !wrongEmp.equals("")) { 
				%>		<table width="100%" class="formbg"><tr><td width="100%"><font color="red"><%=wrongEmp%></font></td></tr></table>
				<%	}	%>
	    	</td>
		</tr>
		
		
		<s:if test="statisticsList">
			<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
					<td colspan="5">
					<strong>Attendance Statistics</strong>
					</td>
				</tr>
				
				<tr>
					<td class="formth" >
					<strong><label id="branch.name" name="branch.name" ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></strong>
					</td>	
					<td class="formth" >
					<strong><label id="total.emp" name="total.emp" ondblclick="callShowDiv(this);"><%=label.get("total.emp")%></label></strong>
					</td>			
					<td class="formth" >
					<strong><label id="uploaded.emp" name="uploaded.emp" ondblclick="callShowDiv(this);"><%=label.get("uploaded.emp")%></label></strong>
					</td>
					<td class="formth" >
					<strong><label id="leave.pending.emp" name="leave.pending.emp" ondblclick="callShowDiv(this);"><%=label.get("leave.pending.emp")%></label></strong>
					</td>
					<td class="formth" >
					<strong><label id="resigned.emp" name="resigned.emp" ondblclick="callShowDiv(this);"><%=label.get("resigned.emp")%></label></strong>
					</td>
					<td class="formth" >
					<strong><label id="onhold.emp" name="onhold.emp" ondblclick="callShowDiv(this);"><%=label.get("onhold.emp")%></label></strong>
					</td>					
				
				</tr>
					
				<s:iterator value="statisticsList">
				<tr>
					<td class="sortableTD" ><s:property value="ittBranchName"/>
					<s:hidden value="ittBranchCode"/><s:hidden value="ittBranchName"/>
					</td>
					<td class="sortableTD" align="right"><s:property value="ittTotalEmployee"/> |
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCode"/>','T');"><font color="blue"><u>View</u></font></a>					
					</td>
					<td class="sortableTD" align="right"><s:hidden name="uploadeFlag" /><s:property value="ittUploadedEmployee"/> | 
					<s:if test="uploadeFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCode"/>','U');"><font color="blue"><u>View</u></font></a>
					</s:if>
					<s:else>View</s:else>
					</td>
					<td class="sortableTD" align="right">
					<s:hidden name="leaveFlag"/><s:hidden name="ittLeaveApplication"/>
					<s:property value="ittLeaveApplication"/> 
					<s:if test="leaveFlag=='true'">
<a href="#" onclick="viewLeaveAdminBranch('<s:property value="ittBranchCode"/>','<s:property value="ittBranchName"/>');"><font color="blue"><u>View</u></font></a>
					</s:if>
					<s:else>View</s:else>
					
					
					
					</td>
					<td class="sortableTD" align="right"><s:hidden name="resigneFlag"/><s:property value="ittResignedEmployee"/> |
					<s:if test="resigneFlag=='true'">
<a href="#" onclick="viewResingEmpStatistics('<s:property value="ittBranchCode"/>','R');"><font color="blue"><u>View</u></font></a>
					</s:if>
					<s:else>View</s:else>
					</td>
					<td class="sortableTD" align="right"><s:property value="ittOnHoldEmployee"/><s:hidden name="onholdFlag"/>
					 | 
					<s:if test="onholdFlag=='true'">					
<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCode"/>','H');"><font color="blue"><u>View</u></font></a>
					</s:if>
					<s:else>View</s:else>
					</td>
				</tr>
				</s:iterator>	
				
				
				<tr>
					<td colspan="5" class="sortableTD" >&nbsp;
					</td>
				</tr>	
				
				<tr>
					<td  class="sortableTD" >
					<strong>Total:</strong>
					</td>	
					<td  class="sortableTD"  align="right">
					<strong><s:property value="ittTotalNoEmployee"/>|
					
<a href="#" onclick="viewEmpStatistics('HH','T');"><font color="blue"><u>View</u></font></a>
					
					</strong>
					</td>
								
					<td  class="sortableTD"  align="right"><s:hidden name="totaluploadeFlag"  />
					<strong><s:property value="ittTotalUploadedEmployee"/> |
					<s:if test="totaluploadeFlag=='false'">
					View
					</s:if> <s:else>
<a href="#" onclick="viewEmpStatistics('HH','U');"><font color="blue"><u>View</u></font></a></s:else></strong>
					</td>
					<td class="sortableTD"  align="right">
					<strong><s:property value="ittTotalLeaveApplication"/> 
					<s:if test="totalleaveFlag=='false'">
					View
					</s:if> <s:else>
					<a href="#" onclick="viewLeaveAdmin();"><font color="blue"><u>View </u></font></a>
					</s:else>
					</strong>
					</td>
					<td class="sortableTD"   align="right">
					<s:hidden name="totalresigneFlag"  />
					<s:hidden name="ittTotalResignedEmployee"  />
					<strong><s:property value="ittTotalResignedEmployee"/> |
					<s:if test="totalresigneFlag=='false'">
					View
					</s:if> 
					<s:else><a href="#" onclick="viewResingEmpStatistics('HH','R');"><font color="blue"><u>View</u></font></a>
					</s:else>
</strong>
					</td>
					<td class="sortableTD"  align="right"><s:hidden name="ittTotalOnHoldEmployee"  />
					<strong><s:property value="ittTotalOnHoldEmployee"/> |
					<s:if test="ittTotalOnHoldEmployee=='0.0'">
					View
					</s:if> <s:else>
<a href="#" onclick="viewEmpStatistics('HH','H');"><font color="blue"><u>View</u></font></a></s:else></strong>
					</td>					
				</tr>
				
				
									
				</table>
			</td>
		</tr>	
	</s:if>	
		
			<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
					<td colspan="4">
					<strong>Remove Employees from attendance process:</strong>
					</td>
					</tr>
					<tr>
					<td colspan="1" width="28%"><label id="remove.emp" name="remove.emp" ondblclick="callShowDiv(this);"><%=label.get("remove.emp")%></label> :<font color="red">*</font> </td>	
					<td colspan="2"> 
					<s:hidden name="removeEmpCode" />
					<s:hidden name="onholdBranchName" />
					
					<s:textfield name="removeEmpToken" readonly="true" size="15" cssStyle="background-color: #F2F2F2;"/>
					<s:textfield name="removeEmpName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
					<s:a href="#" >
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="f9removeEmployee();"></s:a></td>	
					<td width="25%"><input type="button" class="token" theme="simple" value="Remove Employee" title="Remove Employee" 
							onclick="removeEmployee();" /> </td>
					</tr>
				</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
					<td colspan="4">
					<strong>Manage On Hold</strong>
					</td>
					</tr>
					<tr>
					<td colspan="1" width="28%"><label id="add.onhold.emp" name="add.onhold.emp" ondblclick="callShowDiv(this);"><%=label.get("add.onhold.emp")%></label> :<font color="red">*</font> </td>	
					<td colspan="2"> 
					<s:hidden name="onHoldAddEmpCode" /><s:hidden name="onholdBranchName" />
					<s:textfield name="onHoldAddEmpToken" readonly="true" size="15" cssStyle="background-color: #F2F2F2;"/>
					<s:textfield name="onHoldAddEmpName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
					<s:a href="#" ><img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="f9addOnHoldEmployee();"></s:a></td>	
					<td width="25%">
					<input type="button" class="token" theme="simple" value="Add On Hold" title="Add On Hold" 
							onclick="addOnHoldEmployee();" />
					 </td>
					</tr>
					
					<tr>
					<td colspan="1" width="28%"><label id="clear.onhold.emp" name="clear.onhold.emp" ondblclick="callShowDiv(this);"><%=label.get("clear.onhold.emp")%></label> :<font color="red">*</font> </td>	
					<td colspan="2"> 
					<s:hidden name="onHoldEmpCode" />
					<s:textfield name="onHoldEmpToken" readonly="true" size="15" cssStyle="background-color: #F2F2F2;"/>
					<s:textfield name="onHoldEmpName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
						<s:a href="#" ><img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="f9clearOnHoldEmployee();">
							</s:a>
							</td>	
					<td width="25%">			
					
					<input type="button" class="token" theme="simple" value="Clear On Hold" title="Clear On Hold" 
							onclick="clearOnHoldEmployee();" /> </td>
					</tr>
					
					
				</table>
			</td>
		</tr>
		
		
		
		
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> <input type="button" name="report" value="View Uploaded Data" class="token" onclick="return showReport();" />
							<s:submit value=" Reset" cssClass="reset" action="UploadMonthlyAttnStatistics_reset" title="Clear the fields" /> 
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

		function showStatistics(){
			if(document.getElementById('paraFrm_month').value == "0") {
				alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
				document.getElementById('paraFrm_month').focus(); 
				return false;
			}
			if(document.getElementById('paraFrm_year').value == "") {
				alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
				document.getElementById('paraFrm_year').focus();
				return false;
			}
			if(document.getElementById('paraFrm_divisionId').value == "") {
				alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
				//document.getElementById('paraFrm_divisionId').focus();
				return false;
			}
			var checkYearData = document.getElementById('paraFrm_year').value;
			if(checkYearData.length < 4) {
				alert("year should have atleast 4 digits");
				document.getElementById('paraFrm_divisionId').focus();
				return false;
			}
			
			}


			function viewLeaveAdmin(){
				document.getElementById('paraFrm_divisionCode').value=document.getElementById('paraFrm_divisionId').value;
				document.getElementById('paraFrm_branchCode').value=document.getElementById('paraFrm_branchId').value;	
				document.getElementById('paraFrm_branchName').value='';
				document.getElementById('paraFrm').target = '_blank';
				document.getElementById('paraFrm').action = 'LeaveAdminApproval_search.action';
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = 'main';
				}

			function viewLeaveAdminBranch(id,brnname){
				document.getElementById('paraFrm_divisionCode').value=document.getElementById('paraFrm_divisionId').value;
				document.getElementById('paraFrm_branchCode').value=id;	
				document.getElementById('paraFrm_branchName').value=brnname;	
				document.getElementById('paraFrm').target = '_blank';
				document.getElementById('paraFrm').action = 'LeaveAdminApproval_search.action';
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = 'main';
				}
			
		function viewEmpStatistics(branchCode,type) {
						
		document.getElementById('paraFrm_hBranchCode').value=branchCode;
		document.getElementById('paraFrm_hEmpType').value=type;
		callsF9(500,325,'UploadMonthlyAttnStatistics_f9ViewEmpStatistics.action');		
	}

		function viewResingEmpStatistics(branchCode,type) {
			
			document.getElementById('paraFrm_hBranchCode').value=branchCode;
			document.getElementById('paraFrm_hEmpType').value=type;
			callsF9(500,325,'UploadMonthlyAttnStatistics_f9ResignEmpStatistics.action');		
		}

		function f9removeEmployee() {
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		callsF9(500,325,'UploadMonthlyAttnStatistics_f9removeEmployee.action');
	}
		
		
		function f9addOnHoldEmployee() {
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		callsF9(500,325,'UploadMonthlyAttnStatistics_f9addOnHoldEmployee.action');
	}
		
		
		
		function f9clearOnHoldEmployee() {
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		callsF9(500,325,'UploadMonthlyAttnStatistics_f9clearOnHoldEmployee.action');
	}



		function removeEmployee() {	
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_removeEmpCode').value == "") {
			alert("Please " + document.getElementById('remove.emp').innerHTML.toLowerCase());
			document.getElementById('paraFrm_removeEmpToken').focus();
			return false;
		}
		var con=confirm('Do you really want to remove the employee ?');
	 	if(con){
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').action = 'UploadMonthlyAttnStatistics_removeEmployee.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		}
	}

function addOnHoldEmployee() {	
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_onHoldAddEmpCode').value == "") {
			alert("Please " + document.getElementById('add.onhold.emp').innerHTML.toLowerCase());
			document.getElementById('paraFrm_onHoldAddEmpToken').focus();
			return false;
		}
		var con=confirm('Do you really want to add onhold employee ?');
	 	if(con){
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').action = 'UploadMonthlyAttnStatistics_addOnHold.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		}
	}

function clearOnHoldEmployee() {	
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_onHoldEmpCode').value == "") {
			alert("Please " + document.getElementById('clear.onhold.emp').innerHTML.toLowerCase());
			document.getElementById('paraFrm_onHoldEmpToken').focus();
			return false;
		}
		var con=confirm('Do you really want to clear onhold employee?');
	 	if(con){
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').action = 'UploadMonthlyAttnStatistics_clearOnHold.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		}
	}


	function showReport() {
	if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
	callReport('UploadMonthlyAttnStatistics_report.action');
	
	}


	function callF9Employee() {
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		callsF9(500,325,'UploadMonthlyAttnStatistics_f9SeachEmployee.action');
	}
 
	function callDownLoad() {
    	if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		return true;
	}

	function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/attendance";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=50, left=100');
	}
	
 	function calAttendance() {  
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		} 
		//alert(document.getElementById('paraFrm_uploadFileName').value);
		if(document.getElementById('paraFrm_uploadFileName').value == "") {
				alert("Please select the " + document.getElementById('upload').innerHTML.toLowerCase());
				return false;
			}  
		var checkYearData = document.getElementById('paraFrm_year').value;
			if(checkYearData.length < 4) {
				alert("year should have atleast 4 digits");
				document.getElementById('paraFrm_divisionId').focus();
				return false;
			}
		return true;
	}
</script>