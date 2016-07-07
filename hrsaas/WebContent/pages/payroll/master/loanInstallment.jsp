<%@ taglib uri ="/struts-tags" prefix = "s" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action = "LoanInstallment" target="main" theme="simple" validate ="true" id ="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Loan Details</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" />
				<s:hidden name="loanDetailsCode"></s:hidden>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<s:if test="%{insertFlag}">
								<s:submit cssClass="add"   action="LoanInstallment_save" theme="simple"  value="   Add New"/>
							</s:if>
							<s:if test="%{updateFlag}">
								<s:submit cssClass="edit"   action="LoanInstallment_save" theme="simple"  value="   Update"/>
							</s:if>
							<s:if test="%{viewFlag}">
								<s:submit cssClass="search"   action="LoanInstallment_showRecords" theme="simple" value="    Search"/>
							</s:if>
							<s:submit cssClass="reset"   action="LoanInstallment_reset" theme="simple"   value="    Reset" />
							<s:if test="%{deleteFlag}">
								<s:submit cssClass="delete"   action="LoanInstallment_delete" theme="simple" value="    Delete"/>
							</s:if>
							<s:if test="%{viewFlag}">
								<s:submit cssClass="token"   action="LoanInstallment_report" theme="simple" value="    Report"/>
							</s:if>
						</td>
						<td width="22%">
							<div align="right"><font color="red">*</font> Indicates Required</div>
						</td>
					</tr>
				</table>
			<label></label></td>
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
						<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead">Loan Details </strong></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Employee Name :</td>
									<td colspan="3" width="70%" nowrap="nowrap">
										<s:textfield size="10" theme="simple" name="empToken" readonly="true"/>
										<s:textfield size="50" theme="simple" name="empName" readonly="true"/>
										<s:hidden name="empCode"/>
										<s:if test="generalFlag"></s:if>
										<s:else><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
											onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">
										</s:else></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Department :</td>
									<td colspan="1" width="25%"><s:hidden name="deptCode"/>
										<s:textfield size="25" theme="simple" name="deptName" readonly="true"/></td>
									<td colspan="1" width="25%" >Designation :</td>
									<td colspan="1" width="25%"><s:hidden name="desgCode"/>
										<s:textfield size="25" theme="simple" name="desgName" readonly="true"/></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Loan Start Date :</td>
									<td colspan="1" width="25%" nowrap="nowrap">
										<s:textfield size="25" theme="simple" name="loanStartdate"/>
										<s:a href="javascript:NewCal('paraFrm_loanStartdate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
										</s:a></td>
									<td colspan="1" width="25%" >Loan Type :</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:hidden name="loanTypeCode"/>
										<s:textfield size="25" theme="simple" name="loanType" readonly="true"/>
										<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
											onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');"></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Loan Recovery Type :</td>
									<td colspan="1" width="25%">
											<s:select  name="recoveryType" cssStyle="width:150" headerKey="" headerValue="Select"
											list="#{'1':'Principal First','2':'Interest  First','3':'Both'}"/></td>
									<td colspan="1" width="25%" >Rate of Interest :</td>
									<td colspan="1" width="25%" nowrap="nowrap">
										<s:textfield size="25" theme="simple" name="interestRate"/></td></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Total Principal Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="principalAmount"/></td>
									<td colspan="1" width="25%" >Total Interest Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="interestAmount"/></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Monthly Installment Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="monthInstAmount"/></td>
									<td colspan="1" width="25%" >Balance Interest Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="balanceInterestAmount"/></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Principal Balance Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="principalBalanceAmount"/></td>
									<td colspan="1" width="25%" >Total Number of Installments :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="totalInstallments"/></td>
								</tr>
								
								<tr>
									<td colspan="1" width="25%" >Balance Installments Number :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="balanceInstallments"/></td>
									<td colspan="1" width="25%" >Penalty Interest Amount :</td>
									<td colspan="1" width="25%">
										<s:textfield size="25" theme="simple" name="penaltyInstAmount"/></td>
								</tr>
								
								<tr>
									<td colspan="3" valign="bottom" class="txt"><img
										src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
								</tr>
							</table>
							
							<table class="formbg" width="100%">
								<tr>
									<td class="formth" width="10%">Loan Type</td>
									<td class="formth" width="10%">Start Date</td>
									<td class="formth" width="13%">Rate of Interest</td>
									<td class="formth" width="12%">Total Principal Amount</td>
									<td class="formth" width="12%">Total Interest Amount</td>
									<td class="formth" width="12%">Principal Balance Amount</td>
									<td class="formth" width="12%">Interest Balance Amount</td>
									<td class="formth" width="15%">Total Number of Installments</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
	</table>
	
	<s:hidden name="loanInstallment.paracode" />
	<s:hidden theme="simple" name ="loanInstallment.installCode" />
		<s:hidden theme="simple" name ="loanInstallment.loanEmpId"  />
	<s:hidden theme="simple" name ="loanInstallment.loanCode"  />
</s:form> 
<script >

function callAdd(){
	
	var empId = document.getElementById('loanInstallment.loanEmpId').value;
	var loanType = document.getElementById('loanInstallment.loanType').value;
	var loanStartDate = document.getElementById('loanInstallment.loanStartDate').value;
	var interestRate = document.getElementById('loanInstallment.interestRate').value;
	var totalPrincipalAmount = document.getElementById('loanInstallment.totalPrincipalAmount').value;
	var totalInterestAmount = document.getElementById('loanInstallment.totalInterestAmount').value;
	var totalInstallNo = document.getElementById('loanInstallment.totalInstallNo').value;
	
	var monthInstallAmount = document.getElementById('loanInstallment.monthInstallAmount').value;
	var interestBalanceAmount = document.getElementById('loanInstallment.interestBalanceAmount').value;
	var principleBalanceAmount = document.getElementById('loanInstallment.principleBalanceAmount').value;
	var balanceInstallNo = document.getElementById('loanInstallment.balanceInstallNo').value;
	var penaltyInterestAmount = document.getElementById('loanInstallment.penaltyInterestAmount').value;
	
	if(empId==""){
			alert ("Please select the Employee !");  
			return false;
		}
		
	if(loanType==""){
			alert ("Please select the Loan Type !");
			return false;
		}
	if(loanStartDate==""){
			alert ("Please Enter the Loan Start Date  !");
			return false;
		}
	var checkDate = validateDate(loanStartDate);
	
	if(!checkDate){
		alert (" Please Enter valid Date !");
		return false;
	}	
		
	
	if(interestRate==""){
			alert ("Please Enter Rate of Interest !");
			return false;
		}
	var checkNumber = validNumber(interestRate);
	if(!checkNumber){
		alert (" Rate of Interest should be the Number. !");
		return false;
	}		
	if(totalPrincipalAmount==""){
			alert ("Please Enter the Total Principal Amount !");
			return false;
		}
	var checkNumberP = validNumber(totalPrincipalAmount);
	if(!checkNumberP){
		alert (" Total Principal Amount should be the Number. !");
		return false;
	}			
				
	if(totalInterestAmount==""){
			alert ("Please Enter the Total Interest Amount !");
			return false;
		}
	var checkNumberI = validNumber(totalInterestAmount);
	if(!checkNumberI){
		alert (" Total Interest Amount should be the Number. !");
		return false;
	}			
	if(totalInstallNo==""){
			alert ("Please Enter the Total Installment No.!");
			return false;
		}
	var checkNumberN = validNumber(totalInstallNo);
	
	if(!checkNumberN){
		alert (" Total Installment No. should be the Number. !");
		return false;
	}
	var checkNumberM = validNumber(monthInstallAmount);
	
	if(!checkNumberM){
		alert (" Monthly Installment Amount should be the Number. !");
		return false;
	}
	
	var checkNumberIB = validNumber(interestBalanceAmount);
	if(!checkNumberIB){
		alert (" Interest Balance Amount should be the Number. !");
		return false;
	}
	var checkNumberPB = validNumber(principleBalanceAmount);
	if(!checkNumberPB){
		alert (" Principle Balance Amount should be the Number. !");
		return false;
	}
	var checkNumberBN = validNumber(balanceInstallNo);
	if(!checkNumberBN){
		alert (" Balance Installment No. should be the Number. !");
		return false;
	}
	var checkNumberPA = validNumber(penaltyInterestAmount);
	if(!checkNumberPA){
		alert (" Penalty Interest Amount should be the Number. !");
		return false;
	}
			
													
			
	return true ;
}

 function callReport(name){
	document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action = name;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
} 
function callForEdit(id){
	   	document.getElementById("paraFrm").action="LoanInstallment_edit.action";
	    document.getElementById('loanInstallment.paracode').value=id;
	    document.getElementById("paraFrm").submit();
   }
function callDelete(id){
	var r=confirm("Do you want to delete")
		if(r==false){
			return false;
		}else{

	   	document.getElementById("paraFrm").action="LoanInstallment_delete.action";
	   	document.getElementById('loanInstallment.paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }
   }
function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   } 
 function validNumber(fld){  
   
   if(!(fld=="")){
			var iChars = "0123456789.";
		  		for (var i = 0; i < fld.length; i++) {			
			  	if (!(iChars.indexOf(fld.charAt(i)) != -1)) {
				  	
				  	return false;
  					}
  				}
		}
		return true;
	}
   
</script>