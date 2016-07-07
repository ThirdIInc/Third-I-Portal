<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>



<s:form action="MealVoucherBean" validate="true" id="paraFrm"
	theme="simple">
	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Meal
					Voucher Declaration </strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="save" value="  Save"
						onclick="return callValidate(this);" theme="simple" /> <s:submit
						name="reset" value="  Reset" cssClass="reset"
						action="MealVoucherBean_reset" /></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>
					Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>




		<!-- EMPLOYEE DETAILS TABLE  STARTS -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">

				<tr>
					<td width="25%"><label name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><span
						class="formtext">:</span><span class="style2"><font
						color="red">*</font></span></td>
					<td width="20%"><s:hidden theme="simple" name="empCode"
						value="%{empCode}" /> <s:textfield theme="simple" readonly="true"
						size="20" name="empToken" cssStyle="background-color: #F2F2F2;" /></td>
					<td colspan="2"><s:textfield label="%{getText('empName')}"
						theme="simple" size="80" readonly="true" name="empName"
						cssStyle="background-color: #F2F2F2;" /></td>
					
				</tr>

				</tr>
				<tr>
					<td width="20%"><label name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:<span
						class="style2"><font color="red">*</font></span></span></td>
					<td><s:textfield theme="simple" size="20" readonly="true"
						name="branchName" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="20%"><label name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="30%"><s:textfield theme="simple" size="20"
						readonly="true" name="designationName"
						cssStyle="background-color: #F2F2F2;" /></td>
					

				</tr>
				<tr>
					<td><label name="appdate" id="appdate"
						ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label>:</td>
					<td><s:textfield theme="simple" readonly="true"
						name="applicationDate" size="20"
						cssStyle="background-color: #F2F2F2;" /></td>
					
<s:hidden name="fromYear" /> <s:hidden name="toYear" />
				</tr>

				<!--<tr>
					<td nowrap="nowrap" colspan="7" width="100%"><strong>Financial Year
					  <s:property value="fromYear" /> - <s:property value="toYear" /></strong>

					<s:hidden name="fromYear" /> <s:hidden name="toYear" />
				</tr>

			--></table>
			</td>
		</tr>

		<!-- EMPLOYEE DETAILS TABLE  ENDS -->


		<!-- table for meal voucher starts -->

		<tr id='ctrlHide'>  
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead"><label class="set"
						id="mealVoucherDeclaration" name="mealVoucherDeclaration"
						ondblclick="callShowDiv(this);"><%=label.get("mealVoucherDeclaration")%></label>
					</strong></td>
				</tr>

				<tr>
					<td colspan="1" class="text_head"><label class="set"
						id="mealVoucherDeclarationAmt" name="mealVoucherDeclarationAmt"
						ondblclick="callShowDiv(this);"><%=label.get("mealVoucherDeclarationAmt")%></label>:
					</td>
					<td colspan="1" class="text_head"><s:property
						value="mealVoucherDeclaredAmt" /> <s:hidden
						name="mealVoucherDeclaredAmt" value="%{mealVoucherDeclaredAmt}" /> 
					</td>
				</tr>

				<tr>
					<!--<td height="22" width="25%" class="formtext"><label
						class="set" id="meal.coupens" name="meal.coupens"
						ondblclick="callShowDiv(this);"><%=label.get("meal.coupens")%></label>
					:<font color="red">*</font></td>
					<td width="25%" height="22"><s:if test="saveFlag">
						<s:select theme="simple" disabled="true"
							name="mealVoucherCoupenAmt" cssStyle="width:130"
							onchange="return calculate();"
							list="#{'-1':'  ---Select---  ','0':'0','500':'500','1000':'1000','1500':'1500','2200':'2200'}" />
					</s:if> <s:else>
						<s:select theme="simple" name="mealVoucherCoupenAmt"
							cssStyle="width:130" onchange="return calculate();"
							list="#{'-1':'  ---Select---  ','0':'0','500':'500','1000':'1000','1500':'1500','2200':'2200'}" />
					</s:else></td>

					-->   
					<td width="26%" colspan="1"><label id="meal.coupens"
						name="meal.coupens" ondblclick="callShowDiv(this);"><%=label.get("meal.coupens")%></label>:<font color="red">*</font></td>
					<td width="22%" colspan="1"><s:select headerKey="-1"
						headerValue="-----Select------" onchange="return calculate();"
						name="mealVoucherCoupenAmt" list="amtValue" /></td>
					
					<td  width="20%" height="22" class="formtext"><label
						class="set" id="meal.amount" name="meal.amount"
						ondblclick="callShowDiv(this);"><%=label.get("meal.amount")%></label>:</td>
					<td height="22"><s:textfield size="25"
						cssStyle="background-color: #F2F2F2; text-align: right;"
						name="mealVoucherSplAllowanceAmount" theme="simple"
						readonly="true" /></td>
				</tr>
				<!--<tr>
					<td width="25%" colspan="1"><label id="meal.coupens"
						name="meal.coupens" ondblclick="callShowDiv(this);"><%=label.get("meal.coupens")%></label>
					:</td>
					<td width="75%" colspan="3"><s:select headerKey="-1"
						headerValue="-----Select------" onchange="return calculate();"
						name="amtVal" list="amtValue" /></td>
				</tr>

				<td width="25%"> </td> -->
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%"><b> Note: </b></td>
		</tr>

		<tr>
			<td colspan="4" width="100%">1.Meal Voucher declaration is
			accepted only once in <b><s:property value="changePeriodicity"/>   </b>     and within allowed date wise declaration period (<b><s:property value="fromPeriod"/></b> to <b><s:property value="toPeriod"/></b>).</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">2.Meal coupons can be declared for
			 <b><s:property value="amountValue"></s:property></b> only.</label></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">3.The remaining amount (meal
			allowance) will be a part of taxable income.</td>
		</tr>


		<!-- table for meal voucher ends -->

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="save" value="  Save"
						onclick="return callValidate(this);" theme="simple" /> <s:submit
						name="reset" value="  Reset" cssClass="reset"
						action="MealVoucherBean_reset" /></td>
					<td width="30%" id="ctrlHide" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>

	<s:hidden name="checkValidation" />
	<s:hidden name="changePeriodicity" id="changePeriodicity" /> 
	<s:hidden name="previousDate" />

</s:form>



<script>


	function calculate()
	{
	 
		try{
		var mealVoucherDeclaredAmt =document.getElementById('paraFrm_mealVoucherDeclaredAmt').value ;
		var mealVoucherCoupenAmt =document.getElementById('paraFrm_mealVoucherCoupenAmt').value ;
		if(mealVoucherCoupenAmt=="-1")
		{
		mealVoucherCoupenAmt =0;
		}
		document.getElementById('paraFrm_mealVoucherSplAllowanceAmount').value = eval(mealVoucherDeclaredAmt)-eval(mealVoucherCoupenAmt);
		}
		catch(e){ alert(e)}
		
		return true;
	}



 function callValidate(obj)
  {
   
  				try{
  				
  				var checkValidation = document.getElementById('paraFrm_checkValidation').value;
  				
  				var toYear = document.getElementById('paraFrm_toYear').value;
  				
  				var fromYear = document.getElementById('paraFrm_fromYear').value;
  			 	
  			 	var periodicity =document.getElementById('changePeriodicity').value;
  			 
  			if(checkValidation=='false')
  				{
  					alert("You have alreay declared meal voucher.\n for further changes please contach Human Resource department. ");
  						return false ;
  				}
  		 
  				
  				var mealVoucherCoupenAmt =document.getElementById('paraFrm_mealVoucherCoupenAmt').value ;
  				
  				if(mealVoucherCoupenAmt=="-1")
  				{
  						alert("Please select meal coupons");
  						return false ;
  				} 
  	     		 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="MealVoucherBean_save.action";
		  			document.getElementById("paraFrm").submit();
		  		 
				 
  				}
  		 catch(e){alert(e);}	
  		return true; 		
  }
  



</script>
