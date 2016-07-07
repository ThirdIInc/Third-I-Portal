<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<s:form action="SettlmentDetails" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Settlement
					Details </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%" height="100px">
				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="12%" colspan="1"><s:textfield name="empToken"
								size="12" value="%{empToken}" readonly="true" /></td>
							<td width="68%" colspan="2"><s:textfield name="empName"
								size="68" value="%{empName}" readonly="true" /></td>
							<s:hidden name="empCode" value="%{empCode}"></s:hidden>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="desgn" value="%{desgn}" readonly="true" /></td>
							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="branch" value="%{branch}" readonly="true" /></td>
							
						</tr>
<!-- Added By Anantha lakshmi -->
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:
							</td>
							<td width="30%" colspan="1">
								<s:textfield 	name="cadreName" value="%{cadreName}" size="22" readonly="true" />
							</td>
							<td width="20%" colspan="1" class="formtext">
								<label	class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
							<td width="30%" colspan="1">
								<s:textfield name="dateOfJoin"	size="20" onkeypress="return numbersWithHiphen();"	
											 theme="simple" value="%{dateOfJoin}" maxlength="10"	readonly="true" />
							</td>
						</tr>
<!-- ended By Anantha lakshmi -->
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="resignation.date" id="resignation.date3"
								ondblclick="callShowDiv(this);"><%=label.get("resignation.date")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="resignDate" readonly="true" /></td>
							<td width="20%" colspan="1"><label class="set"
								name="seperation.date" id="seperation.date"
								ondblclick="callShowDiv(this);"><%=label.get("seperation.date")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="3"><s:textfield size="22"
								name="sepDate" readonly="true" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set" name="period"
								id="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label><font
								color="red">*</font> :</td>
							<td width="80%" colspan="3"><s:textfield size="5"
								theme="simple" name="noticePeriod"
								onkeypress="return numbersOnly();" readonly="true" /><s:select
								theme="simple" name="noticePeriodStatus" cssStyle="width:67"
								list="#{'D':'Days','M':'Month'}" disabled="true" /><s:hidden
								name="noticeStatus" /> <s:hidden name="noticePeriodStatus" /></td>
						</tr>
					</table>
					</td>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%">
				<tr>
					<td width="20%" height="15" colspan="1" nowrap="nowrap"><strong><label
						class="set" name="settelment.amount" id="settelment.amount"
						ondblclick="callShowDiv(this);"><%=label.get("settelment.amount")%></label>:</strong></td>
					<td width="80%" height="15" colspan="3"><s:textfield
						name="settleAmt" size="20"
						cssStyle="background-color: #F2F2F2;text-align:right;"
						readonly="true" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%" height="100px">
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="settlement.date" id="settlement.date3"
						ondblclick="callShowDiv(this);"><%=label.get("settlement.date")%></label><font
						color="red">*</font> :</td>
					<td width="80%" colspan="3"><s:textfield size="20"
						maxlength="10" name="settDate" onkeypress="return numbersWithHiphen();"/> <!--<s:date name="#myDate.now" format="dd-MM-yyyy" />-->
					<s:a href="javascript:NewCal('paraFrm_settDate','DDMMYYYY');">
						<img src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set" name="mode"
						id="mode" ondblclick="callShowDiv(this);"><%=label.get("mode")%></label>
					:</td>
					<td width="80%" colspan="3"><s:select name="modePayment"
						list="#{'':'-- Select --','CH':'Cheque','CS':'Cash','TS':'Transfer'}"
						value="%{modePayment}" id="chkFlag" onchange="checkVal();" /></td>
				</tr>

				<tr id="bankId">
					<td width="20%" colspan="1"><label class="set" name="bank"
						id="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>
					:</td>
					<td width="80%" colspan="3"><s:textfield size="20" name="bank"
						value="%{bank}" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" onclick="callBank();" /> <s:hidden
						name="bankMicrId" /></td>
				</tr>

				<tr id="cheqId">
					<td width="20%" colspan="1"><label class="set" name="cheque"
						id="cheque" ondblclick="callShowDiv(this);"><%=label.get("cheque")%></label>:</td>
					<td width="30%" colspan="1"><s:textfield size="20"
						name="chequeno" value="%{chequeno}" maxlength="16" /></td>
					<td width="20%" colspan="1"><label class="set"
						name="cheque.date" id="cheque.date"
						ondblclick="callShowDiv(this);"><%=label.get("cheque.date")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="20"
						maxlength="10" name="chequeDate" onkeypress="return numbersWithHiphen();"/> <s:a
						href="javascript:NewCal('paraFrm_chequeDate','DDMMYYYY');">
						<img src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="prepared.by" id="prepared.by"
						ondblclick="callShowDiv(this);"><%=label.get("prepared.by")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="prepbyCode"
						value="%{prepbyCode}"></s:hidden> <s:hidden name="prepbyToken"></s:hidden><s:textfield
						size="20" readonly="true" name="preparedby" value="%{preparedby}" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="callPreEmp();"  id="ctrlHide"/></td>

					<td width="20%" colspan="1"><label class="set" name="handed"
						id="handed" ondblclick="callShowDiv(this);"><%=label.get("handed")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="handOverCode"></s:hidden>
					<s:hidden name="handOverToken"></s:hidden><s:textfield size="20"
						readonly="true" name="handedOver" value="%{handedOver}" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" onclick="callHandoverEmp();" id="ctrlHide" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="checked.by" id="checked.by" ondblclick="callShowDiv(this);"><%=label.get("checked.by")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="chkbyCode"
						value="%{chkbyCode}" /> <s:hidden name="chkbyToken"></s:hidden><s:textfield
						size="20" readonly="true" name="checkedby" value="%{checkedby}" />
					<img src="../pages/images/recruitment/search2.gif" id="ctrlHide"
						class="iconImage" height="14" width="14" onclick="callCheckEmp();" /></td>
					<td width="20%" colspan="1"><label class="set"
						name="checked.by.account" id="checked.by.account"
						ondblclick="callShowDiv(this);"><%=label.get("checked.by.account")%></label>:</td>
					<td width="30%" colspan="1"><s:hidden name="accChkCode"></s:hidden>
					<s:hidden name="accChkToken"></s:hidden><s:textfield size="20"
						readonly="true" name="accCheck" value="%{accCheck}" /><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple" onclick="callAccEmp();" />
					</td>
				</tr>

				
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="4" width="100%">
			<table border="0" width="100%" class="formbg">
				<tr>
					<s:hidden value="eligibleMth" />
					<td width="20%" colspan="1"><label class="set" name="remove"
						id="remove" ondblclick="callShowDiv(this);"><%=label.get("remove")%></label>
					:</td>
					<td width="80%" colspan="3"><s:checkbox name="permSettle"
						id="permSettle" /></td>
					
				</tr>
				<tr>
					<td width="20%" colspan="1"><label name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:</td>
					<td width="60%" colspan="2" nowrap="nowrap"><s:textarea name="comments"
						rows="5" cols="62" readonly="false"
						onkeyup="callLength('comments','descCnt','500');" /> <img id="ctrlHide"
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments','comments','','paraFrm_descCnt','500');">
					</td>
					<td width="20%" colspan="1" id="ctrlHide">
						Remaining chars <s:textfield name="descCnt" readonly="true"
						size="5" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		<s:hidden name="resignCode" value="%{resignCode}" />
		<s:hidden name="settCode" />
		<s:hidden name="settDtlCode" />
		<s:hidden name="settFlag" />
		<s:hidden name="lockFlag" />
		<s:hidden name="status" />
		<s:hidden name="myPage" />
		<s:hidden name="finalSave" />

	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<SCRIPT>
checkVal();	
function checkVal(){
	document.getElementById('bankId').style.display = 'none';
	document.getElementById('cheqId').style.display = 'none';
	if(document.getElementById('chkFlag').value == 'CH'){
		document.getElementById('bankId').style.display = '';
		document.getElementById('cheqId').style.display = '';
	}
}

function callPreEmp(){
	var ename=document.getElementById('paraFrm_empName').value;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	if(ename == "")
	{
	alert('Please select '+empName);
	return false;
	}
	else{
	}
		 callsF9(500,325,'SettlmentDetails_f9prepByaction.action');
}

function callHandoverEmp(){
	var ename=document.getElementById('paraFrm_empName').value;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	if(ename == "")
	{
	alert('Please select '+empName);
	return false;
	}
	else{
		 callsF9(500,325,'SettlmentDetails_f9handOveraction.action');
	}
}

function callCheckEmp(){
	var ename=document.getElementById('paraFrm_empName').value;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	if(ename == ""){
	alert('Please select '+empName);
	return false;
	}
	else{
		 callsF9(500,325,'SettlmentDetails_f9chkByHRaction.action');
	}
}

function callBank(){
	var ename=document.getElementById('paraFrm_empName').value;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	if(ename == ""){
	alert('Please select '+empName);
	return false;
	}
	else{
		 callsF9(500,325,'SettlmentDetails_f9bankaction.action');
	}
}

function callAccEmp(){
	var ename=document.getElementById('paraFrm_empName').value;
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	if(ename == ""){
	alert('Please select '+empName);
	return false;
	}
	else{
		 callsF9(500,325,'SettlmentDetails_f9chkByAccaction.action');
	}
}

function formValidate(){
	var sCode=document.getElementById('paraFrm_resignCode').value;
	var sDate=document.getElementById('paraFrm_settDate').value;
	var rDate=document.getElementById('paraFrm_resignDate').value;
	var sepDate=document.getElementById('paraFrm_sepDate').value;
	var chDate=document.getElementById('paraFrm_chequeDate').value;
  	var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	var noticePeriod = document.getElementById('paraFrm_noticePeriod').value;
	var paymentMode = document.getElementById('chkFlag').value;
	var bankMicrId = document.getElementById('paraFrm_bankMicrId').value;
	var chequeno = document.getElementById('paraFrm_chequeno').value;
	var chequeDate = document.getElementById('paraFrm_chequeDate').value;
	//Labels
	var setDt=document.getElementById('settlement.date3').innerHTML.toLowerCase();
	try{
	
	if(sDate ==""){
		alert("Please select "+setDt);
		return false;
	}
	
	/*if(paymentMode =="CH"){
		if(bankMicrId==""){
			alert("Please select "+document.getElementById('bank').innerHTML.toLowerCase());
			return false;
		}
		if(chequeno=="" || chequeno=="0"){
			alert("Please enter "+document.getElementById('cheque').innerHTML.toLowerCase());
			return false;
		}
		if(chequeDate==""){
			alert("Please select "+document.getElementById('cheque.date').innerHTML.toLowerCase());
			return false;
		}
		
	}*/
	
	var comments=document.getElementById('paraFrm_comments').value;
	if(comments != "" && comments.length >500){
		alert("Maximum length of "+ document.getElementById('comments').innerHTML.toLowerCase()+" is 500 characters.");
		return false;
    } 
    }catch(e){
		alert('dfdfg  '+e);
	} 
	var check= validateDate('paraFrm_settDate', 'settlement.date3');
	if(!check){
		return false;
	}
	var check1= validateDate('paraFrm_chequeDate', 'cheque.date');
	if(!check1){
		return false;
	}
	
	if(!validateDate('paraFrm_resignDate','resignation.date3'))
             return false;	
    if(!validateDate('paraFrm_sepDate','seperation.date'))
             return false;	 
    //alert(document.getElementById('settlement.date3').innerHTML.toLowerCase()); 
    //alert(document.getElementById('resignation.date3').innerHTML.toLowerCase());        
    if(!dateDifferenceEqual(rDate, sDate, 'paraFrm_settDate','resignation.date3','settlement.date3')){
		return false;
	}
	  
	if(!dateDifferenceEqual(rDate, chDate, 'paraFrm_chequeDate','resignation.date3','cheque.date')){
		return false;
	}
	//alert('4');
	return true;
}

function saveFun(){
	if(!formValidate())
     {
      return false;
     }
     document.getElementById("paraFrm_finalSave").value="1";
	document.getElementById("paraFrm").action ='SettlmentDetails_saveFourth.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
	
	return true;
}

function saveandpreviousFun(){
	try{
	if(!formValidate())
	     {
	      return false;
	     }
	}catch(e){
	alert(e);
	return false;
	}
	document.getElementById("paraFrm_finalSave").value="1";
	document.getElementById("paraFrm").action='SettlmentDetails_saveAndPreviousFourth.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
	
	return true;
}



function finalizeFun(){
	var sCode=document.getElementById('paraFrm_settCode').value;
 	if(sCode =="" || trim(sCode.toString())=="null")
  	{
  		alert('Please save record to lock the Settlement');
  		return false;
  	}
  	if(sCode!="" || trim(sCode.toString())!="null"){
		var lockFlag = document.getElementById('paraFrm_lockFlag').value;
 		if(lockFlag=='Y'){  
		  	alert('Settlement has been already locked !');
		  	return false;
	  	}else{
 			try{
 				if(document.getElementById('permSettle').checked==false){
					alert("Please check "+document.getElementById('remove').innerHTML.toLowerCase());
					return false;
				}
			}catch(e){
				//alert(e);
				/*try{
				if(document.getElementById('paraFrm_permSettle').value=="false"){
					alert("Please check remove employee from payroll on previous page");
					return false;
				}
				}catch(e1){
					//alert(e1);
				}*/
			}
			 
		  	var conf = confirm('Do you really want to finalize the settlement ?');
			if(conf){
				document.getElementById("paraFrm").action ='SettlmentDetails_lock.action?status=F';
				document.getElementById("paraFrm").submit();
				document.getElementById('paraFrm').target = "main";
			}
			else
				return false;
	  	}
	}
  	return true;
}

function previousFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_previousFourth.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

/*function unlockFun(){
	 var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	 if(lockFlag=='Y')
	 {
	  	var conf = confirm('Do you really want to unlock the settlement ?');
		if(conf){
			document.getElementById("paraFrm").action ='SettlmentDetails_unLock.action';
			document.getElementById("paraFrm").submit();
			document.getElementById('paraFrm').target = "main";
		}
		else
			return false;
	}else{
		alert('Please Lock the Settlement First!');
		return false;
	}
  	return true;
}*/


	function unlockFun() {
  		doAuthorisation('4', 'Settlement', 'U');
	}
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'SettlmentDetails_unLock.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}

function deleteFun()
{
	if(document.getElementById('paraFrm_settCode').value==""){
		alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
	}
	
	var con=confirm('Do you really want to delete the record?')
	if(con){
		var del="SettlmentDetails_delete.action";
		document.getElementById('paraFrm').action=del;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} else{
	     return false;
	}
}

function reportFun(){
	var settCode=document.getElementById('paraFrm_settCode').value;
	if(settCode=="" || trim(settCode.toString())=="null"){
		alert('Please Select a record to generate report');
		return false;
	}
	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action="SettlmentDetails_report.action";
  	document.getElementById('paraFrm').submit();  
  	document.getElementById('paraFrm').target="main"; 
}


</SCRIPT>