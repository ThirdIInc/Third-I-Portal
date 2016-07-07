<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpPerquisites" id="paraFrm" validate="true" method="post" theme="simple">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	<tr>
		<td colspan="3" width="100%"><s:hidden name="perquisiteFlag"/>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formbg">
			<tr>
				<td width="4%" valign="bottom" class="txt"><strong
					class="formhead"> <img
					src="../pages/images/recruitment/review_shared.gif" width="25"
					height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="text_head">Employee Perquisites Configuration</strong></td>
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
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
			<tr>
				<td colspan="3" class="formhead"><strong
					class="forminnerhead"><label  class = "set"  id="empReq" name="empReq" ondblclick="callShowDiv(this);"><%=label.get("empReq")%></label></strong></td>
			</tr>
			<tr>
				<td width="20%" class="formtext"><label class="set"
					id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
					color="red">*</font></td>
				<td colspan="3"><s:textfield theme="simple" name="empToken"
					size="10" readonly="true" /><s:textfield theme="simple"
					name="empName" size="81" readonly="true" /><s:hidden
					theme="simple" name="empCredit" /> <s:hidden
					name="empstatus"></s:hidden><s:hidden name="emp_Id" /> <s:hidden
					name="empId" /> </td>
			</tr>

			<tr>
				<td width="20%"><label class="set" id="branch"
					name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
				</td>
				<td width="25%"><s:textfield theme="simple"
					readonly="true" name="empCenter"  /></td>
				<td width="20%"><label class="set" id="empDept"
					name="empDept1" ondblclick="callShowDiv(this);"><%=label.get("empDept")%></label> :</td>
				<td width="25%"><s:textfield name="empDeptName" readonly="true" /><s:hidden name="empDeptId"/></td>
			</tr>
			<tr>
				<td width="20%"><label class="set" id="designation"
					name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
				<td width="25%"><s:textfield theme="simple" 
					name="empRank" readonly="true" /></td>
				<td width="20%"><label class="set" id="empgrade"
					name="empgrade" ondblclick="callShowDiv(this);"><%=label.get("empgrade")%></label>
				:</td>
				<td width="25%"><s:hidden name="empGradeId" /><s:textfield
					theme="simple" readonly="true"  name="empGradeName" />
				</td>
			</tr>
			<tr>
			<td width="20%"><label class="set" id="joinDate1"
					name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:</td>
				<td width="25%"><s:textfield name="joiningDate" readonly="true" /></td>
			</tr>
			<tr>
				<td width="20%"><label class="set" id="accountNo1"
					name="accountNo" ondblclick="callShowDiv(this);"><%=label.get("accountNo")%></label>:</td>
				<td width="25%">
				<s:textfield readonly="true" name="empAccountNo" /></td>

				<td width="20%"><label class="set" id="panNo1"
					name="panNo" ondblclick="callShowDiv(this);"><%=label.get("panNo")%></label> :</td>
				<td width="25%">
				<s:textfield readonly="true" name="empPanNo" /></td>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
						<td width="20%">	
							<strong><label class="set" id="finYear"	name="finYear"><%=label.get("finYear")%></label></strong>
						</td>
						<td width="25%" id="ctrlShow"><label class="set" id="frmYr"
							name="frmYr" ondblclick="callShowDiv(this);"><%=label.get("frmYr")%></label>:<font color="red">*</font>&nbsp;
							<s:textfield name="fromYear" size="9" maxlength="4" onkeypress="return numbersOnly();" onblur="return add();"/></td>
						<td width="20%" id="ctrlShow"><label class="set" id="toYr"
							name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label> :&nbsp;
							<s:textfield name="toYear" size="9" maxlength="4"  readonly="true"/>
						</td>
						<td width="25%">	
							<input type="button" value="  View  " Class="token" onclick="callView();" id="ctrlShow"/>
						</td>
					</tr>
					</table>
					</td>
				</tr>
				<s:if test='%{perquisiteFlag}'>
				<tr>
				<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
						<tr>
							<td class="formth"><label  class = "set"  id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
							<td class="formth"><label  class = "set"  id="perHead" name="perHead" ondblclick="callShowDiv(this);"><%=label.get("perHead")%></label></td>
							<td class="formth"><label  class = "set"  id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
							<td class="formth"><label  class = "set"  id="amount" name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
						</tr>
						<%
							int i = 0;
							int s = 1;
						%>
						<%!int p = 0, t = 0;%>
						<s:iterator value="salHeaderList">
						<tr>
							<td class="sortableTD" width="10%" align="center"><%=s%>
							<input type="hidden" name="perqCodeItt" value='<s:property value="perqCodeItt"/>' id="perqCodeItt<%=i%>"/></td>
							<td class="sortableTD" width="30%">
								<s:property value="perqNameItt" />
							</td>
							<td class="sortableTD" width="20%">
								<input type="hidden" name="perqPeriodItt" value='<s:property value="perqPeriodItt"/>' id="perqPeriodItt<%=i%>"/>
								<s:property value="perqPeriodItt" />
							</td>
							<td class="sortableTD" width="30%" align="center">
								<input type="text" style="text-align:right;" size="6" maxlength="15" name="perqAmountItt" id="perqAmountItt<%=i%>" 
									value="<s:property value="perqAmountItt" />" onkeyup="sum(<%=i%>);" onkeypress="return numbersWithDot();"/>&nbsp;
							</td>
						</tr>
						<%
							i++;
							s++;
							p++;
						%>
						</s:iterator>
						<%
							t = p;
							p = 0;
						%>
						
					<%	if (i == 0) {%>
					<tr align="center">
						<td colspan="4" class="sortableTD" width="100%"><font
							color="red">No Data to display</font></td>
					</tr>
					<%	} %>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0"	cellspacing="0" >
					<!-- TOTAL MONTHLY SALARY -->
						<tr>
							<td width="45%">
								&nbsp;
							</td>
							<td><label class="set" id="totMonth"
							name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
							</td>
							<td width="20%">
							<s:textfield name="totalAmt" theme="simple" size="6"
								readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;" />
							</td>
						</tr>
					<!-- TOTAL ANNUAL SALARY -->
						<tr>
							<td>
								&nbsp;
							</td>
							<td><label class="set" id="totalAmount" name="totalAmount"
								ondblclick="callShowDiv(this);"><%=label.get("totalAmount")%></label></td>
							<td>	
							<s:textfield name="annualAmt" readonly="true" size="6" cssStyle="background-color: #F2F2F2;text-align:right;"
								theme="simple" /></td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%"><jsp:include
			page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>
</table>
</s:form>
<script>  

function saveFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpPerquisites_save.action";
	document.getElementById('paraFrm').submit();
}  
function editFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpPerquisites_edit.action";
	document.getElementById('paraFrm').submit();
}  
function searchFun(){
 	javascript:callsF9(500,325,'EmpPerquisites_f9action.action');
}
function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpPerquisites_back.action";
	document.getElementById('paraFrm').submit();
}
function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="EmpPerquisites_reset.action";
	document.getElementById('paraFrm').submit();
}
function deleteFun(){
	var conf=confirm("Are you sure you want to delete this record ?");
  	if(conf) {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmpPerquisites_delete.action";
		document.getElementById('paraFrm').submit();
	}
}
function callView(){
	if(document.getElementById('paraFrm_fromYear').value==""){
		alert("Please enter from year");
		return false;
	}else{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmpPerquisites_fetchPerquisitesByEmployeeId.action";
		document.getElementById('paraFrm').submit();
	}
}

function saveValidate()
{   var employee=document.getElementById('employee').innerHTML.toLowerCase();
    var emp=document.getElementById('paraFrm_empId').value;
    
	if(emp=="")
	{
		alert("Please Select the "+employee);
		return false;
	}
}

function sum(s) {
	var totalrow = <%=t%> ;
	var count=0;
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	
	
	
	for(var row = 0;row < totalrow ;row++) {
	
	if(document.getElementById('perqPeriodItt'+row).value=="Monthly"){
	var values=document.getElementById('perqAmountItt'+row).value;
	if(values ==""){
		values =0;
	}
		count=eval(count)+eval(values);
		count1=eval(count1)+eval(values)*12;
	}
	else if(document.getElementById('perqPeriodItt'+row).value=="Half Yearly"){
			var values=document.getElementById('perqAmountItt'+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count2=eval(count2)+eval(values)*2;
	}
	else if(document.getElementById('perqPeriodItt'+row).value=="Quarterly"){
		var values=document.getElementById('perqAmountItt'+row).value;
			if(values =="" || values=='.'){
				values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
		}
		
	else if(document.getElementById('perqPeriodItt'+row).value=="Annually"){
		var values=document.getElementById('perqAmountItt'+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count3=eval(count3)+eval(values);
		}
	}
document.getElementById('paraFrm_TotalAmt').value=count;
document.getElementById('paraFrm_annualAmt').value=count1+count2+count3+count4;
}

function numbersonly(myfield){
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}

function callChk(id){
	if(document.getElementById(id).value=='Y'){
		alert('The Amount will be set to 0')
		document.getElementById(id).value='N';
	}else  if(document.getElementById(id).value=='N'){
		document.getElementById(id).value='Y';
	} 
}

function add() {
    var from = document.getElementById('paraFrm_fromYear').value;
    if(from=="") {
    	 document.getElementById('paraFrm_toYear').value="";
    } else {
   	 var x=eval(from)+1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
}
</script>