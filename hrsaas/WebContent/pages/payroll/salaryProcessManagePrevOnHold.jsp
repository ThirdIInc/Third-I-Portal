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
						<td width="92%" class="txt"><strong class="text_head">Salary Process Manage Previous OnHold View</strong></td>
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
						<s:hidden name="statisticsHidden"/>
						<s:hidden name="statusChange"/>
						<s:hidden name="totalEmp"/>
						<s:hidden name="zeroDays"/>
						<s:hidden name="onHoldEmp"/>							
						<s:hidden name="statusEmpCode"/>
						<s:hidden name="statusEmpName"/>
						<s:hidden name="companyPFFlag"/>
						<s:hidden name="uploadSalaryFlag"/>	
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
										<td>
										<s:property value="branchName" /><s:hidden name ="branchName"/>
										</td>
										<td>
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
						<td width="100%"><b>Release Previous Month Onhold Records </b>
							<table width="100%" align="center" border="0" >
								<tr>
									<td width="20%" align="left" >Month :<font color="red">*</font></td>
									<td width="25%" ><s:select name="monthOnHold" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" />
									
									</td>
									<td width="10%">
									Year :<font color="red">*</font>
									</td>
									<td width="15%" align="left" >
									<s:textfield name="yearOnHold" size="10" maxlength="4" onkeypress="return numbersOnly(event);"></s:textfield>
									</td>
									<td width="25%" align="left" >&nbsp;
									</td>
								</tr>
								
								<tr>
									<td  align="left" >Select Employee :<font color="red">*</font></td>
									<td >
									<s:textfield name="editEmpName"  size="30" readonly="true"/>
									<s:hidden name="editEmpCode" /><s:hidden name="editEmpToken" />
									</td>
									<td >
									<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the type of an employee" 
											onclick="onHoldEmployee();">
									</td>
									<td  align="left" >
									<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'||ledgerStatus == 'ATTN_READY'">
							</s:if>
							<s:else>
									<input type="button" class="add" theme="simple" onclick="return addOnHoldEmployee();" value=" Release OnHold Salary" />
							</s:else>		
									</td>
									<td  align="left" >
									&nbsp;
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
				<table width="100%" border="0">
					<tr>						
								<td colspan="6" align="right">
								<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'||ledgerStatus == 'ATTN_READY'">
							</s:if>
							<s:else>
								<input type="button" class="save" theme="simple" onclick="return addOnHold();" value=" Remove " />
							</s:else>
								</td>								
					</tr>
				</table>
			</td>
		</tr>				
		
		<tr><s:hidden name="showFlag" />
		
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b></b>
							<table width="100%" align="center" border="0" bordercolor="red" id="onHoldTable">
								
								
								<tr>
								<td class="formth" width="10%">
								
								Sr. No. </td>
								<td class="formth" width="15%">Employee Id</td>
								<td class="formth" width="40%">Employee Name</td>
								<td class="formth" width="15%">Month-Year</td>
								<td class="formth" width="10%">Salary Days</td>	
								<td class="formth" width="10%">Net Salary</td>	
								<td class="formth" width="5%">
								<input type="checkbox" name="onholdCheckBox" id="onholdCheckBoxAll" onclick="selectAllRemoveOnHold()"/>
								</td>
								</tr>
								<%int j=1; %>
								<s:iterator value="empOnholdList">
								<tr>
								<td class="sortableTD" >							
								
								<%=j %></td>	
								<td class="sortableTD" ><s:property value="onholdEmpToken"/><s:hidden name="onholdEmpID" /></td>
								<td class="sortableTD" ><s:property value="onholdEmpName"/><s:hidden name="onholdEmpName" /></td>
								<td class="sortableTD" ><s:property value="onholdMonthYear"/><s:hidden name="onholdMonth" /><s:hidden name="onholdYear" /></td>
								<td class="sortableTD" align="right"><s:property value="onholdSalaryDays"/><s:hidden name="onholdSalaryDays" /></td>
									<td class="sortableTD" align="right"><s:property value="onholdNetSalary"/><s:hidden name="onholdNetSalary" /></td>
									<td class="sortableTD">
									<input type="checkbox" name="onholdCheckBox" id="onholdCheckBox<%=j%>" onclick="callRemoveOnHold('<%=j%>')"/>
								<input type="hidden" name="onholdCheckBoxH" id="onholdCheckBoxH<%=j%>"/>
									</td>
								</tr>
								<%j++ ;%>
								</s:iterator>
								
								
								
								
								
								
							
								 
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

function selectAllRemoveOnHold(){
	var table = document.getElementById('onHoldTable'); 
	var rowCount = table.rows.length; 
	for(var i=1;i<rowCount;i++){
		document.getElementById('onholdCheckBox'+i).checked=false;
		document.getElementById('onholdCheckBoxH'+i).value='N';
		if(document.getElementById('onholdCheckBoxAll').checked){
			document.getElementById('onholdCheckBox'+i).checked=true;
			document.getElementById('onholdCheckBoxH'+i).value='Y';
		}
	}
	
}

	function onHoldEmployee(){

		if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
			alert("Salary is locked.Please unlock first");
			return false;
		}
			if(document.getElementById('paraFrm_monthOnHold').value =='0'){
				alert('Please select month');
				document.getElementById('paraFrm_monthOnHold').focus();
			return false;
			}
			

			
			if(document.getElementById('paraFrm_yearOnHold').value ==''){
				alert('Please enter year');
				document.getElementById('paraFrm_yearOnHold').focus();
			return false;
			}
			if(document.getElementById('paraFrm_month').value ==document.getElementById('paraFrm_monthOnHold').value){
				if(document.getElementById('paraFrm_yearOnHold').value ==document.getElementById('paraFrm_year').value){	
				alert('Salary process month and previous onhold month should be different');
				document.getElementById('paraFrm_monthOnHold').focus();
			return false;
				}
			}
		callsF9(500,325,'SalaryProcess_f9prevOnHold.action');
		}

function callRemoveOnHold(id){
	document.getElementById('onholdCheckBoxH'+id).value='N';
	if(document.getElementById('onholdCheckBox'+id).checked){
		document.getElementById('onholdCheckBoxH'+id).value='Y';
		}
}

function removeOnHold(){
	if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
		alert("Salary is locked.Please unlock first");
		return false;
	}

	var table = document.getElementById('onHoldTable'); 
	var rowCount = table.rows.length; 
	var checkValue='false';
	for(var i=1;i<rowCount;i++){
		if(document.getElementById('onholdCheckBoxH'+i).value=='Y'){
			checkValue='true';
			}
	}
	if(checkValue=='false'){
		alert('Please select atleat one record to remove onhold');
		return false;
		}
	con11=confirm('Do you really want to remove Onhold?');
 	if(con11){
	document.getElementById('paraFrm').target='main';
	document.getElementById('paraFrm').action="SalaryProcess_addOnHoldPrevEmp.action";
	document.getElementById('paraFrm').submit();
 	}
	
}
			function viewOnHoldEmployee(){
				document.getElementById('paraFrm').target='main';
				document.getElementById('paraFrm').action="SalaryProcess_viewOnHoldEmployee.action";
				document.getElementById('paraFrm').submit();
				}

			function addOnHold(){

				if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
					alert("Salary is locked.Please unlock first");
					return false;
				}
				
			var table = document.getElementById('onHoldTable'); 
			var rowCount = table.rows.length; 
			var checkValue='false';
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('onholdCheckBoxH'+i).value=='Y'){
					checkValue='true';
					}
			}
			if(checkValue=='false'){
				alert('Please select atleat one record to add onhold record');
				return false;
				}
			con11=confirm('Do you really want to remove released onhold records?');
		 	if(con11){
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_removeOnHoldPrevEmp.action";
			document.getElementById('paraFrm').submit();
		 	}	
		}
			
function callSumCredit(id){
				var table = document.getElementById('creditID'); 
				var rowCount = table.rows.length; 				
				var count=0;
				for(var i=0;i<rowCount;i++){
					var creditValue=document.getElementById('C'+i).value;
					if(creditValue==''){
						creditValue='0';
					}
					count=parseInt(count)+parseInt(creditValue);					
				}
				document.getElementById('paraFrm_empcreditTotalAmt').value=count;
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
					count=parseInt(count)+parseInt(creditValue);					
				}
				document.getElementById('paraFrm_empdebitTotalAmt').value=count;
			}

			
			function addOnHoldEmployee(){
			
				if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
					alert("Salary is locked.Please unlock first");
					return false;
				}
			if(document.getElementById('paraFrm_monthOnHold').value =='0'){
				alert('Please select month');
				document.getElementById('paraFrm_monthOnHold').focus();
			return false;
			}

			


			
			if(document.getElementById('paraFrm_yearOnHold').value ==''){
				alert('Please enter year');
				document.getElementById('paraFrm_yearOnHold').focus();
			return false;
			}

			if(document.getElementById('paraFrm_month').value ==document.getElementById('paraFrm_monthOnHold').value){
				if(document.getElementById('paraFrm_yearOnHold').value ==document.getElementById('paraFrm_year').value){	
				alert('Salary process month and previous onhold month should be different');
				document.getElementById('paraFrm_monthOnHold').focus();
			return false;
				}
			}


			
			if(document.getElementById('paraFrm_editEmpCode').value ==''){
				alert('Please select employee');
				document.getElementById('paraFrm_editEmpName').focus();
			return false;
		}
			con=confirm('Do you really want to release onhold employee');
		 	if(con){
		 		document.getElementById('paraFrm').target='main';
				document.getElementById('paraFrm').action="SalaryProcess_addOnHoldPrevEmp.action";
				document.getElementById('paraFrm').submit();
		 	}
		 	else{
			return false;
			 	}
			}


		function checkSave() {
			con=confirm('Do you really want to save the record?');
		 	if(con){
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').action="SalaryProcess_saveEmpRecord.action";
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
			alert("Salary is already Locked");
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
			alert("Salary is already Locked");
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
			alert("Salary is already Locked");
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
			alert("Salary is already Locked");
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