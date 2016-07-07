<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="TaxChallan" validate="true" id="paraFrm" theme="simple">
<s:hidden name="challanID"/>
<s:hidden name="applicationType"/>
<s:hidden name="applicationCode"/>
<s:hidden name="backAction"/>
	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax Challan </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
						<div align="right"><font color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						<tr>
							<td width="20%"><label class="set" id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font	color="red">*</font> :</td>
							<td>
								<s:textfield name="divName" size="25" readonly="true" />
								<s:hidden name="divId" />
								<img id="ctrlHide" src="../pages/images/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TaxChallan_f9Division.action');"></td>
							<td><label id="nhold" name="nhold" ondblclick="callShowDiv(this);"><%=label.get("nhold")%></label> :</td>
							<td>
								<s:select theme="simple" name="onHold" cssStyle="width:152" list="#{'N':'No','Y':'Yes','A':'All'}" />
							</td>
						</tr>
						<tr>
							<td><label class="set" id="taxSelMon"
								name="taxSelMon" ondblclick="callShowDiv(this);"><%=label.get("taxSelMon")%></label>
							<font color="red">*</font> :</td>
							<td><s:select theme="simple" name="month" cssStyle="width:155"
								list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
							<td><label class="set" id="taxYear"
								name="taxYear" ondblclick="callShowDiv(this);"><%=label.get("taxYear")%></label>
								<font color="red">*</font> :</td>
							<td><s:textfield name="year" size="25"
								maxlength="4" onkeypress="return numbersOnly();" />
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
									<tr>
										<td width="10%" nowrap="nowrap">Include</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeSalary" id="includeSalary" />Salary</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeArrears" id="includeArrears" />Arrears</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeSettlement" id="includeSettlement"/>Settlement</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeBonus" id="includeBonus"/>Bonus</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeOverTime" id="includeOverTime"/>OT</td>
										<td width="10%" nowrap="nowrap"><s:checkbox name="includeLeaveEncashment" id="includeLeaveEncashment"/>Leave Encashment</td>
										<!--<td colspan="2"><s:checkbox name="includeAllowance" id="includeAllowance"/>Allowance</td>-->
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
			<td width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
<s:hidden name="surchargePercen" id="surchargePercen"/>
<s:hidden name="eduCessPercen" id="eduCessPercen"/>
<s:hidden name="rebateLimit" id="rebateLimit"/>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
callOnLoad();
function callOnLoad(){
	var typeVal = document.getElementById('paraFrm_applicationType').value;
	if(typeVal != ''){
		if(typeVal == 'B'){
			document.getElementById('includeBonus').checked = true;
			document.getElementById('includeBonus').disabled = false;
		}else{
			document.getElementById('includeBonus').disabled = true;
		}
		if(typeVal == 'L'){
			document.getElementById('includeLeaveEncashment').checked = true;
			document.getElementById('includeLeaveEncashment').disabled = false;
		}else{
			document.getElementById('includeLeaveEncashment').disabled = true;
		}
		if(typeVal == 'P'){
			document.getElementById('includeArrears').checked = true;
			document.getElementById('includeArrears').disabled = false;
		}else{
			document.getElementById('includeArrears').disabled = true;
		}
		document.getElementById('includeSalary').disabled = true;
		//document.getElementById('includeArrears').disabled = true;
		document.getElementById('includeSettlement').disabled = true;
		document.getElementById('includeOverTime').disabled = true;
	}else{
		document.getElementById('includeSalary').checked = true;
	}
}
function saveandnextFun(){
try{
	var division=document.getElementById('division').innerHTML.toLowerCase();
  	var selMon=document.getElementById('taxSelMon').innerHTML.toLowerCase();
  	var year=document.getElementById('taxYear').innerHTML.toLowerCase();
	if(document.getElementById("paraFrm_divName").value=="") {
		alert("Please select "+division+".");
		return false;
	}
	if(document.getElementById("paraFrm_month").value=="0")	{
		alert("Please select "+selMon+".");
		return false;
	}
	if(document.getElementById("paraFrm_year").value==""){
		alert("Please enter "+year+".");
		return false;
	}
	if(document.getElementById("includeSalary").checked==false 
		&& document.getElementById("includeArrears").checked==false
		&& document.getElementById("includeSettlement").checked==false
		&& document.getElementById("includeBonus").checked==false
		&& document.getElementById("includeOverTime").checked==false
		&& document.getElementById("includeLeaveEncashment").checked==false){
			alert("Please select atleast one checkbox");
			return false;
	}
	
	document.getElementById("paraFrm").action="TaxChallan_saveAndNext.action";
  	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
   	}catch(e){alert(e);}
} //end of saveandnextFun method

function numbersonly(myfield){
	var key;
	var keychar;
	if (window.event){
		key = window.event.keyCode;
	}else{
		return true;
	}
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1)){
		return true;	
	}else {
		myfield.focus();
		return false;
	}
}

function backFun(){
	document.getElementById("paraFrm").action="TaxChallan_input.action";
  	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
}

function editFun(){
	return true;
}

function resetFun(){
	document.getElementById("paraFrm_divName").value = "";
	document.getElementById("paraFrm_divId").value = "";
	document.getElementById("paraFrm_onHold").value = "A";
	document.getElementById("paraFrm_month").value = "0";
	document.getElementById("paraFrm_year").value = "";
	document.getElementById("includeArrears").value = "";
	document.getElementById("includeSettlement").value = "";
	document.getElementById("includeBonus").value = "";
	document.getElementById("includeOverTime").value = "";
	document.getElementById("includeLeaveEncashment").value = "";
}
function backToBonus(){
	var backAction = document.getElementById('paraFrm_backAction').value;
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action=backAction;
	document.getElementById('paraFrm').submit();
}
</script>

