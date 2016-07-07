
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelApplicationReport" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='TravelApplicationReport_generateReport.action'/>
<s:hidden name="report" />
	<!-- Main Table Starts -->
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Report for Travel Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->

		<tr>
			<td><!-- DIV FIRST BEGINS - FILTERS -->
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="7" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>
				<!-- Initiator -->
				<tr>
					<td width="20%"><label class="set" name="trainitor"
						id="trainitor" ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>:
					</td>
					<td width="30%" colspan="2"><s:hidden name="initiatorId" /><s:textfield
						name="initiatorToken" theme="simple" id="paraFrm_initiatorToken"
						size="10" readonly="true" /> <s:textfield name="initiatorName"
						id="paraFrm_initiatorName" theme="simple" size="25"
						readonly="true" />
					<img height="16" width="16" align="absmiddle"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Initiator.action');"
						class="iconImage" src="../pages/images/search2.gif" id="ctrlHide"></td>
					<td width="23%" colspan="1"></td>
				
				</tr>
				<!-- Approver -->
				<tr>
					<td width="20%"><label class="set" name="approver"
						id="approver" ondblclick="callShowDiv(this);"><%=label.get("approver")%></label>:
					</td>
					<td width="30%" colspan="2"><s:hidden name="approverId" /><s:textfield
						name="approverToken" theme="simple" id="paraFrm_approverToken"
						size="10" readonly="true" /> <s:textfield name="approverName"
						id="paraFrm_approverName" theme="simple" size="25" readonly="true" />
					<img height="16" width="16" align="absmiddle"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Approver.action');"
						class="iconImage" src="../pages/images/search2.gif" id="ctrlHide"></td>
					<td width="23%" colspan="1"></td>
					
				</tr>

				<!-- Division & Departmnet -->
				<tr>
					<td width="20%" colspan="1"><label class="set" name="division"
						id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="divisionId" /><s:textfield
						size="25" name="divisionName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Division.action');"></td>
					<td width="20%" colspan="1"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="23%" colspan="1"><s:hidden name="departmentCode" /><s:textfield
						size="25" name="departmentName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Department.action');"></td>
					
				</tr>

				<!-- branch & designation -->
				<tr>
					<td width="20%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="branchCode" /><s:textfield
						size="25" name="branchName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Branch.action');"></td>
					<td width="20%" colspan="1"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="23%" colspan="1"><s:hidden name="designationCode" /><s:textfield
						size="25" name="designationName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Designation.action');"></td>
					

				</tr>
				<!-- Purpose & Travel Type -->

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="Trapurpose" id="Trapurpose" ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
					:</td>
					<td width="30%" colspan="1"><s:hidden name="purposeId" /> <s:textfield
						name="purposeName" theme="simple" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Purpose.action');"></td>
					<td width="20%" colspan="1"><label class="set"
						name="TravelType" id="TravelType" ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label>:</td>
					<td width="23%" colspan="1"><s:hidden name="travelTypeId" />
					<s:textfield name="travelTypeName" theme="simple" size="25"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9TravelType.action');"></td>
				
				</tr>

				<!-- Travel Customer & Project -->

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="travelCust" id="travelCust" ondblclick="callShowDiv(this);"><%=label.get("travelCustomer")%></label>
					:</td>
					<td width="30%" colspan="1"><s:hidden name="customerId" /> <s:textfield
						name="customerName" theme="simple" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Customer.action');"></td>
					<td width="20%" colspan="1"><label class="set"
						name="travelProj" id="travelProj" ondblclick="callShowDiv(this);"><%=label.get("travelProject")%></label>
					:</td>
					<td width="23%" colspan="1"><s:hidden name="projectId" /> <s:textfield
						name="projectName" theme="simple" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9Project.action');"></td>
					
				</tr>
				<!-- start & end date -->
				<tr>
					<td width="20%"><label class="set" name="Trastdate"
						id="Trastdate" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
					:</td>
					<td width="30%"><s:textfield name="startDate" theme="simple"
						size="25" maxLength="10" onkeypress="return numbersWithHiphen();" /><s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a></td>
					<td width="20%"><label class="set" name="Traenddate"
						id="Traenddate" ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label>:</td>
					<td width="25%"><s:textfield name="endDate" theme="simple"
						size="25" maxLength="10" onkeypress="return numbersWithHiphen();" />
						<s:a href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a></td>
					
				</tr>

 
				<tr>
					<td width="20%" colspan="1"><label class="set" name="trvl.sts"
						id="trvl.sts" ondblclick="callShowDiv(this);"><%=label.get("trvl.sts")%></label>
					:</td>
					<td width="30%" height="22" align="left"><s:select
						theme="simple" name="applicationStatus"
						value="%{applicationStatus}" 
						list="#{'':'--Select--','P':'Pending','A':'Approved','R':'Rejected','B':'Sent Back',
						'C':'Booking Complete','F':'Revoked'}"
						onchange="" /></td>

					
					<td width="20%" colspan="1"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="gradeName" readonly="true" /> <s:hidden name="gradeId" /><img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'TravelApplicationReport_f9gradeName.action');" />
					</td>

				</tr>
				<tr>

					<td height="20" class="formtext"><label name="zone" id="zone"
						ondblclick="callShowDiv(this);"><%=label.get("zone")%></label> :</td>
					<td width="30%" height="22"><s:select name="applicationBean.zone" list="map"
						   headerKey="" headerValue="--Select--"/></td>
					<td width="20%"  ><label class="set"
						id="currency.type" name="currency.type" ondblclick="callShowDiv(this);"><%=label.get("currency.type")%></label>:<font
						color="red"></font></td> 
					<td width="25%" colspan="1" >
						<s:select name="currencyType"
							 list="%{currencyHashmap}" 
							cssStyle="width:250" />
					</td>
				</tr>
			
				<tr>
					<td width="20%"></td>
					<td width="23%" class="formtext" colspan="1"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->
		<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>
		<!-- BUTTON PANEL ENDS -->
	</table>
	<!-- Main Table Ends -->
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>

	function generateReport(){
	if(!validateReportType()){
		return false;
	}else{
		document.getElementById('paraFrm').target = "_blank";
	 	document.getElementById('paraFrm').action = 'TravelApplicationReport_generateReport.action';
		document.getElementById('paraFrm').submit();
		}

	}
	
	function callReset() {
		document.getElementById('paraFrm_initiatorToken').value="";
  	 	document.getElementById('paraFrm_initiatorName').value="";
  	 	document.getElementById('paraFrm_initiatorId').value="";
  	 	document.getElementById('paraFrm_approverToken').value="";
  	 	document.getElementById('paraFrm_approverId').value="";
  	 	document.getElementById('paraFrm_approverName').value="";
  	 	document.getElementById('paraFrm_divisionId').value="";
  	 	document.getElementById('paraFrm_divisionName').value="";
  	 	document.getElementById('paraFrm_branchCode').value="";
  	 	document.getElementById('paraFrm_branchName').value="";
  	 	document.getElementById('paraFrm_departmentCode').value="";
  	 	document.getElementById('paraFrm_departmentName').value="";		
  	 	document.getElementById('paraFrm_designationCode').value="";
  	 	document.getElementById('paraFrm_designationName').value="";			
		//document.getElementById('paraFrm_blockedStatus').value="";
		document.getElementById('paraFrm_travelTypeId').value="";
		document.getElementById('paraFrm_travelTypeName').value="";
		document.getElementById('paraFrm_customerId').value="";
		document.getElementById('paraFrm_customerName').value="";
		document.getElementById('paraFrm_projectId').value="";
		document.getElementById('paraFrm_projectName').value="";
		document.getElementById('paraFrm_startDate').value="";
		document.getElementById('paraFrm_endDate').value="";
		document.getElementById('paraFrm_gradeName').value="";
		document.getElementById('paraFrm_gradeId').value="";
		document.getElementById('paraFrm_purposeId').value="";
		document.getElementById('paraFrm_purposeName').value="";
		document.getElementById('paraFrm_applicationStatus').value="";
		document.getElementById('paraFrm_applicationBean.zone').value="";
  	}
	
	function validateReportType(){
		var reportFormat = document.getElementById("paraFrm_reportType").value;
		
		if(reportFormat == ""){
			alert("Please select a report type to generate report");
			return false;
		}
	return true;
	}
	
	function callReport(type){

	var fields1=["paraFrm_startDate"];
	var labels1=["Trastdate"];
	if(!validateDate(fields1,labels1)){
	return false;
	
	}
	var fields1=["paraFrm_endDate"];
	var labels1=["Traenddate"];
	if(!validateDate(fields1,labels1)){
	return false;
	
	}
	var datediff = checkDateForApplication('paraFrm_startDate','paraFrm_endDate','From date', 'To date');
	  	  	if(!datediff){
	  			return false;
	}
	
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}
function mailReportFun(type){
var fields1=["paraFrm_startDate"];
	var labels1=["Trastdate"];
	if(!validateDate(fields1,labels1)){
	return false;
	
	}
	var fields1=["paraFrm_endDate"];
	var labels1=["Traenddate"];
	if(!validateDate(fields1,labels1)){
	return false;
	
	}
	
	var datediff = checkDateForApplication('paraFrm_startDate','paraFrm_endDate','From Date', 'To Date');
	  	  	if(!datediff){
	  			return false;
	}
			
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='TravelApplicationReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	function checkDateForApplication(fromDate,todate,fromlabel,tolabel){
 		var fromDt =document.getElementById(fromDate).value ;
		var toDate = document.getElementById(todate).value ;
 		try{
 			var datediff = dateDifferenceChk(fromDt,toDate,fromDate,fromlabel,tolabel);
  	  		if(!datediff){
  				return false;
  			}
  		}catch(e){alert(e);}
 		return true;
 	}
 	
 	function dateDifferenceChk(fromDate, toDate, fieldName, fromLabName, toLabName){
		var strDate1 = fromDate.split("-");
		var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		var strDate2 = toDate.split("-"); 
		var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		
		if(endtime < starttime) { 
			alert(""+toLabName+" should be greater than or equal to "+fromLabName);
			document.getElementById(fieldName).focus();
			return false;
		}
		return true;
	}
</script>
