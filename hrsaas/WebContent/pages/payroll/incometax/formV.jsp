<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="FormV" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form V</strong></td>
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
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<!--
					<td><input type="button" class="token"
						onclick="return validation();" value="  Report " />&nbsp;<s:submit
						cssClass="reset" action="FormV_clear" theme="simple"
						value="Reset"  /></td>		
					-->
					<td >
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;
								</td>
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
								style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
						<iframe id="reportFrame" frameborder="0" onload=alertsize();
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
								marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
								width="100%" height="200"></iframe> </div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectPeriod" name="selectPeriod" ondblclick="callShowDiv(this);">
								<%=label.get("selectPeriod")%></label></b>
							</td>
						</tr>
						<tr>
							<td width="8%" colspan="1"><label  class = "set"  id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label><font color="red">*</font> :</td>
							<td width="20%"><s:select name="finmonth"
								list="#{'':'------------Select------------','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"
								theme="simple" /></td>

							<td width="8%" colspan="1"><label  class = "set"  id="years" name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label><font color="red">*</font>:</td>
							<td width="20%"><s:textfield label="%{getText('year')}"
								name="year" size="25" maxlength="4"
								onkeypress="return numbersOnly();" /></td>
						</tr>
						</table>
						</td>
						</tr>
						<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">			
						
					
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectFilterOption" name="selectFilterOption" ondblclick="callShowDiv(this);">
								<%=label.get("selectFilterOption")%></label></b>
							</td>
						</tr>
						<tr>
							<td width="8%" colspan="1"><label  class = "set"  id="division1" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td width="20%" colspan="3" ><s:textfield name="division" size="25"
								maxlength="15" readonly="true" /> <s:hidden name="divCode" />
							<img src="../pages/images/search2.gif" height="16" align="middle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'FormV_f9actionDiv.action');"></td>
						</tr>
						<tr>
							<td width="8%" colspan="1"><label  class = "set"  id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font>:</td>
							<td colspan="3" width="20%"  >
								<s:textfield size="25" maxlength="15" theme="simple" readonly="true" name="branch" /> 
								<s:hidden name="branchCode" />
								<img src="../pages/images/search2.gif" height="16" align="absmiddle" class="iconImage"
									width="16" theme="simple"
									onclick="javascript:callsF9(500,325,'FormV_f9actionBranch.action');">
							</td>
						</tr>
					</table>
					</td>
					</tr>
					<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">	

						<tr>
							<td width="8%" class="formtext"><label  class = "set"  id="payment.date" name="payment.date" ondblclick="callShowDiv(this);"><%=label.get("payment.date")%></label> <font
								color="red">*</font>:</td>
							<td width="20%"><s:textfield name="date" size="12"
								maxlength="10" onkeypress="return numbersWithHiphen();" /> <s:a
								href="javascript:NewCal('paraFrm_date','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>

							<td width="8%" colspan="1"><label  class = "set"  id="challan.no" name="challan.no" ondblclick="callShowDiv(this);"><%=label.get("challan.no")%></label><font color="red">*</font>:</td>
							<td width="20%"><s:textfield label="%{getText('challan')}"
								name="challan" size="25" onkeypress="return numbersOnly();" /></td>
						</tr>
						</table>
						</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	id='topButtonTable'>
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
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='FormV_report.action' />
</s:form>
<script>
function callReport(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}	
} 

function resetFun(){
	document.getElementById('paraFrm').action='FormV_reset.action';
	document.getElementById('paraFrm').submit();
}

function mailReportFun(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='FormV_mailReport.action';
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

function validation() {
		var month=document.getElementById('paraFrm_finmonth').value;
		var year=document.getElementById('paraFrm_year').value;
		var division=document.getElementById('paraFrm_division').value;
		var branch=document.getElementById('paraFrm_branch').value;
		var date=document.getElementById('paraFrm_date').value;
		var challan=document.getElementById('paraFrm_challan').value;
		if (month==""){
		alert("Please select "+document.getElementById('months').innerHTML.toLowerCase());
			return false;
		}
		if(year==""){
			alert("Please enter "+document.getElementById('years').innerHTML.toLowerCase());
			return false;
		}
		if (division==""){
			alert("Please select "+document.getElementById('division1').innerHTML.toLowerCase());
			return false;
		}
		if (branch==""){
			alert("Please select "+document.getElementById('branch1').innerHTML.toLowerCase());
			return false;
		}
		if (date==""){
			alert("Please select "+document.getElementById('payment.date').innerHTML.toLowerCase());
			return false;
		}
		if (challan==""){
			alert("Please select "+document.getElementById('challan.no').innerHTML.toLowerCase());
			return false;
		}
		
			/*document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	*/
		//callReport('FormV_report.action');		
		return true;				
	}
</script>