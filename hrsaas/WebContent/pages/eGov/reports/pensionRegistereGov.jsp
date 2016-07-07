<%@include file="/pages/common/labelManagement.jsp" %>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PensionRegistereGov" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Pension
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
						<input type="button" class="report" onclick="return check();"
							value=" Report " />
					</s:if> <input type="button" class="reset" onclick="return reset()"
						value="  Reset" /></td>
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
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td  width="20%"><label id="brn.order" name="brn.order" ondblclick="callShowDiv(this);"><%=label.get("brn.order")%></label> :
							<input type="checkbox"
								name="chkBrnOrder" id="chkBrnOrder" onclick="callCheck();" /></td>
							<td  width="20%"><label id="dept.order" name="dept.order" ondblclick="callShowDiv(this);"><%=label.get("dept.order")%></label> :
							<input type="checkbox"
								name="chkDeptOrder" id="chkDeptOrder" onclick="callCheck();" /></td>
							<td  width="20%"><label id="desg.order" name="desg.order" ondblclick="callShowDiv(this);"><%=label.get("desg.order")%></label> :
							<input type="checkbox"
								name="chkDesgOrder" id="chkDesgOrder" onclick="callCheck();" /></td>		
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="penReg.month" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td colspan="1" width="20%"><label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td><s:textfield name="penReg.year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="penReg.divCode" />
							<s:textfield name="penReg.divName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PensionRegistereGov_f9div.action');">
							</td>
							<td colspan="1" width="20%"><label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="penReg.branchCode" /> <s:textfield
								name="penReg.branchName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PensionRegistereGov_f9centeraction.action');">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td><s:hidden name="penReg.deptCode" /> <s:textfield
								name="penReg.deptName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PensionRegistereGov_f9deptaction.action');">
							</td>
							<td colspan="1" width="20%"><label id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td><s:hidden name="penReg.desgCode" /> <s:textfield
								name="penReg.desgName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'PensionRegistereGov_f9rankaction.action');">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="penReg.report" cssStyle="width:152"
								list="#{'0':'Select --','Pdf':'Pdf','Xls':'Xls'}" /></td>
								
							<td colspan="1" width="20%">
							<label class="set" id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="penReg.payBillName" readonly="true" maxlength="50" size="25" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'PensionRegistereGov_f9PayBill.action');">
							<s:hidden name="penReg.payBillNo" />
						</td>
						
						</tr>
						
						<tr>
							<td colspan="1" width="20%"><label class="set" id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="penReg.onHold" cssStyle="width:152"
								list="#{'N':'No','Y':'Yes','A':'All'}" /></td>
								
							<td colspan="1" width="20%">
							
						</td>
						<td colspan="1" width="30%">
							
						</td>
						
						</tr>
						<tr>
							<td colspan="4"></td>
						</tr>	
						<tr>
							<td colspan="4"><strong class="formhead">Select any/all of the following options to add in the report</strong></td>
						</tr>			
						<tr>
							<td colspan="1" width="20%"><label id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkBrn" id="checkBrn" onclick="callCheck();" /></td>
							<td colspan="1" width="20%"><label id="department1" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDept" id="checkDept" onclick="callCheck();" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="dob1" name="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDob" id="checkDob" onclick="callCheck();" /></td>
							<td colspan="1" width="20%"><label id="retireDate" name="retireDate" ondblclick="callShowDiv(this);"><%=label.get("retireDate")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkRetDate" id="checkRetDate" onclick="callCheck();" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="bank1" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkBank" id="checkBank" onclick="callCheck();" /></td>
							<td colspan="1" width="20%"><label id="pan.no1" name="pan.no" ondblclick="callShowDiv(this);"><%=label.get("pan.no")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkPan" id="checkPan" onclick="callCheck();" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="join.date1" name="join.date" ondblclick="callShowDiv(this);"><%=label.get("join.date")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDoj" id="checkDoj" onclick="callCheck();" /></td>
							<td colspan="1" width="20%"><label id="designation1" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDesg" id="checkDesg" onclick="callCheck();" /></td>	
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="gender1" name="gender" ondblclick="callShowDiv(this);"><%=label.get("gender")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkGender" id="checkGender" onclick="callCheck();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	</table>
</s:form>

<script>

function callCheck(){
		
	var chkBrn=document.getElementById('chkBrnOrder').checked;
  	var chkDept=document.getElementById('chkDeptOrder').checked;
  	var chkDesg=document.getElementById('chkDesgOrder').checked;
   
	if(chkBrn){
 			document.getElementById('chkBrnOrder').value="Y"; 	
 	}
 	if(chkDept){
 			document.getElementById('chkDeptOrder').value="Y"; 	
 	}
 	if(chkDesg){
 			document.getElementById('chkDesgOrder').value="Y"; 	
 	}
 	 	
	 var brn= document.getElementById('checkBrn').checked;
	 var dept= document.getElementById('checkDept').checked;
	 var dob= document.getElementById('checkDob').checked;
	 var bnk=document.getElementById('checkBank').checked;
	 var pan=document.getElementById('checkPan').checked;
	 var desg=document.getElementById('checkDesg').checked;
	 var doj=document.getElementById('checkDoj').checked;
	 var gen=document.getElementById('checkGender').checked;
	 var retire=document.getElementById('checkRetDate').checked;
	 
 	if(brn){
 		document.getElementById('checkBrn').value="Y";
 	}
 	if(dept){
 		document.getElementById('checkDept').value="Y";
 	}
  	if(dob){
  	    document.getElementById('checkDob').value="Y";
 	}
  	if(bnk){
 	  document.getElementById('checkBank').value="Y";
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
 	if(retire){
 	  document.getElementById('checkRetDate').value="Y";
 	}
 	
 }
 function check()
 {	 		
	var month  =document.getElementById("paraFrm_penReg_month").value;
	var yea   =document.getElementById("paraFrm_penReg_year").value;
	var rep     =document.getElementById("paraFrm_penReg_report").value;
	var divNm   =document.getElementById("paraFrm_penReg_divName").value;
	
	var mont=document.getElementById('month').innerHTML.toLowerCase();
	var yer=document.getElementById('year').innerHTML.toLowerCase();
	
	var div=document.getElementById('division').innerHTML.toLowerCase();
	var report=document.getElementById('report.type').innerHTML.toLowerCase();
	
	 if(month =='0'){
	 	alert("Please select "+mont);
	 	return false;
	 }
	 if(yea ==''){
	 	alert("Please enter "+yer);
	 	return false;
	 }
	 if(!checkYear('paraFrm_penReg_year','year')){
	 	return false;	 
	 }
	 if(divNm==""){
	 	alert("Please select the "+div);
	 	return false;
	  }
	 if(rep=='0'){
	 	alert("Please select the "+report);
	 	return false;
	}
	 
	document.getElementById('paraFrm').target="_blank";	
	document.getElementById('paraFrm').action="PensionRegistereGov_getReport.action";	
	document.getElementById('paraFrm').submit();	
	document.getElementById('paraFrm').target="main";
}
function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_penReg_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_penReg_year").value =year;
	  }
}
getYear();	
</script>