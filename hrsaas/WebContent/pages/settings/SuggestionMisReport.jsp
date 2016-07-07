<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="SuggestionMisReport" method="post" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Suggestion
					MIS Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="70%" colspan="3"><s:if test="%{viewFlag}">
										<input type="button" class="token"
											onclick="return callReportMis();" value="Show Report" />
									</s:if> <s:submit cssClass="reset" action="SuggestionMisReport_reset"
										theme="simple" value="    Reset"  /></td>
									<td width="30%">
									<div align="right"><span class="style2"></span></div>
									</td>
								</tr>
							</table>
							<label></label></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
						</tr>
						<tr>
							<td colspan="2">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="2">
										<tr>
											<td colspan="4" class="formhead"><strong
												class="forminnerhead"><label class="set"
												name="select" id="select" ondblclick="callShowDiv(this);"><%=label.get("select")%></label></strong></td>
										</tr>


										<tr>
											<td width="15%" colspan="1" class="formtext"><label
												class="set" name="employee" id="employee"
												ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
											:</td>
											<s:hidden name="empCode" value="%{empCode}" />
											<td width="80%" colspan="3"><s:textfield
												name="empToken" size="10" value="%{empToken}" theme="simple"
												readonly="true" /><s:textfield name="ename" size="50"
												value="%{ename}" theme="simple" readonly="true" /> <img
												src="../pages/images/recruitment/search2.gif" height="18"
												align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'SuggestionMisReport_f9actionEname.action');">
											</td>

										</tr>
										<tr>
											<td width="15%" colspan="1" class="formtext"><label
												class="set" name="from.date" id="from.date"
												ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:
											
											<td colspan="1"><s:textfield name="frmDate" size="25"
												onkeypress="return numbersWithHiphen();" theme="simple"
												maxlength="10" /> <s:a
												href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
												<img src="../pages/images/recruitment/Date.gif"
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
											<td colspan="1" width="10%"><label class="set"
												name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<s:hidden
												name="today" /></td>
											<td colspan="1"><s:textfield name="toDate" size="25"
												onkeypress="return numbersWithHiphen();" theme="simple"
												maxlength="10" /> <s:a
												href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
												<img src="../pages/images/recruitment/Date.gif"
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
										</tr>
										<tr>
											<td width="15%" colspan="1" class="formtext"><label
												class="set" name="sts" id="sts"
												ondblclick="callShowDiv(this);"><%=label.get("sts")%></label>:</td>
											<td colspan="1"><s:select theme="simple" name="status"
												cssStyle="width:130"
												list="#{'':'Select','P':'Pending','A':'Approved','R':'Rejected'}" />
											</td>
											<td colspan="1" width="10%"><label class="set"
												name="report.type" id="report.type"
												ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
											<td colspan="1"><s:select name="rptType"
												list="#{ 'Pdf':'Pdf','Xls':'Xls'}" theme="simple" /></td>

										</tr>
									</table>
									</td>
								</tr>


							</table>
							</td>
						</tr>

					</table>



					<label></label></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

function callReportMis(){
  	var emp=document.getElementById("paraFrm_ename").value;
  	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	var status=document.getElementById("paraFrm_status").value;
  	var today=document.getElementById("paraFrm_today").value;
  	
  	strDate1 = today.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = frmDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
  	/*
  	if((emp=="")&& (frmDate=="")&&(status=="")&& (emp=="")&& (toDate=="") ){
  	alert("Please select any/all search criteria to generate MIS report !");
  	return false;
  	}else */
  	if(frmDate!=""){
  	if(!validateDate("paraFrm_frmDate","From Date")){
		return false;
	}
  	}else if(toDate!=""){
  	if(!validateDate("paraFrm_toDate","To Date")){
		return false;
	}
  	}
  	
  	 if ((frmDate!="") && (toDate!="")) {
  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'From Date', 'To Date'))
	{
	return false;
	}
	
	}
	
  	
  	
  	else if((frmDate!="") && (toDate=="")){
	if(endtime > starttime) 
	{ 
		alert("From Date should be less than Today's Date !");
		document.getElementById("paraFrm_frmDate").focus();
		return false;
	}
	}
	
  	
  	callReport('SuggestionMisReport_report.action');
   	
	
}
  	function autoDate () {
var tDay = new Date();
var tMonth = tDay.getMonth()+1;
var tDate = tDay.getDate();
if ( tMonth < 10) tMonth = "0"+tMonth;
if ( tDate < 10) tDate = "0"+tDate;

document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();

}
autoDate();
</script>
