

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="LoanMISApplicationReport" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="loanAppStatus" />
	<table width="100%" class="formbg">
		<!-- Start header Table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					MIS Application Report </strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>
		
		<tr>
			<td>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
	<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">
				<tr>
									<td>
									<table width="98%" border="0" align="center" cellpadding="1"
										cellspacing="2">
										<tr>
											<td colspan="6" class="formhead"><strong
												class="forminnerhead">Select Period </strong></td>
										</tr>
										
										
										<tr>
							<td width="15%" class="formtext"><label  class = "set"  id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font color="red">*</font></td>
							<td ><s:textfield name="fromDate" size="25"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="10%" class="formtext"><label  class = "set"  id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font color="red">*</font><s:hidden
								name="today" /></td>
							<td ><s:textfield name="toDate" size="25"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
									</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
				<tr>
					<td colspan="5"> 
					<div id=""> 
					<table width="100%" border="0" class="formbg">
						
						<tr>
											<td colspan="6" class="formhead"><strong
												class="forminnerhead">Select Filters </strong></td>
										</tr>
						
						 
						<tr>
				<td width="15%"><label class="set" id="division1"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
				
				<td colspan="6"><s:hidden name="divId"/>
							<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="divName"  /></td>
				<td width="20%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_divName',350,250,'LoanMISApplicationReport_f9div.action',event,'false','no','right')"></td>
			</tr>
		
		<tr>
			<td width="15%"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
			
			<td colspan="6"><s:hidden name="branchCode"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="branchName"  /></td>
			<td ><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_branchName',350,250,'LoanMISApplicationReport_f9brn.action',event,'false','no','right')"></td>
		</tr>
		
			<tr>
			<td width="15%"><label class="set" id="department"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
			
			<td colspan="6"><s:hidden name="deptId"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="deptName"  /></td>
			<td ><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_deptName',350,250,'LoanMISApplicationReport_f9Department.action',event,'false','no','right')"></td>
		</tr>
		
		<tr>
			<td width="15%"><label class="set" id="pay.bill"
						name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
			
			<td colspan="6"><s:hidden name="paybillId"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="paybillName"  /></td>
			<td ><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'LoanMISApplicationReport_f9PayBill.action',event,'false','no','right')"></td>
		</tr>
						
		<tr>
			<td width="15%"><label class="set" id="cost.center"
						name="cost.center" ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label>:</td>
			
			<td colspan="6"><s:hidden name="costCenterId"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="costCenterName"  /></td>
			<td ><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_costCenterName',350,250,'LoanMISApplicationReport_f9CostCenter.action',event,'false','no','right')"></td>
		</tr>
		
		<tr>
			<td width="15%"><label class="set" id="employee"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
			
			
			<td colspan="6"><s:hidden name="empCode" /><s:textfield name="empToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="empName" size="73" theme="simple" readonly="true" /></td>
			 
			<td ><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9employee.action');"></td>
		</tr>
		  
			 
		 
		
		<!--				
						
						
						
						 Division & Departmnet 
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="division" id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="divId" /><s:textfield
								size="25" name="divName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9divaction.action');"></td>
							<td width="20%" colspan="1"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="deptId" /><s:textfield
								size="25" name="deptName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9department.action');"></td>

							<td width="23%" colspan="1"></td>
						</tr>
						 branch & designation 
						<tr>
							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="branchCode" /><s:textfield
								size="25" name="branchName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9branch.action');"></td>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="designationCode" /><s:textfield
								size="25" name="designationName" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9designation.action');"></td>

							<td width="23%" colspan="1"></td>
						</tr>

						 employee & loan type
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
							<td width="20%" colspan="1"><s:hidden name="empCode" /> <s:hidden
								name="empToken" /> <s:textfield size="25" name="empName"
								readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'LoanMISApplicationReport_f9employee.action');"></td>


							<td width="20%" colspan="1"><label class="set"
								name="loan.status" id="loan.status"
								ondblclick="callShowDiv(this);"><%=label.get("loan.status")%></label>:
							</td>
							<td width="23%" colspan="1" class="formtext"><s:select
								headerValue="All" headerKey="T" name="status"
								list="#{'P':'Pending','A':'Processed'}" cssStyle="width:150" /></td>

							<td width="23%" colspan="1"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="loan.status"
								id="loan.status" ondblclick="callShowDiv(this);"><%=label.get("loan.report")%></label><font
								color="red">*</font>:</td>
							<td width="23%" colspan="1" class="formtext">
							<div id="reportTypeDiv"><s:select headerKey=""
								headerValue="--Select--" name="reportType"
								list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
							</td>
						</tr>

						<tr>

							<td align="center" colspan="5"><input type="button"
								class="token" theme="simple" value="  Generate Report"
								onclick=" return generateReport();" /> <input type="button"
								class="reset" theme="simple" value=" Reset"
								onclick="return calReset();" /></td>
						</tr>


					</table>
					</div>
					</td>
				</tr>
-->


			</table>
			</td>
		</tr>
		<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbg">

								<tr>
									<td>
									<table width="98%" border="0" align="center" cellpadding="1"
										cellspacing="2">
										<tr>
											<td colspan="6" class="formhead"><strong
												class="forminnerhead"></strong></td>
										</tr>
										<tr>
											<td colspan="1" width="30%"><label
												id="display.status" name="display.status"
												ondblclick="callShowDiv(this);"><%=label.get("display.status")%></label>:<font color="red">*</font></td>
											<td width="70%" align="left">
											 <s:checkbox
												name="statusChkAll" id="statusChkAll" onclick="return callCheck('statusChkAll');"></s:checkbox>All
												
												  <s:checkbox
												name="statusChkProcessed" id="statusChkProcessed" onclick="return callCheck('statusChkProcessed');"> </s:checkbox>Processed  
												
												<s:checkbox
												name="statusChkPending" id="statusChkPending" onclick="return callCheck('statusChkPending');"> </s:checkbox>Pending    
												</td>
								<td width="25%"></td>
								<td width="25%"></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	<s:hidden name="reportType" />
	<s:hidden  name="hiddenChechfrmId" id="hiddenChechfrmId"/>
	<s:hidden name="reportAction"
		value='LoanMISApplicationReport_getReport.action' />
	<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>
	
</s:form>

<script>
	getYear();
	 
	 function calReset(){
	 		 document.getElementById('paraFrm_branchName').value="";
	 		 document.getElementById("paraFrm_divName").value="";
		     document.getElementById('paraFrm_deptName').value="";
		     document.getElementById('paraFrm_empName').value="";
		     document.getElementById('paraFrm_status').value="T";
		     document.getElementById('paraFrm_reportType').value="";
		     document.getElementById('paraFrm_designationName').value="";
		     
		  //   document.getElementById('paraFrm').target='main';
		//document.getElementById('paraFrm').action="NewLoanIssuedRpt_reset.action";
		//document.getElementById('paraFrm').submit();
		   getYear();  
	 }
	 
	 function getYear(){
	 try{
	 document.getElementById('hiddenChechfrmId').value="A";
	if(document.getElementById('hiddenChechfrmId').value="A"){
		document.getElementById('statusChkAll').checked= true;
	} 
	
	  }catch(e){
	  	alert(e);
	  }
}


function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_reportType').value=type;
				callReportCommon(type);
			}
}

function generateReport(type)
 {	
	try{
	
	//document.getElementById('paraFrm_report').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="LoanMISApplicationReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }	
	 
	 
}

function validateFields(){
		try{
		var fDate=document.getElementById('paraFrm_fromDate').value;
 var tDate=document.getElementById('paraFrm_toDate').value;
 
 if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		
		if(trim(document.getElementById('paraFrm_toDate').value) == "") {
				alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
		}
		
  	if(!validateDate("paraFrm_fromDate","from.date")){
  			return false;
  		
  		
  		}
  	
  	
  		if(!validateDate("paraFrm_toDate","to.date")){
		return false;
	}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
	
	return false;
	}
		
		var divNm   =document.getElementById("paraFrm_divId").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	 
	 }
	 
	 var statusChkAllVar = document.getElementById('statusChkAll').checked;
		var statusChlProcessVar = document.getElementById('statusChkProcessed').checked;
		var statusChkPendingxVar = document.getElementById('statusChkPending').checked;
		
		if((statusChkAllVar == false) && (statusChlProcessVar == false)&& (statusChkPendingxVar == false) )
		{
			alert("Select options to Display Loan Application with Status   ");
			return false;
		}
	 
	 }catch (e)
	 {
	 	alert(e);
	 }
	 
		return true;
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='LoanMISApplicationReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function callCheck(type){
try{
		if(type=="statusChkAll"){
		document.getElementById('hiddenChechfrmId').value = "A";
		document.getElementById('statusChkProcessed').checked =false;
			document.getElementById('statusChkPending').checked =false;
			
		} 
		else if(type=="statusChkProcessed"){
			document.getElementById('hiddenChechfrmId').value = "P";
		document.getElementById('statusChkAll').checked =false;
			document.getElementById('statusChkPending').checked =false;
		}
		else if(type=="statusChkPending"){
			document.getElementById('hiddenChechfrmId').value = "F";
		document.getElementById('statusChkAll').checked =false;
			document.getElementById('statusChkProcessed').checked =false;
		}
		
			
		}catch(e){
			alert(e);
		}
}

function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="LoanMISApplicationReport_reset.action";
		document.getElementById('paraFrm').submit();
	}

</script>