<!-- @author: REEBA JOSEPH 01 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PFForm98" validate="true" id="paraFrm" validate="true"
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
					<td width="93%" class="txt"><strong class="text_head">PF
					Form 98</strong></td>
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
							<td width="78%"><input type="button" class="reset"
								theme="simple" value="  Reset" onclick="return callReset()" />
							<input type="button" class="token" theme="simple"
								value="  Report" onclick="return callReport()" /></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="20%"><label class="set" name="employee"
								id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							: <font color="red">*</font></td>
							<td width="10%" nowrap="nowrap"><s:textfield name="empToken"
								size="13" readonly="true"></s:textfield></td>
							<td width="44%" nowrap="nowrap" colspan="2"><s:hidden
								name="empCode"></s:hidden> <s:textfield name="empName" size="70"
								readonly="true"></s:textfield></td>
							<td width="26%"><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'PFForm98_f9Employee.action'); "></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="20%"><label class="set" id="dateFrm" name="dateFrm"
						ondblclick="callShowDiv(this);"><%=label.get("dateFrm")%></label>
					: <font color="red">*</font></td>
					<td width="30%"><s:select theme="simple" name="fromMonth"
						cssStyle="width:80" disabled="true"
						list="#{'04':'April','05':'May'}" /> <s:textfield theme="simple"
						name="fromYear" size="9" maxlength="4"
						onkeypress="return numbersOnly();" onkeyup="setToYear();"
						onblur="setToYear();" /></td>
					<td></td>
					<td width="20%"><label class="set" id="dateTo" name="dateTo"
						ondblclick="callShowDiv(this);"><%=label.get("dateTo")%></label> :
					<font color="red">*</font></td>
					<td width="30%" nowrap="nowrap"><s:select theme="simple"
						name="toMonth" cssStyle="width:80" onchange="setToYear();"
						list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
					<s:textfield theme="simple" name="toYear" readonly="true"
						maxlength="4" size="9" /></td>
				</tr>
			</table>
			</td>
		</tr>
	<s:hidden name="branchName"/><s:hidden name="divName"/><s:hidden name="deptName"/>
	<s:hidden name="desgName"/><s:hidden name="empPfNo"/><s:hidden name="empStatus"/>
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

function callReset(){
	document.getElementById('paraFrm').target="self";
	document.getElementById('paraFrm').action='PFForm98_reset.action';
	document.getElementById('paraFrm').submit();
}

		var fields = ["paraFrm_empCode","paraFrm_fromYear","paraFrm_toMonth"];
		var labels = ["employee","dateFrm","dateTo"];
		var types  = ["select","enter","select"];
function callReport(){
	if(!validateBlank(fields,labels, types)){
		return false;
	}
	var fromYear=document.getElementById('paraFrm_fromYear').value;
	if(fromYear.length !=4 || eval(fromYear)==0){
		alert("Please enter valid "+document.getElementById('dateFrm').innerHTML);
		document.getElementById('paraFrm_fromYear').focus();
		return false;
	}
	document.getElementById('paraFrm').action='PFForm98_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}
</script>
