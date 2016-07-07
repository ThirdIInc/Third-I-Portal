<!--Bhushan Dasare--><!--June 5, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<s:form action="MonthlyAttnProcessStatistics" name="MonthlyAttnProcessStatistics" validate="true" id="paraFrm" target="main" theme="simple">
	
<s:hidden name="ledgerCode"/>
<s:hidden name="ledgerStatus"/>
<s:hidden name="ittMonthName"/>
<s:hidden name="hBranchCode" />
<s:hidden name="hEmpType" />

<s:hidden name="statisticsName" />
<s:hidden name="statisticsCode" />
<s:hidden name="statisticsCenter" />
<s:hidden name="resignAcceptDate" />
<s:hidden name="resignSeparationDate" />


	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Monthly Attendance Process</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
						
						<jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" />
						
						
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										
										<s:if test="ledgerCode==''">
										<s:select name="month" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" />
										</s:if>
										<s:else>
										<s:property value="ittMonthName"/>
										<s:hidden name="month" />
										</s:else>
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td>
										<s:if test="ledgerCode==''">
										<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
										onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
										</s:if>
										<s:else>
										<s:property value="year"/>
										<s:hidden name="year" />
										</s:else>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">
								<s:hidden name="divisionId" /><s:hidden name="divisionFlag" />
								<s:if test="divisionFlag">
									<tr>
										<td width="300" align="right">
											<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="divisionName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
										<s:if test="ledgerCode==''">
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the division" 
											onclick="callsF9(500,325,'MonthlyAttnProcessStatistics_f9Division.action');">
										</s:if>
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="divisionName" /></s:else>
								
								<s:hidden name="branchId" /><s:hidden name="branchFlag" />
								<s:if test="branchFlag">
									<tr>
										<td width="300" align="right">
											<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="branchName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
										<s:if test="ledgerCode==''">
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the branch" 
											onclick="callsF9(500,325,'MonthlyAttnProcessStatistics_f9Branch.action');">
										</s:if>
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="branchName" /></s:else>
								
								<s:hidden name="departmentId" /><s:hidden name="departmentFlag" />
								<s:if test="departmentFlag">
									<tr>
										<td width="300" align="right">
											<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="departmentName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<s:if test="ledgerCode==''">
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'MonthlyAttnProcessStatistics_f9Department.action');">
										</s:if>
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="departmentName" /></s:else>
								
								<s:hidden name="employeeTypeId" /><s:hidden name="employeeTypeFlag" />
								<s:if test="employeeTypeFlag">
									<tr>
										<td width="300" align="right">
											<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="employeeTypeName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
										<s:if test="ledgerCode==''">
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the type of an employee" 
											onclick="callsF9(500,325,'MonthlyAttnProcessStatistics_f9EmployeeType.action');">
										</s:if>
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="employeeTypeName" /></s:else>
								
								<s:hidden name="payBillId" /><s:hidden name="payBillFlag" />
								<s:if test="payBillFlag">
									<tr>
										<td width="300" align="right">
											<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="payBillName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
										<s:if test="ledgerCode==''">	
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the pay bill group" 
											onclick="callsF9(500,325,'MonthlyAttnProcessStatistics_f9PayBill.action');">
										</s:if>
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="payBillName" /></s:else>
								
								<tr>
								<td colspan="3" align="center">
									<s:if test="ledgerCode==''">
									<s:submit value="Show Statistics" theme="simple" cssClass="token" onclick="return callShowStatistics();" action="MonthlyAttnProcessStatistics_showStatistics"></s:submit>
									</s:if>
								</td>
								</tr>
								
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
	<s:if test="statisticsList">
			<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
					<td colspan="7">
					<strong>Attendance Process Statistics</strong>
					</td>
				</tr>
				
				<tr>
					<td class="formth" >
					<strong><label id="branch.name" name="branch.name" ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></strong>
					</td>	
					
					<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>
					<td class="formth" >
					<strong><label id="total.no.employee" name="total.no.employee" ondblclick="callShowDiv(this);"><%=label.get("total.no.employee")%></label></strong>
					</td>
					</s:else>
								
					<td class="formth" >
					<strong><label id="attn.uploaded.emp" name="attn.uploaded.emp" ondblclick="callShowDiv(this);"><%=label.get("attn.uploaded.emp")%></label></strong>
					</td>
					<td class="formth" >
					<strong><label id="attn.processed.emp" name="attn.processed.emp" ondblclick="callShowDiv(this);"><%=label.get("attn.processed.emp")%></label></strong>
					</td>
					<td class="formth" >
					<strong><label id="attn.unprocessed.emp" name="attn.unprocessed.emp" ondblclick="callShowDiv(this);"><%=label.get("attn.unprocessed.emp")%></label></strong>
					</td>
				<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>	
					<td class="formth" >
					<strong><label id="not.included.emp" name="not.included.emp" ondblclick="callShowDiv(this);"><%=label.get("not.included.emp")%></label></strong>
					</td>	
				</s:else>
				<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>	
					<td class="formth" width="10%">
					<strong><label id="resign.emp" name="resign.emp" ondblclick="callShowDiv(this);"><%=label.get("resign.emp")%></label></strong>
					</td>					
				</s:else>
				
				<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>
				<td class="formth" >
					<strong><label id="onhold.emp" name="onhold.emp" ondblclick="callShowDiv(this);"><%=label.get("onhold.emp")%></label></strong>
					</td>
				</s:else>
				</tr>
					
				<s:iterator value="statisticsList">
				<tr>
					<td class="sortableTD" width="25%"><s:property value="ittBranchNameS"/>
					<s:hidden value="ittBranchCodeS"/>					
					</td>
					
				<s:if test="bean.ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>	
					<td class="sortableTD" align="right"><s:hidden name="totalFlag" /><s:property value="ittTotalEmployee"/>	
					|
					<s:if test="totalFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','T');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>					
					&nbsp;		
					</td>
				</s:else>
					
					<td class="sortableTD" align="right"><s:hidden name="ittUploadedEmployee"  /><s:property value="ittUploadedEmployee"/> 
					|<s:hidden name="uploadFlag" />
					<s:if test="uploadFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','U');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					&nbsp;
					</td>
					<td class="sortableTD" align="right"><s:property value="ittProcessedEmployee"/>
					|<s:hidden name="processFlag" />
					<s:if test="processFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','P');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					 &nbsp;
					</td>
					<td class="sortableTD" align="right"><s:property value="ittUnProcessedEmployee"/>
					|<s:hidden name="notProcessFlag" />
					<s:if test="notProcessFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','NP');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					&nbsp; 
					</td>
				<s:if test="bean.ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>	
					<td class="sortableTD" align="right"><s:property value="ittNotIncludedEmployee"/> 
					|<s:hidden name="notIncludeFlag" />
					<s:if test="notIncludeFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','NI');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					&nbsp; 
					</td>
					</s:else>
					
				<s:if test="bean.ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>	
					<td class="sortableTD" align="right"><s:property value="ittResignEmployee"/>
					|<s:hidden name="resignFlag" />
					<s:if test="resignFlag=='true'">
					<a href="#" onclick="viewResingEmpStatistics('<s:property value="ittBranchCodeS"/>','NP');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					&nbsp; 
					</td>
				</s:else>	
				
			<s:if test="bean.ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>		
					<td class="sortableTD" align="right"><s:hidden name="ittOnholdEmployee"  /><s:property value="ittOnholdEmployee"/> 
					|<s:hidden name="onHoldFlag" />
					<s:if test="onHoldFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('<s:property value="ittBranchCodeS"/>','O');"><font color="blue"><u>View</u></font></a>					
					</s:if>
					<s:else>View</s:else>
					&nbsp;
					</td>
				</s:else>	
					
				</tr>
				</s:iterator>	
				
				
				<tr>
					<td colspan="6" class="sortableTD" >&nbsp;
					</td>
				</tr>	
				
				<tr>
					<td  class="sortableTD" >
					<strong>Total:</strong>
					</td>	
					<!-- TOTAL NO EMPLOYEE -->
			<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>		
					<td  class="sortableTD"  align="right">
					<strong><s:hidden name="ittTotalNoEmployee" /><s:property value="ittTotalNoEmployee"/>
					| 				
					<a href="#" onclick="viewEmpStatistics('HH','T');"><font color="blue"><u>View</u></font></a>
					</strong>
					</td>
				</s:else>	
					
					<!-- TOTAL UPLOADED EMPLOYEE -->		
					<td  class="sortableTD"  align="right">
					<strong><s:hidden name="totaluploadFlag" /><s:property value="totalUploadedEmployee"/>| 
					<s:if test="totaluploadFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('HH','U');"><font color="blue"><u>View</u></font></a>
					</s:if><s:else>View</s:else>
					</strong> 
					<!-- TOTAL PROCESSED EMPLOYEE -->	
					</td>
					<td class="sortableTD"  align="right">
					<strong><s:hidden name="totalprocessFlag" /><s:property value="totalProcessedEmployee"/> | 
					<s:if test="totalprocessFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('HH','P');"><font color="blue"><u>View</u></font></a>
					</s:if><s:else>View</s:else>
					</strong> 
					</td>
					<!-- TOTAL UN PROCESSED EMPLOYEE -->
					<td class="sortableTD"   align="right">
					<strong><s:property value="totalUnProcessedEmployee"/>
					<s:hidden name="totalnotProcessFlag"></s:hidden><s:hidden name="totalUnProcessedEmployee" />
					| 
					<s:if test="totalnotProcessFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('HH','NP');"><font color="blue"><u>View</u></font></a> 
					<s:if test="%{ledgerStatus == 'SAL_FINAL'||ledgerStatus == 'ATTN_READY'||ledgerStatus == 'SAL_START'||ledgerStatus == ''}">
								
					</s:if>
					<s:else>
						<s:submit action="MonthlyAttnProcessStatistics_attendanceProcess" value="Process" cssClass="token" 
							title="Process the attendance" onclick="return attendanceProcess()" />
					</s:else>	
					</s:if><s:else>View</s:else></strong>
					<!-- TOTAL NOT INCLUDED EMPLOYEE -->
					
					</td>
					<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>
					
					<td class="sortableTD"  align="right">
					<strong><s:hidden name="totalnotIncludeFlag" /><s:hidden name="totalNotIncludeEmployee" /><s:property value="totalNotIncludeEmployee"/>| 
					<s:if test="totalnotIncludeFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('HH','NI');"><font color="blue"><u>View</u></font></a> 
					</s:if><s:else>View</s:else>
					</strong>
					</td>
					</s:else>	
					<!-- TOTAL RESIGNED EMPLOYEE -->
					
					<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>
					<td class="sortableTD"  align="right">
					<strong><s:hidden name="totalresignFlag" /><s:property value="totalResignEmployee"/> 
					| 
					<s:if test="totalresignFlag=='true'">
					<a href="#" onclick="viewResingEmpStatistics('HH','R');"><font color="blue"><u>View</u></font></a> 
					</s:if>
					<s:else>View</s:else>
					</strong>					
					</td>
					</s:else>
					
					<!-- TOTAL ONHOLD EMPLOYEE -->
					
					<s:if test="ledgerStatus=='SAL_FINAL'">
					</s:if>
					<s:else>
					<td class="sortableTD"  align="right">
					<strong><s:hidden name="totalOnHoldFlag" /><s:property value="totalOnHoldEmployee"/> 
					| 
					<s:if test="totalOnHoldFlag=='true'">
					<a href="#" onclick="viewEmpStatistics('HH','O');"><font color="blue"><u>View</u></font></a> 
					</s:if>
					<s:else>View</s:else>
					</strong>					
					</td>
					</s:else>
					
									
				</tr>
				
				
									
				</table>
			</td>
		</tr>	
	</s:if>	
		
		
		
		
<s:if test="%{ledgerStatus == 'SAL_FINAL'||ledgerStatus == 'ATTN_READY'||ledgerStatus == 'SAL_START'||ledgerStatus == ''}">
								
								</s:if>
								<s:else>		
		
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
					<s:a href="#" ><img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
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
							width="18" title="Select an employee" onclick="f9clearOnHoldEmployee();"></s:a></td>	
					<td width="25%">			
					
					<input type="button" class="token" theme="simple" value="Clear On Hold" title="Clear On Hold" 
							onclick="clearOnHoldEmployee();" /> </td>
					</tr>					
				</table>
			</td>
		</tr>
		
	</s:else>	
		
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<jsp:include	page="/pages/ApplicationStudio/navigationPanel.jsp" />
							<!--<s:submit action="MonthlyAttnProcessStatistics_attendanceProcess" value="Process" cssClass="token" 
							title="Process the attendance" onclick="return attendanceProcess()" />
							<input type="button" id="btnLock" value=" Lock" class="lock"  title="Lock the attendance" 
									onclick="lockFun();" />
									<input type="button" id="btnUnlock" value=" Unlock" class="unlock" title="Unlock the attendance" 
									onclick="unlockFun();" />
									<input type="button" name="report" value="Report" class="token" onclick="return reportFun();" />
							<s:submit action="MonthlyAttnProcessStatistics_reset" value="Reset" cssClass="reset" title="Clear the fields" />
						
						-->
						
						
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		
		
	</table>
</s:form>

<script type="text/javascript">
	processing('N');


		function callShowStatistics(){
			if(document.getElementById('paraFrm_month').selectedIndex == 0) {
				alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
				document.getElementById('paraFrm_month').focus();
				return false;
			}
			if(document.getElementById('paraFrm_year').value == "") {
				alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
				document.getElementById('paraFrm_year').focus();
				return false;
			}
			var checkYearData = document.getElementById('paraFrm_year').value;
			if(checkYearData.length < 4) {
				alert("year should have atleast 4 digits");
				//document.getElementById('paraFrm_divisionId').focus();
				return false;
			}	
			if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
			document.getElementById('paraFrm_divisionId').value == "") {
				alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
			document.getElementById('paraFrm_branchId').value == "") {
				alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
			document.getElementById('paraFrm_departmentId').value == "") {
				alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
			document.getElementById('paraFrm_employeeTypeId').value == "") {
				alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
			document.getElementById('paraFrm_payBillId').value == "") {
				alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
				return false;
			}
			}
	function viewEmpStatistics(branchCode,type) {		
		document.getElementById('paraFrm_hBranchCode').value=branchCode;
		document.getElementById('paraFrm_hEmpType').value=type;
		callsF9(500,325,'MonthlyAttnProcessStatistics_f9ViewEmpStatistics.action');		
	}
	function viewResingEmpStatistics(branchCode,type) {
		
		document.getElementById('paraFrm_hBranchCode').value=branchCode;
		document.getElementById('paraFrm_hEmpType').value=type;
		callsF9(500,325,'MonthlyAttnProcessStatistics_f9ResignEmpStatistics.action');		
	}
	
		function lockFun(){
		
			if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			//document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}

		if(document.getElementById('paraFrm_totalUnProcessedEmployee').value=='0'){
			
		}else{
			alert('Some employees are pending for Attendance Process \n First process then lock attendance');
			return false;
			}

		
			var isLock = confirm('Do you really want to lock the attendance?');
			if(isLock){
			document.getElementById("paraFrm").action = 'MonthlyAttnProcessStatistics_lockAttendance.action';
    		document.getElementById("paraFrm").submit();
    		document.getElementById('paraFrm').target = 'main';	
			}			
		}
		function unlockFun(){
		
			if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			//document.getElementById('paraFrm_divisionId').focus();
			return false;
		}	
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}

		


		
		var isUnlock = confirm('Do you really want to Unlock the attendance?');
		if(isUnlock){
			doAuthorisation('1', 'Attendance', 'U');
		}			
		}

		function doUnlock() {
			document.getElementById("paraFrm").action = 'MonthlyAttnProcessStatistics_unLockAttendance.action';
	    	document.getElementById("paraFrm").submit();
	    	document.getElementById('paraFrm').target = 'main';	
		}
		
	function processing(show) {
		if(show == 'Y') {
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
		} else {
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
		}
	}
	
	function reportFun() {
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			//document.getElementById('paraFrm_divisionId').focus();
			return false;
		}	
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}
		callReport('MonthlyAttnProcessStatistics_report.action');
	
	}
	
	
	function attendanceProcess() {
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			//document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}

		if(document.getElementById('paraFrm_totalUnProcessedEmployee').value=='0'){
		alert('None of the employee is pending for process');
		return false;
		}
		var valMess = confirm('Do you really want to process the attendance?');
		if(valMess){
		return true;
		}else{
return false;
			}
		
		processing('Y');
		
		return true;
	}


	function processFun() {

	var attnStatus=document.getElementById('paraFrm_ledgerStatus').value;
	
	
		
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			//document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}

		var valMess = confirm('Do you really want to process the attendance?');
		if(valMess){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_attendanceProcess.action';
		document.getElementById('paraFrm').submit();
		}
		else{
				return false;
			}
		return true;
	}
	function backtolistFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_input.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_reset.action';
		document.getElementById('paraFrm').submit();
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
		callsF9(500,325,'MonthlyAttnProcessStatistics_f9removeEmployee.action');
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
		callsF9(500,325,'MonthlyAttnProcessStatistics_f9addOnHoldEmployee.action');
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
		callsF9(500,325,'MonthlyAttnProcessStatistics_f9clearOnHoldEmployee.action');
	}

		function removeEmployee() {			
			if(document.getElementById('paraFrm_removeEmpCode').value == "") {
				alert("Please " + document.getElementById('remove.emp').innerHTML.toLowerCase());
				document.getElementById('paraFrm_removeEmpToken').focus();
				return false;
			}
			var con=confirm('Do you really want to remove the employee ?');
		 	if(con){
			document.getElementById('paraFrm').target = 'main';
			document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_removeEmployee.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
			}
		}

		function addOnHoldEmployee() {			
			if(document.getElementById('paraFrm_onHoldAddEmpCode').value == "") {
				alert("Please " + document.getElementById('add.onhold.emp').innerHTML.toLowerCase());
				document.getElementById('paraFrm_onHoldAddEmpToken').focus();
				return false;
			}
			var con=confirm('Do you really want to add onhold employee ?');
		 	if(con){
			document.getElementById('paraFrm').target = 'main';
			document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_addOnHoldEmployee.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
			}
		}

	function clearOnHoldEmployee() {		
			if(document.getElementById('paraFrm_onHoldEmpCode').value == "") {
				alert("Please " + document.getElementById('clear.onhold.emp').innerHTML.toLowerCase());
				document.getElementById('paraFrm_onHoldEmpToken').focus();
				return false;
			}
			var con=confirm('Do you really want to clear onhold employee?');
		 	if(con){
			document.getElementById('paraFrm').target = 'main';
			document.getElementById('paraFrm').action = 'MonthlyAttnProcessStatistics_clearOnHoldEmployee.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
			}
		}
	
</script>