<!-- @author: REEBA JOSEPH 01 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PFTrustMasterReport" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Trust Master Report</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<tr>
							<td width="78%">
							<input type="button" class="token" theme="simple"
								value="  Report" onclick="return callReport()" />
							<input type="button" class="reset"
								theme="simple" value="  Reset" onclick="return callReset()" />
							</td>
							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td width="20%"><label class="set" id="dateFrm" name="dateFrm"
						ondblclick="callShowDiv(this);"><%=label.get("dateFrm")%></label>
					: <font color="red">*</font></td>
					<td width="30%"><s:select theme="simple" name="fromMonth"
						cssStyle="width:80" disabled="true"
						list="#{'4':'April','5':'May'}" /> <s:textfield theme="simple"
						name="fromYear" size="8" maxlength="4"
						onkeypress="return numbersOnly();" onkeyup="setToYear();"
						onblur="setToYear();" /></td>
					<td width="20%"><label class="set" id="dateTo" name="dateTo"
						ondblclick="callShowDiv(this);"><%=label.get("dateTo")%></label> :</td>
					<td width="30%" nowrap="nowrap"><s:select theme="simple"
						name="toMonth" cssStyle="width:80" onchange="setToYear();"
						list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					<s:textfield theme="simple" name="toYear" readonly="true"
						maxlength="4" size="8" /></td>
				</tr>
				
				<tr>
						<td width="20%"><label
							name="emp.div" class="set" id="emp.div"
							ondblclick="callShowDiv(this);"><%=label.get("emp.div")%></label> :<font color="red">*</font></td>
						<td width="30%">
							<s:textfield name="sDivName" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" 
							onclick="javascript:callsF9(500,325,'PFTrustMasterReport_f9division.action');">
							<s:hidden name="sDivId" />
						</td>
						<td width="20%"><label
							name="emp.brch" class="set" id="emp.brch"
							ondblclick="callShowDiv(this);"><%=label.get("emp.brch")%></label> :</td>
						<td width="30%"> 
							<s:textfield name="sBrchName" readonly="true" size="25"/> <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" 
							onclick="javascript:callsF9(500,325,'PFTrustMasterReport_f9branch.action');">
							<s:hidden name="sBrchId" />
						</td>
					</tr>
					<tr>
						<td width="20%"><label
							name="emp.dept" class="set" id="emp.dept"
							ondblclick="callShowDiv(this);"><%=label.get("emp.dept")%></label> :</td>
						<td width="30%">
							<s:textfield name="sDeptName" readonly="true" size="25" />
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'PFTrustMasterReport_f9department.action');">
							<s:hidden name="sDeptId" />
						</td>
						<td width="20%"><label name="report.type" class="set" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>: <font color="red">*</font></td>
						<td width="30%">
							<s:select name="reportType" list="#{'Pdf':'Pdf','Xls':'Xls'}"  headerKey="" headerValue="-- Select --" /></select>
						</td>
					</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" cellpadding="2" cellspacing="1">
					<tr>
						<td width="78%">
						<input type="button" class="token" theme="simple"
								value="  Report" onclick="return callReport()" />
						<input type="button" class="reset"
								theme="simple" value="  Reset" onclick="return callReset()" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
callOnload();
function callOnload(){
	if(document.getElementById("paraFrm_fromYear").value==""){
		setCurrentYear("paraFrm_fromYear");
	}
}

function setToYear(){
	var toMonth = document.getElementById('paraFrm_toMonth').value;
	var fromYear = document.getElementById('paraFrm_fromYear').value;
	if(fromYear !=""){
		if(eval(toMonth)<4){
			document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
		}else if(eval(toMonth)>=4){
			document.getElementById('paraFrm_toYear').value=eval(fromYear);
		}else{
			document.getElementById('paraFrm_toYear').value='';
		}
	}else{
		document.getElementById('paraFrm_toYear').value='';
	}
}
function callReport(){
	if(!validateReport()){
		return false;
	}else{
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='PFTrustMasterReport_report.action';
		document.getElementById('paraFrm').submit();
	}
}

function callReset(){
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='PFTrustMasterReport_reset.action';
	document.getElementById('paraFrm').submit();
}
function validateReport(){
	var fromDt = document.getElementById('paraFrm_fromYear').value;
	var div = document.getElementById('paraFrm_sDivId').value;
	var type = document.getElementById('paraFrm_reportType').value;
	
	if(fromDt==""){
		alert("Please enter from year.");
		return false;
	}
	if(div==""){
		alert("Please select division");
		return false;
	}
	if(type==""){
		alert("Please select report type");
		return false;
	}
	return true;
}

</script>
