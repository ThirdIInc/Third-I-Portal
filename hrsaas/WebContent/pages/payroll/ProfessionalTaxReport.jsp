<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PTax" id="paraFrm" theme="simple">

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
					<td width="93%" class="txt"><strong class="text_head">Professional
					Tax Report</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
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
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg" id='reportBodyTable'>
					<tr>
						<td colspan="4">
							<table width="100%" border="0" class="formbg" >
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id=selectPeriod name="selectPeriod"
										ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
									</strong></td>
								</tr>
								<tr>
									<s:hidden name="tdsCode" />
									<td colspan="1" width="20%"><label class="set" id="month"
										name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="month" cssStyle="width:90"
										onchange="return getToYear();"
										list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
									<s:textfield name="year" maxlength="4"
										onkeypress="return numbersOnly();"
										onkeyup="return getToYear();" onblur="return getToYear();"
										theme="simple" size="3" /></td>
									<td colspan="1" width="20%"><label class="set"
										id="toMonth" name="toMonth" ondblclick="callShowDiv(this);"><%=label.get("toMonth")%></label>
									:</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="toMonth" cssStyle="width:90"
										onchange="return getToYear();"
										list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
									<s:textfield name="toYear" maxlength="4"
										onkeypress="return numbersOnly();" readonly="true"
										theme="simple" size="3" /></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id=selectReportFilter name="selectReportFilter"
										ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
									</strong></td>
								</tr>	
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="report.opttion" name="report.opttion"
										ondblclick="callShowDiv(this);"><%=label.get("report.opttion")%></label>:</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="reportOption" cssStyle="width:152"
										list="#{'O':'All','S':'Only Salary','A':'Only Arrears','se':'Only Settlement'}" />
									</td>
		
									<td colspan="1" width="20%"><label class="set" id="onhold"
										name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>
									:</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="onHold" cssStyle="width:152"
										list="#{'A':'All','N':'No','Y':'Yes'}" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="30%"><s:hidden name="divId" /><s:textfield
										name="divName" theme="simple" readonly="true" size="21" /><s:hidden
										name="divAdd" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PTax_f9div.action');">
									</td>
									<td colspan="1" width="20%"></td>
									<td colspan="1" width="30%"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id="reportOptions" name="reportOptions"
										ondblclick="callShowDiv(this);"><%=label.get("reportOptions")%></label>
									</strong></td>
								</tr>
								<tr>
									<td colspan="1" width="20%" valign="top"><label
										class="set" id="branchwise" name="branchwise"
										ondblclick="callShowDiv(this);"><%=label.get("branchwise")%></label> :</td>
									<td colspan="1" width="30%" valign="top"><s:checkbox
										name="branchCheck" id='branchCheck'
										onclick="callBrnCheck(this);" /></td>
									<td colspan="1" width="20%" id='brnTD' valign="top"><label
										class="set" id="branch" name="branch"
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="30%" id='brnTD1' valign="top"><s:hidden
										name="brnId" /><s:textarea name="brnName" theme="simple"
										readonly="true" value='--All--' cols="25" rows="2" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PTax_f9brn.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%" valign="top"><label
										class="set" id="state" name="state"
										ondblclick="callShowDiv(this);"><%=label.get("state")%></label>	:</td>
									<td colspan="1" width="30%" valign="top"><s:checkbox
										name="stateCheck" id='stateCheck'
										onclick="callStateCheck(this);" /></td>
									<td colspan="1" width="20%" id='stateTD' valign="top"><label
										class="set" id="state" name="state"
										ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
									:</td>
									<td colspan="1" width="30%" id='stateTD1' valign="top"><s:hidden
										name="stateId" /><s:textarea name="stateName" theme="simple"
										readonly="true" value='--All--' cols="25" rows="2" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PTax_f9state.action');">
									</td>
		
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="showIncome" name="showIncome"
										ondblclick="callShowDiv(this);"><%=label.get("showIncome")%></label>
									:</td>
									<td colspan="1" width="30%"><s:checkbox name="incomeCheck"
										id="incomeCheck" onclick="callCheck();" /></td>
									<td colspan="1" width="20%" id='slabTD'><label class="set"
										id="slabCheck" name="slabCheck"
										ondblclick="callShowDiv(this);"><%=label.get("slabCheck")%></label>
									:</td>
									<td colspan="1" width="30%" id='slabTD1'><s:checkbox
										name="slabCheck" id="slabCheck" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="showDOJ" name="showDOJ" ondblclick="callShowDiv(this);"><%=label.get("showDOJ")%></label>
									:</td>
									<td colspan="1" width="30%"><s:checkbox name="dojCheck"
										id="dojCheck" /></td>
									<td colspan="1" width="20%"><label class="set"
										id="cons.arrears" name="cons.arrears"
										ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label>
									:</td>
									<td colspan="1" width="30%"><input type="checkbox"
										name="checkFlag" id="checkFlag" onclick="callCheck();" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='PTax_report.action'/>
</s:form>
<script>
	callOnload();	
		
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="PTax_reset.action";
		document.getElementById('paraFrm').submit();
	}
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				callReportCommon(type);
			}
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='PTax_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	function validateFields(){
		var month = document.getElementById('paraFrm_month').value;
		var year = document.getElementById('paraFrm_year').value;
		var division = document.getElementById('paraFrm_divName').value;
				
		if(month==""){
			alert("Please enter from month");
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(year==""){
			alert("Please enter from year");
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(division==""){
			alert("Please select division");
			document.getElementById('paraFrm_divName').focus();
			return false;
		}
		return true;
	}
	
	function callCheck(){
		var checkDefault = document.getElementById('checkFlag').checked;
	
		if(checkDefault){
				document.getElementById('checkFlag').value="Y";
		
		}
	}
	
	function getToYear(){
		var fromMonth =document.getElementById('paraFrm_month').value;
		var fromYear =document.getElementById('paraFrm_year').value;
		var toMonth =document.getElementById('paraFrm_toMonth').value;
		if(!(fromMonth=="" || toMonth=="" || fromYear=="")){ 
		if(eval(fromMonth) <= eval(toMonth)){
			document.getElementById('paraFrm_toYear').value=fromYear;
		}else{
			document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
		}
		}else{
			document.getElementById('paraFrm_toYear').value="";
		}
		if(fromMonth==""|| toMonth=="" || fromMonth==toMonth){
			document.getElementById('slabTD').style.display='';
			document.getElementById('slabTD1').style.display='';
	}else{
		document.getElementById('slabTD').style.display='none';
		document.getElementById('slabTD1').style.display='none';
	}
	}
	
function call()
 {
		var fields=["paraFrm_month","paraFrm_year","paraFrm_divName"];
		var labels=["month","month","division"];
		var flags=["select","enter","select"];
		
		if(!validateBlank(fields,labels,flags)){
		return false;
		}
		
	 		document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='PTax_report.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 	

}

function callBrnCheck(obj){
	if(obj.checked){
		document.getElementById('brnTD').style.display='';
		document.getElementById('brnTD1').style.display='';
		document.getElementById('stateTD').style.display='none';
		document.getElementById('stateTD1').style.display='none';
		document.getElementById('stateCheck').checked=false;
		document.getElementById('paraFrm_brnId').value='';
		document.getElementById('paraFrm_brnName').value='--All--';
		document.getElementById('paraFrm_stateId').value='';
		document.getElementById('paraFrm_stateName').value='';
	}else{
		document.getElementById('brnTD').style.display='none';
		document.getElementById('brnTD1').style.display='none';
		document.getElementById('paraFrm_brnId').value='';
		document.getElementById('paraFrm_brnName').value='';
	}
	
}

function callStateCheck(obj){
	if(obj.checked){
		document.getElementById('brnTD').style.display='none';
		document.getElementById('brnTD1').style.display='none';
		document.getElementById('stateTD').style.display='';
		document.getElementById('stateTD1').style.display='';
		document.getElementById('branchCheck').checked=false;
		document.getElementById('paraFrm_brnId').value='';
		document.getElementById('paraFrm_brnName').value='';
		document.getElementById('paraFrm_stateId').value='';
		document.getElementById('paraFrm_stateName').value='--All--';
	}else{
		document.getElementById('stateTD').style.display='none';
		document.getElementById('stateTD1').style.display='none';
		document.getElementById('paraFrm_stateId').value='';
		document.getElementById('paraFrm_stateName').value='';
	}
	
	
}
function callOnload(){
		document.getElementById('brnTD').style.display='none';
		document.getElementById('brnTD1').style.display='none';
		document.getElementById('stateTD').style.display='none';
		document.getElementById('stateTD1').style.display='none';
		document.getElementById('slabTD').style.display='';
		document.getElementById('slabTD1').style.display='';
		setCurrentYear("paraFrm_year");
}

</script>