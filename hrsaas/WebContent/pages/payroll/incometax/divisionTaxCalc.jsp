<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="DivisionTaxCalc" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax
					Calculation Process</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<tr>
					<td><!--<s:submit cssClass="token" action="TaxSlab_save"
											theme="simple" value="  Save   " onclick="return formValidate()" />
								
								
											--> <input type="button" class="token"
						onclick="callCalculateTax();" theme="simple" value="Calculate Tax" />
					<!--<s:submit cssClass="reset"
										action="TaxSlab_reset" theme="simple" value="    Reset  " />
									--> <input type="button" class="token" value="  Report  "
						onclick=" return reportFun();" theme="simple" /> <s:submit
						cssClass="reset" action="DivisionTaxCalc_reset" value=" Reset"
						theme="simple" /></td>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>

				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>

				<tr>
					<td colspan="4">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td colspan="4" width="100%" class="formhead"><b>Financial
							Year:</b></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="fromYear"
								name="fromYear" ondblclick="callShowDiv(this);"><%=label.get("fromYear")%></label><font
								color="red">*</font> :</td>
							<td width="25%" colspan="2"><s:textfield name="fromYear"
								onkeypress="return numbersOnly();setToYear();"
								onblur="setToYear();" theme="simple" maxlength="4" size="30" />
							</td>

							<td width="25%"><label class="set" id="toYear" name="toYear"
								ondblclick="callShowDiv(this);"><%=label.get("toYear")%></label><font
								color="red">*</font> :</td>
							<td width="25%"><s:textfield name="toYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="30" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="4" width="100%" class="formhead"><b>Select
							Filters:</b></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								
								<tr>
									<td width="25%"><label class="set" id="division"
										name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									<font color="red">*</font> :</td>
									<td width="73%" colspan="1"><s:hidden name="divCode" />
									 
										<s:textarea name="divName" cols="120" rows="1" readonly="true" />
									</td>
									<td width="2%">
									<img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callDropdown('paraFrm_divName',350,250,'DivisionTaxCalc_f9div.action',event,'false','no','right')">
	
										
									</td>
								</tr>
								<tr>
									<td width="25%"><label class="set" id="department"
										name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td width="73%"><s:hidden name="deptCode" /> 
									
										<s:textarea name="deptName" cols="120" rows="1" readonly="true" />
									</td>
									<td width="2%">	
									
									<img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callDropdown('paraFrm_deptName',350,250,'DivisionTaxCalc_f9dept.action',event,'false','no','right')">
	
										 
									</td>
								</tr>
								<tr>
									<td width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td width="73%" colspan="1"><s:hidden name="branchCode" />
									 
									<s:textarea name="branchName" cols="120" rows="1" readonly="true" />
									</td>
									<td width="2%">
									<img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callDropdown('paraFrm_branchName',350,250,'DivisionTaxCalc_f9Branch.action',event,'false','no','right')">
						
									
									
									</td>
									</tr>
										
									<!--<td><label name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
									<td><s:hidden name="employeeId" />
									<s:hidden name="employeeToken" />
									<s:textfield name="employeeName" size="30" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callsF9(500,325,'DivisionTaxCalc_f9Employee.action');">
									</td>
								-->
								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
									:</td>
									<td width="73%"><s:hidden name="payBillNo" />
									<s:textarea name="payBillName" cols="120" rows="1" readonly="true" />
									
									</td>
									<td width="2%">	
									<img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callDropdown('paraFrm_payBillName',350,250,'DivisionTaxCalc_f9payBill.action',event,'false','no','right')">
						
										
									</td>

								</tr>
								<!--<tr>
							<td><label  class = "set"  id="fromYear" name="fromYear" ondblclick="callShowDiv(this);"><%=label.get("fromYear")%></label><font color="red">*</font> :</td>
							<td colspan="2">
							<s:textfield name="fromYear" onkeypress="return numbersOnly();setToYear();" onblur="setToYear();"   theme="simple" maxlength="4" size="25"  />
							</td>
							
							<td><label  class = "set"  id="toYear" name="toYear" ondblclick="callShowDiv(this);"><%=label.get("toYear")%></label><font color="red">*</font> :</td>
							<td>
							<s:textfield name="toYear" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25"  />
							</td>
						</tr>
						<tr>
							<td colspan="5" width="100%" align="center"><input type="button" value="Calculate" class="token" onclick="callCalculateTax();"/>
							<input type="button" value="Reset" class="reset" onclick="callReset();"/>
							</td>
						</tr>	
						-->
								<tr>
									<td width="25%"><label class="set" id="employee.type"
										name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%>
									</label> :</td>
									<td width="73%"><s:hidden name="empTypeCode" />
									
									<s:textarea name="empTypeName" cols="120" rows="1" readonly="true" />
									</td>
									<td width="2%">
									<img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callDropdown('paraFrm_empTypeName',350,250,'DivisionTaxCalc_f9type.action',event,'false','no','right')">
						
										</td>

								</tr>
								<tr>
									<td colspan="4" align="center"><strong>OR</strong></td>
								</tr>
								<tr>
									<td colspan="4" align="center"></td>
								</tr>
								<tr>
									<td width="15%"><label name="employee" id="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td width="85%" colspan="3"><s:hidden name="employeeId" />
									<s:textfield name="employeeToken" size="30" readonly="true" />
									<s:textfield name="employeeName" size="70" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callsF9(500,325,'DivisionTaxCalc_f9Employee.action');">
									</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<!-- 
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td colspan="4" width="100%" class="formhead"><b>
							Calculate Tax on:</b></td>
						</tr>
						<tr>
							<td width="25%"><label name="declared.investments"
								id="declared.investments" ondblclick="callShowDiv(this);"></label>
							:</td>
							<td width="25%"><s:checkbox theme="simple"
								name="declaredInvestments" onclick="" /></td>

							<td width="25%"><label name="verified.investments"
								id="verified.investments" ondblclick="callShowDiv(this);"></label>
							:</td>
							<td width="25%"><s:checkbox theme="simple"
								name="verifiedInvestments" onclick="" /></td>
						</tr>
					</table>
					</td>
				</tr> -->
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td><!--<s:submit cssClass="token" action="TaxSlab_save"
											theme="simple" value="  Save   " onclick="return formValidate()" />
								
								
											--> <input type="button" class="token"
						onclick="callCalculateTax();" theme="simple" value="Calculate Tax" />
					<!--<s:submit cssClass="reset"
										action="TaxSlab_reset" theme="simple" value="    Reset  " />
									--> <input type="button" class="token" value="  Report  "
						onclick=" return reportFun();" theme="simple" /> <s:submit
						cssClass="reset" action="DivisionTaxCalc_reset" value=" Reset"
						theme="simple" /></td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script>
	setFinancialYear('paraFrm_fromYear','paraFrm_toYear');
	function callCalculateTax(){
 		var name=['paraFrm_divName','paraFrm_fromYear'];
 		var labName=['division','fromYear'];
 		var flag=['select','enter'];
 		if(document.getElementById('paraFrm_employeeId').value==""){
 			if(!validateBlank(name, labName, flag)){
 			return false;
 		}
 		}else if(document.getElementById('paraFrm_fromYear').value==""){
 			alert('Please enter '+document.getElementById('fromYear').innerHTML);
 			document.getElementById('paraFrm_fromYear').focus();
 			return false;
 		}
 		document.getElementById('paraFrm').action="DivisionTaxCalc_calculateTaxForDivision.action";
 		document.getElementById('paraFrm').submit();
 }
	
	function setToYear(){
    	var from = document.getElementById('paraFrm_fromYear').value;
    	if(from=="") {
    		document.getElementById('paraFrm_toYear').value="";
    	} else {
   	 		var x=eval(from) +1;
	  		document.getElementById('paraFrm_toYear').value=x;
	  	}
	}
   
   function callReset(){
   try{
   		document.getElementById('paraFrm_deptCode').value="";
   		document.getElementById('paraFrm_deptName').value="";
   		document.getElementById('paraFrm_divCode').value="";
   		document.getElementById('paraFrm_divName').value="";
   		document.getElementById('paraFrm_branchCode').value="";
   		document.getElementById('paraFrm_branchName').value="";
   		document.getElementById('paraFrm_employeeId').value="";
   		document.getElementById('paraFrm_employeeName').value="";
   		document.getElementById('paraFrm_employeeToken').value="";
   		
   		document.getElementById('paraFrm_payBillNo').value="";
   		document.getElementById('paraFrm_payBillName').value="";
   		document.getElementById('paraFrm_empTypeCode').value="";
   		document.getElementById('paraFrm_empTypeName').value="";
   		
   		document.getElementById('paraFrm_declaredInvestments').value="true";
   		}
   		catch(e)
   		{
   		alert(e);
   		}
   		setFinancialYear('paraFrm_fromYear','paraFrm_toYear');
   }
  
  function reportFun() {
  {
		 var from = document.getElementById('paraFrm_fromYear').value;
	    
	    
	     var finYrFrm=document.getElementById('fromYear').innerHTML.toLowerCase(); 
	    
	  	if(from==""){
	  		alert("Please enter "+finYrFrm);
	  		document.getElementById('paraFrm_fromYear').focus();
	  		return false;
	  	}
	  	 if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	document.getElementById('paraFrm_fromYear').focus();
	    	return false;
	    }
	    
	    
	    var divisionNo = document.getElementById('paraFrm_divCode').value;
	    if(divisionNo==""){
	  		alert("Please select Division ");
	  		document.getElementById('paraFrm_divName').focus();
	  		return false;
	  	}
	    
	  	
		document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="DivisionTaxCalc_report.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";		
	}
		
	}
	
</script>