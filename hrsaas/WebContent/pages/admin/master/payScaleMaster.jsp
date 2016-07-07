
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PayScaleMaster" validate="true" id="paraFrm"
	theme="simple">
	
	
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="formhead">Pay Scale </strong></td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="%{insertFlag}">
						<s:submit action="PayScaleMaster_save" cssClass="add"
							onclick="return callSave('addnew');" value="  Add New" />
					</s:if> <s:if test="%{updateFlag}">
						<s:submit cssClass="edit" action="PayScaleMaster_save"
							onclick="return callSave('update');" value="   Update" />
					</s:if> <s:if test="%{viewFlag}">
						<input type="button" class="search" value="    Search"
							onclick="javascript:callsF9(500,325,'PayScaleMaster_f9action.action');" />
					</s:if> <s:submit cssClass="reset" action="PayScaleMaster_reset"
						theme="simple" value="    Reset"  /> <s:if
						test="%{deleteFlag}">
						<s:submit cssClass="delete" action="PayScaleMaster_delete"
							onclick="return del()" theme="simple" value="    Delete"
							 />
					</s:if> <s:if test="%{viewFlag}">
						<input type="button" class="token" value=" Report"
							onclick="callReport('PayScaleMaster_report.action')" />
					</s:if></td>
					<td width="22%">
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
								class="forminnerhead">Pay Scale : </strong> <s:hidden
								name="payScaleMaster.payID" /></td>
						</tr>


						<tr>

							<td width="20%">Pay Scale Name:</td>
							<td width="60%"><s:textfield size="30"
								name="payScaleMaster.payName" maxlength="50" readonly="true" /></td>

						</tr>

						<tr>

							<td width="20%">Pay Scale Start Amount<font color="red">*</font>
							:</td>
							<td width="60%"><s:textfield size="30"
								name="payScaleMaster.payStartAmt"
								onkeypress="return numbersWithSpaceandDot();" maxlength="10" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Increment Amount1 <font
								color="red">*</font>:</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payIncrAmt1"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Final Amount1 <font color="red">*</font>:</td>
							<td width="60%"><s:textfield size="30"
								name="PayScaleMaster.payFinalAmt1" maxlength="10"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Increment Amount2 :</td>
							<td width="60%"><s:textfield size="30"
								name="PayScaleMaster.payIncrAmt2" maxlength="10"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Final Amount2 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payFinalAmt2"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Increment Amount3 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payIncrAmt3"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Final Amount3 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payFinalAmt3"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="27%">Pay Scale Commission :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payCommission" /></td>

						</tr>
						<tr>
							<td width="20%">Pay Scale Increment Amount4 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payIncrAmt4"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Final Amount4 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payFinalAmt4"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Increment Amount5 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payIncrAmt5"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>
						<tr>

							<td width="20%">Pay Scale Final Amount5 :</td>
							<td width="60%"><s:textfield size="30" maxlength="10"
								name="PayScaleMaster.payFinalAmt5"
								onkeypress="return numbersWithSpaceandDot();" /></td>

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</td></tr></table>
 
	



</s:form>

<script>
  	function del(){
	    if(document.getElementById("paraFrm_payScaleMaster_payID").value==""){
    		 alert('Please Select Pay Scale');
		     return false;
    	}else{
    		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			 document.getElementById('paraFrm').submit();
  			 return true;
  			}
   		} 
   return false;
}
  	function numbersonly(myfield)
	{
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
  	function callReport(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	document.getElementById('paraFrm').target="main";
  	}
	function callSave(type)
	{
	
	
		if(type == 'update'){
		
		if(document.getElementById('paraFrm_payScaleMaster_payID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
	
		if(!document.getElementById('paraFrm_payScaleMaster_payID').value==""){
			alert("Please Click Update ");
  			return false;
			}
		
	}
	
	var fieldName = ["paraFrm_payScaleMaster_payStartAmt","paraFrm_PayScaleMaster_payIncrAmt1","paraFrm_PayScaleMaster_payFinalAmt1"];
	var lableName = ["Pay Scale Start Amount","Pay Scale Increment Amount1","Pay Scale Final Amount1"];
     
     if(!checkMandatory(fieldName,lableName))
        return false;
        
        return true;
	}
	function callAdd(){
	
	
	var disName = document.getElementById('paraFrm_PayScaleMaster_payName').value;
			
		if(disName=="")
		{
			alert ("Please Enter Pay Scale Name !");
			return false;
		}
			
				 if(!(disName==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < disName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(disName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==disName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;	
  					}
				}
		
		}
</script>
















