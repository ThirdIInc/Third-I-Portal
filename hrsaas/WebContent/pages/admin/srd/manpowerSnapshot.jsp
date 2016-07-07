<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ManpowerSnapshot" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower Snapshot</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" class="token"
						onclick="return callReport();" value="Generate Snapshot"  />


					<input type='button' class="reset" onclick="return callReset();"
						theme="simple" value="Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="divName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9divaction.action');"><s:hidden name='divCode'/></td>
				</tr>
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label name="periodFrm" id="periodFrm"
								ondblclick="callShowDiv(this);"><%=label.get("periodFrm")%></label>  <font color="red">*</font> :</td>
					<td colspan="1" width="25%"><s:textfield theme="simple"
						onkeypress="return numbersWithHiphen();" name="fromDate"
						maxlength="10" size="25" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="20%" class="formtext"><label name="periodTo" id="periodTo"
								ondblclick="callShowDiv(this);"><%=label.get("periodTo")%></label>  <font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						onkeypress="return numbersWithHiphen();" name="toDate"
						maxlength="10" size="25" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label name="gen.ManSnap.for" id="gen.ManSnap.for"
								ondblclick="callShowDiv(this);"><%=label.get("gen.ManSnap.for")%></label> :</td>
					<td colspan="1" width="25%"><s:select theme="simple" name="snapShotFor" cssStyle="width:155"
							list="#{'':'--Select--','DP':'Department','BR':'Branch','ET':'Employee Type','CC':'Cost Center'}" onchange="checkGroupBy();clearFilter();" /></td>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="group.by" id="group.by"
								ondblclick="callShowDiv(this);"><%=label.get("group.by")%></label> <font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:select theme="simple" name="snapShotGroupBy" cssStyle="width:155"
							list="#{'':'--Select--','DP':'Department','BR':'Branch','ET':'Employee Type','CC':'Cost Center'}" onchange="return checkGroupBy();"  /></td>
				</tr>
				
				<tr id='DPTr' style="display: none">
					<td colspan="1" width="20%" class="formtext" height="22"><label name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="deptName" maxlength="10" size="25" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9deptAction.action');"><s:hidden name='deptCode'/></td>
				</tr>
				
				<tr id='BRTr' style="display: none">
					<td colspan="1" width="20%" class="formtext" height="22"><label name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="branchName" readonly="true" maxlength="10" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9branchAction.action');"><s:hidden name='branchCode'/></td>
				</tr>
				
				<tr id='ETTr' style="display: none" >
					<td colspan="1" width="20%" class="formtext" height="22"><label name="employee.type" id="employee.type"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="eTypeName" readonly="true" maxlength="10" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9eTypeAction.action');"><s:hidden name='eTypeCode'/></td>
				</tr>
				
				<tr id='CCTr' style="display: none">
					<td colspan="1" width="20%" class="formtext" height="22"><label name="cost.center" id="cost.center"
								ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="costCenterName" readonly="true" maxlength="10" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9costCenterAction.action');"><s:hidden name='costCenterCode'/></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:155"
							list="#{'':'--Select--','Pdf':'Pdf','Xls':'Xls'}" /></td>
					<td colspan="1" width="20%"></td>
					<td colspan="1" width="30%"></td>
				</tr>
				

				 
			</table>
			

	</table>

</s:form>
<script type="text/javascript">
function checkGroupBy(){
	var snapShotFor=document.getElementById('paraFrm_snapShotFor').value;
	var groupBy=document.getElementById('paraFrm_snapShotGroupBy').value;
	var snapShotForLable=document.getElementById('gen.ManSnap.for').innerHTML;
	var groupByLable=document.getElementById('group.by').innerHTML;
	if(snapShotFor!=''){
		if(snapShotFor==groupBy){
			alert('\"'+snapShotForLable+'\" must be different from \"'+groupByLable+'\".');
			document.getElementById('paraFrm_snapShotGroupBy').value='';
			return false;
		}
	}
	document.getElementById('DPTr').style.display='none';
	document.getElementById('BRTr').style.display='none';
	document.getElementById('ETTr').style.display='none';
	document.getElementById('CCTr').style.display='none';
		
	if(snapShotFor!=''){
		document.getElementById(snapShotFor+'Tr').style.display='';
		}
	return true;
}
function validateSnapshotFor(){
	var snapShotFor=document.getElementById('paraFrm_snapShotFor').value;
	
	if(snapShotFor=='DP'){
		if(document.getElementById('paraFrm_deptName').value==''){
			alert('Please select '+document.getElementById('department').innerHTML);
			document.getElementById('paraFrm_deptName').focus();
			return false;
		}
	}else if(snapShotFor=='BR'){
		if(document.getElementById('paraFrm_branchName').value==''){
			alert('Please select '+document.getElementById('branch').innerHTML);
			document.getElementById('paraFrm_branchName').focus();
			return false;
		}
	}
	else if(snapShotFor=='ET'){
		if(document.getElementById('paraFrm_eTypeName').value==''){
			alert('Please select '+document.getElementById('employee.type').innerHTML);
			document.getElementById('paraFrm_eTypeName').focus();
			return false;
		}
	}
	else if(snapShotFor=='CC'){
		if(document.getElementById('paraFrm_costCenterName').value==''){
			alert('Please select '+document.getElementById('cost.center').innerHTML);
			document.getElementById('paraFrm_costCenterName').focus();
			return false;
		}
	}
	return true;
}
function callReport(){
	try
	
	{
	var fields=["paraFrm_divName","paraFrm_fromDate","paraFrm_toDate","paraFrm_snapShotGroupBy"];
	var lables=["division","periodFrm","periodTo","group.by"];
	var flags=["select","select","select","select"];
	
	var frmDate = document.getElementById("paraFrm_fromDate").value;
  	var toDate  = document.getElementById("paraFrm_toDate").value;
   	
   		if(!validateBlank(fields, lables,flags))
		{
  			return false;
  		}
		if(!validateDate('paraFrm_fromDate', 'periodFrm'))
		{
  			return false;
  		}	
  	
		if(!validateDate('paraFrm_toDate', 'periodTo'))
		{
  			return false;
  		}
	 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_fromDate', 'periodFrm', 'periodTo'))
			return false;
	}
	if(!checkGroupBy()) return false;
	if(!validateSnapshotFor()) return false;
	
	if(document.getElementById('paraFrm_reportType').value==''){
		alert('Please select '+document.getElementById('report.type').innerHTML);
		document.getElementById('paraFrm_reportType').focus();
		return false;
	}
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='ManpowerSnapshot_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	catch(e)
	{
		alert(e);
	}
	}
	
	function  callReset(){
		document.getElementById('paraFrm_fromDate').value='';
		document.getElementById('paraFrm_toDate').value='';
		document.getElementById('paraFrm_divName').value='';
		document.getElementById('paraFrm_snapShotFor').value='';
		document.getElementById('paraFrm_snapShotGroupBy').value='';
		document.getElementById('paraFrm_deptName').value='';
		document.getElementById('paraFrm_branchName').value='';
		document.getElementById('paraFrm_eTypeName').value='';
		document.getElementById('paraFrm_costCenterName').value='';
		
		document.getElementById('paraFrm_deptCode').value='';
		document.getElementById('paraFrm_divCode').value='';
		document.getElementById('paraFrm_branchCode').value='';
		document.getElementById('paraFrm_eTypeCode').value='';
		document.getElementById('paraFrm_costCenterCode').value='';
		document.getElementById('paraFrm_reportType').value='';
		
		document.getElementById('DPTr').style.display='none';
		document.getElementById('BRTr').style.display='none';
		document.getElementById('ETTr').style.display='none';
		document.getElementById('CCTr').style.display='none';
	}

function clearFilter(){
		document.getElementById('paraFrm_deptName').value='';
		document.getElementById('paraFrm_branchName').value='';
		document.getElementById('paraFrm_eTypeName').value='';
		document.getElementById('paraFrm_costCenterName').value='';
		
		document.getElementById('paraFrm_deptCode').value='';
		document.getElementById('paraFrm_branchCode').value='';
		document.getElementById('paraFrm_eTypeCode').value='';
		document.getElementById('paraFrm_costCenterCode').value='';
}

</script>


