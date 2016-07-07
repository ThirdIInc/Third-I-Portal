<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="HelpDeskReqDurationReport" method="post" id="paraFrm">
<s:hidden name="report" />
<s:hidden name="reportAction" value='HelpDeskReqDurationReport_getReport.action' />
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Request Analysis Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- New added code for report begins -->
		<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
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
		<!-- New added code for report Ends-->
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="return validateForm();" value="Show Report" />
						</s:if>
						<s:submit cssClass="reset" action="HelpDeskMisReport_reset" theme="simple" value="Reset" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		--><tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0" id="reportBodyTable">
				<tr>
					<td>
					<table width="100%" cellspacing="2" border="0">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select any/all search criteria to
							generate  report </strong></td>
						</tr>

						<tr>
							<td width="23%" colspan="1" class="formtext"><s:if
								test="generalFlag">Employee Name</s:if><s:else>Select Employee</s:else>
							 <s:hidden name="employeeCode" /></td>
							 <td width="1%">:</td>
							<td width="77%" colspan="4" nowrap="nowrap"><s:textfield
								name="employeeToken" size="14" theme="simple" readonly="true" />
							<s:textfield name="employeeName" size="55" theme="simple"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'HelpDeskMisReport_f9actionEmployeeName.action');">
							</td>
						</tr>
						
									
						<tr>
							<td width="23%" colspan="1" class="formtext">
								
								<label class="set" name="reqOwner" id="reqOwner" ondblclick="callShowDiv(this);"><%=label.get("reqOwner")%>
							<s:hidden name="teamMemberCode" /></td>
							<td width="1%">:</td>
							<td width="77%" colspan="4" nowrap="nowrap"><s:textfield
								name="teamMemberToken" size="14" theme="simple" readonly="true" />
							<s:textfield name="teamMemberName" size="55" theme="simple"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'HelpDeskMisReport_f9teamMember.action');">
							</td>
						</tr>
						
						<tr>
							<td width="23%" colspan="1" class="formtext">
								 
								<label class="set" name="brnName" id="brnName" ondblclick="callShowDiv(this);"><%=label.get("brnName")%>
							 <s:hidden name="centerId" /></td>
							 <td width="1%">:</td>
							<td width="77%" colspan="4" ><s:textfield
								name="centerName" size="76" theme="simple" readonly="true" />
							<img 	src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'HelpDeskMisReport_f9centerName.action');">
							</td>
						</tr>
						

						<tr>
							<td width="23%" colspan="1" class="formtext">
								<label class="set" name="deptName" id="deptName" ondblclick="callShowDiv(this);"><%=label.get("deptName")%>
							 <s:hidden name="deptCode" /></td>
							 <td width="1%">:</td>
							<td width="77%" colspan="4" ><s:textfield
								name="deptName" size="76" theme="simple" readonly="true" />
							<img 	src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'HelpDeskMisReport_f9deptName.action');">
							</td>
						</tr>
						
						
						<tr>
							<td width="23%" colspan="1" class="formtext">
							<label class="set" name="catName" id="catName" ondblclick="callShowDiv(this);"><%=label.get("catName")%>
							<s:hidden name="catagoryCode" /></td>
							<td width="1%">:</td>
							<td width="77%" colspan="4">
							<s:textfield name="catagoryName" size="76" theme="simple"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'HelpDeskMisReport_f9catagoryName.action');">
							</td>
						</tr>
						


						<tr>
							<td width="23%" colspan="1"><label class="set" name="from_date" id="from_date" ondblclick="callShowDiv(this);"><%=label.get("from_date")%></label>
							</td>
							<td width="1%">:</td>
							<td width="17%" colspan="1" nowrap="nowrap"><s:textfield name="fromDate"
								size="10" onkeypress="return numbersWithHiphen();"
								theme="simple" maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="17%" colspan="1"><label class="set" name="to_date" id="to_date" ondblclick="callShowDiv(this);"><%=label.get("to_date")%></label> 
							</td>
							<td width="1%">:</td>
							<td width="43%" colspan="1" nowrap="nowrap"><s:textfield name="toDate"
								size="10" onkeypress="return numbersWithHiphen();"
								theme="simple" maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>
							<tr>
							<td width="23%" colspan="1">Status </td>
							<td width="1%">:</td>	
							<td width="18%" colspan="1"><s:select name="status" theme="simple"
								list="statusMap" /></td>
						<!--
						   <td width="17%" colspan="1">Report Type <font color="red">*</font> </td>	
						   <td width="1%">:</td>
							<td width="43%" colspan="1"><s:select name="reportType" headerKey="-1" headerValue="---Select---" theme="simple" list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>		
						-->
						</tr>
					
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- New added code for report begins -->
		<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
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
					<div id='bottomButtonTable'></div>
				  </td>
				</tr>
		<!-- New added code for report Ends-->
	</table>
	
</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>

<script type="text/javascript">

	function validateForm(){
	
		var employeeName = document.getElementById("paraFrm_employeeName").value;
		var fromDate	 = document.getElementById("paraFrm_fromDate").value;
	
		var toDate		 = document.getElementById("paraFrm_toDate").value;
	
		var status		 = document.getElementById("paraFrm_status").value;
		
		var teamMemberName		 = document.getElementById("paraFrm_teamMemberName").value;
		var deptName		 = document.getElementById("paraFrm_deptName").value;
		var catagoryName		 = document.getElementById("paraFrm_catagoryName").value;
		
		
	
		if(employeeName == "" && fromDate == ""  && toDate == "" && teamMemberName =="" && deptName=="" && catagoryName =="" ){
			alert("Please select any/all search criteria to generate report");

			document.getElementById().focus("paraFrm_employeeName");
			
			document.getElementById().focus("paraFrm_teamMemberName");
			document.getElementById().focus("paraFrm_deptName");
			document.getElementById().focus("paraFrm_catagoryName");
			document.getElementById().focus("paraFrm_status");
			return false;
			
		}
		if(!fromDate == "")
		{
		
			if(!validateDate("paraFrm_fromDate", "from_date"))return false;
		}
		if(!toDate == "")
		{
		
			if(!validateDate("paraFrm_toDate", "to_date"))return false;
		}
		if(!fromDate == "" && !toDate == "")
		{
		
			if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_fromDate", "from_date", "to_date")){
			document.getElementById('paraFrm_toDate').focus();
			return false;
			}
		}
		var reportType = document.getElementById('paraFrm_reportType').value;
		
		if(reportType==-1)
		{
		alert("Please select report type");
		return false;
		}
		
		callReport('HelpDeskMisReport_report.action')
	}

//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
	
	   var empName=document.getElementById("paraFrm_employeeToken").value;
	   var memName=document.getElementById("paraFrm_teamMemberToken").value;
	   var deptName=document.getElementById("paraFrm_deptName").value;
	   var categoryName = document.getElementById("paraFrm_catagoryName").value;
	   var fromDate	 = document.getElementById("paraFrm_fromDate").value;
		var toDate = document.getElementById("paraFrm_toDate").value;
		var centerName = document.getElementById("paraFrm_centerName").value;
		var status		 = document.getElementById("paraFrm_status").value;


	   if(empName=="" &&  memName=="" && deptName=="" && categoryName=="" && fromDate=="" && toDate==""  && centerName=="" && status==""){

	   alert("Please select any/all search criteria to generate report ");

	   return false;
	   }
	   
	   if(!validateDate('paraFrm_fromDate', 'from_date')) {
			return false;
		}
		
	   if(!validateDate('paraFrm_toDate', 'to_date')) {
			return false;
		}
		
		if(fromDate != "" && toDate==""){
		alert("Please enter/select To Date");
		return false;
		}
		if(toDate != "" && fromDate==""){
		alert("Please enter/select From Date");
		return false;
		}
		if(fromDate != ""){
			if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_fromDate', 'from_date', 'to_date')){
				return false;
			}
		}
		
		
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='HelpDeskReqDurationReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	function callReset(){
	document.getElementById('paraFrm').target='_self';
		document.getElementById('paraFrm').action='HelpDeskReqDurationReport_reset.action';
		document.getElementById('paraFrm').submit();
	}
	//Added by Tinshuk Banafar ...End....
</script>
