<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="LoanIndexReport" method="post" id="paraFrm">
<table class="formbg" width="100%">
<s:hidden name="trackingNumber"/>
	        <tr>
	        	<td  width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Loan Index Report </strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
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

		</tr><!--
	
            <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td width="70%" colspan="3">
				<input type="button" 
					class="token" onclick="return callReport();"	value="    Show Report" />
				<input type="button" class="reset" onclick="return callReset();" 
					theme="simple" value="    Reset"  />
				</td>
					<td width="30%">
					<div align="right"><span class="style2"></span> </div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		--><tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
										

						<tr><s:hidden name="loanAppCode"></s:hidden>
							<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font><s:hidden name="empCode" value="%{empCode}" /></td>
							<td width="75%" colspan="3"><s:hidden name="sanctionAmount"></s:hidden><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empname" size="50" value="%{empname}" theme="simple"
								readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18" 
										onclick="javascript:callsF9(500,325,'LoanIndexReport_f9actionLoanAppl.action');">
									</td>

						</tr>
						<tr>
							<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="typeLoan" name="typeLoan" ondblclick="callShowDiv(this);"><%=label.get("typeLoan")%></label>:</td>
							<td width="25%" colspan="1"><s:textfield
								name="loanType" size="25" value="%{empname}" theme="simple"
								readonly="true" /> 
								
							</td>
							<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="ApplDate" name="ApplDate" ondblclick="callShowDiv(this);"><%=label.get("ApplDate")%></label>:</td>
							<td width="25%" colspan="1"><s:textfield
								name="appDate" size="25" value="%{appDate}" theme="simple"
								readonly="true" /> 
								
							</td>
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
	<s:hidden name="reportType" />
	
	<s:hidden name="reportAction"
		value='LoanIndexReport_getReport.action' />
	<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>
</s:form>
<script type="text/javascript">
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
				document.getElementById('paraFrm').action="LoanIndexReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }	
	 
	 
}

function validateFields(){
		try{
		var employee=document.getElementById('employee').innerHTML.toLowerCase();
var emp=document.getElementById("paraFrm_empname").value
  	if(emp==""){
  		alert("Please select "+employee);
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
			document.getElementById('paraFrm').action='LoanIndexReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="LoanIndexReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	

function callReset(){
	
	document.getElementById("paraFrm_empToken").value="";
	document.getElementById("paraFrm_empname").value="";
	document.getElementById("paraFrm_loanAppCode").value="";
	document.getElementById("paraFrm_empCode").value="";
	document.getElementById("paraFrm_loanType").value="";
	document.getElementById("paraFrm_appDate").value="";
}
  	
</script>
