<!--SHashikant DOke--><!--May 11, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="SalaryProcess" name="SalaryProcess" validate="true" id="paraFrm" target="main" theme="simple">
	<table width="100%" class="formbg"  >
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Salary Process Employee Editable View</strong></td>
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
							<s:if test="onHoldFlag=='false'">
							<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>
							<input type="button" class="token" theme="simple" onclick="return callonhold();" value=" OnHold" />
							</s:else>
						
						</s:if>
						<s:else>
						<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>
						<input type="button" class="token" theme="simple" onclick="return callRemoveOnHold();" value=" Remove OnHold" />
						</s:else>
						</s:else>
							
						<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>	
							<s:if test="uploadSalaryFlag!='NU'">
								<input type="button" class="edit" onclick="callRecalculate();"	value="   Recalculate" />
							</s:if>
							<input type="button" class="save" theme="simple" onclick="return checkSave();" value=" Save" />
						</s:else>	
							
							<s:submit action="SalaryProcess_callForEdit" value="Back" cssClass="token" 
								title="Back"  />
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr><s:hidden name="ledgerCode" />
						<s:hidden name="ledgerStatus" />
						<s:hidden name="joinDaysFlag" />
						<s:hidden name="recoveryFlag" />
						<s:hidden name="profHandiFLag" />
						<s:hidden name="incomeTaxFlag" />
						<s:hidden name="vpfFlag" />
						<s:hidden name="lwfFlag" />
						<s:hidden name="lwfDebitCode" />
						<s:hidden name="lwfCreditCode" />
						<s:hidden name="creditRound" />
						<s:hidden name="totalCreditRound" />
						<s:hidden name="totalDebitRound" />
						<s:hidden name="netPayRound" />
						<s:hidden name="recordsPerPage" />
						<s:hidden name="dataFlag" />
						<s:hidden name="recoveryDebitCode" />
						<s:hidden name="monthView"/>
						<s:hidden name="extraWorkFlag"/>
						<s:hidden name="leaveEncashFlag"/>
						<s:hidden name="allowanceFlag"/>
						<s:hidden name="otherIncomeFlag"></s:hidden>
						<s:hidden name="onHoldEmp"/>	
						<s:hidden name="onHoldFlag"/>	
							<s:hidden name="reProcessEmpCode"/>
							<s:hidden name="companyPFFlag"/>
							<s:hidden name="uploadSalaryFlag"/>	
							<s:hidden name="empRecalculateSalFlag"/>	
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										<s:if test="showFlag"><s:property value="monthView"/><s:hidden name="month"/></s:if><s:else><s:select name="month" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></s:else>
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td colspan="1">
									<s:if test="showFlag"><s:property value="year"/><s:hidden name="year"/></s:if>
										<s:else><s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
										onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" /></s:else>
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
										<s:if test="showFlag">
										<td colspan="2">
											<s:property value="divisionName" /><s:hidden name ="divisionName"/>
										</td>
										</s:if>
										<s:else>
										<td width="20">
											<s:textfield name="divisionName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the division" 
											onclick="callsF9(500,325,'SalaryProcess_f9Division.action');">
										</td>
										</s:else>
										
									</tr>
								</s:if>
								<s:else><s:hidden name="divisionName" /></s:else>
								
								<s:hidden name="branchId" /><s:hidden name="branchFlag" />
								<s:if test="branchFlag">
									<tr>
										<td width="300" align="right">
											<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red">*</font>
										</td>
										<s:if test="showFlag">
										<td width="20" >
										<s:property value="branchName" /><s:hidden name ="branchName"/>
										</td>
										<td>&nbsp;
										</td>
										</s:if>
										<s:else>
										<td width="20">
											<s:textfield name="branchName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the branch" 
											onclick="callsF9(500,325,'SalaryProcess_f9Branch.action');">
										</td>
										</s:else>
										
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
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'SalaryProcess_f9Department.action');">
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
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the type of an employee" 
											onclick="callsF9(500,325,'SalaryProcess_f9EmployeeType.action');">
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
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the pay bill group" 
											onclick="callsF9(500,325,'SalaryProcess_f9PayBill.action');">
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="payBillName" /></s:else>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
			<tr>		
			<td width="100%">
				<table width="100%" class="formbg" >
					<tr>
						<td width="100%"><b></b>
							<table width="100%" align="center" border="0" >
								<tr>
									<td width="20%" align="left" >Select Employee :</td>
									<td width="30%" ><s:textfield name="editEmpName" size="30" readonly="true"  cssStyle="background-color: #F2F2F2; "/>
									<s:hidden name="editEmpCode" /><s:hidden name="editEmpToken" />
									
									</td>
									<td width="15%">Salary Days :
									</td>
									<td width="34%" align="left" ><s:hidden name="editEmpSalaryDays" />
									<s:property value="editEmpSalaryDays" />
									</td>
								</tr>
								
										 
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr><s:hidden name="showFlag" />
		
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>Employee Salary Details</b>
							<table width="100%" align="center" border="0"  >
								
								
								<tr>
								<td colspan="2">
									<table width="100%" >
										<tr>
										<td class="formth" width="70%">Credits </td>
										<td class="formth" width="30%">Amount </td>
										</tr>		
									</table>
								 </td>
								<td colspan="2">
									<table width="100%" >
											<tr>
											<td class="formth" width="70%">Debits </td>
											<td class="formth" width="30%">Amount</td>									
										   </tr>	
									</table>
								 </td>
								</tr>
								
								
								
								<%int j=0; %>
								
								<tr>
								<td colspan="2" valign="top">
									<table width="100%" id="creditID" border="0" bordercolor="green">
											<s:iterator value="empList">
											<tr>									
											<td class="sortableTD" width="70%" style='border-top: 0.5pt solid #c2c2c2;'><s:property value="empcreditName"/><s:hidden name="empcreditCode" /></td>
											<td class="sortableTD" width="30%" align="right" style='border-top: 0.5pt solid #c2c2c2;'> 
											<input type="text" onkeyup="callSumCredit('<%=j%>');" style="text-align: right" align="right" id="C<%=j%>" onkeypress="return numbersWithDot();" name="empcreditAmt" size="10" value='<s:property value="empcreditAmt"/>'>
											</td>
											</tr>
											<%j++; %>
											</s:iterator>
									</table>
								 </td>
								<td colspan="2" valign="top">
									<table width="100%"  id="debitID">
									<%int jj=0; %>
											<s:iterator value="empDebitList">
											<tr>									
											<td class="sortableTD" width="70%" style='border-top: 0.5pt solid #c2c2c2;'><s:property value="empdebitName"/><s:hidden name="empdebitCode" /></td>
											<td class="sortableTD" width="30%" align="right" style='border-top: 0.5pt solid #c2c2c2;'>
											<input type="text" onkeyup="callSumDebit('<%=jj%>');" style=" text-align: right" align="right" id="D<%=jj%>" onkeypress="return numbersWithDot();" name="empdebitAmt" size="10" value='<s:property value="empdebitAmt"/>'>
											 </td>
											</tr>
											<%jj++; %>
											</s:iterator>
									</table>
								 </td>
								</tr>
								
								<tr>
								<td colspan="2" valign="top">
									<table width="100%" >
										<tr>
										<td class="sortableTD" width="70%" style='border-top: 0.5pt solid #c2c2c2;'><b>Total Credits </b></td>
										<td class="sortableTD" width="30%" align="right" style='border-top: 0.5pt solid #c2c2c2;'>
										<s:textfield name="empcreditTotalAmt" size="10" readonly="true" cssStyle="background-color: #F2F2F2;  text-align: right"/>
										 </td>
										</tr>	
										<tr>
										<td class="sortableTD" width="70%" style='border-top: 0.5pt solid #c2c2c2;'><b>Net Salary </b></td>
										<td class="sortableTD" width="30%" align="right" style='border-top: 0.5pt solid #c2c2c2;'>
										<s:textfield name="netSalary" size="10" readonly="true" cssStyle="background-color: #F2F2F2;  text-align: right"/>
										 </td>
										</tr>	
									</table>
								 </td>
								<td colspan="2" valign="top">
									<table width="100%" >
											<tr>
											<td class="sortableTD" width="70%" style='border-top: 0.5pt solid #c2c2c2;'><b>Total Debits</b> </td>
											<td class="sortableTD" width="30%" align="right" style='border-top: 0.5pt solid #c2c2c2;'>
											<s:textfield name="empdebitTotalAmt" size="10" readonly="true"  cssStyle="background-color: #F2F2F2;  text-align: right"/>
											</td>									
										   </tr>	
									</table>
								 </td>
								</tr>
								
							
								 
							</table>
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
						<s:if test="onHoldFlag=='false'">
						<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>
						<input type="button" class="token" theme="simple" onclick="return callonhold();" value=" OnHold" />
						</s:else>
						</s:if>
						<s:else>
						<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>
						<input type="button" class="token" theme="simple" onclick="return callRemoveOnHold();" value=" Remove OnHold" />
						</s:else>
						</s:else>
						
							<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
							</s:if>
							<s:else>
							<s:if test="uploadSalaryFlag!='NU'">
								<input type="button" class="edit" onclick="callRecalculate();"	value="   Recalculate" />
							</s:if>
							<input type="button" class="save" theme="simple" onclick="return checkSave();" value=" Save" />
						</s:else>
						
							<s:submit action="SalaryProcess_callForEdit" value="Back" cssClass="token" 
								title="Back" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

			function callSumCredit(id){
				var table = document.getElementById('creditID'); 
				var rowCount = table.rows.length; 				
				var count=0;
				for(var i=0;i<rowCount;i++){
					var creditValue=document.getElementById('C'+i).value;
					if(creditValue==''){
						creditValue='0';
					}
					count=parseFloat(count)+parseFloat(creditValue);					
				}
				count=count.toFixed(2);
				document.getElementById('paraFrm_empcreditTotalAmt').value=count;

				var totalCredit=document.getElementById('paraFrm_empcreditTotalAmt').value;
				var totalDebit=document.getElementById('paraFrm_empdebitTotalAmt').value;
				document.getElementById('paraFrm_netSalary').value='0';
				if(parseFloat(totalCredit)>parseFloat(totalDebit)){
					document.getElementById('paraFrm_netSalary').value=(parseFloat(totalCredit)-parseFloat(totalDebit));
					}
				
				
			}
			function callSumDebit(id){
				var table = document.getElementById('debitID'); 
				var rowCount = table.rows.length; 	
				//alert(rowCount);			
				var count=0;
				for(var i=0;i<rowCount;i++){
					var creditValue=document.getElementById('D'+i).value;
					if(creditValue==''){
						creditValue='0';
					}
					count=parseFloat(count)+parseFloat(creditValue);					
				}
				count=count.toFixed(2);
				document.getElementById('paraFrm_empdebitTotalAmt').value=count;

				var totalCredit=document.getElementById('paraFrm_empcreditTotalAmt').value;
				var totalDebit=document.getElementById('paraFrm_empdebitTotalAmt').value;
				document.getElementById('paraFrm_netSalary').value='0';
				if(parseFloat(totalCredit)>parseFloat(totalDebit)){
					document.getElementById('paraFrm_netSalary').value=(parseFloat(totalCredit)-parseFloat(totalDebit));
					}
				else{
					var oldValue=document.getElementById('D'+id).value;
					document.getElementById('D'+id).value='0';
					document.getElementById('paraFrm_empdebitTotalAmt').value=(parseFloat(count)-parseFloat(oldValue));
					}
			}

			
			function callShowRecord(){
			if(document.getElementById('paraFrm_editEmpCode').value ==''){
					alert('Please select employee');
					document.getElementById('paraFrm_editEmpName').focus();
				return false;
				}
			}


		function checkSave() {
			if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
				alert("Salary is locked.Please unlock first");
				return false;
			}
			con=confirm('Do you really want to save the record?');
		 	if(con){
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_saveEmpRecord.action";
			document.getElementById('paraFrm').submit();
		 	}
		}

		function callRecalculate() {
			if(!checkValidationData()){
				return false;
				}

			if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
				alert("Salary is locked.Please unlock first");
				return false;
			}
			document.getElementById('paraFrm_reProcessEmpCode').value=document.getElementById('paraFrm_editEmpCode').value;

			con=confirm('Do you really want to recalculate the record?');
		 	if(con){
		 	document.getElementById('paraFrm_empRecalculateSalFlag').value='Y';		 	
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_recalculateEmpSalary.action";
			document.getElementById('paraFrm').submit();
		 	}
		}

		
		function callonhold() {
			if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
				alert("Salary is locked.Please unlock first");
				return false;
			}
			con=confirm('Do you really want to onhold record?');
		 	if(con){
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_onHoldEmpRecord.action";
			document.getElementById('paraFrm').submit();
		 	}
		}

		function checkValidationData(){
			var applStatus=document.getElementById('paraFrm_ledgerStatus').value;
			if(applStatus=='SAL_FINAL'){
				alert('Salary is locked.Please unlock first');
				return false;
			}
			if(applStatus=='ATTN_START'){
				alert('Attendance has not been locked');
				return false;
			}
			if(applStatus=='ATTN_UNLOCK'){
				alert('Attendance has not been locked');
				return false;
			}if(applStatus==''){
				alert('Attendance has not been processed');
				return false;
			}
			return true;
			}
		
		function callRemoveOnHold() {
			if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
				alert('Salary is locked.Please unlock first');
				return false;
			}
			con=confirm('Do you really want to remove onhold record?');
		 	if(con){
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_removeOnHoldEmpRecord.action";
			document.getElementById('paraFrm').submit();
		 	}
		}
	function callOnload(){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
}

	function processSal(processType) {
	if(processType=='process'){
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
				document.getElementById("overlay").style.visibility = "";
				document.getElementById("overlay").style.display = "";
				document.getElementById("progressBar").style.visibility = "";
				document.getElementById("progressBar").style.display = "";
				document.getElementById('paraFrm').action = "SalaryProcess_processSalary.action";
				document.getElementById('paraFrm').submit();
				
		}else if(processType=='reProcess'){
			var con=confirm('Do you really want to re-Process the salary?');
			 	if(!con){
			 	return false;
			 	}
			 	document.getElementById("overlay").style.visibility = "";
				document.getElementById("overlay").style.display = "";
				document.getElementById("progressBar").style.visibility = "";
				document.getElementById("progressBar").style.display = "";
				document.getElementById('paraFrm').action = "SalaryProcess_deleteAndProcessSalary.action";
				document.getElementById('paraFrm').submit();
		}
		return true;
	}
	function uploadFile(fieldName) 
{
	var path="oo/<%=session.getAttribute("session_pool")%>/pay";
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	function callReturntoList() {
		document.getElementById("paraFrm_ledgerCode").value = "";
		document.getElementById("paraFrm_month").value = "";
		document.getElementById("paraFrm_year").value = "";
		document.getElementById("paraFrm_employeeTypeName").value = "";
		document.getElementById("paraFrm_employeeTypeId").value = "";
		document.getElementById("paraFrm_payBillName").value = "";
		document.getElementById("paraFrm_payBillId").value = "";
		document.getElementById("paraFrm_departmentName").value = "";
		document.getElementById("paraFrm_departmentId").value = "";
		document.getElementById("paraFrm_branchName").value = "";
		document.getElementById("paraFrm_branchId").value = "";
		document.getElementById("paraFrm_divisionName").value = "";
		document.getElementById("paraFrm_divisionId").value = "";
		document.getElementById("paraFrm_ledgerStatus").value = "";
	}
	
	function downloadTemplate(){
		document.getElementById('paraFrm').action="SalaryProcess_downloadTemplate.action";
		document.getElementById('paraFrm').submit();
	}
	
function checkCreditUpload(){
	var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is locked.Please unlock first");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var upload = document.getElementById('paraFrm_uploadFileNameCredit').value;
    var credithead = document.getElementById('paraFrm_uploadCreditCode').value;
   
		
		if(credithead==""){
			alert("Please select "+document.getElementById('credit.Name').innerHTML);
			return false;
		}
		
		if(upload==""){
			alert("Please select the Xls File");
			return false;
		}else
		{
		//alert("with in else....!!");
		var sub=upload.substring(upload.length-4,upload.length);

		if(!(sub==".xls"||sub==".Xls")){
		alert("Please Upload Only Xls File with .xls Extension");
		 return false;
		}
		
	   
		}
	document.getElementById('paraFrm').action='SalaryProcess_uploadCredit.action';
	document.getElementById('paraFrm').submit();
}



function checkFun(action){
 	 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is locked.Please unlock first");
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
		}
		else{
		if(action=='Lock'){
			 	con=confirm('Do you really want to lock the salary?');
			 	if(con){
			 	//enableBlockDiv();
				document.getElementById('paraFrm').action="SalaryProcess_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}else if(action=='Tax'){
				document.getElementById('paraFrm').action="SalaryProcess_recalculateTax.action";
				document.getElementById('paraFrm').submit();
			}
			
		}
 }
 function unlockSal() {
		doAuthorisation('7', 'Salary', 'U');
	}
	
	function doUnlock() {
		//enableBlockDiv();
		document.getElementById('paraFrm').action="SalaryProcess_unLockSalary.action";
		document.getElementById('paraFrm').submit();
	}
function checkDebitUpload(){
var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is locked.Please unlock first");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var upload = document.getElementById('paraFrm_uploadFileNameDebit').value;
    var Debithead = document.getElementById('paraFrm_uploadDebitCode').value;
   
		
		if(Debithead==""){
			alert("Please select "+document.getElementById('debit.Name').innerHTML);
			return false;
		}
		
		if(upload==""){
			alert("Please select the Xls File");
			return false;
		}else
		{
		//alert("with in else....!!");
		var sub=upload.substring(upload.length-4,upload.length);

		if(!(sub==".xls"||sub==".Xls")){
		alert("Please Upload Only Xls File with .xls Extension");
		 return false;
		}
		
	   
		}
		document.getElementById('paraFrm').action='SalaryProcess_uploadDebit.action';
		document.getElementById('paraFrm').submit();

}

function callPullOtherIncome(actionName){
	var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is locked.Please unlock first");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var con=confirm('Do you really want to pull Data in salary?');
			 	if(!con){
			 	return false;
			 	}
		document.getElementById('paraFrm').action=actionName;
		document.getElementById('paraFrm').submit();
}




</script>