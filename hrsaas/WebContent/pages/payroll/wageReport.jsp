<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="WageReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
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
			<td width="93%" class="txt"><strong class="text_head">Wage Report</strong></td>
			<td width="3%" valign="top" class="txt">
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td width="22%">
					<div align="right"><span class="style2"><font color="red">*</font></span> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>

		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td>Select Employee <font color="red">*</font>:</td>
							<td colspan="3"><s:hidden
								name="empID" /> <s:textfield name="empTokenNo" size="10"
								readonly="true" /> <s:textfield name="empName"
								size="40" readonly="true" /><s:if
								test="%{generalFlag}">
							</s:if> <s:else>
								<img src="../pages/images/search2.gif" class="iconImage"
									height="17" align="absmiddle" width="17"
									onclick="javascript:callsF9(500,325,'WageReport_f9empaction.action');">
							</s:else> 
								</td>
						</tr>


						<tr>
							<td>Designation :</td>
							<td><s:textfield size="25" name="designation"
								readonly="true" /></td>

							<td>Branch :</td>
							<td><s:textfield size="25" name="branch"
								readonly="true" /></td>
						</tr>
						<tr>
							<td>Financial Year From <font color="red">*</font>:</td>
							<td><s:textfield size="25" name="fromYear"
								maxlength="4" onkeyup="return add()" onkeypress="return numbersOnly();"
								onblur="add()" /></td>

							<td>Financial Year To <font color="red">*</font>:</td>
							<td><s:textfield size="25" name="toYear"
								maxlength="4" 
								readonly="true" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>


						<tr align="center">

							<td colspan="4"><s:if test="viewFlag">
								<s:submit cssClass="token" 
									theme="simple" value=" Report " onclick="return callReport()" />
									</s:if><s:else></s:else>
							
						<tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
	
	</table>
</s:form>

<script>
    function callReport(){
	   	
	  var empId =document.getElementById('paraFrm_empName').value;
	  var from = document.getElementById('paraFrm_fromYear').value;
	 
	    
	    if(empId=="")
	    {
	    	alert("Please select employee.");
	    	return false;
	    }
	    
	     if(from=="")
	    {
	    	alert("Please enter financial year from.");
	    	return false;
	    }
	    
	    document.getElementById('paraFrm').target="_blank";
	    document.getElementById('paraFrm').action="WageReport_report.action";
	 	
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
    
   
       
	       
	      
   
   
   </script>