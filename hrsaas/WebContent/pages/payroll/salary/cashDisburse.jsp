<!-- MODIFIED BY: REEBA JOSEPH 17 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<s:form action="CashDisburse" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table class="formbg" width="100%">
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Claim
					Disbursement</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><!--<s:submit cssClass="add"
								action="CashDisburse_save" theme="simple" value="    Save "
								onclick="return callSave()" /> <s:submit cssClass="reset"
								action="CashDisburse_reset" value="   Reset " />--> <jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong>Employee Details</strong></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="employee" id="employee2"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					: </td>
					<td width="80%" colspan="3"><s:property value="viewEmpToken" />
					- <s:property value="viewEmpName" /><s:hidden name="viewEmpId" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>: </td>
					<td width="30%" colspan="1"><s:property value="branchName" /></td>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label
					: </td>
					<td width="30%" colspan="1"><s:property value="departmentName" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						class="set" name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label: </td>
					<td width="30%" colspan="1"><s:property value="divisionName" /></td>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					: </td>

					<td width="30%" colspan="1"><s:property
						value="designationName" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="appl.date" name="appl.date"
						ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
					: </td>
					<td width="30%" colspan="1"><s:property value="disburseDate" /><s:hidden name="disburseDate" />
					<s:hidden name="claimId"/></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>: </td>
					<td width="30%" colspan="1"><s:property value="disburseStatus" /></td>
					<s:hidden name="disburseStatus" />

				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong>Payment Details</strong></td>
				</tr>
				
				
				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label class="set" name="pay.date"
						id="pay.date" ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label>
					<font color="red">*</font>: </td>
					<td width="30%" colspan="1"><s:textfield name="paymentDate"
						theme="simple" size="20" maxLength="10"
						onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16" id="ctrlHide"> </a></td>

					<td width="20%" colspan="1" class="formtext"><label class="set" name="pay.mode"
						id="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label>
					: </td>
					<td width="30%" colspan="1"><s:select name="paymentmode"
						cssStyle="width:150" theme="simple"
						list="#{'CA':'Cash','TR':'Transfer'}"
						onchange="return changeCombo();" /></td>
				</tr>

				<tr id="trans">
					<td width="20%" colspan="1" class="formtext" height="22"><label class="set" name="acco.no"
						id="acco.no" ondblclick="callShowDiv(this);"><%=label.get("acco.no")%></label>
					<font color="red">*</font>: </td>
					<td width="30%" colspan="1"><s:textfield name="accountNo"
						theme="simple" size="20" maxLength="10"
						onkeypress="return numbersOnly();" /></td>

					<td width="20%" colspan="1" class="formtext"><label class="set"
						name="bank.name" id="bank.name" ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
					<font color="red">*</font>: </td>
					<td width="30%" colspan="1"><s:textfield name="bank"
						theme="simple" size="20" maxLength="10"
						onkeypress="return numbersOnly();" /> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple"  id="ctrlHide"
						onclick="javascript:callsF9(500,325,'CashDisburse_f9Bank.action');">
					</td>
					<s:hidden name="bankid" />	
						
				</tr>
				
				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="disbAmount" name="disbAmount"
						ondblclick="callShowDiv(this);"><%=label.get("disbAmount")%></label>
					: </td>
					<td width="80%" colspan="3"><s:property value="disburseAmount" /><s:hidden
						name="disburseAmount" /></td>
				</tr>
				
				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label class="set" name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
					: </td>
					<td width="80%" colspan="3"><s:hidden name="descCnt1" /><s:textarea
						name="comment" rows="3" cols="70" readonly="false" /><img
						src="../pages/images/zoomin.gif" height="12" align="bottom"
						width="12" theme="simple"  id="ctrlHide"
						onclick="javascript:callWindow('paraFrm_comment','comments','','paraFrm_descCnt1','450');"></td>
				</tr>

				
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

changeCombo();

 function changeCombo(){
 	//alert("changeCombo");
 	var status=document.getElementById('paraFrm_paymentmode').value;
 	if(status=='CA'){
		document.getElementById('trans').style.display='none';
	}else{
		document.getElementById('trans').style.display='';
	}
}

function sendbackFun(){
	var comments = trim(document.getElementById('paraFrm_comment').value);
	if(comments==""){
		alert("Please enter "+document.getElementById('comments').innerHTML.toLowerCase());
 		document.getElementById('paraFrm_comment').focus();
 		return false;
	}
	if(comments.length > 450){
 		alert("Maximum length of "+document.getElementById('comments').innerHTML.toLowerCase()+" is 450 characters.");
 		document.getElementById('paraFrm_comment').focus();
 		return false;
 	}
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='CashDisburse_save.action?status=B';
   	document.getElementById('paraFrm').submit()
}

function saveFun(){
	try{
	if(document.getElementById('paraFrm_paymentDate').value==""){
		alert("Please enter "+document.getElementById('pay.date').innerHTML.toLowerCase());
		return false;
	}
	if(!validateDate("paraFrm_paymentDate","pay.date")){
  		return false;
  	}
  	var paydate=trim(document.getElementById('paraFrm_paymentDate').value);
  	if(!dateCompare(currentDate,paydate,'paraFrm_paymentDate','pay.date'))
        return false;
  	if(document.getElementById('paraFrm_paymentmode').value=="TR"){
  		if(document.getElementById('paraFrm_accountNo').value==""){
  			alert("Please select "+document.getElementById('acco.no').innerHTML.toLowerCase());
  			return false;
  		}
  		if(document.getElementById('paraFrm_bankid').value==""){
  			alert("Please select "+document.getElementById('bank.name').innerHTML.toLowerCase());
  			return false;
  		}
  	}
  	var comments = trim(document.getElementById('paraFrm_comment').value);
  	if(comments.length > 450){
 		alert("Maximum length of "+document.getElementById('comments').innerHTML.toLowerCase()+" is 450 characters.");
 		document.getElementById('paraFrm_comment').focus();
 		return false;
 	}
  	}catch(e){alert(e);}
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='CashDisburse_save.action?status=D';
   	document.getElementById('paraFrm').submit();
}

var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
var currentDate=day+"-"+month+"-"+year ;
function dateCompare(fromDate, toDate, fieldName,toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(document.getElementById(toLabName).innerHTML+" should be greater or equal to current date.");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

function backFun(){
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='CashDisburse_input.action';
   	document.getElementById('paraFrm').submit();
}

function callReport() {


 var fDate=document.getElementById('paraFrm_fromDate').value;
 	var tDate=document.getElementById('paraFrm_toDate').value;
 
 
  	if(!validateDate("paraFrm_fromDate","From Date")){
  	return false;
  	}
  	  	
  	if(!validateDate("paraFrm_toDate","To Date")){
		return false;
	 }
		
	if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'From Date', 'To Date')){
	
	return false;
	}
	



}



  
	
	function callSave() {
	
			
	if (document.getElementById('paraFrm_checkboxSel').value !="C")
		{
		alert('Please Select an Application.');
		return false;
		}
	
	}	  
  
	function callChk(id){
	
 	if(document.getElementById(id).value=='Y'){
  		document.getElementById(id).value='N';
  		 document.getElementById('paraFrm_checkboxSel').value='';
 		}else  if(document.getElementById(id).value=='N'){
  		document.getElementById(id).value='Y';
  		 document.getElementById('paraFrm_checkboxSel').value='C';
 		} 
	}
	
	

  </script>

