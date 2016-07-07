<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="Form10Report" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Form 10</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3" align="right" width="100%">
			<table width="100%">
				<tr>
					<td width="22%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="Reset"> Reset </a>&nbsp;&nbsp;</td>
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
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable" >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4"><b><label class="set"
								id="selectFinancialYear" name="selectFinancialYear"
								ondblclick="callShowDiv(this);"> <%=label.get("selectFinancialYear")%></label></b>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="months"
								name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label>:
							<font color="red">*</font></td>
							<td colspan="1" width="30%"><s:select name="month"
								list="#{'':'------------Select------------','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"
								theme="simple" /></td>

							<td colspan="1" width="20%"><label class="set" id="years"
								name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label>:<font
								color="red">*</font></td>
							<td><s:textfield label="%{getText('year')}" name="year"
								size="25" maxlength="4" onkeypress="return numbersonly(this)" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4"><b><label class="set"
								id="selectFilterOption" name="selectFilterOption"
								ondblclick="callShowDiv(this);"> <%=label.get("selectFilterOption")%></label></b>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
							<font color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield name="division"
								size="25" maxlength="15" readonly="true" /> <s:hidden
								name="divCode" /> <img src="../pages/images/search2.gif"
								height="16" align="middle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'Form10Report_f9actionDiv.action');"></td>
						</tr>
					</table>
				</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
									<td width="100%"><%@ include
										file="/pages/common/reportButtonPanelBottom.jsp"%>
									</td>
								</tr>
							</table>
					</td>
				</tr>
	</table>
	</td>
		</tr>
	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='Form10Report_form10Report.action' />
</s:form>

<script>
function callReport(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}
}

function resetFun(){
	document.getElementById('paraFrm').action='Form10Report_reset.action';
	document.getElementById('paraFrm').submit();
} 
  
function validateFields() {
	var month=document.getElementById('paraFrm_month').value;
	var year=document.getElementById('paraFrm_year').value;	
	var division=document.getElementById('paraFrm_division').value;
	
	if(month==""){
	  alert("Please enter "+document.getElementById('months').innerHTML.toLowerCase());	
	  return false;
	}
	if(year==""){
	  alert("Please enter "+document.getElementById('years').innerHTML.toLowerCase());	
	  return false;
	}	
	if(division==""){
	  alert("Please enter "+document.getElementById('division').innerHTML.toLowerCase());	
	  return false;
	}	
	return true;	
}

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='Form10Report_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_year").value;
	if(yr==''){
		document.getElementById("paraFrm_year").value =year;
	}
}

getYear();   
  	
</script>