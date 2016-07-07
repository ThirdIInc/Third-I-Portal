<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="MealVoucherConf" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="mealvoucherconfID" />

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Meal
					Voucher Configuration </strong></td>

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
					<td><input type="button" value=" Save" class="add"
						onclick="return saveFun();"></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>
					Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<!--<tr>
					<td class="text_head"  width="35%" ><label class="set" id="financial.year"
						name="financial.year" ondblclick="callShowDiv(this);"><%=label.get("financial.year")%></label>
					<font
						color="red">*</font>:</td>
					<td width="65%" colspan="1"><label class="set" id="meal.YrFrm"
						name="meal.YrFrm" ondblclick="callShowDiv(this);"><%=label.get("meal.YrFrm")%></label><font
						color="red"></font>:
						<s:textfield size="10" name="fromYear"
						maxlength="4" onkeypress="return numbersOnly();" onblur="add()" />
				<label class="set" id="meal.YrTo"
						name="meal.YrTo" ondblclick="callShowDiv(this);"><%=label.get("meal.YrTo")%></label><font
						color="red"></font>: <s:textfield size="10" name="toYear"
						maxlength="4" readonly="true" /></td>
						
				</tr>

				--><tr>
					<td width="30%"><label name="cah.component" id="cah.component"
						ondblclick="callShowDiv(this);"><%=label.get("cah.component")%></label>:<font color="Red">*</font></td>
					<td width="25%"><s:hidden name="creditCode" /> <s:textfield
						name="creditHead" theme="simple" size="25" readonly="true" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'MealVoucherConf_f9credit.action');">

					</td>
					<td width="25%">&nbsp;</td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="coupen.component" id="coupen.component"
						ondblclick="callShowDiv(this);"><%=label.get("coupen.component")%></label>:<font
						color="Red">*</font></td>
					<td width="25%"><s:hidden name="coupenCode" /> <s:textfield
						name="coupenHead" theme="simple" size="25" readonly="true" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'MealVoucherConf_f9creditcoupen.action');">

					</td>
				</tr>
				<tr>
					<td width="25%"><label name="debit.component" id="debit.component"
						ondblclick="callShowDiv(this);"><%=label.get("debit.component")%></label>:<font
						color="Red">*</font></td>
					<td width="25%"><s:hidden name="debitCode" /> <s:textfield
						name="debitHead" theme="simple" size="25" readonly="true" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'MealVoucherConf_f9debit.action');">

					</td>
				</tr>


				<tr>
					<td width="25%"><label name="frequency.change" id="frequency.change"
						ondblclick="callShowDiv(this);"><%=label.get("frequency.change")%></label>:<font color="Red">*</font></td>
					<td width="25%"><s:select theme="simple"
						name="freqencyOfChange" cssStyle="" onchange=""
						list="#{'-1':'  ---Select---  ','M':'Monthly','Q':'Quarterly','H':'Half Yearly','A':'Annually'}" />
					</td>
				</tr>

				<tr>
					<td width="25%"><label class="set"
						id="meal.voucher.amount.limit " name="meal.voucher.amount.limit"
						ondblclick="callShowDiv(this);"><%=label.get("meal.voucher.amount.limit")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:textfield size="25"
						name="mealVoucherAmtLimit" maxlength="4"
						onkeypress="return numbersOnly();" /></td>

				</tr>

				<tr>
					<td width="25%"><label class="set"
						id="meal.voucher.amount.declare "
						name="meal.voucher.amount.declare" ondblclick="callShowDiv(this);"><%=label.get("meal.voucher.amount.declare")%></label>:<font	color="red">*</font></td>
					<td width="25%"><s:textfield size="25"
						name="mealVoucherAmtDeclare" maxlength="200" onkeypress="return numbersOnlyWithComma();"/></td>

				</tr>
				<tr>
					<td class="text_head"><label class="set" id="declaration.period" name="declaration.period"
						ondblclick="callShowDiv(this);"><%=label.get("declaration.period")%></label>:<font color="red">*</font></td>
					<td><label class="set" id="from" name="from"
						ondblclick="callShowDiv(this);"><%=label.get("from")%></label><font
						color="red"></font> : 
						<s:textfield size="5" name="fromPeriod"
						maxlength="2" onkeypress="return numbersOnly();" onblur="datePeriodFrom();"/>
					 	
					<label class="set" id="to" name="to"
						ondblclick="callShowDiv(this);"><%=label.get("to")%></label><font
						color="red"></font> : <s:textfield size="5" name="toPeriod"
						maxlength="2" onkeypress="return numbersOnly();" onblur="datePeriodTo();"/></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" value=" Save" class="add"
						onclick="return saveFun();"></td>

				</tr>
			</table>
			</td>
		</tr>
	</table> 
</s:form>
<script>


	function saveFun()
	{
				try{
				
	///				var from = document.getElementById('paraFrm_fromYear').value;
	 
	////   var YrFrm=document.getElementById('meal.YrFrm').innerHTML.toLowerCase();
	  
	    	   
	///    if(from=="")
	///    {
	 ///   	alert("Please Enter "+YrFrm);
	 ///   	return false;
	///    }
	    
	////    if(eval(from)<2000){
	////    	alert(YrFrm+" cannot be less than 2000");
	 ///   	return false;
	 ///   }
		  					
		  				var checkValidation = document.getElementById('paraFrm_creditCode').value;
		  				 
		  				if(checkValidation=="")
		  					{
		  						alert("Please Select Meal Voucher cash component");
		  						return false;
		  					}
		  					
		  					var checkcupenval = document.getElementById('paraFrm_coupenCode').value;
		  					
		  				if(checkcupenval=="")
		  					{
		  						alert("Please Select Meal Voucher coupen component");
		  						return false;
		  					}
		  					
		  					var checkdebitval = document.getElementById('paraFrm_debitCode').value;
		  					
		  				if(checkdebitval=="")
		  					{
		  						alert("Please Select Meal Voucher debit component");
		  						return false;
		  					}
		  					
		  					var freqencyOfChange = document.getElementById('paraFrm_freqencyOfChange').value;
		  				 
		  				if(freqencyOfChange=="-1")
		  					{
		  						alert("Please Select Frequency Of Change");
		  						document.getElementById('paraFrm_freqencyOfChange').focus();
		  						return false;
		  					}
		  					
		  					var mealVoucherAmtLimitval = document.getElementById('paraFrm_mealVoucherAmtLimit').value;
		  					
		  				if(mealVoucherAmtLimitval=="")
		  					{
		  						alert("Please Enter Meal Voucher Amount Limit.");
		  						document.getElementById('paraFrm_mealVoucherAmtLimit').focus();
		  						return false;
		  					}
		  				
		  					
		  					var mealVoucherAmtDeclareVal = document.getElementById('paraFrm_mealVoucherAmtDeclare').value;
		  					
		  				if(mealVoucherAmtDeclareVal=="")
		  					{
		  						alert("Please Enter Meal Voucher Decalre Amount .");
		  						document.getElementById('paraFrm_mealVoucherAmtDeclare').focus();
		  						return false;
		  					}
		  					
		  					
		  			
		  				fieldList = mealVoucherAmtDeclareVal.split(",");
		  				for(var ii=0;ii<fieldList.length;ii++){		  				
		  				if(parseFloat(fieldList[ii])>parseFloat(mealVoucherAmtLimitval)){
		  				alert("Meal Voucher declare Amount (Rs) must be less than Meal Voucher Amount Limit (Rs)");
		  				return false;
		  				}
		  				
		  				}
		  				
		  				
		  				
		  				var fromPeriod = document.getElementById('paraFrm_fromPeriod').value;
   						var toPeriod = document.getElementById('paraFrm_toPeriod').value;	
		  					
		  					
		  					if(fromPeriod == "") {
				alert("Please enter "+document.getElementById('from').innerHTML.toLowerCase()+" Date Period");
				document.getElementById('paraFrm_fromPeriod').focus();
				return false;
		}
		
		if(parseInt(fromPeriod) > parseInt(toPeriod)){
			 alert("To Date Period Must Greater Than From Date Period");
			 document.getElementById('paraFrm_toPeriod').focus();
    			document.getElementById('paraFrm_toPeriod').value="";
			return false;
			}
			
		if(toPeriod == "") {
				alert("Please enter "+document.getElementById('to').innerHTML.toLowerCase()+" Date Period");
				document.getElementById('paraFrm_toPeriod').focus();
				return false;
		} 
		
		
		  					
		  					document.getElementById('paraFrm').action='MealVoucherConf_save.action';
		  					document.getElementById('paraFrm').submit();
		  				}
		  				catch(e){alert(e);}
		  				return true; 		
	
	}
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'MealVoucherConf_reset.action';
		document.getElementById('paraFrm').submit();
  	}
  	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'MealVoucherConf_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}	
	function editFun() {
		return true;
	} 
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'MealVoucherConf_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	   function add()
   {
    var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
   
   function datePeriodFrom(){
   try{
   
   var fromPeriod = parseInt(document.getElementById('paraFrm_fromPeriod').value);
   var toPeriod = parseInt(document.getElementById('paraFrm_toPeriod').value);
 
   if (document.getElementById('paraFrm_fromPeriod').value==0  ) {
    			 alert("From Date Period Must be Between 1 to 31\n");
    			document.getElementById('paraFrm_fromPeriod').focus();
    			document.getElementById('paraFrm_fromPeriod').value="";
		 		return false;
			}
			
			if ((document.getElementById('paraFrm_fromPeriod').value) > 31) {
    			 alert("From Date Period Must be Between 1 to 31\n");
    			document.getElementById('paraFrm_fromPeriod').focus();
    			document.getElementById('paraFrm_fromPeriod').value="";
    			
		 		return false;
			}
			
			
			if(fromPeriod > parseInt(document.getElementById('paraFrm_toPeriod').value)){
			 alert("To Date Period Must Greater Than From Date Period");
			 document.getElementById('paraFrm_toPeriod').focus();
    			document.getElementById('paraFrm_toPeriod').value="";
			return false;
			}
			
			
			} catch(e) {
				alert(e);
			}
	}	
	function datePeriodTo(){
   try{
  var fromPeriod = parseInt(document.getElementById('paraFrm_fromPeriod').value);
 
   if (parseInt(document.getElementById('paraFrm_toPeriod').value)==0  ) {
    			 alert("To Date Period Must be Between 1 to 31\n");
    			document.getElementById('paraFrm_toPeriod').focus();
    			document.getElementById('paraFrm_toPeriod').value="";
		 		return false;
			}
			
			if (parseInt(document.getElementById('paraFrm_toPeriod').value) > 31) {
    			 alert(" To Date Period Must be Between 1 to 31\n");
    			 document.getElementById('paraFrm_toPeriod').focus();
    			document.getElementById('paraFrm_toPeriod').value="";
    			
		 		return false;
			}
			
			if(fromPeriod > parseInt(document.getElementById('paraFrm_toPeriod').value)){
			 alert("To Date Period Must Greater Than From Date Period");
			 document.getElementById('paraFrm_toPeriod').focus();
    			document.getElementById('paraFrm_toPeriod').value="";
			return false;
			}
			
			
			} catch(e) {
				alert(e);
			}
	}		
   
   function numbersOnlyWithComma(e){ 
  
   document.onkeypress = numbersOnlyWithComma;
	var key;  
	if (window.event) {
	    key = event.keyCode
	    } else {
	    	key = e.which
	    }
	   // alert(key);
	clear();
	if ( key == 44 || key > 47 && key < 58 || key == 8 || key == 0 ){
		return true;
	}else{
		return false;
	}
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
   
}

function clear(){
	try{
		document.onkeypress = "";
	}catch(e){
		//alert("333 >"+e);
	}
}
</script>