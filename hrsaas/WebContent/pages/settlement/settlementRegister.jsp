<%@include file="/pages/common/labelManagement.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="SettlementRegister" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Settlement
					Register </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><s:if test="%{viewFlag}">
						<input type="button" class="token" onclick="return check();"
							value=" Report " />
					</s:if> <input type="button" class="token" onclick="return resetBtn();"
						value=" Reset " /></td>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						
						<input type="hidden" name="chkBrnOrder" id="chkBrnOrder" />
						<input type="hidden" name="chkDeptOrder" id="chkDeptOrder" />
						<!--  <tr>

							<td colspan="1" width="20%"><label id=brn.order name="brn.order"
								ondblclick="callShowDiv(this);"><%//=label.get("brn.order")%></label>:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="chkBrnOrder" id="chkBrnOrder" onclick="callCheck();" />
							</td>

							<td colspan="1" width="20%"><label id="dept.order" name="dept.order"
								ondblclick="callShowDiv(this);"><%//=label.get("dept.order")%></label>:</td>
							<td><input type="checkbox"
								name="chkDeptOrder" id="chkDeptOrder" onclick="callCheck();" /></td>
						</tr>-->
						
						
						<!-- 
						<tr>
							<td colspan="1" width="20%"><label id="brndept"
								name="brndept" ondblclick="callShowDiv(this);"><%//=label.get("brndept")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="chkBrnDept" id="chkBrnDept" onclick="callCheck();" /></td>
						</tr>
						 -->

						<tr>

							<td colspan="1" width="20%"><label id="fromMonth" name="fromMonth"
								ondblclick="callShowDiv(this);"><%=label.get("fromMonth")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="fromMonth" cssStyle="width:100"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							<s:textfield name="fromYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="5" /></td>
							<td colspan="1" width="20%"><label id="toMonth" name="toMonth"
								ondblclick="callShowDiv(this);"><%=label.get("toMonth")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="toMonth" cssStyle="width:100"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							<s:textfield name="toYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="5" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'SettlementRegister_f9div.action');">

							</td>
							<td colspan="1" width="20%"><label id="department"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:hidden name="deptCode" /> <s:textfield
								name="deptName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'SettlementRegister_f9deptaction.action');">
							</td>

						</tr>



						<tr>

							<td colspan="1" width="20%"><label id="branch" name="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden
								name="branchCode" /> <s:textfield
								name="branchName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'SettlementRegister_f9centeraction.action');">

							</td>

							<td colspan="1" width="20%"><label id="designation"
								name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td><s:hidden name="desgCode" /> <s:textfield
								name="desgName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'SettlementRegister_f9rankaction.action');">
							</td>
						</tr>



						<tr>

							<td colspan="1" width="20%"><label id="employee.type"
								name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="typeCode" />
							<s:textfield name="typeName" theme="simple"
								readonly="true" maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'SettlementRegister_f9type.action');">

							</td>
							<td colspan="1" width="20%"><label id="report.type"
								name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="report" cssStyle="width:152"
								list="#{'Xls':'Xls','Pdf':'Pdf'}" /></td>
							
						</tr>

						
					<!--  	<tr>
							<td colspan="4"><strong class="formhead"><label
								id="add.report" name="add.report"
								ondblclick="callShowDiv(this);"><%//=//label.get("add.report")%></label>
							:</strong></td>
						</tr>

						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>

						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="branch1"
								name="branch" ondblclick="callShowDiv(this);"><%//=label.get("branch")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkBrn" id="checkBrn" onclick="callCheck();" /></td>


							<td colspan="1" width="20%"><label id="department1"
								name="department" ondblclick="callShowDiv(this);"><%//=label.get("department")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDept" id="checkDept" onclick="callCheck();" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="dob1" name="dob"
								ondblclick="callShowDiv(this);"><%//=label.get("dob")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDob" id="checkDob" onclick="callCheck();" /></td>


							<td colspan="1" width="20%"><label id="employee.type1"
								name="employee.type" ondblclick="callShowDiv(this);"><%//=label.get("employee.type")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkEmpType" id="checkEmpType" onclick="callCheck();" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="bank1" name="bank"
								ondblclick="callShowDiv(this);"><%//=label.get("bank")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkBank" id="checkBank" onclick="callCheck();" /></td>


							<td colspan="1" width="20%"><label id="ac.no1" name="ac.no"
								ondblclick="callShowDiv(this);"><%//=label.get("ac.no")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkAccount" id="checkAccount" onclick="callCheck();" /></td>
						</tr>




						<tr>

							<td colspan="1" width="20%"><label id="pan.no1"
								name="pan.no" ondblclick="callShowDiv(this);"><%//=label.get("pan.no")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkPan" id="checkPan" onclick="callCheck();" /></td>

							<td colspan="1" width="20%"><label id="join.date1"
								name="join.date" ondblclick="callShowDiv(this);"><%//=label.get("join.date")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDoj" id="checkDoj" onclick="callCheck();" /></td>


						</tr>


						<tr>

							<td colspan="1" width="20%"><label id="designation1"
								name="designation" ondblclick="callShowDiv(this);"><%//=label.get("designation")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDesg" id="checkDesg" onclick="callCheck();" /></td>

							<td colspan="1" width="20%"><label id="gender1"
								name="gender" ondblclick="callShowDiv(this);"><%//=label.get("gender")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkGender" id="checkGender" onclick="callCheck();" /></td>



						</tr>

						<tr>

							<td colspan="1" width="20%"><label id="nhold1" name="nhold"
								ondblclick="callShowDiv(this);"><%//=label.get("nhold")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkHold" id="checkHold" onclick="callCheck();" /></td>

							<td colspan="1" width="20%"><label id="grade" name="grade"
								ondblclick="callShowDiv(this);"><%//=label.get("grade")%></label>
							:</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkGrade" id="checkGrade" onclick="callCheck();" /></td>



						</tr>
						
					

-->

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>

	</table>
</s:form>



<script>
/*function getYear(){
	var current = new Date();
	 var year =current.getYear();
	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}
getYear();	*/

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
 //var chk=document.getElementById('chkBrnDept').checked;
 var hold=document.getElementById('checkHold').checked;
 var grd=document.getElementById('checkGrade').checked;
 var chkBrnOrder = document.getElementById('chkBrnOrder').checked;
 var chkDeptOrder = document.getElementById('chkDeptOrder').checked;
 var chkConsSummary = document.getElementById('chkConsSummary').checked;
 var checkEmployerESIC = document.getElementById('checkEmployerESIC').checked;
 var checkEmployerPF = document.getElementById('checkEmployerPF').checked;

 /*if(chk){
 			document.getElementById('chkBrnDept').value="Y";
 	
 	}*/
 
 
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
 	
 
 }
function check()
 {	
 try{
	 var month  =document.getElementById("paraFrm_fromMonth").value;
	 var yea   =document.getElementById("paraFrm_fromYear").value;
	 var toMonth  =document.getElementById("paraFrm_toMonth").value;
	 var toYear   =document.getElementById("paraFrm_toYear").value;
	var rep     =document.getElementById("paraFrm_report").value;
	var divNm   =document.getElementById("paraFrm_divName").value;
	var mont="From Month";
	var yer="From Year";
	var toMonthLabel="To Month";
	var toYearLabel="To Year";
	
	var div=document.getElementById('division').innerHTML.toLowerCase();
	var report=document.getElementById('report.type').innerHTML.toLowerCase();
	

	 if(month =='0'){
	 	alert("Select "+mont);
	 	return false;
	 }
	 if(yea ==''){
	 	alert("Enter "+yer);
	 	return false;
	 }
	  if(toMonth =='0'){
	 	alert("Select "+toMonthLabel);
	 	return false;
	 }
	 if(toYear ==''){
	 	alert("Enter "+toYearLabel);
	 	return false;
	 }
	 if(!checkYearFun('paraFrm_fromYear','from year')){
	 	return false;	 
	 }
	  if(!checkYearFun('paraFrm_toYear','to year')){
	 	return false;	 
	 }
	 if(eval(month)>eval(toMonth) && (eval(fromYear)>=eval(toYear))){
	 	alert("From period must be less than or equal to period");
	 }
	 if(divNm==""){
	 	alert("Please select the "+div);
	 	return false;
	 
	 }
	 
	
	 if(rep=='0'){
	 	alert("Please select the "+report);
	 	return false;
	}
	 	
	 }catch(e){alert(e)}
	 document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="SettlementRegister_getSalaries.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
}

function callRadio(id)
{
	
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
 function checkYearFun(name, labelName){
   		var year = document.getElementById(name).value;
   		if(year == "") {
   			return true;
   		}
   		if(year.length < 4) {
   			alert(labelName + " should have atleast 4 digits");
   			document.getElementById(name).focus();
   			return false;
   		}
   		return true;
   }
function resetBtn(){
			document.getElementById('paraFrm_fromMonth').value="0";
			document.getElementById('paraFrm_toMonth').value="0";
			document.getElementById('paraFrm_fromYear').value="";
			document.getElementById('paraFrm_toYear').value="";
			document.getElementById('paraFrm_typeCode').value="";
			document.getElementById('paraFrm_typeName').value="";
			document.getElementById('paraFrm_deptCode').value="";
			document.getElementById('paraFrm_deptName').value="";
			document.getElementById('paraFrm_branchCode').value="";
			document.getElementById('paraFrm_branchName').value="";
			document.getElementById('paraFrm_divCode').value="";
			document.getElementById('paraFrm_divName').value="";
			document.getElementById('paraFrm_desgCode').value="";
			document.getElementById('paraFrm_desgName').value="";
			document.getElementById('paraFrm_report').value="Xls";

}
</script>

