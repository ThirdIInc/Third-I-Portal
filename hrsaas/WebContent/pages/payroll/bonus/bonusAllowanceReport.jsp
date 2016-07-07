<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="BonusReport" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<s:hidden name="bonusAllowancePeriod" />
		<s:hidden name="bonusAllowanceID" />
		<s:hidden name="bonusAllowanceStatus" />
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="3%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Bonus Report </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right">
								<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
							</div>
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
						<s:if test="fromBonusAllowanceFlag">
						 	<a href="#" onclick="backFun();">
							<img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" title="Back"> Back </a>&nbsp;&nbsp;
						</s:if>
						
						<s:else>
						 	<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="Reset"> Reset </a>&nbsp;&nbsp;
							
							<a href="#" onclick="searchBonusRecord();">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage"  align="absmiddle" title="Select Bonus Record"> Select Bonus Record </a>&nbsp;&nbsp;
						</s:else>
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
			<td>
				<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						 <td colspan="3">
						 	<b><label name="bonusPeriod" id="bonusPeriod" ondblclick="callShowDiv(this);"><%=label.get("bonusPeriod")%></label></b>
							:<font color="red">*</font>
						 </td>
						 <td colspan="2" align="right">
						 	<s:if test="fromBonusAllowanceFlag">
						 		&nbsp;
						 	</s:if>
						 	<s:else>&nbsp;
						 		 <!-- 
						 		 	<input type="button" value="Select Bonus Record" class="token" onclick="searchBonusRecord();"/>
						 		  -->
						 	</s:else> 
						 	
						 </td>
					</tr>
					<tr>
						 <td width="5%">&nbsp;</td>
						 <td width="11%">
						 	<label name="fromPeriod" id="fromPeriod" ondblclick="callShowDiv(this);"><%=label.get("fromPeriod")%></label>
							:
						 </td>
						 <td width="38%">
						 	<s:select name="fromMonth" headerKey="-1" headerValue="----Select----" cssStyle="background-color: #F2F2F2;" disabled="true" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
						 	&nbsp;<s:textfield name="fromYear" size="10" maxLength="4" cssStyle="background-color: #F2F2F2;" readonly="true" onkeypress="return numbersOnly();"/>			
						 		 
						 </td>
						 <td width="10%">
						 	<label name="toPeriod" id="toPeriod" ondblclick="callShowDiv(this);"><%=label.get("toPeriod")%></label>
							:
						 </td>
						 <td width="36%">
						 	<s:select name="toMonth" headerKey="-1" headerValue="----Select----" cssStyle="background-color: #F2F2F2;" disabled="true" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
						 	&nbsp;<s:textfield name="toYear" size="10" maxLength="4" cssStyle="background-color: #F2F2F2;" readonly="true" onkeypress="return numbersOnly();"/>		
						 	 	 
						 </td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						 <td colspan="10">
						 	<label name="bonusReportOptions" id="bonusReportOptions" ondblclick="callShowDiv(this);"><%=label.get("bonusReportOptions")%></label>
							: 
						 </td>
					</tr>
					<tr>
						 <td width="2%">&nbsp;</td>
						 <td width="10%">
						 	<label name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="divisionReportCheckBox" />	
						 </td>
						<td width="10%">
						 	 <label name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="branchReportCheckBox" />	
						 </td>
						<td width="10%">
						 	 <label name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="departmentReportCheckBox" />	
						 </td>
						 <td width="10%">
						 	 <label name="applicableCredit" id="applicableCredit" ondblclick="callShowDiv(this);"><%=label.get("applicableCredit")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="applicableCreditReportCheckBox" />	
						 </td>
						 <td width="3%">&nbsp;</td>
					</tr>
					<tr>
						 <td width="2%">&nbsp;</td>
						 <td width="10%">
						 	 <label name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="gradeReportCheckBox" />	
						 </td>
						<td width="10%">
						 	 <label name="accountNumber" id="accountNumber" ondblclick="callShowDiv(this);"><%=label.get("accountNumber")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="accountNumberReportCheckBox" />	
						 </td>
						<td width="10%">
						 	 <label name="bank" id="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="bankReportCheckBox" />	
						 </td>
						 <td width="10%">
						 	 <label name="individualRating" id="individualRating" ondblclick="callShowDiv(this);"><%=label.get("individualRating")%></label> 
						 </td>
						 <td width="14%">
						 	<s:checkbox name="individualRatingReportCheckBox" />	
						 </td>
						 <td width="3%">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>

		 <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap">
						<s:if test="fromBonusAllowanceFlag">
						 	<a href="#" onclick="backFun();">
							<img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" title="Back"> Back </a>&nbsp;&nbsp;
						</s:if>
						
						<s:else>
						 	<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="Reset"> Reset </a>&nbsp;&nbsp;
							
							<a href="#" onclick="searchBonusRecord();">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage"  align="absmiddle" title="Select Bonus Record"> Select Bonus Record </a>&nbsp;&nbsp;
						</s:else>
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:hidden name="reportType" />
		<s:hidden name="reportAction" value='BonusReport_getBonusReport.action' />
	</table>
</s:form>
<script>

function resetFun() {
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='BonusReport_reset.action';
	document.getElementById('paraFrm').submit();
}

function backFun() {
	var bonusCode = document.getElementById('paraFrm_bonusAllowanceID').value;
	var lockUnlockStatus = document.getElementById('paraFrm_bonusAllowanceStatus').value;
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='BonusAllowance_callForEdit.action?bonusCode='+bonusCode+'&lockUnlockStatus='+lockUnlockStatus;
	document.getElementById('paraFrm').submit();
}

function callReport(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}
}

function validation() {
	var bonusAllowanceID = trim(document.getElementById('paraFrm_bonusAllowanceID').value);
	if (bonusAllowanceID == "") {
		alert("Please select bonus record");
		return false;
	} else {
		return true;
	}
}

function validation_OLD() {
	try {
		//Validation for Select Period Section -- BEGINS
		var fromMonthVar = document.getElementById('paraFrm_fromMonth').value; 
		var fromYearVar = trim(document.getElementById('paraFrm_fromYear').value);
		var toMonthVar = document.getElementById('paraFrm_toMonth').value;
		var toYearVar = trim(document.getElementById('paraFrm_toYear').value);
	
		if (fromMonthVar=="-1") {
			alert("Please select from month");
			document.getElementById('paraFrm_fromMonth').focus();
			return false;
		}
		if (fromYearVar=="") {
			alert("Please select from year");
			document.getElementById('paraFrm_fromYear').focus();
			return false;
		} else if(fromYearVar.length < 4) {
   			alert("from year should have 4 digits");
   			document.getElementById('paraFrm_fromYear').focus();
   			return false;
   		}
	
	
		if (toMonthVar=="-1") {
			alert("Please select to month");
			document.getElementById('paraFrm_toMonth').focus();
			return false;
		}
		if (toYearVar=="") {
			alert("Please select to year");
			document.getElementById('paraFrm_toYear').focus();
			return false;
		} else if(eval(toYearVar.length) < 4) {
   			alert("to year should have 4 digits");
   			document.getElementById('paraFrm_toYear').focus();
   			return false;
   		}
	
		if ((fromMonthVar!=""||fromYearVar!="")&&(toMonthVar!=""||toYearVar!="")) {
			if (eval(fromMonthVar)==eval(toMonthVar)) {
				if (eval(fromYearVar)>eval(toYearVar)) {
					alert("from year should not be greater than to year");
					document.getElementById('paraFrm_fromYear').focus();
					return false;
				}
			}
		
			if (eval(fromYearVar)==eval(toYearVar)) {
				if (eval(fromMonthVar)>eval(toMonthVar)) {
					alert("from month should not be greater than to month");
					document.getElementById('paraFrm_fromMonth').focus();
					return false;
				}
			}
		
			if (((eval(fromMonthVar)>eval(toMonthVar))&&(eval(fromYearVar)>eval(toYearVar)))
											||
				((eval(fromMonthVar)<eval(toMonthVar))&&(eval(fromYearVar)>eval(toYearVar)))							
				) {
				alert("from period should not be greater than to period");
				return false;
			}
		}
	//Validation for Select Period Section -- ENDS	
		return true;
	} catch(e) {
		alert("Exception in validation >>"+e);
		return false;
	}
}

function mailReportFun(type){
	if(!validation()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='BonusReport_mailReport.action';
		document.getElementById('paraFrm').submit();
	}
} 

function searchBonusRecord() {
	if(navigator.appName == 'Netscape') {
	  var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	}  else {
	  var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='BonusReport_searchBonusRecords.action';
	document.getElementById("paraFrm").submit();
}

</script>