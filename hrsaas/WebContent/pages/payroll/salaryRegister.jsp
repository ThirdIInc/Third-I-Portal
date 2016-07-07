<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SalaryRegister" validate="true" id="paraFrm" validate="true" theme="simple">
<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
	<tr>
		<td width="100%">
		<table width="100%" class="formbg">
			<tr>
				<td width="3%" valign="bottom" class="txt">
					<strong	class="formhead">
						<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />
					</strong>
				</td>
				<td width="93%" class="txt">
					<strong class="text_head">Salary Register</strong>
				</td>
				<td width="4%" valign="top" class="txt">
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
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
				<tr>
					<td>
						<s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="return callReport('Xls');" value=" Report " />
						</s:if> 
						 <s:if test="linkSource ==''">
						 	<input type="button" class="token" onclick="return reset()" value=" Reset " />
						</s:if>
						<s:else>						
							<input type="button" class="token" onclick="return backFun();" value=" Back " />		
						</s:else>
					</td>
					<td>
						<div align="right"><font color="red">*</font> Indicates	Required</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<s:hidden name="linkSource" />
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td colspan="4" class="formhead"><strong class="forminnerhead"></strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label id="month" name="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%></label>
						:<font color="red">*</font></td>
					<td colspan="1" width="30%">
					<s:if test="linkSource ==''">
						<s:select theme="simple" name="month" cssStyle="width:152"
						list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
					</s:if>
					<s:else>
						<s:property value="monthView"/><s:hidden name="month"/>
					</s:else>
					</td>
					<td colspan="1" width="20%">
						<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:
					</td>
					<td>
						<s:if test="linkSource ==''">
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25" />
						</s:if>
						<s:else>
							<s:textfield name="year" readonly="true" theme="simple" maxlength="4" />
						</s:else>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg">
			<tr>
				<td colspan="4"><strong class="formhead"><label id=selectReportFilter name="selectReportFilter"
					ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
				:</strong></td>
			</tr>
			<tr>
				<td width="20%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
					color="red">*</font>:</td>
				<td colspan="3" width="80%"><s:hidden name="divCode" /> <s:hidden name="divisionAbbrevation" /> 
					<s:textarea cols="105" rows="1"	name="divName" theme="simple" readonly="true" />
					<s:if test="linkSource ==''">
					<img src="../pages/images/recruitment/search2.gif" class="iconImage"	height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_divName',200,250,'SalaryRegister_f9div.action',event,'false','no','right')">
					</s:if>
				</td>
			</tr>
			<tr>
				<td><label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
				:</td>
				<td colspan="3"><s:hidden name="branchCode" />
				<s:textarea cols="105" rows="1" name="branchName" theme="simple" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_branchName',200,250,'SalaryRegister_f9centeraction.action',event,'false','no','right')">
				</td>
			</tr>
			<tr>
				<td><label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
				:</td>
				<td colspan="3"><s:hidden name="deptCode" />
				<s:textarea cols="105" rows="1" name="deptName" theme="simple" readonly="true" />
				<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_deptName',200,250,'SalaryRegister_f9deptaction.action',event,'false','no','right')">
				</td>
	
			</tr>
			<tr>
				<td><label class="set" id="paybill1" name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
				<td colspan="3" width="80%"><s:hidden name="paybillId" />
				<s:textarea cols="105" rows="1" theme="simple" name="paybillName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_paybillName',200,250,'SalaryRegister_f9paybill.action',event,'false','no','right')">
				</td>
	
			</tr>
			<tr>
				<td><label class="set" id="grade" name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
				<td colspan="3" width="80%"><s:hidden name="empGradeId" />
				<s:textarea cols="105" rows="1" theme="simple" name="empGradeName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_empGradeName',200,250,'SalaryRegister_f9empgrade.action',event,'false','no','right')">
				</td>
	
			</tr>
			<!--<tr>
				<td><label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
				:</td>
				<td colspan="3"><s:hidden name="typeCode" />
				<s:textarea cols="105" rows="1" name="typeName" theme="simple" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_typeName',200,250,'SalaryRegister_f9type.action',event,'false','no','right')">
				</td>
			</tr>
			<tr>
				<td ><label id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
				:</td>
				<td colspan="3"><s:hidden name="desgCode" /> 
				<s:textarea	cols="105" rows="1" name="desgName" theme="simple" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_desgName',200,250,'SalaryRegister_f9rankaction.action',event,'false','no','right')">
				</td>
			</tr>
			-->
			<tr>
				<td><label id="costcenter" name="costcenter" ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label>
				:</td>
				<td colspan="3"><s:hidden name="costcenterid" /> 
				<s:textarea cols="105" rows="1" name="costcentername" theme="simple" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_costcentername',200,250,'SalaryRegister_f9Costcenter.action',event,'false','no','right')">
				</td>
			</tr>
			<tr>
				<td><label id="subcostcenter"name="subcostcenter" ondblclick="callShowDiv(this);"><%=label.get("subcostcenter")%></label>
				:</td>
				<td colspan="3"><s:hidden name="subcostcenterid" /> 
				<s:textarea cols="105" rows="1" name="subcostcentername" theme="simple"	readonly="true"/>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_subcostcentername',200,250,'SalaryRegister_f9SubCostcenter.action',event,'false','no','right')">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
			<tr>
				<td colspan="4"><strong class="formhead">
				<label id="reportOptions" name="reportOptions" ondblclick="callShowDiv(this);"><%=label.get("reportOptions")%></label>
				:</strong></td>
			</tr>
			<tr>
				<td width="20%"><label id="nhold" name="nhold"
					ondblclick="callShowDiv(this);"><%=label.get("nhold")%></label>
				:</td>
				<td colspan="4"><s:select theme="simple" name="onHold"
					cssStyle="width:152" list="#{'A':'All','N':'No','Y':'Yes'}" />
				</td>
			</tr>
			<tr>
				<td width="25%">
					<input type="checkbox" name="checkFlag" id="checkFlag" onclick="callCheck();" />
					<label id=cons.arrears name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label>
				</td>
				<td>
					<input type="checkbox" name="chkConsSummary" id="chkConsSummary" onclick="callCheck();" />
					<label id="cons.summary" name="cons.summary" ondblclick="callShowDiv(this);"><%=label.get("cons.summary")%></label>
				</td>
				<td>
					<input type="checkbox" name="chkBrnOrder" id="chkBrnOrder" onclick="callCheck();" />
					<label id=brn.order name="brn.order" ondblclick="callShowDiv(this);"><%=label.get("brn.order")%></label>
				</td>
				<td>
					<input type="checkbox" name="chkDeptOrder" id="chkDeptOrder" onclick="callCheck();" />
					<label id="dept.order" name="dept.order" ondblclick="callShowDiv(this);"><%=label.get("dept.order")%></label>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
			<tr>
				<td colspan="4"><strong class="formhead">
					<label	id="add.report" name="add.report" ondblclick="callShowDiv(this);"><%=label.get("add.report")%></label>
				:</strong></td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td width="15%">
							<input type="checkbox" name="checkBrn" id="checkBrn" onclick="callCheck();" />
							<label id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						</td>
						<td width="15%">
							<input type="checkbox" name="checkDept" id="checkDept" onclick="callCheck();" />
							<label id="department1" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkDob" id="checkDob" onclick="callCheck();" />
							<label id="dob1" name="dob"	ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkEmpType" id="checkEmpType" onclick="callCheck();" />
							<label id="employee.type1" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkBank" id="checkBank" onclick="callCheck();" />
							<label id="bank1" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkAccount" id="checkAccount" onclick="callCheck();"/>
							<label id="ac.no1" name="ac.no"	ondblclick="callShowDiv(this);"><%=label.get("ac.no")%></label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" name="checkPan" id="checkPan" onclick="callCheck();"/>
							<label id="pan.no1" name="pan.no" ondblclick="callShowDiv(this);"><%=label.get("pan.no")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkDoj" id="checkDoj" onclick="callCheck();"/>
							<label id="join.date1" name="join.date"	ondblclick="callShowDiv(this);"><%=label.get("join.date")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkDesg" id="checkDesg" onclick="callCheck();"/>
							<label id="designation1" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkGender" id="checkGender" onclick="callCheck();"/>
							<label id="gender1" name="gender" ondblclick="callShowDiv(this);"><%=label.get("gender")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkHold" id="checkHold" onclick="callCheck();"/>
							<label id="nhold1" name="nhold" ondblclick="callShowDiv(this);"><%=label.get("nhold")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkGrade" id="checkGrade" onclick="callCheck();"/>
							<label id="salary.grade" name="salary.grade" ondblclick="callShowDiv(this);"><%=label.get("salary.grade")%></label>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" name="checkEmpGrade" id="checkEmpGrade" onclick="callCheck();" />
							<label id="grade" name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
						</td>
						<td>
							<input type="checkbox" name="paybillCheck" id="paybillCheck" onclick="callCheck();" />
							<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkEmployerPF" id="checkEmployerPF" onclick="callCheck();" />
							<label id="employer.PF" name="employer.PF" ondblclick="callShowDiv(this);"><%=label.get("employer.PF")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkEmployerESIC" id="checkEmployerESIC" onclick="callCheck();" />
							<label id="employer.ESIC" name="employer.ESIC" ondblclick="callShowDiv(this);"><%=label.get("employer.ESIC")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkCostCenter" id="checkCostCenter" onclick="callCheck();" />
							<label id="costcenter1" name="costcenter" ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label>
						</td>
						<td>
							<input type="checkbox" name="checkSubCostCenter" id="checkSubCostCenter" onclick="callCheck();" />
							<label id="subcostcenter1" name="subcostcenter" ondblclick="callShowDiv(this);"><%=label.get("subcostcenter")%></label>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="return callReport('Xls');" value=" Report " />
						</s:if>
						<s:if test="linkSource ==''">
					 		<input type="button" class="token" onclick="return reset()" value=" Reset " />
						</s:if>
						<s:else>						
							<input type="button" class="token" onclick="return backFun();"	value=" Back " />		
						</s:else>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<s:hidden name="report"/>
</s:form>
<script>
function backFun(){
	var action11=document.getElementById('paraFrm_linkSource').value;
	document.getElementById('paraFrm').action=action11;
	document.getElementById('paraFrm').submit();
}

function callReport(type) {
	document.getElementById('paraFrm_report').value=type;
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action="SalaryRegister_report.action";
		document.getElementById('paraFrm').submit();
	}
	document.getElementById('paraFrm').target = "_self";
}
	 
function mailReportFun(type) {	
	if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='SalaryRegister_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
}

function validateFields() {	
	var month = document.getElementById("paraFrm_month").value;
	var yea = document.getElementById("paraFrm_year").value;
	var divNm = document.getElementById("paraFrm_divName").value;
	var mont = document.getElementById('month').innerHTML.toLowerCase();
	var yer = document.getElementById('year').innerHTML.toLowerCase();
	var div=document.getElementById('division').innerHTML.toLowerCase();

	if(month =='0'){
		alert("Select "+mont);
		return false;
	}
	if(yea ==''){
		alert("Enter "+yer);
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(!checkYear('paraFrm_year','year')){
		return false;	 
	}
	if(divNm==""){
	 	alert("Please select the "+div);
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	}
	return true;
}

function callCheck(){
	var checkDefault = document.getElementById('checkFlag').checked;
	var brn= document.getElementById('checkBrn').checked;
	var dept= document.getElementById('checkDept').checked;
	var dob= document.getElementById('checkDob').checked;
	var emp=document.getElementById('checkEmpType').checked;
	var bnk=document.getElementById('checkBank').checked;
	var acc=document.getElementById('checkAccount').checked;
	var pan=document.getElementById('checkPan').checked;
	var desg=document.getElementById('checkDesg').checked;
	var doj=document.getElementById('checkDoj').checked;
	var gen=document.getElementById('checkGender').checked;
	var chkPaybill=document.getElementById('paybillCheck').checked;
	var hold=document.getElementById('checkHold').checked;
	var grd=document.getElementById('checkGrade').checked;
	var chkBrnOrder = document.getElementById('chkBrnOrder').checked;
	var chkDeptOrder = document.getElementById('chkDeptOrder').checked;
	var chkConsSummary = document.getElementById('chkConsSummary').checked;
	var checkEmployerESIC = document.getElementById('checkEmployerESIC').checked;
	var checkEmployerPF = document.getElementById('checkEmployerPF').checked;
	var checkCostCenter = document.getElementById('checkCostCenter').checked;
	var checkSubCostCenter = document.getElementById('checkSubCostCenter').checked;

	var empGrd=document.getElementById('checkEmpGrade').checked;

 	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	}
 	if(brn){
 		document.getElementById('checkBrn').value="Y";
 	}
 	if(dept){
 
 		document.getElementById('checkDept').value="Y";
 	}
 	if(dob){
 	    document.getElementById('checkDob').value="Y";
 	}
 	if(emp){
 		document.getElementById('checkEmpType').value="Y";
 	}
 	if(bnk){
 	  document.getElementById('checkBank').value="Y";
 	}
 	if(acc){
 	  document.getElementById('checkAccount').value="Y";
 	}
 	if(pan){
 	   document.getElementById('checkPan').value="Y";
 	}
 	if(doj){
 	   document.getElementById('checkDoj').value="Y";
 	}
 	if(desg){
		document.getElementById('checkDesg').value="Y";
 	}
 	if(gen){
		document.getElementById('checkGender').value="Y";
 	}
 	if(hold){
 	  document.getElementById('checkHold').value="Y";
 	}
 	if(grd){
		document.getElementById('checkGrade').value="Y";
 	}
 	if(chkPaybill){
		document.getElementById('paybillCheck').value="Y";
 	}
 	if(chkBrnOrder){
 		document.getElementById('chkBrnOrder').value="Y";
 	}
 	if(chkDeptOrder){
 		document.getElementById('chkDeptOrder').value="Y";
 	}
 	if(chkConsSummary){
 		document.getElementById('chkConsSummary').value="Y";
 	}
 	if(checkEmployerPF){
 		document.getElementById('checkEmployerPF').value="Y";
 	}
 	if(checkEmployerESIC){
 		document.getElementById('checkEmployerESIC').value="Y";
 	}
 	if(checkCostCenter){
 		document.getElementById('checkCostCenter').value="Y";
 	}
 	if(checkSubCostCenter){
 		document.getElementById('checkSubCostCenter').value="Y";
 	}
 	if(empGrd){
		document.getElementById('checkEmpGrade').value="Y";
 	}
 }

function callRadio(id) {
	
	if(id.value=="department")
	{
		document.getElementById('deptDiv').style.display='';
		document.getElementById('paybillDiv').style.display='none';
	}
	else if(id.value=="paybill"){
		document.getElementById('paybillDiv').style.display='';
		document.getElementById('deptDiv').style.display='none'
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

function resetFun(){
	document.getElementById("paraFrm_month").value="0";
	document.getElementById("paraFrm_year").value="";
	document.getElementById("paraFrm_report").value="0";
	getYear();
}
</script>

