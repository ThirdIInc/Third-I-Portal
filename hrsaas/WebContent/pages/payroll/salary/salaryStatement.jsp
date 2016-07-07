<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="SalaryStatement" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Payment Statement</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td>
				 	<!--  
	 			  	<input type="button" class="token" value=" Payment Statement " onclick="return validatePaymentStatement('SalaryStatement_report.action');">
	  		 			<input type="button" class="token" value=" Bank Statement " onclick="return validatePaymentStatementBank('SalaryStatement_bankStmt.action');">
	  		 		-->
	  		 		<input type="button" class="token" value="Covering Letter" onclick="return validate('SalaryStatement_previewCoveringLetter.action','C');" />
					<input type="button" class="token" value="Bank Statement" onclick="return validate('SalaryStatement_bankStatementView.action','B');" />
					<input type="button" class="token" value="  Reset  " onclick="reset()">
				
				</td>
				<td> 
				<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
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
					
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font></td>
							<td colspan="1" width="30%">
							<s:select theme="simple" name="month" cssStyle="width:152"
							list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font color="red">*</font></td>
							<td>
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25"  />
							</td>
						</tr>	
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font></td>
							<td colspan="1" width="30%">
							<s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9div.action');">	
							
							</td>
							
							
						</tr>	
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="brnCode" />
							<s:textfield name="brnName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9brn.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td>
							<s:hidden name="deptCode" />
								<s:textfield name="deptName" theme="simple"  readonly="true" maxlength="50" size="25" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9dept.action');">
							</td>
						</tr>		
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="typeCode" />
							<s:textfield name="typeName" theme="simple" readonly="true"  maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9type.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :</td>
							<td>
							<s:hidden name="payBillNo"/>
							<s:textfield name="payBillName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9payBill.action');">
							</td>
						</tr>
						
						
						<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label> :</td>
						<td colspan="1" width="30%">
							<s:select theme="simple" name="onHold" cssStyle="width:50"
							list="#{'A':'All','N':'No','Y':'Yes'}" />
						</td>
					
						<td colspan="1" width="20%" >
						<label  class = "set"  id="pay.mode" name="pay.mode" ondblclick="callShowDiv(this);">
						<%=label.get("pay.mode")%></label> :</td>
						<!-- <font color="red">*</font> -->
						<td>
						<s:select theme="simple" name="payMode" cssStyle="width:150"
						list="#{'A':'All','T':'Transfer','C':'Cash','H':'Cheque'}" />
						</td>
					
					</tr>
						
						
						
							
						<tr>
										
						
							<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :<font color="red">*</font></td>
							<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:152"
							list="#{'0':'Select --','Xls':'Xls','Txt':'Text'}" /></td>
							<!-- ,'Txt':'Text' 'Pdf':'Pdf',-->
						
						
							<td colspan="1" width="20%"><label  class = "set"  id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :</td>
							<td>
							<s:hidden name="bankCode" />
							<s:textfield name="bankName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<s:hidden name="bankBrnch" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9bank.action');">
							</td>
						<tr>
						
						<!--<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label> :</td> 
					
						<td colspan="1" width="30%"><input type="checkbox" name="checkFlag"  id="checkFlag"
						 onclick="callCheck();" /></td>
						</tr>
							
							
							
							-->
							
							
							
						
						<tr>
					
						<td class="txt"><strong class="formhead"><label  class = "set"  id="covletter" name="covletter" ondblclick="callShowDiv(this);"><%=label.get("covletter")%></label>
			 			</strong></td>
						</tr>
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="chequeno" name="chequeno" ondblclick="callShowDiv(this);"><%=label.get("chequeno")%></label> :<font color="red">*</font> </td>
							<td colspan="1" width="30%">
						
							<s:textfield name="chq" theme="simple" maxlength="50" size="25" onkeypress="return numbersOnly();"/>
								
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="chedate" name="chedate" ondblclick="callShowDiv(this);"><%=label.get("chedate")%></label> :<font color="red">*</font></td>
							<td>
							<s:textfield name="chqDate" theme="simple" 
							onkeypress="return numbersWithHiphen();" maxlength="10" 
							 size="25" />
							<s:a href="javascript:NewCal('paraFrm_chqDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
							
							
							</td>
						</tr>
						
						<tr>
							<td colspan="1" width="20%">
							<label  class = "set"  id="template" name="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label> :</td>
							<td>
							<s:hidden name="templatecode" />
							<s:textfield name="templatename" theme="simple"  readonly="true" maxlength="50" size="25" />
							
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'SalaryStatement_f9coveringLetter.action');">
							</td>
							
						</tr>
						
						
							
						
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
							
							<td colspan="3" width="20%" >
								
										
									<!-- 	<input type="button" class="token" value="Covering Letter" onclick="return validate('SalaryStatement_coveringReport.action');" />
									 -->
									<input type="button" class="token" value="Covering Letter" onclick="return validate('SalaryStatement_previewCoveringLetter.action','C');" />
									
									<input type="button" class="token" value="Bank Statement" onclick="return validate('SalaryStatement_bankStatementView.action','B');" />
									<input type="button" class="token" value="  Reset  " onclick="reset()">
									</td>
							</tr>
		
		</table>
		</td></tr></table>
		</s:form>
<script >
callCheck1();
function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked;
 
 	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	
 	}
 	
 
 }
 function callCheck1(){
 getYear();	
 document.getElementById('checkFlag').checked=true;
 //document.getElementById('checkFlag').value="Y";
 }
function reset(){
document.getElementById("paraFrm_month").value=="0";
document.getElementById("paraFrm_year").value=="";
document.getElementById("paraFrm_divCode").value=="";
document.getElementById("paraFrm_divName").value=="";
document.getElementById("paraFrm_brnCode").value=="";
document.getElementById("paraFrm_brnName").value=="";
document.getElementById("paraFrm_deptCode").value=="";
document.getElementById("paraFrm_deptName").value=="";
document.getElementById("paraFrm_typeCode").value=="";
document.getElementById("paraFrm_typeName").value=="";
document.getElementById("paraFrm_payBillNo").value=="";
document.getElementById("paraFrm_payMode").value=="";
document.getElementById("paraFrm_payBillName").value=="";
getYear();
}

function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}




function validate(id,flag){
 var fDate=document.getElementById('paraFrm_chqDate').value;
 
 var pay=document.getElementById('paraFrm_payMode').value;
 
  var reportType=document.getElementById('paraFrm_reportType').value;
  
 if(document.getElementById("paraFrm_month").value=="0"){
  alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
  document.getElementById("paraFrm_month").focus();
  return false;  
 }if(document.getElementById("paraFrm_year").value==""){
  alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
  document.getElementById("paraFrm_year").focus();
  return false;  
 }
 
 if(document.getElementById("paraFrm_divName").value==""){
  alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
  document.getElementById("paraFrm_divName").focus();
  return false;  
 }
if(flag!='C')
{
 if(document.getElementById('paraFrm_reportType').value=="0")
 {	
  alert("Please Select the "+document.getElementById('report.type').innerHTML.toLowerCase());
   document.getElementById("paraFrm_reportType").focus();
  return false;
 }
 }
if(flag=='C')	 
 		{
  if(document.getElementById("paraFrm_chq").value==""){
 	alert("Please enter the "+document.getElementById('chequeno').innerHTML.toLowerCase());
  	document.getElementById("paraFrm_chq").focus();
  	return false;
  
  }
   if(fDate=="") {
 	alert("Please Select the "+document.getElementById('chedate').innerHTML.toLowerCase());
 	document.getElementById("paraFrm_chqDate").focus();
  	return false;
  }
 }


 
  
   if(!validateDate('paraFrm_chqDate',"chedate"))
          return false;	 
  /*
  if(pay=='0'){
		alert("Please select the "+document.getElementById('pay.mode').innerHTML.toLowerCase());
 		return false;	
 
 			}
 			*/
 			
 		if(flag=='C')	 
 		{
 		if(document.getElementById("paraFrm_templatename").value==""){
		alert("Please select the "+document.getElementById('template').innerHTML.toLowerCase());
 		return false;	
 
 			}
 			}
 			
 		    	callReport(id);
 			
 		
 
}








function validatePaymentStatement(id){
 var fDate=document.getElementById('paraFrm_chqDate').value;
 var rep = document.getElementById('paraFrm_reportType').value;	
 var pay=document.getElementById('paraFrm_payMode').value;
 if(document.getElementById("paraFrm_month").value=="0"){
  alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
  document.getElementById("paraFrm_month").focus();
  return false;  
 }if(document.getElementById("paraFrm_year").value==""){
  alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
  document.getElementById("paraFrm_year").focus();
  return false;  
 }if(document.getElementById("paraFrm_divCode").value==""){
  alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
  document.getElementById("paraFrm_year").focus();
  return false;  
 }if(rep=='0'){
	 		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 		return false;
			}
			
 if(pay=='0'){
		alert("Please select the "+document.getElementById('pay.mode').innerHTML.toLowerCase());
 		return false;	
 
 			}
 			

 callReport(id);
}

function validatePaymentStatementBank(id){

 
			  if(document.getElementById("paraFrm_month").value=="0"){
			     alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
			     document.getElementById("paraFrm_month").focus();
			     return false;  
			   }
			 
			  if(document.getElementById("paraFrm_year").value==""){
			    alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
			    document.getElementById("paraFrm_year").focus();
			    return false;  
			 }
			 
			 if(document.getElementById("paraFrm_divName").value==""){
			    alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
			    document.getElementById("paraFrm_divName").focus();
			    return false;  
			 }
			
		
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=id;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";



}

 
function callReport(id){	
var fDate=document.getElementById('paraFrm_chqDate').value;

 	
 
  	if(!validateDate("paraFrm_chqDate","Cheque Date")){
  	return false;
  	}	
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=id;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";
		//}else{	
		//	document.getElementById('paraFrm').submit();		
		//}						
}

function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}

</script>

 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>