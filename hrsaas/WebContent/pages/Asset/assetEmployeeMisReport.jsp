<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AssetEmployeeReport" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Assignment MIS Report </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%"><s:if test="%{viewFlag}">
						<input type="button" class="token"
							onclick="return callReportMis();" value=" Show Report" />
					</s:if> <s:submit cssClass="reset"
						action="AssetEmployeeReport_reset" theme="simple" value=" Reset" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<s:hidden
						name="empCode" value="%{empCode}" /></td>
					<td width="75%" colspan="3"><s:textfield name="empToken"
						size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
						name="ename" size="50" value="%{ename}" theme="simple"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'AssetEmployeeReport_f9actionEname.action');">
					</td>
				</tr>
				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetAsgnFrmDate" name="assetAsgnFrmDate" ondblclick="callShowDiv(this);"><%=label.get("assetAsgnFrmDate")%></label>:</td>
					<td width="25%"><s:textfield name="frmDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td width="25%" class="formtext"><label  class = "set"  id="assetAsgnToDate" name="assetAsgnToDate" ondblclick="callShowDiv(this);"><%=label.get("assetAsgnToDate")%></label>:</td>
					<td width="25%"><s:textfield name="toDate" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetRetFlag" name="assetRetFlag" ondblclick="callShowDiv(this);"><%=label.get("assetRetFlag")%></label>:</td>
					<td width="25%"><s:select theme="simple" name="status"
						cssStyle="width:130"
						list="#{'':'Select','Y':'Available','N':'Assigned','L':'Lost','D':'Damaged'}" /></td>

				</tr>

				<tr>
					<td width="25%" class="formtext"><label  class = "set"  id="assetRepType" name="assetRepType" ondblclick="callShowDiv(this);"><%=label.get("assetRepType")%></label>:</td>
					<td width="25%"><s:select name="type" headerKey="Pdf"
						headerValue="Pdf" theme="simple"
						list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>

				</tr>
			</table>
			<s:hidden name="today" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

function callReportMis(){
  /*	var emp=document.getElementById("paraFrm_ename").value;
  	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	var status=document.getElementById("paraFrm_status").value;
  	var today=document.getElementById("paraFrm_today").value;
  	
  	strDate1 = today.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = frmDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
  	
  	if((emp=="")&& (frmDate=="")&&(status=="")&& (emp=="") && (toDate=="") ){
  	alert("Please select any/all search criteria to generate MIS report !");
  	return false;
  	}else if(frmDate!=""){
  	if(!validateDate("paraFrm_frmDate","Assigned From Date")){
		return false;
	}
  	}else if(toDate!=""){
  	if(!validateDate("paraFrm_toDate","To Date")){
		return false;
	}
  	}
  	
  	 if ((frmDate!="") && (toDate!="")) {
  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'Assigned From Date', 'Assigned To Date'))
	{
	
	return false;
	}
	
	}
	*/
	
	//function callReportMis(){
  	callReport('AssetEmployeeReport_report.action');
	}
	
	
	
  	
  	
  	/*else
	if(endtime > starttime) 
	{ 
		alert("Assigned From Date should be less than Today's Date !");
		document.getElementById("paraFrm_frmDate").focus();
		return false;
	}*/
	
  	
   	
	
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
