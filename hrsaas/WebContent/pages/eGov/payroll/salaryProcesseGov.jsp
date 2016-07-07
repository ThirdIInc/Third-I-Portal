<!--Prakash Shetkar--><!--May 11, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<s:form action="SalaryProcesseGov" name="SalaryProcess" validate="true" id="paraFrm" target="main" theme="simple">
	<div id="msgDiv" style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 50px; left: 250px;'></div>
<div id="confirmationDiv" style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
<div align="center" id="overlay" style="z-index: 3; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;"></div>

<div id="progressBar" style="z-index: 3; position: absolute; width: 770px;">
<table width="100%">
	<tr>
		<td height="200"></td>
	</tr>
	<tr>
		<td align="center"><img src="../pages/images/ajax-loader.gif">
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Processing...</span>
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Please
		do not close the browser and do not click anywhere</span></td>
	</tr>
</table>
</div>
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Salary Process</strong></td>
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
							<s:if test="showFlag">
								<input type="button" value="Drop & Process" class="token" 
								title="Delete & Process the Salary" onclick="return processSal('reProcess')" /> 
								<input type="button"  value="Recalculate Tax" class="token" 
								title="Recalculate Tax" onclick="return checkFun('Tax');" />
									<s:if test="%{ledgerStatus == 'SAL_START'}">
									<input type="button" class="lock" theme="simple" title="Lock Salary"
									onclick="return checkFun('Lock');" value="    Lock" /></s:if>
									
									<s:if test="%{ledgerStatus == 'SAL_FINAL'}">		
								<input type="button" class="unlock" title="Unlock Salary" theme="simple"
									onclick="return unlockSal();" value="    Unlock" /></s:if>	
									
							</s:if>
							<s:else>
								<input type="button" value="Process" class="token" 
								title="Process the Salary" onclick="return processSal('process')" />
							</s:else>
							<s:submit action="SalaryProcesseGov_reset" value="Reset" cssClass="reset" title="Clear the fields" />
							<s:submit action="SalaryProcesseGov_input" value="Return to List" cssClass="token" 
								title="Return to List" onclick="return callReturntoList()" />
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
						<td width="100%"><b>Process Salary</b>
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
											onclick="callsF9(500,325,'SalaryProcesseGov_f9Division.action');">
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
										<td width="20">
											<s:textfield name="branchName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the branch" 
											onclick="callsF9(500,325,'SalaryProcesseGov_f9Branch.action');">
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
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'SalaryProcesseGov_f9Department.action');">
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
											onclick="callsF9(500,325,'SalaryProcesseGov_f9EmployeeType.action');">
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
											onclick="callsF9(500,325,'SalaryProcesseGov_f9PayBill.action');">
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
		<tr><s:hidden name="showFlag" />
		<s:if test="showFlag">
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>View Salary</b>
							<table width="100%" align="center" >
								<tr>
									<td width="15%" align="left" >View salary register :</td>
									<td width="30%" ><s:submit action="SalaryProcesseGov_viewSalaryReport" value="View Records" cssClass="token" 
										title="View Records" /></td>
									<td width="20%">View salary in editable mode :</td>
									<td width="34%" align="left" ><s:submit action="SalaryProcesseGov_viewEditableSalary" value="Edit Records" cssClass="token" 
										title="Edit Records"  /></td>
								</tr>
										 
							</table>
						</td>
					</tr>
				</table>
			</td>
		</s:if>
		</tr>
		<tr>
		<s:if test="showFlag">
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>Upload Credit</b>
							<table width="100%" border ="0">
								<tr>
									<td width="100" align="left">
										<label id="credit.Name" name="credit.Name" ondblclick="callShowDiv(this);"><%=label.get("credit.Name")%></label> :<font color="red">*</font>
									</td>
									<td width="30">
										<s:textfield name="uploadCreditName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										<s:hidden name="uploadCreditCode"/>
									</td>
									<td>	
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
										align="middle" width="18"  
										onclick="callsF9(500,325,'SalaryProcesseGov_f9CreditAction.action');">
									</td>
									<td><input type="button" value="Download Template" class="token" onclick="downloadTemplate();" /></td>
								</tr>
								<tr>
									<td width="100" align="left">
										<label id="upload" name="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label> :<font color="red">*</font>
									</td>
									<td width="30">
										<s:textfield name="uploadFileNameCredit" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
									</td>
									<td>
										<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileNameCredit')"/>											
									</td>
									<td ><input type="button" value=" Upload Credit Data " class="token" onclick="checkCreditUpload();"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</s:if>					
		</tr>
		<s:if test="showFlag">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>Upload Debit</b>
							<table width="100%" border ="0">
								<tr>
									<td width="100" align="left">
										<label id="debit.Name" name="debit.Name" ondblclick="callShowDiv(this);"><%=label.get("debit.Name")%></label> :<font color="red">*</font>
									</td>
									<td width="30">
										<s:textfield name="uploadDebitName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										<s:hidden name="uploadDebitCode"/>
									</td>
									<td>	
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
										align="middle" width="18" title="Select the pay bill group" 
										onclick="callsF9(500,325,'SalaryProcesseGov_f9DebitAction.action');">
									</td>
									<td><input type="button" value="Download Template" class="token" onclick="downloadTemplate();" /></td>
								</tr>
								<tr>
									<td width="100" align="left">
										<label id="upload" name="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label> :<font color="red">*</font>
									</td>
									<td width="30">
										<s:textfield name="uploadFileNameDebit" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
									</td>
									<td>
										<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileNameDebit')"/>											
									</td>
									<td ><input type="button" value="  Upload Debit Data  " class="token" onclick="checkDebitUpload();"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<s:if test="otherIncomeFlag">
			<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>Pull Other Income in Salary</b>
							<table width="100%" border ="0">
								<tr>
								<td width="100" align="left">
									<s:if test="extraWorkFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcesseGov_pullExtraWorkBenefit.action')" value='Extrawork Benefits'/>
									</s:if>
									<s:if test="leaveEncashFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcesseGov_pullLeaveEncashment.action')" value='Leave Encashment'/>
									</s:if>
									<s:if test="allowanceFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcesseGov_pullAllowance.action')" value='Allowance'/>
									</s:if>
								</td>
								
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</s:if>
	</s:if>			
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<s:if test="showFlag">
								<input type="button" type="button" value="Drop & Process" class="token" 
								title="Delete & Process the Salary" onclick="return processSal('reProcess')" /> 
								<input type="button"  value="Recalculate Tax" class="token" 
								title="Recalculate Tax" onclick="return checkFun('Tax');" />
									<s:if test="%{ledgerStatus == 'SAL_START'}">
									<input type="button" class="lock" theme="simple" title="Lock Salary"
									onclick="return checkFun('Lock');" value="    Lock" /></s:if>
									
									<s:if test="%{ledgerStatus == 'SAL_FINAL'}">		
								<input type="button" class="unlock" title="Unlock Salary" theme="simple"
									onclick="return unlockSal();" value="    Unlock" /></s:if>	
									
							</s:if>
							<s:else>
								<input type="button" value="Process" class="token" 
								title="Process the Salary" onclick="return processSal('process')" />
							</s:else>
							<s:submit action="SalaryProcesseGov_reset" value="Reset" cssClass="reset" title="Clear the fields" />
							<s:submit action="SalaryProcesseGov_input" value="Return to List" cssClass="token" 
								title="Return to List" onclick="return callReturntoList()" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function callOnload(){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
}
callOnload();
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
				document.getElementById('paraFrm').action = "SalaryProcesseGov_processSalary.action";
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
				document.getElementById('paraFrm').action = "SalaryProcesseGov_deleteAndProcessSalary.action";
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
		document.getElementById('paraFrm').action="SalaryProcesseGov_downloadTemplate.action";
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
	document.getElementById('paraFrm').action='SalaryProcesseGov_uploadCredit.action';
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
				document.getElementById('paraFrm').action="SalaryProcesseGov_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}else if(action=='Tax'){
				document.getElementById('paraFrm').action="SalaryProcesseGov_recalculateTax.action";
				document.getElementById('paraFrm').submit();
			}
			
		}
 }
 function unlockSal() {
		doAuthorisation('7', 'Salary', 'U');
	}
	
	function doUnlock() {
		//enableBlockDiv();
		document.getElementById('paraFrm').action="SalaryProcesseGov_unLockSalary.action";
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
		document.getElementById('paraFrm').action='SalaryProcesseGov_uploadDebit.action';
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