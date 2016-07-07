<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<s:form action="EmpCredit" id="paraFrm" validate="true" method="post" theme="simple">
<s:hidden name="pfFlag"/>
<s:hidden name="salHeadEditFlag"/>
<s:hidden name="gradeId"/>
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Credit Configuration</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="80%">
									<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
								</td>
								<td width="20%">
									<div align="right"><font color="red">*</font> Indicates Required</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
								<tr>
									<td colspan="4" class="formhead">
										<strong	class="forminnerhead">
											<label class="set" id="empCredit" name="empCredit""><%=label.get("empCredit")%></label>
										</strong>
									</td>
								</tr>
								<tr>
									<td width="25%" class="formtext">
										<label class="set" id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font>
									</td>
									<td colspan="3" nowrap="nowrap">
										<s:textfield theme="simple" name="empToken"	size="20" readonly="true"/>
										<s:textfield theme="simple"	name="empName" size="72" readonly="true"/>
										<s:hidden theme="simple" name="empCredit"/> 
										<s:hidden name="empstatus"/>
										<s:hidden name="emp_Id"/>
										<s:hidden name="empId"/>
									</td>
								</tr>
								<tr>
									<td>
										<label class="set" id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
									</td>
									<td>
										<s:textfield theme="simple"	readonly="true" name="empCenter" size="20"/>
									</td>
									<td width="25%">
										<label class="set" id="empDept"	name="empDept1" ondblclick="callShowDiv(this);"><%=label.get("empDept")%></label>:
									</td>
									<td>
										<s:textfield name="empDeptName" readonly="true" size="20"/>
										<s:hidden name="empDeptId"/>
									</td>
								</tr>
								<tr>
									<td>
										<label class="set" id="designation"	name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
									</td>
									<td>
										<s:textfield theme="simple"	name="empRank" readonly="true" size="20"/>
									</td>
									<td>
										<label class="set" id="empgrade" name="empgrade" ondblclick="callShowDiv(this);"><%=label.get("empgrade")%></label>:
									</td>
									<td><s:hidden name="empGradeId" />
										<s:textfield theme="simple" readonly="true"  name="empGradeName" size="20"/>
									</td>
								</tr>
								<tr>
									<td>
										<label class="set" id="joinDate1" name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:
									</td>
									<td colspan="3">
										<s:textfield name="joiningDate" readonly="true" size="20"/>
									</td>
								</tr>
								<tr>
									<td>
										<label class="set" id="accountNo1" name="accountNo1"><%=label.get("accountNo")%></label>:
									</td>
									<td>
										<s:textfield readonly="true" name="empAccountNo" size="20"/>
									</td>
									<td>
										<label class="set" id="panNo" name="panNo"><%=label.get("panNo")%></label>:
									</td>
									<td>
										<s:textfield readonly="true" name="empPanNo" size="20"/>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- emp CTC block -->
				<s:if test="flagList">
				<tr>
					<td colspan="3">
						<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg">
							<tr id="ctrlHide"> 
								<td width="25%">
									<label class="set" id="applicableFormula" name="applicableFormula"><%=label.get("applicableFormula")%></label>:<font color="red">*</font>
								</td>
								<td>
									<s:textfield theme="simple"	readonly="true"  name="frmName" size="20"/> 
									<s:hidden name="frmId"/> 
									<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callsF9(800,525,'EmpCredit_f9frmaction.action');clearFields('paraFrm_gradeId', 'paraFrm_gradeName');" id="ctrlHide">
								</td>
								<td width="25%"><label class="set" id="salGrade"
									name="salGrade" ondblclick="callShowDiv(this);"><%=label.get("salGrade")%></label>
								:</td>
								<td>
								<s:textfield readonly="true" name="gradeName" />
								<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callsF9(800,525,'EmpCredit_f9gradection.action');clearFields('paraFrm_frmId', 'paraFrm_frmName');" id="ctrlHide">
								</td>
							</tr>
							<tr id="ctrlHide">
								<td>
									<label class="set" id="grsAmount" name="grsAmount" ondblclick="callShowDiv(this);"><%=label.get("grsAmount")%></label>:<font color="red">*</font>
								</td>
								<td>
									<s:textfield theme="simple"	name="grsAmt" onkeypress="return numbersOnly();" size="20"/>
								</td>
								<td colspan="2">
									<s:submit cssClass="token" name="calculate"	action="EmpCredit_calCtc" value="Calculate"	onclick="return callCal();" />
								</td>
							</tr>
							<tr>
								<td width="25%">
									<label class="set" id="incHistory"	name="incHistory" ondblclick="callShowDiv(this);"><%=label.get("incHistory")%></label>:
								</td>
								<td width="25%" id="ctrlShow">
									<s:select name="incrementPeriod" headerKey="" headerValue="   --Select--  " list="%{incrementHistoryMap}" cssStyle="width:129"/>
								</td>
								<td colspan="2">
									<input type="button" value="  View  " Class="token" onclick="callView();" id="ctrlShow"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</s:if>
					<!-- emp CTC block ends -->
					<%	int i = 0;
						int i2 = 0;
					%>
					<%!int p = 0, t = 0;%>
									
				<s:if test="flagList">
					<tr>
						<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
								<tr>
									<td colspan="3">
									<table border="0" cellpadding="1" cellspacing="1" width="100%">
										<tr>
											<td class="formth" width="30%"><label class="set"
												id="salary.header" name="salary.header"
												ondblclick="callShowDiv(this);"><%=label.get("salary.header")%></label></td>
											<td class="formth" width="10%" now><label class="set"
												id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
											<td class="formth" width="10%" now><label class="set"
												id="amount" name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
										</tr>
										<!-- Credit-Monthly -->
										<s:iterator value="salHeaderList">
											<tr>
												<td class="sortableTD" width="30%"><s:property value="creditNameItt" /> <s:hidden
													name="credCode" /> &nbsp; <s:hidden name="creditNameItt" /> <input type="hidden" name='period' value="<s:property value='creditPeriodItt' />" id='<%="period"+i%>' /></td>
												<td class="sortableTD" width="10%"><s:property value="creditPeriodItt" /></td>
												<td class="sortableTD" width="20%" align="center">
												<input type="text" style="text-align:right;" size="10" maxlength="15" name="amount" id="amount<%=i%>" 
													onkeyup="sumAmt('period',<%=i%>)" value="<s:property value="creditAmountItt" />"
													onkeypress="return numbersWithDot();" />&nbsp;
												</td>
											</tr>
											<%p++;i++;%>
										</s:iterator>
										<%if(i==0){%>
											<tr align="center">
												<td colspan="6" class="sortableTD" width="100%"><font
													color="red">No Data to display</font></td>
											</tr>
										<%}%>	
									</table>
								</td>
							</tr>
									<%
										t = p;
										p = 0;
									%>
						</table>
					</td>
				</tr>

					<tr>
						<td colspan="3">
						<table width="100%" class="formbg" cellpadding="1" cellspacing="1"	border="0">
							<!-- TOTAL MONTHLY SALARY -->
							<tr>
								<td>
									&nbsp;
								</td>
								<td width="27%"><label class="set" id="totMonth"
									name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
								</td>
								<td width="22%"><s:if test="generalFlag">
									<s:property value="totalamt" />
								</s:if> <s:else>
									<s:textfield name="totalamt" theme="simple" size="10"
										readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;" />
								</s:else></td>
							</tr>
							<!-- TOTAL ANNUAL SALARY -->
							<tr>
								<td>
									&nbsp;
								</td>
								<td><label class="set" id="totAnnual"
									name="totAnnual" ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>
								</td>
								<td><s:if test="generalFlag">
									<s:property value="annualAmt" />
								</s:if> <s:else>
									<s:textfield name="annualAmt" theme="simple" size="10"
										readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;" />
								</s:else></td>
							</tr>
							<!-- CTC -->
							<tr>
								<td>
									&nbsp;
								</td>
								<td><label class="set" id="ctcamt1"
									name="ctcamt" ondblclick="callShowDiv(this);"><%=label.get("ctcamt")%></label>
								</td>
								<td>
									<s:textfield name="ctcAmt" theme="simple" readonly="true" size="10"
										cssStyle="background-color: #F2F2F2;text-align:right;" />
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>

<script>
document.getElementById('paraFrm_incrementPeriod').value="";
function saveFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpCredit_save.action";
	document.getElementById('paraFrm').submit();
}  
function editFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpCredit_edit.action";
	document.getElementById('paraFrm').submit();
}  
function searchFun(){
 	javascript:callsF9(500,325,'EmpCredit_f9action.action');
}
function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpCredit_back.action";
	document.getElementById('paraFrm').submit();
}
function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpCredit_reset.action";
	document.getElementById('paraFrm').submit();
}
function deleteFun(){
	var con=confirm('Do you want to delete the record(s) ?');
	if(con){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmpCredit_delete.action";
		document.getElementById('paraFrm').submit();
	}
}
function callCal(){
	var grossamount=document.getElementById('paraFrm_grsAmt').value
	var formula=document.getElementById('paraFrm_frmId').value
	
	if(formula==""){
		alert("please select formula");				   
		return false;
	}
	if(grossamount=="0" || grossamount==""){
		alert("please enter gross amount");
		document.getElementById('paraFrm_grsAmt').focus();
		return false;
	}
}
 
function call(){
	var employee=document.getElementById('employee').innerHTML.toLowerCase();
    var emp=document.getElementById('paraFrm_empId').value
	if(emp=="") {
		alert("Please select the "+employee);
		return false;
	}
}
function callDel(){
	var conf=confirm("Are you sure to delete this record?");
	if(conf) {
		return true;
	}else {
		return false;
	}
}
function sumPreAmt(s){
	var totalrow = <%=t%>;
	var count=0;
	for(var row = 0;row < totalrow ;row++){
		var values=document.getElementById('preCommAmt'+row).value;
		if(values =="" || values=='.'){
			values =0;
		}
		values =eval(values*100/100);
		count=eval(count)+eval(values);
	}
	document.getElementById('totalPreCommisionamt').value=count;
}
function sumAmt(p,s) {
	var value=document.getElementById('amount'+s).value;
	if(value!='.' && isNaN(value)){
		alert("Only single dot is allowed");
		document.getElementById('amount'+s).value=0;
		value=0;
	}
	var totalrow = <%=t%> ;
	var a='amount';
	var count=0;
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	
	for(var row = 0;row < totalrow ;row++){
	if(document.getElementById(p+row).value=="Monthly"){
		var values=document.getElementById(a+row).value;
		if(values =="" || values=='.'){
			values =0;
		}
		values =eval(values*100/100);
		count=eval(count)+eval(values);
		count1=eval(count1)+eval(values)*12;
	}else if(document.getElementById(p+row).value=="Half Yearly"){
			var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
			values =eval(values*100/100);
			count2=eval(count2)+(eval(values)*2);
	}else if(document.getElementById(p+row).value=="Annually"){
		var values=document.getElementById(a+row).value;
		if(values =="" || values=='.'){
			values =0;
			}
			values =eval(values*100/100);
			count3=eval(count3)+eval(values);
		} else if(document.getElementById(p+row).value=="Quarterly"){
			var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
		}
	}
	var amount = 0;
	document.getElementById('paraFrm_totalamt').value=roundNumber(count,2);	
	document.getElementById('paraFrm_annualAmt').value=roundNumber(count1+count2+count3+count4,2);
}	

function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}

function numbersonly(myfield){
	var key;
	var keychar;
	if(window.event){
			key = window.event.keyCode;
	}else {
			return true;
	}
	keychar = String.fromCharCode(key);	
	if ((("0123456789.").indexOf(keychar) > -1)){
		return true;	
	} else {
		myfield.focus();	
		return false;
		}
	}

function valAmt(ctcfieldname,ctclabelname,period,i) {
	var amount=document.getElementById(ctcfieldname).value;
	
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
			alert("Single dot is allowed in "+ctclabelname+" field.");
			document.getElementById(ctcfieldname).value='0';
			sumAmt(period,i);
			document.getElementById(ctcfieldname).focus();
			return false;
		}	
	}
	return true;
}

function callView(){
	var incPeriod = document.getElementById('paraFrm_incrementPeriod').value; 
	if(incPeriod==""){
		alert("Please select increment history");
		return false;
	}
	else if(incPeriod=="N/A"){
		alert("Increment period not available");
		return false;
	}
	else{
	 	document.getElementById("paraFrm").action="EmpCredit_viewIncrementHistory.action"; 
	  	document.getElementById("paraFrm").submit();
  	}
}     

function clearFields(field1, field2){
	document.getElementById(field1).value="";
	document.getElementById(field2).value="";
}	
</script>
