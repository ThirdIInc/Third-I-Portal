
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelMisReport" method="post" id="paraFrm" theme="simple">

	<s:hidden name="empId" />
	<s:hidden name="travelId" value="%{travelId}" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					MIS Report </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%" colspan="3"><s:if test="%{viewFlag}">
						<input type="button" class="token"
							onclick="return callReportMis();" value="    Show Report" />
					</s:if> <s:submit cssClass="reset" action="TravelMisReport_reset"
						theme="simple" value="    Reset"  /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		
		
		<tr>

			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="16%" class="formtext"><label  class = "set"  id="analytics.trvlMisReport.aooFrmDate"  name="analytics.trvlMisReport.aooFrmDate" ondblclick="callShowDiv(this);"><%=label.get("analytics.trvlMisReport.aooFrmDate")%></label>:</td>
					<td width="24%"><s:textfield name="frmDate" size="20"
						onkeypress="return numbersWithHiphen();" theme="simple"
						value="%{frmDate}" maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td width="15%" class="formtext"><label  class = "set"  id="analytics.trvlMisReport.aooToDate"  name="analytics.trvlMisReport.aooToDate" ondblclick="callShowDiv(this);"><%=label.get("analytics.trvlMisReport.aooToDate")%></label>:<s:hidden
						name="today" /></td>
					<td width="25%"><s:textfield name="toDate" size="20"
						onkeypress="return numbersWithHiphen();" theme="simple"
						value="%{toDate}" maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>

				<tr>
					<td width="16%" colspan="1"><label  class = "set"  id="branch"  name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="24%" colspan="1"><s:textfield size="20"
						theme="simple" name="trBranch" value="%{trBranch}" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelMisReport_f9trBrnach.action');">
					<s:hidden name="trBranCode" value="%{trBranCode}" /></td>

					<td width="10%" colspan="1"><label  class = "set"  id="department"  name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
					<td width="30%" colspan="3"><s:textfield size="20"
						theme="simple" name="trDept" value="%{trDept}" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelMisReport_f9trDept.action');">
					<s:hidden name="trDeptCode" value="%{trDeptCode}" /></td>
				</tr>

				<tr>
					<td width="16%" colspan="1"><label  class = "set"  id="designation"  name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
					<td width="24%" colspan="1"><s:textfield size="20"
						theme="simple" name="trDesg" readonly="true" value="%{trDesg}" />
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelMisReport_f9trDesg.action');">
					<s:hidden name="trDesgCode" value="%{trDesgCode}" /></td>
					<td width="15%" colspan="1"><label  class = "set"  id="division"  name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
					<td width="35%" colspan="3"><s:textfield size="20"
						theme="simple" name="trDiv" readonly="true" value="%{trDiv}" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelMisReport_f9trDiv.action');">
					<s:hidden name="trDivCode" value="%{trDivCode}" /></td>
				</tr>
				<tr>
					<td width="16%" class="formtext"><label  class = "set"  id="analytics.trvlMisReport.status"  name="analytics.trvlMisReport.status" ondblclick="callShowDiv(this);"><%=label.get("analytics.trvlMisReport.status")%></label>:</td>
					<td width="24%"><s:select theme="simple" name="status"
						list="#{'1':'Select','P':'Pending','A':'Approved','R':'Rejected','S':'Scheduled'}" /></td>

					<td width="16%" class="formtext"><label  class = "set"  id="analytics.trvlMisReport.cancelStatus"  name="analytics.trvlMisReport.cancelStatus" ondblclick="callShowDiv(this);"><%=label.get("analytics.trvlMisReport.cancelStatus")%></label>:</td>
					<td width="24%"><s:select theme="simple" name="cancelStatus"
						list="#{'1':'Select','C':'Confirm','N':'Cancel In Process','K':'Cancel'}" /></td>
				</tr>

				<tr>
					<td width="16%" colspan="1" class="formtext"><label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<s:hidden
						name="empCode" value="%{empCode}" /></td>
					<td width="84%" colspan="4"><s:textfield name="empToken"
						size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
						name="empName" size="75" value="%{empName}" theme="simple"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelMisReport_f9Employee.action');">
					</td>

				</tr>
				<tr>
					<td width="16%" colspan="1"><label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
					<td width="24%"><s:select name="rptType" headerKey="Pdf"
						headerValue="Pdf" list="#{'Xls':'Xls', 'Txt':'Text'}"
						theme="simple" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	</table>



</s:form>
<script type="text/javascript">

function callReportMis(){
	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	var toDate=document.getElementById('analytics.trvlMisReport.aooToDate').innerHTML.toLowerCase();
  	var frmDate=document.getElementById('analytics.trvlMisReport.aooFrmDate').innerHTML.toLowerCase();	 
  	
  	
  	
  	if(frmDate!="" && toDate=="")
  	{
     alert('Please enter '+toDate);
     return false;        
    }	
    else if(frmDate=="" && toDate!="")
    {
        alert('Please enter '+frmDate);
        return false;     
    }
    if((frmDate!="") && (toDate!="")) {
    	if(!validateDate('paraFrm_frmDate','analytics.trvlMisReport.aooFrmDate')){
		             return false;
		     }
		if(!validateDate('paraFrm_toDate','analytics.trvlMisReport.aooToDate')){
             return false;	 
             }     
	  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'analytics.trvlMisReport.aooFrmDate', 'analytics.trvlMisReport.aooToDate'))
		{
		return false;
		}
	}
	
        
 	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="TravelMisReport_report.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
	
}
  
</script>
